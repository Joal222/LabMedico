package com.proyecto.progra.backend.service;

import com.proyecto.progra.backend.model.entity.UnidadMedida;

import java.util.List;

public interface IUMedida {

    UnidadMedida findById(Integer id);
    List<UnidadMedida> findAll();
}
