package com.proyecto.progra.backend.model.dto;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class MuestraMedicaDto implements Serializable {

    private Integer id;
    private Integer idSolicitudMuestraMedica;
    private Integer idPresentacionMuestra;
    private Integer idTipoMuestra;
    private Integer idDocumentoMuestraAdjunto;
    private Integer idUnidadMedida;
    private Date fechaRecepcionMuestra;
    private Date fechaCreacionMuestra;
    private String observacionExpediente;
}