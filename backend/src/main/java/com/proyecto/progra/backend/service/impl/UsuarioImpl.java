package com.proyecto.progra.backend.service.impl;
//Importamos la clase dao.UsuarioDao
import com.proyecto.progra.backend.model.dao.UsuarioDao;
//Importamos la clase entity.Usuario
import com.proyecto.progra.backend.model.entity.Usuario;
//Importamos la service.IUsuario
import com.proyecto.progra.backend.service.IUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Debemos llamar a nuestro cliente DAO o el repositoryo que contiene los métodos CRUD
//Primero debemos implementar nuestra clase de tipo interfaz IUsuario para poder utilizar los métodos que elegimos
//Debemos definir con la notación @Service que esta clas será un servicio, la cual se usa para construir una clas de Servicio que habitualmente se conecta a varios
//repositorios y agrupa su funcionalidad
@Service
public class UsuarioImpl implements IUsuario {
    //Aquí vamos a hacer el match entre dao y entity
    //Agregar notación @Autowired que nos proporciona control a la hora de querer inyectar nuestras dependencias o instancias que se almacenan
    @Autowired
    private UsuarioDao usuarioDao;

    // Todo este servicio trabaja como transacciones, por tal motivo debemos utilziar la notación @Transactional de Spring Framework, esta característica da soporte
    //a la transaccionalidad, de lo contrario, deberiamos inicializar la transacción con begin() y cerrarla con commit() o rollback()
    @Transactional
    @Override
    public Usuario save(Usuario usuario) {
        //en el return debemos de llamar el método de la clase que queremos ejecutar de nuestro dao/repository, ejemplo clase.método(modelo/entidad)
        return usuarioDao.save(usuario);
    }

    //Cuando sean consultas o recuperación debemos de utilizar la notación @Transactional(read Only=true) de spring framework, para asegurarnos de que solo podemos
    //realizar operaciones de solo lectura
    @Transactional(readOnly = true)
    @Override
    public Usuario findById(Integer id) {
        //en el return debemos de llamar el método de la clase que queremos ejecutar de nuestro dao/repository, ejemplo clase.método(modelo/entidad)
        //Debemos de especificar si un objeto no existe entonces debemos de retornarlo como nulo, y esto es posible gracias a
        //gracias a CrudRepository JP, ya que nos da un pequeño método por defecto siento este .orElse(null)
        return usuarioDao.findById(id).orElse(null);
    }

    // Todo este servicio trabaja como transacciones, por tal motivo debemos utilziar la notación @Transactional de Spring Framework, esta característica da soporte
    //a la transaccionalidad, de lo contrario, deberiamos inicializar la transacción con begin() y cerrarla con commit() o rollback()
    @Transactional
    @Override
    public void delete(Usuario usuario) {
        //en el retur debemos de llamar el método de la clase que queremos ejecutar de nuestro dao/repository, ejemplo clase.método(modelo/entidad)
        usuarioDao.delete(usuario);
    }
}
