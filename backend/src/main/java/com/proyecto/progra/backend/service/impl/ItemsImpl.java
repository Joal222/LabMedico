package com.proyecto.progra.backend.service.impl;
import com.proyecto.progra.backend.model.dao.ItemsDao;
import com.proyecto.progra.backend.model.dto.ItemsDto;
import com.proyecto.progra.backend.model.entity.Items;
import com.proyecto.progra.backend.service.IItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemsImpl implements IItems {
    @Autowired
    private ItemsDao itemsDao;
    @Transactional
    @Override
    public Items save(ItemsDto itemsDto) {

        Items items = Items.builder()
                .idTipoItems(itemsDto.getIdTipoItems())
                .idSolicitudMuestraMedica(itemsDto.getIdSolicitudMuestraMedica())
                .build();
        return itemsDao.save(items);
    }

    @Transactional(readOnly = true)
    @Override
    public Items findById(Integer id) {
        return itemsDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Items items) {
        itemsDao.delete(items);
    }

    @Transactional
    @Override
    public List<Items> findAll() {
        return (List<Items>) itemsDao.findAll();
    }

    @Override
    public boolean existById(Integer id) {
        return itemsDao.existsById(id);
    }

}
