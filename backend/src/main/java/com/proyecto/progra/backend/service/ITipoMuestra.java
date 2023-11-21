package com.proyecto.progra.backend.service;

import com.proyecto.progra.backend.model.entity.Muestra;
import com.proyecto.progra.backend.model.entity.TipoMuestra;

import java.util.List;

public interface ITipoMuestra {
    TipoMuestra findById(Integer id);
    List<TipoMuestra> findAll();
}
