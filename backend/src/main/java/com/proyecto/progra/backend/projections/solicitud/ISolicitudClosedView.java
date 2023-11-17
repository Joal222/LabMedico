package com.proyecto.progra.backend.projections.solicitud;

import com.proyecto.progra.backend.projections.tipoSolicitud.ITipoSolicitudClosedView;
import com.proyecto.progra.backend.projections.usuario.IUsuarioClosedView;


import java.util.Date;

public interface ISolicitudClosedView {
    Integer getId();
    IUsuarioClosedView getIdUsuario();
    String getNumeroSoporte();
    ITipoSolicitudClosedView getIdTipoSolicitud();


    Date getFechaCreacionSolicitud();

}
