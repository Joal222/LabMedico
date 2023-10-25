package com.proyecto.progra.backend.service;
import com.proyecto.progra.backend.model.dto.MuestraMedicaDto;
import com.proyecto.progra.backend.model.entity.MuestraMedica;

import java.util.List;
public interface IMuestraMedica {
    MuestraMedica save (MuestraMedicaDto muestraMedica);
    MuestraMedica findById(Integer id);
    void delete(MuestraMedica muestraMedica);
    List<MuestraMedica> findAll();
    boolean existById(Integer id);
}
