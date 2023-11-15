package com.proyecto.progra.backend.projections.solicitud;

import com.proyecto.progra.backend.projections.usuario.IUsuarioClosedView;

import java.util.Date;

public interface ISolicitudClosedView {
    Integer getId();
    String getDescripcionSolicitudMuestraMedica();
    Date getFechaCreacionSolicitud();
    IUsuarioClosedView getIdUsuario();
}
