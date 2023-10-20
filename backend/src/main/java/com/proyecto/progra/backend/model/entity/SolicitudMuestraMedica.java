package com.proyecto.progra.backend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

public class SolicitudMuestraMedica {

    @Id
    @Column(name="id")
    //Hace referencia que nuestra clave primaria es autoincrementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name="id_tipo_solicitante")
    private Integer idTipoSolicitante;

    @Column (name="id_tipo_solicitud")
    private Integer idTipoSolicitud;

    @Column (name="id_estado_solicitud")
    private Integer idEstadoSolicitud;

    @Column (name="descripcion_solicitud_muestra_medica")
    private String descripcionSolicitud;

    @Column (name="fecha_creacion_solicitud")
    private Date fechaCreacion;

    @Column (name="dias_vencimiento_solicitud")
    private Integer diasVencimiento;
}
