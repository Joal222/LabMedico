package com.proyecto.progra.backend.service.impl;

import com.proyecto.progra.backend.model.dao.TipoItemsDao;
import com.proyecto.progra.backend.model.dto.TipoItemsDto;
import com.proyecto.progra.backend.model.entity.TipoExamen;
import com.proyecto.progra.backend.model.entity.TipoItems;
import com.proyecto.progra.backend.service.ITipoItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipoItemsImpl implements ITipoItems {

    @Autowired
    private TipoItemsDao tipoItemsDao;


    @Transactional(readOnly = true)
    @Override
    public TipoItems findById(Integer id) {

        return tipoItemsDao.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TipoItems> findAll() {

        return (List<TipoItems>) tipoItemsDao.findAll();
    }
}
