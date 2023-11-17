package com.proyecto.progra.backend.model.dao;

import com.proyecto.progra.backend.projections.estado.IBitacoraClosedView;

import java.util.List;

public interface BitacoraDao {
    List<IBitacoraClosedView> findAllProjectedBy();

}
