package com.proyecto.progra.backend.service;

import com.proyecto.progra.backend.model.entity.PresentacionMuestra;

import java.util.List;

public interface IPTMuestra {

    PresentacionMuestra findById(Integer id);
    List<PresentacionMuestra> findAll();
}
