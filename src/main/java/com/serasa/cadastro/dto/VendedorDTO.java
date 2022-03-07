package com.serasa.cadastro.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.serasa.cadastro.entity.Estado;
import com.serasa.cadastro.entity.Vendedor;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendedorDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime dataInclusao;
    private List<Estado> estados;


    public VendedorDTO(Vendedor vendedor) {
        this.id = vendedor.getId();
        this.nome = vendedor.getNome();
        this.dataInclusao = vendedor.getDataInclusao();
        this.estados = vendedor.getEstados();
    }
}
