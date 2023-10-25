package com.proyecto.progra.backend.service.impl;
import com.proyecto.progra.backend.model.dao.SolicitudDao;
import com.proyecto.progra.backend.model.dto.SolicitudDto;
import com.proyecto.progra.backend.model.entity.Solicitud;
import com.proyecto.progra.backend.service.ISolicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SolicitudImpl implements ISolicitud {
    @Override
    public Solicitud save(SolicitudDto solicitud) {
        return null;
    }

    @Override
    public Solicitud findById(Integer id) {
        return null;
    }

    @Override
    public void delete(Solicitud solicitud) {

    }

    @Override
    public List<Solicitud> findAll() {
        return null;
    }

    @Override
    public boolean existById(Integer id) {
        return false;
    }
}
