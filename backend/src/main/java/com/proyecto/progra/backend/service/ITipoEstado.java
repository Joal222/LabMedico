package com.proyecto.progra.backend.service;

import com.proyecto.progra.backend.projections.closed.ITipoEstadoClosedView;

import java.util.List;

public interface ITipoEstado {
    List<ITipoEstadoClosedView> getAllTipoEstadoProjection();
}
