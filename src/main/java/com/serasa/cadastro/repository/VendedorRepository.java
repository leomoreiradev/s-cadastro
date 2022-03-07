package com.serasa.cadastro.repository;

import com.serasa.cadastro.entity.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
    Optional<Vendedor> findByTelefone(String telefone);
}
