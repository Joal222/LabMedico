package com.proyecto.progra.backend.service;
import com.proyecto.progra.backend.model.entity.TipoItems;

import java.util.List;

public interface ITipoItems {
    TipoItems findById(Integer id);

    List<TipoItems> findAll();
}
