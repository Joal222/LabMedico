package com.proyecto.progra.backend.model.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proyecto.progra.backend.model.entity.Rol;
import jakarta.persistence.Column;
import lombok.*;
import java.io.Serializable;

@Data
@ToString
//Nueva anotación para eliminar notaciones @AllArgsConstructor, @Column, @NoArgsConstructor
@Builder
public class UsuarioDto implements Serializable {
    private Integer id;
    private Integer idTipoUsuario;
    private Rol idRol;
    private String nit;
    private String nombres;
    private String apellidos;
    private String email;
    private String genero;
    private String telefono;
    private String direccion;
    private String password;
}
