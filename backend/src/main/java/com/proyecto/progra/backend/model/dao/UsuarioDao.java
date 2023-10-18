package com.proyecto.progra.backend.model.dao;

import com.proyecto.progra.backend.model.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

//Se utiliza la herencia CrudRepository para tener acceso a los métodos crud y otros más
//Los parámetros son la clase "Usuario" y el tipo de dato de nuestra clase que es "Integer"
public interface UsuarioDao extends CrudRepository<Usuario, Integer> {
}
