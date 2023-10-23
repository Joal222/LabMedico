package com.proyecto.progra.backend.model.dao;

import com.proyecto.progra.backend.model.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RolDao extends JpaRepository<Rol, Integer> {
}
