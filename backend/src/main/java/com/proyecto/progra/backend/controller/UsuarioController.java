package com.proyecto.progra.backend.controller;
import com.proyecto.progra.backend.model.dto.UsuarioDto;
import com.proyecto.progra.backend.model.entity.Usuario;
import com.proyecto.progra.backend.model.payload.MensajeResponse;
import com.proyecto.progra.backend.service.IUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class UsuarioController {

    //Primero debemos de llamar a nuestro servicio según la lógica de negocio.
    //debemos de llamar a nuestra Interfaz
    @Autowired
    private IUsuario usuarioService;

    //Importantisimo! @RequestBody, aquí indicamos que cuando me envien a través de JSON la información, va a ser transformada a Usuario
    //Para identificar el status de Respueta "ej:200 OK" se utiliza la notación @ResponseStatus() y se inidica el número que se requiere según sea el tipo
    // de método, ejemplo create sería o corresponde (HttpStatus.CREATED), para update se utiliza siempre el mismo que el de CREATE
    //A partir de creación de paquete dto, clase UsuarioDto, se cambió la entidad de donde recibirá los datos, siendo de paquete entity clase Usuario a
    //paquete dto clase Usuario Dto para controlar los datos que quermos mostrar.
    @PostMapping("usuario")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody UsuarioDto usuarioDto){
        Usuario usuarioSave = null;
        try{
            usuarioSave = usuarioService.save(usuarioDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .object(UsuarioDto.builder()
                            .nit(usuarioSave.getNit())
                            .nombres(usuarioSave.getNombres())
                            .apellidos(usuarioSave.getApellidos())
                            .email(usuarioSave.getEmail())
                            .genero(usuarioSave.getGenero())
                            .telefono(usuarioSave.getTelefono())
                            .direccion(usuarioSave.getDireccion())
                            .password(usuarioSave.getPassword())
                    .build())
                    .build()
                    ,HttpStatus.CREATED);
        }catch (DataAccessException exDt){
            return new ResponseEntity<>
                    (MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build(),HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping ("usuario/{id}")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> update(@RequestBody UsuarioDto usuarioDto, @PathVariable Integer id) {
        Usuario usuarioUpdate = null ;
        try{
            if(usuarioService.existById(id)){
                usuarioDto.setId(id);
                usuarioUpdate = usuarioService.save(usuarioDto);
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("Guardado correctamente")
                                .object(UsuarioDto.builder()
                                        .idTipoUsuario(usuarioUpdate.getIdTipoUsuario())
                                        .idRol(usuarioUpdate.getIdRol())
                                        .nit(usuarioUpdate.getNit())
                                        .nombres(usuarioUpdate.getNombres())
                                        .apellidos(usuarioUpdate.getApellidos())
                                        .email(usuarioUpdate.getEmail())
                                        .genero(usuarioUpdate.getGenero())
                                        .telefono(usuarioUpdate.getTelefono())
                                        .direccion(usuarioUpdate.getDireccion())
                                        .password(usuarioUpdate.getPassword())
                                        .build())
                                .build()
                        ,HttpStatus.CREATED);
            }else {
                return new ResponseEntity<>
                        (MensajeResponse.builder()
                                .mensaje("El registro que intenta actualizar no se encuentra en la base de datos.")
                                .object(null)
                                .build(),HttpStatus.NOT_FOUND);
            }

        }catch (DataAccessException exDt){
            return new ResponseEntity<>
                    (MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build(),HttpStatus.METHOD_NOT_ALLOWED);
        }
    }


    //En los parámetros recibe el Id y no va a retornar ningún valor, hay que indicarle que nos envíe el cliente completo, no solo el Id
    //La notación @PathVariable indica que el id se va a enviar desde nuestra URL para y eso se identifica en el @DeleteMapping("usuario") agregandoles /{id}
    //Estudiar estructura DTO
    @DeleteMapping ("usuario/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try{
            Usuario usuarioDelete = usuarioService.findById(id);
            usuarioService.delete(usuarioDelete);
            return new ResponseEntity<>(usuarioDelete,HttpStatus.NO_CONTENT);
        }catch (DataAccessException exDt){
            return new ResponseEntity<>
                    (MensajeResponse.builder()
                    .mensaje(exDt.getMessage())
                    .object(null)
                    .build(),HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    //Para identificar el status de Respueta "ej:200 OK" se utiliza la notación @ResponseStatus() y se inidica el número que se requiere según sea el tipo
    // de método, ejemplo showById que es un método para consultar sería o corresponde (HttpStatus.OK) con número #200
    @GetMapping("usuario/{id}")
    //@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        Usuario usuario = usuarioService.findById(id);
        if(usuario==null){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("El registro que intenta buscar, no existe!!")
                            .object(null)
                            .build()
                    ,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("Consulta Exitosa")
                        .object(UsuarioDto.builder()
                                .id(usuario.getId())
                                .idTipoUsuario(usuario.getIdTipoUsuario())
                                .nit(usuario.getNit())
                                .idRol(usuario.getIdRol())
                                .nombres(usuario.getNombres())
                                .apellidos(usuario.getApellidos())
                                .email(usuario.getEmail())
                                .genero(usuario.getGenero())
                                .telefono(usuario.getTelefono())
                                .direccion(usuario.getDireccion())
                                .password("********")
                                .solicitudList(usuario.getSolicitudes())
                                .build())
                        .build()
                        ,HttpStatus.OK);
    }

    @GetMapping("usuarios")
    public ResponseEntity<?> findAll() {
        try {
            List<Usuario> usuarios = usuarioService.findAll();

            // Convierte la lista de entidades Usuario a lista de DTO UsuarioDto
            List<UsuarioDto> usuariosDto = usuarios.stream()
                    .map(usuario -> UsuarioDto.builder()
                            .id(usuario.getId())
                            .idTipoUsuario(usuario.getIdTipoUsuario())
                            .idRol(usuario.getIdRol())
                            .nit(usuario.getNit())
                            .nombres(usuario.getNombres())
                            .apellidos(usuario.getApellidos())
                            .email(usuario.getEmail())
                            .genero(usuario.getGenero())
                            .telefono(usuario.getTelefono())
                            .direccion(usuario.getDireccion())
                            .password("********")
                            .solicitudList(usuario.getSolicitudes())
                            .build())
                    .collect(Collectors.toList());

            // Retorna la lista de DTOs en una respuesta OK
            return new ResponseEntity<>(usuariosDto, HttpStatus.OK);
        } catch (DataAccessException exDt) {
            // Si hay una excepción, devuelve un mensaje de error con un código 500 (Internal Server Error)
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Error al obtener la lista de usuarios: " + exDt.getMessage())
                            .object(null)
                            .build(),
                    HttpStatus.NOT_FOUND
            );
        }
    }
}


//Respuesta métodos Http estáticos
    /*
    @DeleteMapping ("usuario/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        Usuario usuarioDelete = usuarioService.findById(id);
        usuarioService.delete(usuarioDelete);
    }
    */