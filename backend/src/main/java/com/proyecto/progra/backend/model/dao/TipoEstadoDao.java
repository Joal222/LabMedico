package com.proyecto.progra.backend.model.dao;

import com.proyecto.progra.backend.model.entity.TipoEstadoSolicitud;
import com.proyecto.progra.backend.projections.closed.ITipoEstadoClosedView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoEstadoDao extends JpaRepository<TipoEstadoSolicitud, Integer> {
    List<ITipoEstadoClosedView> findAllProjectionBy();
}

