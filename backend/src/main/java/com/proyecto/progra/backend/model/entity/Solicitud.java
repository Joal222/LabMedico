package com.proyecto.progra.backend.model.entity;
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
public class Solicitud implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario idUsuario;

    @Column(name = "numero_soporte")
    private String numeroSoporte;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_solicitante", referencedColumnName = "id",columnDefinition = "integer default 1")
    private TipoSolicitante idTipoSolicitante;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_solicitud",referencedColumnName = "id")
    private TipoSolicitud idTipoSolicitud;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_soporte", referencedColumnName = "id")
    private TipoSoporte idTipoSoporte;

    @Column(name = "descripcion_solicitud_muestra_medica", length = 2000)
    private String descripcionSolicitudMuestraMedica;


    @Column(name = "dias_vencimiento_solicitud")
    private Integer diasVencimientoSolicitud;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_solicitud_muestra_medica", referencedColumnName = "id")
    List<Muestra> muestraList = new ArrayList<>();

    /*
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
     */

    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_solicitud_muestra_medica", referencedColumnName = "id")
    List<Items> itemsList = new ArrayList<>();


    @ManyToOne(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
    @JoinColumn(name = "id_tipo_estado_solicitud", referencedColumnName = "id")
    private TipoEstadoSolicitud tipoEstadoSolicitud;

    @Column(name = "fecha_creacion_solicitud")
    private Date fechaCreacionSolicitud;


    @PrePersist
    public void prePersist() {
        TipoEstadoSolicitud tipoEstadoSolicitud = new TipoEstadoSolicitud();
        tipoEstadoSolicitud.setId(1);
        this.tipoEstadoSolicitud = tipoEstadoSolicitud;
        this.fechaCreacionSolicitud = new Date();
    }
    /*
    @PreUpdate
    public void preUpdate() {
        this.fechaModificacion = new Date();
    }
    */


}
