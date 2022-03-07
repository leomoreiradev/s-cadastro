package com.serasa.cadastro.dto;

import com.serasa.cadastro.entity.Estado;
import com.serasa.cadastro.entity.Vendedor;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendedorDTOPaged implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String telefone;
    private Integer idade;
    private String cidade;
    private String estado;

    private List<Estado> estados;


    public VendedorDTOPaged(Vendedor vendedor) {
        this.id = vendedor.getId();
        this.nome = vendedor.getNome();
        this.telefone = vendedor.getTelefone();
        this.idade = vendedor.getIdade();
        this.cidade = vendedor.getCidade();
        this.estado = vendedor.getEstado();
        this.estados = vendedor.getEstados();
    }
}
