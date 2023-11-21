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
@Table(name ="tipo_items")
public class TipoItems implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_Examen", referencedColumnName = "id")/*id viene de la clase padre, nombre de nuestra variable*/
    private TipoExamen idTipoExamen;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @JsonIgnore
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @JsonIgnore
    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @JsonIgnore
    @Column(name = "creado_por")
    private String creadoPor;

    @JsonIgnore
    @Column(name = "modificado_por")
    private String modificadoPor;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_items", referencedColumnName = "id")
    List<Items> itemsList = new ArrayList<>();

}
