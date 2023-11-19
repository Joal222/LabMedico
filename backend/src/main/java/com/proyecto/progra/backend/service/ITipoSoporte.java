package com.proyecto.progra.backend.service;

import com.proyecto.progra.backend.model.entity.TipoItems;
import com.proyecto.progra.backend.model.entity.TipoSoporte;

import java.util.List;

public interface ITipoSoporte {

    TipoSoporte findById(Integer id);
    List<TipoSoporte> findAll();
}
