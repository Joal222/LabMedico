package com.proyecto.progra.backend.service.impl;

import com.proyecto.progra.backend.model.dao.BitacoraEstadoDao;
import com.proyecto.progra.backend.projections.closed.IBitacoraEstadoClosedView;
import com.proyecto.progra.backend.service.IBitacoraEstado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BitacoraEstadoImpl implements IBitacoraEstado {

    @Autowired
    private BitacoraEstadoDao bitacoraEstadoDao;
    @Override
    public List<IBitacoraEstadoClosedView> getAllBitacoraEstadoProjection() {
        return bitacoraEstadoDao.findAllProjectedBy();
    }
}
