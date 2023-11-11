package com.proyecto.progra.backend.model.dao;

import com.proyecto.progra.backend.model.entity.TipoItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoItemsDao extends JpaRepository<TipoItems,Integer> {
}
