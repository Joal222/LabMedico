package com.proyecto.progra.backend.service.impl;

import com.proyecto.progra.backend.model.dao.TipoSolicitudDao;
import com.proyecto.progra.backend.model.entity.TipoSolicitud;
import com.proyecto.progra.backend.projections.closed.ITipoSolicitudClosedView;
import com.proyecto.progra.backend.service.ITipoSolicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoSolicitudImpl implements ITipoSolicitud {

    @Autowired
    private TipoSolicitudDao tipoSolicitudDao;


    @Override
    public TipoSolicitud findById(Integer id) {
        return tipoSolicitudDao.findById(id).orElse(null);
    }

    @Override
    public List<ITipoSolicitudClosedView> getAllTiposSolicitudProjection() {
        return tipoSolicitudDao.findAllProjectedBy();
    }
}
