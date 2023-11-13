package com.proyecto.progra.backend.model.entity;
//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import jakarta.persistence.*;
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
@Table(name ="rol")
public class Rol implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rol", referencedColumnName = "id")
    private List<Usuario> usuarios = new ArrayList<>();

    /*
    @PrePersist
    public void prePersist() {
        this.fechaCreacion = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.fechaModificacion = new Date();
    }
    */
}
