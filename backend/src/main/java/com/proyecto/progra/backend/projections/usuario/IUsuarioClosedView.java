package com.proyecto.progra.backend.projections.usuario;

import com.proyecto.progra.backend.model.entity.Rol;

public interface IUsuarioClosedView {
    Integer getId();
    String getNombres();
    String getApellidos();
    String getEmail();
}
