package com.proyecto.progra.backend.service;

import com.proyecto.progra.backend.model.dto.MuestraItemsDto;
import com.proyecto.progra.backend.model.entity.MuestraItems;

import java.util.List;

public interface IMuestraItems {
        MuestraItems save (MuestraItemsDto muestraItems);
        MuestraItems findById(Integer id);
        void delete(MuestraItems muestraItems);
        List<MuestraItems> findAll();
        boolean existById(Integer id);
    }
