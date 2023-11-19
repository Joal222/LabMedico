package com.proyecto.progra.backend.service.impl;
import com.proyecto.progra.backend.model.dao.ItemsDao;
import com.proyecto.progra.backend.model.dao.SolicitudDao;
import com.proyecto.progra.backend.model.entity.Items;
import com.proyecto.progra.backend.model.entity.Solicitud;
import com.proyecto.progra.backend.projections.closed.ISolicitudClosedView;
import com.proyecto.progra.backend.service.ISolicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SolicitudImpl implements ISolicitud {
    @Autowired
    private SolicitudDao solicitudDao;

    @Autowired
    private ItemsDao itemsDao;
    @Override
    public Solicitud save(Solicitud solicitud) {
            List<Items> itemsList = new ArrayList<>();
            solicitud.getItemsList().forEach(item -> {
                itemsList.add(itemsDao.findById(item.getIdTipoItems()).get());
            });
            solicitud.setItemsList(itemsList);
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
