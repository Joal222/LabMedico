package com.proyecto.progra.backend.service;

import com.proyecto.progra.backend.model.entity.TipoSolicitud;
import com.proyecto.progra.backend.model.entity.Usuario;
import com.proyecto.progra.backend.projections.closed.ITipoSolicitudClosedView;

import java.util.List;

public interface ITipoSolicitud {

    TipoSolicitud findById(Integer id);

    List<ITipoSolicitudClosedView> getAllTiposSolicitudProjection();
}
