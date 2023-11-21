package com.proyecto.progra.backend.service.impl;

import com.proyecto.progra.backend.model.dao.PTMuestraDao;
import com.proyecto.progra.backend.model.entity.PresentacionMuestra;
import com.proyecto.progra.backend.service.IPTMuestra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PTMuestraImpl implements IPTMuestra {

    @Autowired
    private PTMuestraDao ptMuestraDaoService;
    @Transactional(readOnly = true)
    @Override
    public PresentacionMuestra findById(Integer id) {
        return ptMuestraDaoService.findById(id).orElse(null);
    }
    @Transactional(readOnly = true)
    @Override
    public List<PresentacionMuestra> findAll() {
        return (List<PresentacionMuestra>) ptMuestraDaoService.findAll();
    }
}
