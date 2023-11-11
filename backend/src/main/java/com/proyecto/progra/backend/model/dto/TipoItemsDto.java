package com.proyecto.progra.backend.model.dto;

import com.proyecto.progra.backend.model.entity.TipoExamen;
import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
public class TipoItemsDto implements Serializable {

    private Integer id;
    private String descripcion;
    private TipoExamen tipoExamen;

}
