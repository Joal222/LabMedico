package com.proyecto.progra.backend.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.cache.interceptor.CacheAspectSupport;

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

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "id_presentacion_muestra",referencedColumnName = "id")
    private PresentacionMuestra idPresentacionMuestra;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_muestra", referencedColumnName = "id")
    private TipoMuestra idTipoMuestra;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "id_unidad_medida",referencedColumnName = "id")
    private UnidadMedida idUnidadMedida;

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