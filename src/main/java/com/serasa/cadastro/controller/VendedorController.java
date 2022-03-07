package com.serasa.cadastro.controller;

import com.serasa.cadastro.dto.VendedorDTO;
import com.serasa.cadastro.dto.VendedorDTOPaged;
import com.serasa.cadastro.entity.Vendedor;
import com.serasa.cadastro.services.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/vendedor")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    @PostMapping
    public ResponseEntity<Vendedor> criarVendedor(@Valid @RequestBody Vendedor vendedor) {
        vendedor = vendedorService.criarVendedor(vendedor);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(vendedor.getId())
                .toUri();
        return ResponseEntity.created(uri).body(vendedor);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VendedorDTO> buscarVendedorPorId(@PathVariable Long id) {
        VendedorDTO vendedorDTO = vendedorService.buscarVendedorPorId(id);
        return  ResponseEntity.ok().body(vendedorDTO);
    }

    @GetMapping
    public ResponseEntity<Page<VendedorDTOPaged>> buscarTodosVendedores(Pageable pageable){
        Page<VendedorDTOPaged> list = vendedorService.buscarTodosVendedoresPaged(pageable);
        return ResponseEntity.ok().body(list);

    }
}
