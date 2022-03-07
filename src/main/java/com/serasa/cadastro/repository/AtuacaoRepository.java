package com.serasa.cadastro.repository;

import com.serasa.cadastro.entity.Atuacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtuacaoRepository extends JpaRepository<Atuacao,Long> {
    Atuacao findByRegiaoIgnoreCase(String regiao);
}
