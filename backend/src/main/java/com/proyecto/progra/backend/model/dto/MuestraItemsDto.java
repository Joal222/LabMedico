package com.proyecto.progra.backend.model.dto;
import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
public class MuestraItemsDto implements Serializable {


    private Integer id;
    private Integer idMuestraMedica;
    private Integer idItems;


}
