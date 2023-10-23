package com.proyecto.progra.backend.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name ="expediente")
public class Expediente implements Serializable {

    @Id
    @Column (name="id")
    //Hace referencia que nuestra clave primaria es autoincrementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@OneToOne(mappedBy = "expediente")
    //private Usuario usuario;

    @Column (name="nit_cliente")
    private String nitCliente;

    @Column (name="direccion")
    private String direccion;

    //Pendiente crear entidad tipo_soporte
    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "id_tipo_soporte",referencedColumnName = "id")
    //private Integer idExpediente;

    @Column (name="fecha_creacion")
    private Date fechaCreacion;

    /*
    @OneToOne
    @JoinColumn(name = "id_expediente", referencedColumnName = "id")
    private Usuario usuario;


    @PrePersist
    public void prePersist(){
        this.fechaCreacion = new Date();
    }
    */
}
