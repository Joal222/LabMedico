package com.proyecto.progra.backend.service;

import com.proyecto.progra.backend.model.entity.TipoSolicitante;

import java.util.List;

public interface ITipoSolicitante {

    TipoSolicitante findById(Integer id);
    List<TipoSolicitante> findAll();
}
