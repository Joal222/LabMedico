package com.proyecto.progra.backend.model.dto;
import com.proyecto.progra.backend.model.entity.Usuario;
import lombok.*;
import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class SolicitudDto implements Serializable{

    private Integer id;
    private Usuario idUsuario;
    private Integer idTipoSolicitante;
    private Integer idTipoSolicitud;
    private Integer idTipoSoporte;
    private String descripcionSolicitudMuestraMedica;
    private Date fechaCreacionSolicitud;
    private Integer diasVencimientoSolicitud;
}
