package com.proyecto.progra.backend.model.dao;

import com.proyecto.progra.backend.model.entity.Rol;
import com.proyecto.progra.backend.model.entity.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudDao extends JpaRepository<Solicitud, Integer> {
}
