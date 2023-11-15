package com.proyecto.progra.backend.model.dao;

import com.proyecto.progra.backend.model.entity.Solicitud;
import com.proyecto.progra.backend.projections.solicitud.ISolicitudClosedView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SolicitudDao extends JpaRepository<Solicitud, Integer> {
//    @Query("SELECT s.id as id, u as usuario, s.descripcionSolicitudMuestraMedica as descripcionSolicitudMuestraMedica, s.fechaCreacionSolicitud as fechaCreacionSolicitud FROM Solicitud s JOIN s.idUsuario u")
    List<ISolicitudClosedView> findAllProjectedBy();
}
