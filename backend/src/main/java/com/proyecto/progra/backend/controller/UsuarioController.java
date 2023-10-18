package com.proyecto.progra.backend.controller;

import ch.qos.logback.core.net.server.Client;
import com.proyecto.progra.backend.model.entity.Usuario;
import com.proyecto.progra.backend.service.IUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

//La anotación @RestController se aplica a una clase para marcarla como controlador de solicitudes. La anotación se usa para crear servicios web Restful usando Spring MVC
@RestController
//La notación RequestMappingse utiliza para asignar solicitudes web a las clases del controlador específica y/o métodos de controlador
//Dentro del paréntesis colocamos api para indicar que es una api y luego colocamos la version con v1
@RequestMapping("/api/v1")
public class UsuarioController {
    //Primero debemos de llamar a nuestro servicio según la lógica de negocio.
    //debemos de llamar a nuestra Interfaz
    @Autowired
    private IUsuario usuarioService;

    //Para realizar acciones debemos utilizar los métodos HTTP
    //El sustantivo usuario queda asociado a nuestro recurso @PostMappeing
    //Importantisimo! @RequestBody, aquí indicamos que cuando me envien a través de JSON la información, va a ser transformada a Usuario
    @PostMapping("usuario")
    //Para identificar el status de Respueta "ej:200 OK" se utiliza la notación @ResponseStatus() y se inidica el número que se requiere según sea el tipo
    // de método, ejemplo create sería o corresponde (HttpStatus.CREATED), para update se utiliza siempre el mismo que el de CREATE
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario create(@RequestBody Usuario usuario){
        return usuarioService.save(usuario);
    }
    @PutMapping ("usuario")
    //Para identificar el status de Respueta "ej:200 OK" se utiliza la notación @ResponseStatus() y se inidica el número que se requiere según sea el tipo
    // de método, ejemplo create sería o corresponde (HttpStatus.CREATED), para update se utiliza siempre el mismo que el de CREATE
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario update(@RequestBody Usuario usuario){
        return usuarioService.save(usuario);
    }
    //En los parámetros recibe el Id y no va a retornar ningún valor, hay que indicarle que nos envíe el cliente completo, no solo el Id
    //La notación @PathVariable indica que el id se va a enviar desde nuestra URL para y eso se identifica en el @DeleteMapping("usuario") agregandoles /{id}
    @DeleteMapping ("usuario/{id}")
    //Para identificar el status de Respueta "ej:204 OK" se utiliza la notación @ResponseStatus() y se inidica el número que se requiere según sea el tipo
    // de método, ejemplo delete sería o corresponde (HttpStatus.NO_CONTENT) con número #204
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        Usuario usuarioDelete = usuarioService.findById(id);
        usuarioService.delete(usuarioDelete);
    }
    //La notación @PathVariable indica que el id se va a enviar desde nuestra URL para y eso se identifica en el @GetMapping("usuario") agregandoles /{id}
    @GetMapping("usuario/{id}")
    //Para identificar el status de Respueta "ej:200 OK" se utiliza la notación @ResponseStatus() y se inidica el número que se requiere según sea el tipo
    // de método, ejemplo showById que es un método para consultar sería o corresponde (HttpStatus.OK) con número #200
    @ResponseStatus(HttpStatus.OK)
    public Usuario showById(@PathVariable Integer id) {
        return usuarioService.findById(id);
    }
}
