package com.proyecto.progra.backend.service.impl;
import com.proyecto.progra.backend.model.dao.SolicitudDao;
import com.proyecto.progra.backend.model.dao.UsuarioDao;
import com.proyecto.progra.backend.model.dto.SolicitudCreatedDto;
import com.proyecto.progra.backend.model.dto.SolicitudDto;
import com.proyecto.progra.backend.model.entity.Solicitud;
import com.proyecto.progra.backend.projections.closed.ISolicitudClosedView;
import com.proyecto.progra.backend.service.ISolicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SolicitudImpl implements ISolicitud {
    @Autowired
    private SolicitudDao solicitudDao;
    @Override
    public Solicitud save(Solicitud solicitud) {

        return solicitudDao.save(solicitud);
    }

    @Transactional(readOnly = true)
    @Override
    public Solicitud findById(Integer id) {
        return solicitudDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Solicitud solicitud) {
        solicitudDao.delete(solicitud);
    }
    @Transactional
    @Override
    public List<Solicitud> findAll() {

        return (List<Solicitud>) solicitudDao.findAll();
    }

    @Override
    public boolean existById(Integer id) {

        return solicitudDao.existsById(id);
    }

    @Override
    public List<ISolicitudClosedView> getSolicitudProjectionAll() {
        return solicitudDao.findAllProjectedBy();
    }
}
