package com.proyecto.progra.backend.service;
import com.proyecto.progra.backend.model.dto.SolicitudDto;
import com.proyecto.progra.backend.model.entity.Solicitud;
import com.proyecto.progra.backend.projections.solicitud.ISolicitudClosedView;

import java.util.List;
import java.util.Optional;

public interface ISolicitud {
    Solicitud save(SolicitudDto solicitud);
    Solicitud findById(Integer id);
    void delete(Solicitud solicitud);
    List<Solicitud> findAll();
    boolean existById(Integer id);
    List<ISolicitudClosedView> getSolicitudProjectionAll();
}
