package com.proyecto.progra.backend.model.dao;
import com.proyecto.progra.backend.model.entity.Usuario;
import com.proyecto.progra.backend.projections.usuario.IUsuarioClosedView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//Se utiliza la herencia CrudRepository para tener acceso a los métodos crud y otros más
//Los parámetros son la clase "Usuario" y el tipo de dato de nuestra clase que es "Integer"

public interface UsuarioDao extends JpaRepository<Usuario, Integer> {
    List<IUsuarioClosedView> findAllProjectedBy();
}