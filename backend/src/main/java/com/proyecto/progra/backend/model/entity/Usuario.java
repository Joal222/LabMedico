package com.proyecto.progra.backend.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name ="usuario")
public class Usuario implements Serializable {

    @Id
    //Hace referencia que nuestra clave primaria es autoincrementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id")
    private Integer id;

    @Column (name="id_tipo_usuario")
    private Integer idTipoUsuario;

    //En la entidad Usuario, se agregado una relación @ManyToOne con la entidad Rol.
    //Esto establece una relación many-to-one entre Usuario y Rol, lo que significa que muchos usuarios pueden tener el mismo rol, pero un rol está asociado a un solo usuario.

    @Column(name = "id_rol")
    private Integer idRol;
    /*
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;
    */

    @Column (name="id_expediente")
    private Integer idExpediente;

    /*@JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_expediente",referencedColumnName = "id")
    private Expediente expediente;
    */

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
