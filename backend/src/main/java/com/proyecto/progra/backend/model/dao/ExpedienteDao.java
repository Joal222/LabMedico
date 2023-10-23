package com.proyecto.progra.backend.model.dao;

import com.proyecto.progra.backend.model.entity.Expediente;
import org.springframework.data.repository.CrudRepository;

public interface ExpedienteDao extends CrudRepository<Expediente, Integer> {
}
