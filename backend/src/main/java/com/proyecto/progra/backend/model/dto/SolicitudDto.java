package com.proyecto.progra.backend.model.dto;
import com.proyecto.progra.backend.model.entity.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ToString
@Builder
public class SolicitudDto implements Serializable{

    private Integer id;
    private UsuarioGetDto idUsuario;
    private String numeroSoporte;
    private TipoSolicitante idTipoSolicitante;
    private TipoSolicitud idTipoSolicitud;
    private TipoSoporte idTipoSoporte;
    private String descripcionSolicitudMuestraMedica;
    private Date fechaCreacionSolicitud;
    private String diasVencimientoSolicitud;
    private List<Muestra> muestraList;
    private List<Items> itemsList;
    private TipoEstadoSolicitud tipoEstadoSolicitud;
}
