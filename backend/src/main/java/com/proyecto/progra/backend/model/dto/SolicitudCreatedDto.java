package com.proyecto.progra.backend.model.dto;

import com.proyecto.progra.backend.model.entity.Items;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@Builder
public class SolicitudCreatedDto implements Serializable{

    private Integer idUsuario;
    private String numeroSoporte;
    private Integer idTipoSolicitud;
    private Integer idTipoSoporte;
    private String descripcionSolicitudMuestraMedica;
    private List<Items> itemsList;
}
