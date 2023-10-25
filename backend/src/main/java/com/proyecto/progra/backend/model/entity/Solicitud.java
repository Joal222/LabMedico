package com.proyecto.progra.backend.model.entity;
//import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "solicitud_muestra_medica")
public class Solicitud implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @Column(name = "id_tipo_solicitante", columnDefinition = "integer default 1", nullable = false)
    private Integer idTipoSolicitante;

    @Column(name = "id_tipo_solicitud", nullable = false)
    private Integer idTipoSolicitud;

    @Column(name = "id_estado_solicitud")
    private Integer idEstadoSolicitud;

    @Column(name = "id_tipo_soporte", nullable = false)
    private Integer idTipoSoporte;

    @Column(name = "descripcion_solicitud_muestra_medica", length = 2000)
    private String descripcionSolicitudMuestraMedica;

    @Column(name = "fecha_creacion_solicitud", nullable = false)
    private Date fechaCreacionSolicitud;

    @Column(name = "dias_vencimiento_solicitud")
    private Integer diasVencimientoSolicitud;

}
