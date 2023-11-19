package com.proyecto.progra.backend.service.impl;

import com.proyecto.progra.backend.model.dao.TipoSoporteDao;
import com.proyecto.progra.backend.model.entity.TipoSoporte;
import com.proyecto.progra.backend.service.ITipoSoporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoSoporteImpl implements ITipoSoporte {

    @Autowired
    private TipoSoporteDao tipoSoporteDao;
    @Override
    public TipoSoporte findById(Integer id) {
        return tipoSoporteDao.findById(id).orElse(null);
    }
    @Override
    public List<TipoSoporte> findAll() {

        return tipoSoporteDao.findAll();
    }
}
