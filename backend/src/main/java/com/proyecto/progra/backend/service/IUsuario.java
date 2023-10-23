package com.proyecto.progra.backend.service;
//Importar clase entity.Usuario
import com.proyecto.progra.backend.model.dto.UsuarioDto;
import com.proyecto.progra.backend.model.entity.Usuario;

import java.util.List;

//Una interfaz es un contrato que tienen que seguir al pie de la letra las clases para poder realizar las operacioens CRUD
//Se implementan métodos CRUD, estos pueden ser consultados en las declaraciones de CrudRepository
//A partir de creación de paquete dto, clase UsuarioDto, se cambió la entidad de donde recibirá los datos, siendo de paquete entity clase Usuario a
//paquete dto clase Usuario Dto para controlar los datos que quermos mostrar.
public interface IUsuario {
    //Guardar y actualizar
    Usuario save (UsuarioDto usuario);
    //Consultar
    Usuario findById(Integer id);
    //Eliminar
    void delete(Usuario usuario);
    //Consultar todos;
    List<Usuario> findAll();
    //Listar tambien se puede realizar de manera cruda
    //public List<Usuario> listAll{return (list) usuarioDao.findAll();}

    boolean existById(Integer id);

}
