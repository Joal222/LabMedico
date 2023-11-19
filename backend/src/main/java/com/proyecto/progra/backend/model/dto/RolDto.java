package com.proyecto.progra.backend.model.dto;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
public class RolDto implements Serializable{

    private Integer id;

    private String descripcion;
}
