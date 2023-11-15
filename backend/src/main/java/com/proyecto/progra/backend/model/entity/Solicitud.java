package com.proyecto.progra.backend.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "solicitud_muestra_medica")
public class Solicitud{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario idUsuario;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_solicitante", referencedColumnName = "id",columnDefinition = "integer default 1")
    private TipoSolicitante idTipoSolicitante;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_solicitud",referencedColumnName = "id")
    private TipoSolicitud idTipoSolicitud;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_soporte", referencedColumnName = "id")
    private TipoSoporte idTipoSoporte;

    @Column(name = "descripcion_solicitud_muestra_medica", length = 2000)
    private String descripcionSolicitudMuestraMedica;

    @Column(name = "fecha_creacion_solicitud")
    private Date fechaCreacionSolicitud;

    @Column(name = "dias_vencimiento_solicitud")
    private Integer diasVencimientoSolicitud;
    /*
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_solicitud_muestra_medica", referencedColumnName = "id")
    List<Muestra> muestraList = new ArrayList<>();
     */
    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "items",
            joinColumns = @JoinColumn(
                    name = "id_solicitud_muestra_medica",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "id_tipo_items",
                    referencedColumnName = "id"
            )
    )
    private List<Items> itemsList;

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "bitacora_estado",
            joinColumns = @JoinColumn(
                    name = "id_solicitud_muestra_medica",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "id_tipo_estado_solicitud",
                    referencedColumnName = "id"
            )
    )
    private List<BitacoraEstado> bitacoraEstadoList;
}
