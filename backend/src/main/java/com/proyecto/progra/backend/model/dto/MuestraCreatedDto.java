package com.proyecto.progra.backend.model.dto;

import com.proyecto.progra.backend.model.entity.PresentacionMuestra;
import com.proyecto.progra.backend.model.entity.TipoMuestra;
import com.proyecto.progra.backend.model.entity.UnidadMedida;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class MuestraCreatedDto implements Serializable {

    private Integer idSolicitudMuestraMedica;
    private PresentacionMuestra idPresentacionMuestra;
    private TipoMuestra idTipoMuestra;
    private UnidadMedida idUnidadMedida;
    private String observacionExpediente;
}