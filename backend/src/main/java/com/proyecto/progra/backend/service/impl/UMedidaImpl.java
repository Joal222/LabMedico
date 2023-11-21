package com.proyecto.progra.backend.service.impl;

import com.proyecto.progra.backend.model.dao.UMedidaDao;
import com.proyecto.progra.backend.model.entity.UnidadMedida;
import com.proyecto.progra.backend.service.IUMedida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UMedidaImpl implements IUMedida{
    @Autowired
    private UMedidaDao uMedidaDao;
    @Transactional(readOnly = true)
    @Override
    public UnidadMedida findById(Integer id) {
        return uMedidaDao.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UnidadMedida> findAll() {
        return (List<UnidadMedida>) uMedidaDao.findAll();
    }
}
