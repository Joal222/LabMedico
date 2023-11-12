package com.proyecto.progra.backend.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "muestra_medica")
public class Muestra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "id_solicitud_muestra_medica", referencedColumnName = "id")
    private Solicitud idSolicitudMuestraMedica;

    @Column(name = "id_presentacion_muestra")
    private Integer idPresentacionMuestra;

    @Column(name = "id_tipo_muestra")
    private Integer idTipoMuestra;

    @Column(name = "id_unidad_medida")
    private Integer idUnidadMedida;

    @Column(name = "fecha_recepcion_muestra")
    private Date fechaRecepcionMuestra;

    @Column(name = "fecha_creacion_muestra")
    private Date fechaCreacionMuestra;

    @Column(name = "observacion_expediente", length = 2000)
    private String observacionExpediente;

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "muestra_items",
            joinColumns = @JoinColumn(
                    name = "id_muestra_medica",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "id_items",
                    referencedColumnName = "id"
            )
    )
    private List<MuestraItems> muestraItemsList;
}