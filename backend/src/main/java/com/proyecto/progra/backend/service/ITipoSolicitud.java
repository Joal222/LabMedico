package com.proyecto.progra.backend.service;

import com.proyecto.progra.backend.projections.closed.ITipoSolicitudClosedView;

import java.util.List;

public interface ITipoSolicitud {

    List<ITipoSolicitudClosedView> getAllTiposSolicitudProjection();
}
