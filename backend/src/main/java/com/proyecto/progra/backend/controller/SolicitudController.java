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
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class SolicitudController {
    @Autowired
    private IUsuario usuarioService;
    @PostMapping("usuario")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody UsuarioDto usuarioDto){
        Usuario usuarioSave = null;
        try{
            usuarioSave = usuarioService.save(usuarioDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .object(UsuarioDto.builder()
                            .id(usuarioSave.getId())
                            .idTipoUsuario(usuarioSave.getIdTipoUsuario())
                            .idRol(usuarioSave.getIdRol())
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
                                        .id(usuarioUpdate.getId())
                                        .idTipoUsuario(usuarioUpdate.getIdTipoUsuario())
                                        .idRol(usuarioUpdate.getIdRol())
                                        .nit(usuarioUpdate.getNit())
                                        .nombres(usuarioUpdate.getNombres())
                                        .apellidos(usuarioUpdate.getApellidos())
                                        .email(usuarioUpdate.getEmail())
                                        .genero(usuarioUpdate.getGenero())
                                        .telefono(usuarioUpdate.getTelefono())
                                        .password(usuarioUpdate.getPassword())
                                        .direccion(usuarioUpdate.getDireccion())
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
                                .password(usuario.getPassword())
                                .build())
                        .build()
                        ,HttpStatus.OK);
    }
    @GetMapping("usuarios")
    public ResponseEntity<?> findAll() {
        try {
            List<Usuario> usuarios = usuarioService.findAll();
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
                            .password(usuario.getPassword())
                            .direccion(usuario.getDireccion())
                            .build())
                    .collect(Collectors.toList());

            return new ResponseEntity<>(usuariosDto, HttpStatus.OK);
        } catch (DataAccessException exDt) {
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
