package com.proyecto.progra.backend.model.dao;

import com.proyecto.progra.backend.model.entity.UnidadMedida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UMedidaDao extends JpaRepository<UnidadMedida,Integer> {
}
