package com.proyecto.progra.backend.model.dao;

import com.proyecto.progra.backend.model.entity.MuestraItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MuestraItemsDao extends JpaRepository<MuestraItems, Integer> {
    
}
