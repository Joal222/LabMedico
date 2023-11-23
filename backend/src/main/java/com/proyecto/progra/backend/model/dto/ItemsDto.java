package com.proyecto.progra.backend.model.dto;
import lombok.*;
import java.io.Serializable;

@Data
@ToString
@Builder
public class ItemsDto implements Serializable {

    private Integer id;
    private Integer idTipoItems;
    private Integer idSolicitudMuestraMedica;
    private Integer muestraItemsList;


}
