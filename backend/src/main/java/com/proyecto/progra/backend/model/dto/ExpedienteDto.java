package com.proyecto.progra.backend.model.dto;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class ExpedienteDto {

    private String nitCliente;
    private String direccion;
}
