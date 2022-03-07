package com.serasa.cadastro.services;

import com.serasa.cadastro.entity.Atuacao;
import com.serasa.cadastro.entity.Estado;
import com.serasa.cadastro.repository.AtuacaoRepository;
import com.serasa.cadastro.services.exceptions.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class AtuacaoService {

    @Autowired
    private AtuacaoRepository atuacaoRepository;

    @Transactional
    public Atuacao criarAtuacao(Atuacao atuacao) {
        log.info("Criando Atuacao...");
        Atuacao regiao = atuacaoRepository.findByRegiaoIgnoreCase(atuacao.getRegiao());
        if (Objects.nonNull(regiao)) {
            throw new BusinessException("Região já cadastrada");
        }
        atuacao = atuacaoRepository.save(atuacao);
        Optional<Atuacao> objSaved = atuacaoRepository.findById(atuacao.getId());
        List<Estado> estadoList = objSaved.get().getEstados();
        estadoList.stream().forEach(estado -> estado.setAtuacao(objSaved.get()));

        log.info("Entidade atuacao criada...");
        return atuacao;
    }

    @Transactional(readOnly = true)
    public Atuacao buscarAtuacaoPorRegiao(String regiao) {
        log.info("Buscando regiao...");
        return atuacaoRepository.findByRegiaoIgnoreCase(regiao);
    }
}
