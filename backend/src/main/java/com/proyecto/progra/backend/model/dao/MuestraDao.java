package com.proyecto.progra.backend.model.dao;

import com.proyecto.progra.backend.model.entity.Muestra;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MuestraDao extends JpaRepository<Muestra, Integer> {
}
