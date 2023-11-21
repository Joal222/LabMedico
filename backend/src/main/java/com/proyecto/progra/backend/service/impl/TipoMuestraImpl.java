package com.proyecto.progra.backend.service.impl;

import com.proyecto.progra.backend.model.dao.TipoMuestraDao;
import com.proyecto.progra.backend.model.entity.TipoMuestra;
import com.proyecto.progra.backend.service.ITipoMuestra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipoMuestraImpl implements ITipoMuestra {
    @Autowired
    private TipoMuestraDao tipoMuestraDao;

    @Transactional(readOnly = true)
    @Override
    public TipoMuestra findById(Integer id) {
        return tipoMuestraDao.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TipoMuestra> findAll() {
        return (List<TipoMuestra>) tipoMuestraDao.findAll();
    }
}
