package com.proyecto.progra.backend.model.dto;

import com.proyecto.progra.backend.model.entity.MuestraItems;
import com.proyecto.progra.backend.model.entity.PresentacionMuestra;
import com.proyecto.progra.backend.model.entity.TipoMuestra;
import com.proyecto.progra.backend.model.entity.UnidadMedida;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ToString
@Builder
public class MuestraCreatedDto implements Serializable {

    private Integer idSolicitudMuestraMedica;
    private Integer idPresentacionMuestra;
    private Integer idTipoMuestra;
    private Integer idUnidadMedida;
    private String observacionExpediente;
    private List<MuestraItemsDto> muestraItemsList;

}