package com.proyecto.progra.backend.model.dao;

import com.proyecto.progra.backend.model.entity.BitacoraEstado;
import com.proyecto.progra.backend.projections.closed.IBitacoraEstadoClosedView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BitacoraEstadoDao extends JpaRepository<BitacoraEstado,Integer> {
    List<IBitacoraEstadoClosedView> findAllProjectedBy();

}
