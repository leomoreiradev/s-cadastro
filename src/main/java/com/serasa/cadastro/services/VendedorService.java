package com.serasa.cadastro.services;

import com.serasa.cadastro.dto.VendedorDTO;
import com.serasa.cadastro.dto.VendedorDTOPaged;
import com.serasa.cadastro.entity.Atuacao;
import com.serasa.cadastro.entity.Estado;
import com.serasa.cadastro.entity.Vendedor;
import com.serasa.cadastro.repository.VendedorRepository;
import com.serasa.cadastro.services.exceptions.BusinessException;
import com.serasa.cadastro.services.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class VendedorService {


    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private AtuacaoService atuacaoService;

    @Transactional
    public Vendedor criarVendedor(Vendedor vendedor) {
        log.info("Criando vendedor...");
        Optional<Vendedor> obj = vendedorRepository.findByTelefone(vendedor.getTelefone());
        if (obj.isPresent()) {
            throw new BusinessException("Vendedor já cadastrado");
        }

        log.info("Entidade vendedor criada...");
        return vendedorRepository.save(vendedor);
    }

    @Transactional(readOnly = true)
    public VendedorDTO buscarVendedorPorId(Long id) {
        log.info("Buscando vendedor...");
            Optional<Vendedor> vendedor = vendedorRepository.findById(id);
           vendedor.orElseThrow(() -> new ResourceNotFoundException("Vendedor não encontrado"));
            List<Estado> estados = buscarEstados(vendedor.get().getRegiao());
        log.info("Vendedor encontrado...");
            return VendedorDTO.builder()
                    .id(vendedor.get().getId())
                    .nome(vendedor.get().getNome())
                    .dataInclusao(vendedor.get().getDataInclusao())
                    .estados(estados)
                    .build();
    }

    @Transactional(readOnly = true)
    public Page<VendedorDTOPaged> buscarTodosVendedoresPaged(Pageable pageable) {
        log.info("Buscando vendedores...");
        Page<Vendedor> list = vendedorRepository.findAll(pageable);
        list.stream().forEach(vendedor -> vendedor.setEstados(atuacaoService.buscarAtuacaoPorRegiao(vendedor.getRegiao()).getEstados()));
        Page<VendedorDTOPaged> vendedorDTOSDtoPageds = list.map(vendedor -> new VendedorDTOPaged(vendedor));

        return vendedorDTOSDtoPageds;
    }


    List<Estado> buscarEstados(String regiao) {
        Atuacao regiaoAtuacao = atuacaoService.buscarAtuacaoPorRegiao(regiao);
        List<Estado> estados = regiaoAtuacao.getEstados();
        return estados;
    }
}
