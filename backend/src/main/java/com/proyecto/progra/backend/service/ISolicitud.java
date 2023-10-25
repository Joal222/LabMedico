package com.proyecto.progra.backend.service;
import com.proyecto.progra.backend.model.dto.SolicitudDto;
import com.proyecto.progra.backend.model.entity.Solicitud;

import java.util.List;
public interface ISolicitud {
    Solicitud save (SolicitudDto solicitud);
    Solicitud findById(Integer id);

    void delete(Solicitud solicitud);
    List<Solicitud> findAll();
    boolean existById(Integer id);
}
