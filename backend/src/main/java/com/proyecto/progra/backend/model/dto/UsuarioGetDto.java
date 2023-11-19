package com.proyecto.progra.backend.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class UsuarioGetDto implements Serializable {
    private Integer id;
    private String cui;
    private Integer idTipoUsuario;
    private String nit;
    private String nombres;
    private String apellidos;
    private String email;
    private String genero;
    private String telefono;
    private String direccion;
    private String password;
}
