package com.proyecto.progra.backend.model.dto;
import lombok.*;
import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class SolicitudDto implements Serializable{

    private Integer id;
    private Integer idUsuario;
    private Integer idTipoSolicitante;
    private Integer idTipoSolicitud;
    private Integer idEstadoSolicitud;
    private Integer idTipoSoporte;
    private String descripcionSolicitudMuestraMedica;
    private Date fechaCreacionSolicitud;
    private Integer diasVencimientoSolicitud;
}
