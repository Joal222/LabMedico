package com.proyecto.progra.backend.service.impl;

import com.proyecto.progra.backend.model.dao.TipoEstadoDao;
import com.proyecto.progra.backend.model.entity.TipoEstadoSolicitud;
import com.proyecto.progra.backend.model.entity.TipoExamen;
import com.proyecto.progra.backend.projections.closed.ITipoEstadoClosedView;
import com.proyecto.progra.backend.service.ITipoEstado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipoEstadoImpl implements ITipoEstado {
    @Autowired
    private TipoEstadoDao tipoEstadoDao;

    @Transactional
    @Override
    public List<ITipoEstadoClosedView> getAllTipoEstadoProjection() {
        return tipoEstadoDao.findAllProjectionBy();
    }

    @Transactional(readOnly = true)
    @Override
    public TipoEstadoSolicitud findById(Integer id) {
        return tipoEstadoDao.findById(id).orElse(null);
    }
    @Transactional(readOnly = true)
    @Override
    public List<TipoEstadoSolicitud> finAll() {
        return (List<TipoEstadoSolicitud>) tipoEstadoDao.findAll();
    }

}
