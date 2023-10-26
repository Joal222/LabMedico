package com.proyecto.progra.backend.controller;

import com.proyecto.progra.backend.model.dto.SolicitudDto;
import com.proyecto.progra.backend.model.entity.Solicitud;
import com.proyecto.progra.backend.model.payload.MensajeResponse;
import com.proyecto.progra.backend.service.ISolicitud;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2")
@CrossOrigin(origins = "*")
public class SolicitudController {

    @Autowired
    private ISolicitud solicitudService;
    @PostMapping("solicitud")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody SolicitudDto solicitudDto){
        Solicitud solicitudSave = null;
        try {
            solicitudSave = solicitudService.save(solicitudDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .object(SolicitudDto.builder()
                            .id(solicitudSave.getId())
                            .idUsuario(solicitudSave.getIdUsuario())
                            .idTipoSolicitante(solicitudSave.getIdTipoSolicitante())
                            .idTipoSolicitud(solicitudSave.getIdTipoSolicitud())
                            .idTipoSoporte(solicitudSave.getIdTipoSoporte())
                            .descripcionSolicitudMuestraMedica(solicitudSave.getDescripcionSolicitudMuestraMedica())
                            .fechaCreacionSolicitud(solicitudSave.getFechaCreacionSolicitud())
                            .diasVencimientoSolicitud(solicitudSave.getDiasVencimientoSolicitud())

                    .build())
                    ,HttpStatus.CREATED);
        }catch (DataAccessException exDt){
            return new ResponseEntity<>
                    (MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build(),HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping ("solicitud/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> update(@RequestBody SolicitudDto solicitudDto, @PathVariable Integer id){
        Solicitud solicitudUpdate = null;
        try {
        if(solicitudService.existById(id)){
            solicitudDto.setId(id);
            solicitudUpdate = solicitudService.save(solicitudDto);
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .object(SolicitudDto.builder()
                            .id(solicitudUpdate.getId())
                            .idUsuario(solicitudUpdate.getIdUsuario())
                            .idTipoSolicitante(solicitudUpdate.getIdTipoSolicitante())
                            .idTipoSolicitud(solicitudUpdate.getIdTipoSolicitud())
                            .idTipoSoporte(solicitudUpdate.getIdTipoSoporte())
                            .descripcionSolicitudMuestraMedica(solicitudUpdate.getDescripcionSolicitudMuestraMedica())
                            .fechaCreacionSolicitud(solicitudUpdate.getFechaCreacionSolicitud())
                            .diasVencimientoSolicitud(solicitudUpdate.getDiasVencimientoSolicitud())
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

    @DeleteMapping ("solicitud/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try{
            Solicitud solicitudDelte  = solicitudService.findById(id);
            solicitudService.delete(solicitudDelte);
            return new ResponseEntity<>(solicitudDelte,HttpStatus.NO_CONTENT);
        }catch (DataAccessException exDt){
            return new ResponseEntity<>
                    (MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build(),HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping ("solicitud/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id){
        Solicitud solicitud = solicitudService.findById(id);
        if(solicitud==null){
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
                        .object(SolicitudDto.builder()
                                .id(solicitud.getId())
                                .idUsuario(solicitud.getIdUsuario())
                                .idTipoSolicitante(solicitud.getIdTipoSolicitante())
                                .idTipoSolicitud(solicitud.getIdTipoSolicitud())
                                .idTipoSoporte(solicitud.getIdTipoSoporte())
                                .descripcionSolicitudMuestraMedica(solicitud.getDescripcionSolicitudMuestraMedica())
                                .fechaCreacionSolicitud(solicitud.getFechaCreacionSolicitud())
                                .diasVencimientoSolicitud(solicitud.getDiasVencimientoSolicitud())
                                .build())
                        .build()
                ,HttpStatus.OK);
    }

    @GetMapping("solicitudes")
    public ResponseEntity<?> findAll() {
        try {
            List<Solicitud> solicitudes = solicitudService.findAll();
            List<SolicitudDto> solicitudesDto = solicitudes.stream()
                    .map(solicitud -> SolicitudDto.builder()
                                    .id(solicitud.getId())
                                    .idUsuario(solicitud.getIdUsuario())
                                    .idTipoSolicitante(solicitud.getIdTipoSolicitante())
                                    .idTipoSolicitud(solicitud.getIdTipoSolicitud())
                                    .idTipoSoporte(solicitud.getIdTipoSoporte())
                                    .descripcionSolicitudMuestraMedica(solicitud.getDescripcionSolicitudMuestraMedica())
                                    .fechaCreacionSolicitud(solicitud.getFechaCreacionSolicitud())
                                    .diasVencimientoSolicitud(solicitud.getDiasVencimientoSolicitud())
                                    .build())
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



}
