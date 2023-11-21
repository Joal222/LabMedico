package com.proyecto.progra.backend.controller;

import com.proyecto.progra.backend.model.dto.SolicitudCreatedDto;
import com.proyecto.progra.backend.model.dto.SolicitudCreatedIntDto;
import com.proyecto.progra.backend.model.dto.SolicitudDto;
import com.proyecto.progra.backend.model.dto.UsuarioGetDto;
import com.proyecto.progra.backend.model.entity.Items;
import com.proyecto.progra.backend.model.entity.Solicitud;
import com.proyecto.progra.backend.model.entity.Usuario;
import com.proyecto.progra.backend.model.payload.MensajeResponse;
import com.proyecto.progra.backend.projections.closed.ISolicitudClosedView;
import com.proyecto.progra.backend.service.*;
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
public class SolicitudController {

    @Autowired
    private ISolicitud solicitudService;

    @Autowired
    private IUsuario usuarioService;

    @Autowired
    private ITipoSolicitud tipoSolicitudService;

    @Autowired
    private ITipoSoporte tipoSoporteService;

    @Autowired
    private ITipoSolicitante tipoSolicitanteService;

    @Autowired
    private ITipoItems tipoItemsService;

    @Autowired
    private IItems itemsService;

    @Autowired
    private ITipoEstado tipoEstadoService;

    @Autowired
    private IMuestra muestraService;

    @Autowired
    private IMuestraItems muestraItemsService;

    @PostMapping("solicitud/external")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createExternal(@RequestBody SolicitudCreatedDto solicitudCreatedDto) {
        Solicitud solicitud = new Solicitud();

        solicitud.setIdUsuario(usuarioService.findById(solicitudCreatedDto.getIdUsuario()));
        solicitud.setNumeroSoporte(solicitudCreatedDto.getNumeroSoporte());
        solicitud.setIdTipoSolicitante(tipoSolicitanteService.findById(2));
        solicitud.setIdTipoSolicitud(tipoSolicitudService.findById(solicitudCreatedDto.getIdTipoSolicitud()));
        solicitud.setIdTipoSoporte(tipoSoporteService.findById(solicitudCreatedDto.getIdTipoSolicitud()));
        solicitud.setDescripcionSolicitudMuestraMedica(solicitudCreatedDto.getDescripcionSolicitudMuestraMedica());
        solicitud.setDiasVencimientoSolicitud("SIN ESPECIFICAR");

        Solicitud solicitudResponse = solicitudService.save(solicitud);

            solicitudCreatedDto.getItemsList().forEach(itemsDto -> {
                Items items = new Items();
                items.setIdTipoItems(tipoItemsService.findById(itemsDto.getIdTipoItems()));
                items.setIdSolicitudMuestraMedica(solicitudResponse);
                itemsService.save(items);
            });
        return  ResponseEntity.ok(solicitudResponse);
    }

    @PostMapping("solicitud/internal")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createInternal(@RequestBody SolicitudCreatedIntDto solicitudCreatedIntDto) {
        Solicitud solicitud = new Solicitud();

        solicitud.setIdUsuario(usuarioService.findByCui(solicitudCreatedIntDto.getCui()));

        solicitud.setNumeroSoporte(solicitudCreatedIntDto.getNumeroSoporte());
        solicitud.setIdTipoSolicitante(tipoSolicitanteService.findById(1));
        solicitud.setIdTipoSolicitud(tipoSolicitudService.findById(solicitudCreatedIntDto.getIdTipoSolicitud()));
        solicitud.setIdTipoSoporte(tipoSoporteService.findById(solicitudCreatedIntDto.getIdTipoSolicitud()));
        solicitud.setDescripcionSolicitudMuestraMedica(solicitudCreatedIntDto.getDescripcionSolicitudMuestraMedica());


        Solicitud solicitudResponse = solicitudService.save(solicitud);

        solicitudCreatedIntDto.getItemsList().forEach(itemsDto -> {
            Items items = new Items();
            items.setIdTipoItems(tipoItemsService.findById(itemsDto.getIdTipoItems()));
            items.setIdSolicitudMuestraMedica(solicitudResponse);
            itemsService.save(items);
        });
        return  ResponseEntity.ok(solicitudResponse);
    }

