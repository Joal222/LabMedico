package com.proyecto.progra.backend.service.impl;

import com.proyecto.progra.backend.model.dao.BitacoraDao;
import com.proyecto.progra.backend.projections.estado.IBitacoraClosedView;
import com.proyecto.progra.backend.service.IBitacora;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BitacoraImpl implements IBitacora {

    @Autowired
    private BitacoraDao bitacoraDao;
    @Override
    public List<IBitacoraClosedView> getBitacoraProjectionAll() {
        return bitacoraDao.findAllProjectedBy();
    }
}
