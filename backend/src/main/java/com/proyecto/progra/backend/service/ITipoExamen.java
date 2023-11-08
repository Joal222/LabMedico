package com.proyecto.progra.backend.service;

import com.proyecto.progra.backend.model.dto.TipoExamenDto;
import com.proyecto.progra.backend.model.entity.TipoExamen;

import java.util.List;

public interface ITipoExamen {
    TipoExamen save (TipoExamenDto tipoExamen);
    TipoExamen findById(Integer id);
    void delete(TipoExamen tipoExamen);
    List<TipoExamen> findAll();

    boolean existById(Integer id);

}
