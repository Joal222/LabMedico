package com.proyecto.progra.backend.controller;

import com.proyecto.progra.backend.model.dto.UsuarioExternalDto;
import com.proyecto.progra.backend.model.dto.UsuarioGetDto;
import com.proyecto.progra.backend.model.entity.Usuario;
import com.proyecto.progra.backend.model.payload.MensajeResponse;
import com.proyecto.progra.backend.projections.closed.IUsuarioClosedView;
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

    @Autowired
    private IUsuario usuarioService;
    @PostMapping("usuario/external")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createExternal(@RequestBody UsuarioExternalDto usuarioExternalDto) {
        Usuario usuario = new Usuario();

        usuario.setCui(usuarioExternalDto.getCui());
        usuario.setIdTipoUsuario(2);
        usuario.setNit(usuarioExternalDto.getNit());
        usuario.setNombres(usuarioExternalDto.getNombres());
        usuario.setApellidos(usuarioExternalDto.getApellidos());
        usuario.setEmail(usuarioExternalDto.getEmail());
        usuario.setGenero(usuarioExternalDto.getGenero());
        usuario.setTelefono(usuarioExternalDto.getTelefono());
        usuario.setDireccion(usuarioExternalDto.getDireccion());
        usuario.setPassword(usuarioExternalDto.getPassword());

        Usuario usuarioResponse = usuarioService.save(usuario);

        return ResponseEntity.ok(usuarioResponse);
    }

    /*
    @PutMapping ("usuario/{id}")
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
     */
    @DeleteMapping ("usuario/delete/{id}")
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
                        .object(UsuarioGetDto.builder()
                                .id(usuario.getId())
                                .cui(usuario.getCui())
                                .idTipoUsuario(usuario.getIdTipoUsuario())
                                .nit(usuario.getNit())
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
    @GetMapping("usuarios/all")
    public ResponseEntity<?> findAll() {
        try {
            List<Usuario> usuarios = usuarioService.findAll();

            List<UsuarioGetDto> usuariosGetDto = usuarios.stream()
                    .map(usuario -> UsuarioGetDto.builder()
                            .id(usuario.getId())
                            .cui(usuario.getCui())
                            .idTipoUsuario(usuario.getIdTipoUsuario())
                            .nit(usuario.getNit())
                            .nombres(usuario.getNombres())
                            .apellidos(usuario.getApellidos())
                            .email(usuario.getEmail())
                            .genero(usuario.getGenero())
                            .telefono(usuario.getTelefono())
                            .direccion(usuario.getDireccion())
                            .password(usuario.getPassword())
                            .build())
                    .collect(Collectors.toList());

            return new ResponseEntity<>(usuariosGetDto, HttpStatus.OK);
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

    @GetMapping("usuarios/allProjection")
    public List<IUsuarioClosedView> getUsuarioAll(){
        return usuarioService.getAllUsuarioProjection();
    }
}
