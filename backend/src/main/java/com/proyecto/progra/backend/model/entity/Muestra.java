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
@Table(name = "muestra_medica")
public class Muestra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @Column(name = "id_solicitud_muestra_medica")
    private Integer idSolicitudMuestraMedica;

    @Column(name = "id_presentacion_muestra")
    private Integer idPresentacionMuestra;

    @Column(name = "id_tipo_muestra")
    private Integer idTipoMuestra;

    @Column(name = "id_unidad_medida")
    private Integer idUnidadMedida;

    @Column(name = "fecha_recepcion_muestra")
    private Date fechaRecepcionMuestra;

    @Column(name = "fecha_creacion_muestra")
    private Date fechaCreacionMuestra;

    // Otros campos comentados

    @Column(name = "observacion_expediente", length = 2000)
    private String observacionExpediente;
}