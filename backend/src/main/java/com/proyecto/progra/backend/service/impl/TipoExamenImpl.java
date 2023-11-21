package com.proyecto.progra.backend.service.impl;

import com.proyecto.progra.backend.model.dao.TipoExamenDao;
import com.proyecto.progra.backend.model.entity.TipoExamen;
import com.proyecto.progra.backend.model.entity.Usuario;
import com.proyecto.progra.backend.service.ITipoExamen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipoExamenImpl implements ITipoExamen {

    @Autowired
    private TipoExamenDao tipoExamenDao;

    @Transactional(readOnly = true)
    @Override
    public TipoExamen findById(Integer id) {
        return tipoExamenDao.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TipoExamen> findAll() {

        return (List<TipoExamen>) tipoExamenDao.findAll();
    }
}
