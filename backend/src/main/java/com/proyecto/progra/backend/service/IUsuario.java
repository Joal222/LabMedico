package com.proyecto.progra.backend.service;
//Importar clase entity.Usuario
import com.proyecto.progra.backend.model.entity.Usuario;

//Una interfaz es un contrato que tienen que seguir al pie de la letra las clases para poder realizar las operacioens CRUD
//Se implementan métodos CRUD, estos pueden ser consultados en las declaraciones de CrudRepository
public interface IUsuario {
    //Guardar y actualizar
    Usuario save (Usuario usuario);
    //Consultar
    Usuario findById(Integer id);
    //Eliminar
    void delete(Usuario usuario);
}
