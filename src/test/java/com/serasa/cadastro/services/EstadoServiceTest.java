package com.serasa.cadastro.services;

import com.serasa.cadastro.entity.Estado;
import com.serasa.cadastro.objetoFactory.Factory;
import com.serasa.cadastro.repository.EstadoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith({SpringExtension.class})
class EstadoServiceTest {

    @InjectMocks
    private EstadoService estadoService;

    @Mock
    private EstadoRepository estadoRepository;

    private Estado estado;

    @BeforeEach
    void setUp() {
        estado = Factory.criaEstado();
        Mockito.when(estadoRepository.findAll()).thenReturn(List.of(estado));
    }

    @Test
    void buscarTodos() {
        List<Estado> estados = estadoRepository.findAll();
        Assertions.assertTrue(estados.size() > 0);
    }
}