package com.proyecto.progra.backend.model.dto;
import lombok.*;
import java.io.Serializable;

@Data
@ToString
//Nueva anotación para eliminar notaciones @AllArgsConstructor, @Column, @NoArgsConstructor
@Builder
public class UsuarioDto implements Serializable {
    private Integer id;
    private Integer idTipoUsuario;
    private Integer idRol;
    private String nit;
    private String nombres;
    private String apellidos;
    private String email;
    private String genero;
    private String telefono;
    private String direccion;
    private String password;
}
