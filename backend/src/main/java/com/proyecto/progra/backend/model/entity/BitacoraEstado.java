package com.proyecto.progra.backend.model.entity;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.security.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name ="bitacora_estado")
public class BitacoraEstado implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_tipo_estado_solicitud")
    private Integer idTipoEstadoSolicitud;

    @Column(name = "id_solicitud_muestra_medica")
    private Integer idSolicitudMuestraMedica;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "fecha_estado", columnDefinition = "timestamp default current_timestamp")
    private Timestamp fechaEstado;

    @Column(name = "comentario", length = 255)
    private String comentario;


}
