package com.proyecto.progra.backend.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Entity
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@ToString
@Table(name ="tipo_examen")
public class TipoExamen implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @Column(name = "creado_por")
    private String creadoPor;

    @Column(name = "modificado_por")
    private String modificadoPor;


    /*name="id_tipo_examen" es el nombre de la fkey en la tabla hija en nuestra db, referencedColumnName = "id" es el nombre de nuestra variable dentro de la tabla padre*/
    /*fecht = FetchTipye. EAGER indica a nuestra relación que va a obtener o mostrar como lista a nuestra tabla hija o padre*/
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="id_tipo_examen", referencedColumnName = "id")
    private List<TipoItems> tipoItemsList;

}
