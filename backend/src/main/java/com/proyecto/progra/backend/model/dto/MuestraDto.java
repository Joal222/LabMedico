package com.proyecto.progra.backend.model.dto;
import com.proyecto.progra.backend.model.entity.PresentacionMuestra;
import com.proyecto.progra.backend.model.entity.Solicitud;
import com.proyecto.progra.backend.model.entity.TipoMuestra;
import com.proyecto.progra.backend.model.entity.UnidadMedida;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class MuestraDto implements Serializable {

    private Integer id;
    private Integer idSolicitudMuestraMedica;
    private PresentacionMuestra idPresentacionMuestra;
    private TipoMuestra idTipoMuestra;
    private UnidadMedida idUnidadMedida;
    private Date fechaRecepcionMuestra;
    private Date fechaCreacionMuestra;
    private String observacionExpediente;
}