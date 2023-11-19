package com.proyecto.progra.backend.model.dao;

import com.proyecto.progra.backend.model.entity.TipoSolicitud;
import com.proyecto.progra.backend.projections.closed.ITipoSolicitudClosedView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoSolicitudDao extends JpaRepository<TipoSolicitud, Integer> {
    List<ITipoSolicitudClosedView> findAllProjectedBy();
}
