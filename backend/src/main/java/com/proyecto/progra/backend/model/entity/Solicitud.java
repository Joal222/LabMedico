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
@Table(name = "solicitud_muestra_medica")
public class Solicitud implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "id_tipo_solicitante", columnDefinition = "integer default 1")
    private Integer idTipoSolicitante;

    @Column(name = "id_tipo_solicitud")
    private Integer idTipoSolicitud;

    @Column(name = "id_tipo_soporte")
    private Integer idTipoSoporte;

    @Column(name = "descripcion_solicitud_muestra_medica", length = 2000)
    private String descripcionSolicitudMuestraMedica;

    @Column(name = "fecha_creacion_solicitud")
    private Date fechaCreacionSolicitud;

    @Column(name = "dias_vencimiento_solicitud")
    private Integer diasVencimientoSolicitud;

}
