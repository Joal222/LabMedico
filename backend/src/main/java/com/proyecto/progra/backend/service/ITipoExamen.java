package com.proyecto.progra.backend.service;

import com.proyecto.progra.backend.model.entity.TipoExamen;

import java.util.List;

public interface ITipoExamen {
    /*
    TipoExamen save (TipoExamenDto tipoExamen);
    void delete(TipoExamen tipoExamen);
    boolean existById(Integer id);
     */
    TipoExamen findById(Integer id);

    List<TipoExamen> findAll();

}
