package com.serasa.cadastro.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_ESTADO")
public class Estado implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sigla;
    private String nome;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "atuacao_id")
    private Atuacao atuacao;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;

}
