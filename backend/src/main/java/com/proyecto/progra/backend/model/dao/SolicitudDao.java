package com.proyecto.progra.backend.model.dao;

import com.proyecto.progra.backend.model.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudDao extends JpaRepository<Rol, Integer> {
}