    @DeleteMapping("solicitud/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            Solicitud solicitudDelete = solicitudService.findById(id);
            solicitudService.delete(solicitudDelete);

            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Solicitud eliminada con éxito")
                            .object(solicitudDelete)
                            .build(),
                    HttpStatus.NO_CONTENT
            );
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build(),
                    HttpStatus.METHOD_NOT_ALLOWED
            );
        }
    }

    @GetMapping("solicitudes")
    public ResponseEntity<List<Solicitud>> finAll() {
        List<Solicitud> solicitudes = solicitudService.findAll();
        return ResponseEntity.ok(solicitudes);
    }


    @GetMapping("solicitud/{id}")
    public ResponseEntity<Solicitud> showById(@PathVariable Integer id) {
        Solicitud solicitud = solicitudService.findById(id);
        return ResponseEntity.ok(solicitud);

    }

    @GetMapping("solicitudes/all")
    public List<ISolicitudClosedView> allSolicitudesUsuarios() {
        return solicitudService.getSolicitudProjectionAll();
    }

    /*
    @PutMapping("solicitud/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> update(@RequestBody SolicitudCreatedDto solicitudCreatedDto) {
        solicitudService.update(solicitudCreatedDto,id);
        return  ResponseEntity.ok("Solicitud actualizada!");
    }
    */

    /*
    @GetMapping("solicitudes")
    public ResponseEntity<?> findAllExternalSolicitudes() {
        try {
            List<Solicitud> solicitudes = solicitudService.findAll();
            List<SolicitudDto> solicitudesDto = solicitudes.stream()
                    .map(solicitud -> {
                        Usuario usuario = solicitud.getIdUsuario();
                        UsuarioGetDto usuarioDto = UsuarioGetDto.builder()
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
                                .build();

                        return SolicitudDto.builder()
                                .id(solicitud.getId())
                                .idUsuario(usuarioDto)
                                .numeroSoporte(solicitud.getNumeroSoporte())
                                .idTipoSolicitante(solicitud.getIdTipoSolicitante())
                                .idTipoSolicitud(solicitud.getIdTipoSolicitud())
                                .idTipoSoporte(solicitud.getIdTipoSoporte())
                                .muestraList(solicitud.getMuestraList())
                                .descripcionSolicitudMuestraMedica(solicitud.getDescripcionSolicitudMuestraMedica())
                                .fechaCreacionSolicitud(solicitud.getFechaCreacionSolicitud())
                                .diasVencimientoSolicitud(solicitud.getDiasVencimientoSolicitud())
                                .itemsList(solicitud.getItemsList())
                                .tipoEstadoSolicitud(solicitud.getTipoEstadoSolicitud())
                                .build();
                    })
                    .collect(Collectors.toList());

            return new ResponseEntity<>(solicitudesDto, HttpStatus.OK);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Error al obtener la lista de solicitudes: " + exDt.getMessage())
                            .object(null)
                            .build(),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping("solicitud/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        try {
            Solicitud solicitud = solicitudService.findById(id);

            if (solicitud != null) {
                Usuario usuario = solicitud.getIdUsuario();
                UsuarioGetDto usuarioDto = UsuarioGetDto.builder()
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
                        .build();

                SolicitudDto solicitudDto = SolicitudDto.builder()
                        .id(solicitud.getId())
                        .idUsuario(usuarioDto)
                        .numeroSoporte(solicitud.getNumeroSoporte())
                        .idTipoSolicitante(solicitud.getIdTipoSolicitante())
                        .idTipoSolicitud(solicitud.getIdTipoSolicitud())
                        .idTipoSoporte(solicitud.getIdTipoSoporte())
                        .muestraList(solicitud.getMuestraList())
                        .descripcionSolicitudMuestraMedica(solicitud.getDescripcionSolicitudMuestraMedica())
                        .fechaCreacionSolicitud(solicitud.getFechaCreacionSolicitud())
                        .diasVencimientoSolicitud(solicitud.getDiasVencimientoSolicitud())
                        .itemsList(solicitud.getItemsList())
                        .tipoEstadoSolicitud(solicitud.getTipoEstadoSolicitud())
                        .build();

                return new ResponseEntity<>(solicitudDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("No se encontró la solicitud con ID: " + id)
                                .object(null)
                                .build(),
                        HttpStatus.NOT_FOUND
                );
            }
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Error al obtener la solicitud: " + exDt.getMessage())
                            .object(null)
                            .build(),
                    HttpStatus.NOT_FOUND
            );
        }
    }
     */
}