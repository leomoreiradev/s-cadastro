package com.serasa.cadastro.dto;


import com.serasa.cadastro.entity.Estado;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstadoDTO {

    @NotBlank
    private Long id;

    @NotBlank
    @Size(max = 2, min = 2)
    private String sigla;

    @NotBlank
    private String nome;

    public EstadoDTO(Estado estado) {
        this.id = estado.getId();
        this.sigla = estado.getSigla();
        this.nome = estado.getNome();
    }


}
