package com.serasa.cadastro.services;

import com.serasa.cadastro.dto.VendedorDTO;
import com.serasa.cadastro.dto.VendedorDTOPaged;
import com.serasa.cadastro.entity.Estado;
import com.serasa.cadastro.entity.Vendedor;
import com.serasa.cadastro.objetoFactory.Factory;
import com.serasa.cadastro.repository.VendedorRepository;
import com.serasa.cadastro.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith({SpringExtension.class})
class VendedorServiceTest {

    @InjectMocks
    private VendedorService vendedorService;

    @Mock
    private VendedorRepository vendedorRepository;

    @Mock
    private EstadoService estadoService;

    private Vendedor vendedor;
    private Long idExistente;
    private Long idInexistente;
    private PageImpl<Vendedor> page;


    @BeforeEach
    void setUp() {
        vendedor = Factory.criavendedor();
        idExistente = 1L;
        idInexistente = 2L;
        page = new PageImpl<>(List.of(vendedor));

        Mockito.when(vendedorRepository.save(vendedor)).thenReturn(vendedor);
        Mockito.when(vendedorRepository.findById(idExistente)).thenReturn(Optional.of(vendedor));
        Mockito.when(vendedorRepository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(page);
        Mockito.doThrow(ResourceNotFoundException.class).when(vendedorRepository).findById(idInexistente);
        Mockito.when(estadoService.buscarTodos()).thenReturn(List.of(Factory.criaEstado()));
    }

    @Test
    @DisplayName("Deve criar vendedor com sucesso")
    void criarVendedorSucesso() {
        Vendedor vendedorAtual = vendedorRepository.save(vendedor);

        Long id = vendedor.getId();
        Assertions.assertNotNull(vendedorAtual);
        Assertions.assertEquals(vendedorAtual.getId(), id);
        Mockito.verify(vendedorRepository, Mockito.times(1)).save(vendedorAtual);

    }

    @Test
    @DisplayName("Deve buscar pelo ID com sucesso ")
    void buscarVendedorPorId() {
        VendedorDTO vendedorEsperado = new VendedorDTO(vendedor);
        Optional<Vendedor> vendedorAtual = vendedorRepository.findById(idExistente);

        Assertions.assertNotNull(vendedorAtual.isPresent());
        Assertions.assertEquals(vendedorAtual.get().getId(), vendedorEsperado.getId());
        Mockito.verify(vendedorRepository, Mockito.times(1)).findById(idExistente);
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException ")
    void criarVendedorResourceNotFound() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            vendedorRepository.findById(idInexistente);
        });
        Mockito.verify(vendedorRepository, Mockito.times(1)).findById(idInexistente);
    }


    @Test
    void buscarTodosVendedoresPaged() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Vendedor> result = vendedorRepository.findAll(pageable);
        result.stream().map(v -> new VendedorDTOPaged(v));
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.getTotalElements() > 0);

        Mockito.verify(vendedorRepository, Mockito.times(1)).findAll(pageable);
    }

    @Test
    void buscarEstados() {
        List<Estado> estados = estadoService.buscarTodos();
        Assertions.assertTrue(estados.size() > 0);

    }
}