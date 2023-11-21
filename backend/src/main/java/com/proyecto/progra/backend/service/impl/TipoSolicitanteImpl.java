package com.proyecto.progra.backend.service.impl;

import com.proyecto.progra.backend.model.dao.TipoSolicitanteDao;
import com.proyecto.progra.backend.model.entity.TipoSolicitante;
import com.proyecto.progra.backend.service.ITipoSolicitante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class TipoSolicitanteImpl implements ITipoSolicitante {

    @Autowired
    private TipoSolicitanteDao tipoSolicitanteDao;
    @Transactional(readOnly = true)
    @Override
    public TipoSolicitante findById(Integer id) {
        return tipoSolicitanteDao.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TipoSolicitante> findAll() {
        return tipoSolicitanteDao.findAll();
    }
}
