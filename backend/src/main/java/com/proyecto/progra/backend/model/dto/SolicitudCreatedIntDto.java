package com.proyecto.progra.backend.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@Builder
public class SolicitudCreatedIntDto implements Serializable{

    private Integer idUsuario;
    private String cui;
    private String numeroSoporte;
    private Integer idTipoSolicitud;
    private Integer idTipoSoporte;
    private String descripcionSolicitudMuestraMedica;
    private List<ItemsDto> itemsList;
}
