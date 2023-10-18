package com.proyecto.progra.backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name ="usuario")
public class Usuario implements Serializable {

    @Id
    @Column (name="id")
    //Hace referencia que nuestra clave primaria es autoincrementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name="id_tipo_usuario")
    private Integer idTipoUsuario;

    @Column (name="id_rol")
    private Integer idRol;

    @Column (name="id_expediente")
    private Integer idExpediente;

    @Column (name="nombres")
    private String nombres;

    @Column (name="apellidos")
    private String apellidos;

    @Column (name="email")
    private String email;

    @Column (name="genero")
    private String genero;

    @Column (name="telefono")
    private String telefono;

    @Column (name="password")
    private String contraseña;
}
