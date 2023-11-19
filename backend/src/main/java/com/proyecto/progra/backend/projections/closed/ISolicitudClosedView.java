package com.proyecto.progra.backend.projections.closed;


import java.util.Date;
import java.util.List;

public interface ISolicitudClosedView {
    Integer getId();
    IUsuarioClosedView getIdUsuario();
    String getNumeroSoporte();
    ITipoSolicitudClosedView getIdTipoSolicitud();
    IItemsClosedView getItemsList();
    ITipoEstadoClosedView getTipoEstadoSolicitud();
    Date getFechaCreacionSolicitud();


}
