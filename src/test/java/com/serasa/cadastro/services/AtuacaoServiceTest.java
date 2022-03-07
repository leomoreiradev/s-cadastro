package com.serasa.cadastro.services;

import com.serasa.cadastro.entity.Atuacao;
import com.serasa.cadastro.entity.Estado;
import com.serasa.cadastro.objetoFactory.Factory;
import com.serasa.cadastro.repository.AtuacaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class})
class AtuacaoServiceTest {

    @InjectMocks
    private AtuacaoService atuacaoService;

    @Mock
    private AtuacaoRepository atuacaoRepository;

    private Atuacao atuacao;


    @BeforeEach
    void setUp() {
        atuacao = Factory.criaAtuacao();

        Mockito.when(atuacaoRepository.save(atuacao)).thenReturn(atuacao);
        Mockito.when(atuacaoRepository.findByRegiaoIgnoreCase(atuacao.getRegiao())).thenReturn(atuacao);
    }

    @Test
    @DisplayName("Deve criar atuacao com sucesso")
    void criarAtuacao() {
        Atuacao atuacaoEsperada = atuacao;
        Atuacao atuacaoAtual = atuacaoRepository.save(atuacao);
        Assertions.assertNotNull(atuacaoAtual);
        Assertions.assertEquals(atuacaoEsperada.getRegiao(), atuacaoAtual.getRegiao());
        Mockito.verify(atuacaoRepository, Mockito.times(1)).save(atuacao);
    }

    @Test
    @DisplayName("Deve buscar atuacao com sucesso")
    void buscarAtuacaoPorRegiao() {
        Atuacao atuacaoEsperada = atuacao;
        Atuacao atuacaoAtual = atuacaoRepository.findByRegiaoIgnoreCase(atuacao.getRegiao());
        Assertions.assertNotNull(atuacaoAtual);
        Mockito.verify(atuacaoRepository, Mockito.times(1)).findByRegiaoIgnoreCase(atuacao.getRegiao());
    }
}