package com.proyecto.progra.backend.model.dto;

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
public class TipoItemsDto implements Serializable {

    private Integer id;
    private String descripcion;


}
