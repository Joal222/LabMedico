package com.proyecto.progra.backend.model.dto;
import jakarta.persistence.PrePersist;
import lombok.*;
import java.io.Serializable;

@Data
@ToString
@Builder
public class UsuarioExternalDto implements Serializable {
    private String cui;
    private String nit;
    private String nombres;
    private String apellidos;
    private String email;
    private String genero;
    private String telefono;
    private String direccion;
    private String password;
}
