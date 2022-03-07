package com.serasa.cadastro.objetoFactory;

import com.serasa.cadastro.dto.VendedorDTO;
import com.serasa.cadastro.dto.VendedorDTOPaged;
import com.serasa.cadastro.entity.Atuacao;
import com.serasa.cadastro.entity.Estado;
import com.serasa.cadastro.entity.Vendedor;

import java.time.LocalDateTime;
import java.util.List;

public class Factory {
    public static Vendedor criavendedor() {
        return Vendedor.builder()
                .id(1L)
                .nome("Fulano de Tal")
                .telefone("99 9999-9999")
                .idade(28)
                .cidade("Cidade de Fulano")
                .estado("SP")
                .regiao("sudeste")
                .build();
    }


    public static Atuacao criaAtuacao() {
        return Atuacao.builder()
                .id(1L)
                .regiao("sudeste")
                .estados(List.of(Estado.builder().id(1L).build()))
                .build();
    }


    public static Estado criaEstado() {
        return Estado.builder()
                .id(1L)
                .atuacao(Factory.criaAtuacao())
                .sigla("SP")
                .nome("São Paulo")
                .vendedor(Factory.criavendedor())
                .build();
    }


    public static VendedorDTO criavendedorDTO() {
        return VendedorDTO.builder()
                .id(1L)
                .nome("Fulano de Tal")
                .dataInclusao(LocalDateTime.of(2022, 2,2,14,30))
                .estados(List.of(Factory.criaEstado()))
                .build();
    }

    public static VendedorDTOPaged criavendedorDTODtoPaged() {
        return VendedorDTOPaged.builder()
                .id(1L)
                .nome("Fulano de Tal")
                .estados(List.of(Factory.criaEstado()))
                .build();
    }
}
