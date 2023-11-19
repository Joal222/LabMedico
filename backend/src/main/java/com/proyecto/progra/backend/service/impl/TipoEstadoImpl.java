package com.proyecto.progra.backend.service.impl;

import com.proyecto.progra.backend.model.dao.TipoEstadoDao;
import com.proyecto.progra.backend.projections.closed.ITipoEstadoClosedView;
import com.proyecto.progra.backend.service.ITipoEstado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoEstadoImpl implements ITipoEstado {
    @Autowired
    private TipoEstadoDao tipoEstadoDao;
    @Override
    public List<ITipoEstadoClosedView> getAllTipoEstadoProjection() {
        return tipoEstadoDao.findAllProjectionBy();
    }
}
