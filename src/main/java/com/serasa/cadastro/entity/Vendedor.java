package com.serasa.cadastro.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_VENDEDOR")
public class Vendedor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private Integer idade;
    private String cidade;
    private String estado;
    private String regiao;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime dataInclusao;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendedor", fetch = FetchType.EAGER)
    private List<Estado> estados = new ArrayList<>();

    @PrePersist
    private void prePersist() {
        dataInclusao = LocalDateTime.now();

    }
}
