package com.proyecto.progra.backend.model.dto;

import com.proyecto.progra.backend.model.entity.Usuario;
import lombok.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ToString
@Builder
public class RolDto implements Serializable {

    private Integer id;
    private String nombre;
    private String descripcion;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private String creadoPor;
    private String modificadoPor;
    private List<Usuario> usuarios = new ArrayList<>();
    }
