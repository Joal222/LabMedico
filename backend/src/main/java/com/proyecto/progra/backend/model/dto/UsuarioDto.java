package com.proyecto.progra.backend.model.dto;

import com.proyecto.progra.backend.model.entity.Expediente;
import com.proyecto.progra.backend.model.entity.Rol;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@ToString
//Nueva anotación para eliminar notaciones @AllArgsConstructor, @Column, @NoArgsConstructor
@Builder
public class UsuarioDto implements Serializable {

    private Integer id;
    private Integer idTipoUsuario;
    private Rol rol;
    private Expediente expediente;
    private String nombres;
    private String apellidos;
    private String email;
    private String genero;
    private String telefono;
    private String contraseña;
}
