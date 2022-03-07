package com.serasa.cadastro.controller;

import com.serasa.cadastro.entity.Atuacao;
import com.serasa.cadastro.entity.Vendedor;
import com.serasa.cadastro.services.AtuacaoService;
import com.serasa.cadastro.services.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/atuacao")
public class AtuacaoController {

    @Autowired
    private AtuacaoService atuacaoService;

    @PostMapping
    public ResponseEntity<Atuacao> criarAtuacao(@Valid @RequestBody Atuacao atuacao) {
        atuacao = atuacaoService.criarAtuacao(atuacao);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(atuacao.getId())
                .toUri();
        return ResponseEntity.created(uri).body(atuacao);
    }

    @GetMapping(value = "/regiao/{regiao}")
    public ResponseEntity<Atuacao> buscarAtuacaoPorRegiao(@PathVariable String regiao) {
        Atuacao atuacao = atuacaoService.buscarAtuacaoPorRegiao(regiao);
        return ResponseEntity.ok().body(atuacao);
    }
}
