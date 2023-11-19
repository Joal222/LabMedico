package com.proyecto.progra.backend.model.dao;
import com.proyecto.progra.backend.model.entity.Items;
import com.proyecto.progra.backend.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ItemsDao extends JpaRepository<Items, Integer> {


}