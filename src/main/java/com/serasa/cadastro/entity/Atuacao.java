package com.serasa.cadastro.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_ATUACAO")
public class Atuacao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String regiao;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "atuacao", fetch = FetchType.EAGER)
    private List<Estado> estados = new ArrayList<>();



}
