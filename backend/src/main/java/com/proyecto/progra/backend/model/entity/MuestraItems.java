package com.proyecto.progra.backend.model.entity;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name ="muestra_items")
public class MuestraItems implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_muestra_medica")
    private Integer idMuestraMedica;

    @Column(name = "id_items")
    private Integer idItems;


}
