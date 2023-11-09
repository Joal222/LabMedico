package com.proyecto.progra.backend.model.dto;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class TipoExamenDto implements Serializable {

    private String descripcion;
}
