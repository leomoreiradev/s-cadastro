package com.serasa.cadastro.services;

import com.serasa.cadastro.entity.Estado;
import com.serasa.cadastro.repository.EstadoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> buscarTodos() {
        log.info("Buscando estados...");
        return estadoRepository.findAll();
    }
}
