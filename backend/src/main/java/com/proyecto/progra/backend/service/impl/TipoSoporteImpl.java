package com.proyecto.progra.backend.service.impl;

import com.proyecto.progra.backend.model.dao.TipoSoporteDao;
import com.proyecto.progra.backend.model.entity.TipoSoporte;
import com.proyecto.progra.backend.service.ITipoSoporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipoSoporteImpl implements ITipoSoporte {

    @Autowired
    private TipoSoporteDao tipoSoporteDao;
    @Transactional(readOnly = true)
    @Override
    public TipoSoporte findById(Integer id) {
        return tipoSoporteDao.findById(id).orElse(null);
    }
    @Transactional(readOnly = true)
    @Override
    public List<TipoSoporte> findAll() {
        return tipoSoporteDao.findAll();
    }
}
