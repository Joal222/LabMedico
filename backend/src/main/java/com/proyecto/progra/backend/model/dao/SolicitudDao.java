package com.proyecto.progra.backend.model.dao;

import com.proyecto.progra.backend.model.entity.Solicitud;
import com.proyecto.progra.backend.projections.closed.ISolicitudClosedView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitudDao extends JpaRepository<Solicitud, Integer> {
 List<ISolicitudClosedView> findAllProjectedBy();
}
