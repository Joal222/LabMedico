package com.proyecto.progra.backend.model.entity;

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

    /*
    @OneToMany (mappedBy = "idTipoExamen",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TipoItems> tipoItemsList;
     */

}
