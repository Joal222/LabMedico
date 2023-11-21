package com.proyecto.progra.backend.service;

import com.proyecto.progra.backend.model.entity.TipoEstadoSolicitud;
import com.proyecto.progra.backend.model.entity.TipoExamen;
import com.proyecto.progra.backend.projections.closed.ITipoEstadoClosedView;

import java.util.List;

public interface ITipoEstado {
    List<ITipoEstadoClosedView> getAllTipoEstadoProjection();

    TipoEstadoSolicitud findById(Integer id);

    List<TipoEstadoSolicitud> finAll();

}
