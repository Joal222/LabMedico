package com.proyecto.progra.backend.model.dao;

import com.proyecto.progra.backend.model.entity.PresentacionMuestra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PTMuestraDao extends JpaRepository<PresentacionMuestra, Integer> {
}
