package com.proyecto.progra.backend.service;
import com.proyecto.progra.backend.model.dto.MuestraDto;
import com.proyecto.progra.backend.model.entity.Muestra;

import java.util.List;
public interface IMuestra {
    Muestra save (MuestraDto muestraMedica);
    Muestra findById(Integer id);
    void delete(Muestra muestra);
    List<Muestra> findAll();
    boolean existById(Integer id);
}
