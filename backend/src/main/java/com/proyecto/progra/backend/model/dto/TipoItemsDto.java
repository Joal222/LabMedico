package com.proyecto.progra.backend.model.dto;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class TipoItemsDto implements Serializable {

    private Integer id;
    private String nombre;
    private String descripcion;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private String creadoPor;
    private String modificadoPor;


}