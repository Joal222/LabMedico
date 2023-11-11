package com.proyecto.progra.backend.model.dto;
import com.proyecto.progra.backend.model.entity.TipoItems;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@Builder
public class TipoExamenDto implements Serializable {

    private Integer id;
    private String descripcion;
    /*
    private List<TipoItems> itemsList;
    */
}
