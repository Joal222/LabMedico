package com.proyecto.progra.backend.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name ="rol")
public class Rol implements Serializable {

    @Id
    @Column (name="id")
    //Hace referencia que nuestra clave primaria es autoincrementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name="nombre")
    private String nombre;

    @Column (name="descripcion")
    private String descripcion;

    @Column (name="fecha_creacion")
    private Date fechaCreacion;

    @Column (name="fecha_modificacion")
    private Date fechaModificacion;

    @Column (name="creado_por")
    private String creadoPor;

    @Column (name="modificado_por")
    private String modificadoPor;


    /*
    @OneToMany(mappedBy = "rol")
    private List<Usuario> usuarios = new ArrayList<>();

    @PrePersist
    public void prePersist(){
        this.fechaCreacion = new Date();
    }

    @PreUpdate
    public void preUpdate(){
        this.fechaModificacion = new Date();
    }
    */

}