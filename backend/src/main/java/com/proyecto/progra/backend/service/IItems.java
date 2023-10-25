package com.proyecto.progra.backend.service;
import com.proyecto.progra.backend.model.dto.ItemsDto;
import com.proyecto.progra.backend.model.entity.Items;

import java.util.List;

public interface IItems {
    Items save (ItemsDto items);
    Items findById(Integer id);
    void delete(Items items);
    List<Items> findAll();
    boolean existById(Integer id);
}
