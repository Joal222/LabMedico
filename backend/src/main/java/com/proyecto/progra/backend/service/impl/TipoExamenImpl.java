package com.proyecto.progra.backend.service.impl;

import com.proyecto.progra.backend.model.dao.TipoExamenDao;
import com.proyecto.progra.backend.model.dto.TipoExamenDto;
import com.proyecto.progra.backend.model.entity.TipoExamen;
import com.proyecto.progra.backend.service.ITipoExamen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipoExamenImpl implements ITipoExamen {

    @Autowired
    private TipoExamenDao tipoExamenDao;
    @Override
    public TipoExamen save(TipoExamenDto tipoExamenDto) {
        TipoExamen tipoExamen = TipoExamen.builder()
                .id(tipoExamenDto.getId())
                .nombre(tipoExamenDto.getNombre())
                .descripcion(tipoExamenDto.getDescripcion())
                .fechaCreacion(tipoExamenDto.getFechaCreacion())
                .fechaModificacion(tipoExamenDto.getFechaModificacion())
                .creadoPor(tipoExamenDto.getCreadoPor())
                .modificadoPor(tipoExamenDto.getModificadoPor())
                .build();
        return tipoExamenDao.save(tipoExamen);
    }

    @Transactional(readOnly = true)
    @Override
    public TipoExamen findById(Integer id) {
        return tipoExamenDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(TipoExamen tipoExamen) {
        tipoExamenDao.delete(tipoExamen);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TipoExamen> findAll() {
        return (List<TipoExamen>) tipoExamenDao.findAll();
    }

    @Override
    public boolean existById(Integer id) {
        return tipoExamenDao.existsById(id);
    }
}
