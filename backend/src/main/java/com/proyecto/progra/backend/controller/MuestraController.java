package com.proyecto.progra.backend.controller;
import com.proyecto.progra.backend.model.dto.MuestraDto;
import com.proyecto.progra.backend.model.entity.Muestra;
import com.proyecto.progra.backend.model.payload.MensajeResponse;
import com.proyecto.progra.backend.service.IMuestra;
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
public class MuestraController {
    //Full realizado
    @Autowired
    private IMuestra muestraService;
    @PostMapping("muestra")
    public ResponseEntity<?> create(@RequestBody MuestraDto muestraDto){
        Muestra muestraSave = null;
        try{
            muestraSave=muestraService.save(muestraDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .object(MuestraDto.builder()
                            .id(muestraSave.getId())
                            .idPresentacionMuestra(muestraSave.getIdPresentacionMuestra())
                            .idTipoMuestra(muestraSave.getIdTipoMuestra())
                            .idUnidadMedida(muestraSave.getIdUnidadMedida())
                            .fechaRecepcionMuestra(muestraSave.getFechaRecepcionMuestra())
                            .fechaCreacionMuestra(muestraSave.getFechaCreacionMuestra())
                            .observacionExpediente(muestraSave.getObservacionExpediente())
                            .build())
                    .build()
                    , HttpStatus.CREATED);
        }catch (DataAccessException exDt){
            return new ResponseEntity<>
                    (MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build(),HttpStatus.METHOD_NOT_ALLOWED);
        }

    }

    @PutMapping("muestra/{id}")
    public ResponseEntity<?> update(@RequestBody MuestraDto muestraDto, @PathVariable Integer id) {
        Muestra muestraUpdate = null;
        try {
            if (muestraService.existById(id)) {
                muestraDto.setId(id);
                muestraUpdate = muestraService.save(muestraDto);
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("Guardado correctamente")
                                .object(MuestraDto.builder()
                                        .id(muestraUpdate.getId())
                                        .idPresentacionMuestra(muestraUpdate.getIdPresentacionMuestra())
                                        .idTipoMuestra(muestraUpdate.getIdTipoMuestra())
                                        .idUnidadMedida(muestraUpdate.getIdUnidadMedida())
                                        .fechaRecepcionMuestra(muestraUpdate.getFechaRecepcionMuestra())
                                        .fechaCreacionMuestra(muestraUpdate.getFechaCreacionMuestra())
                                        .observacionExpediente(muestraUpdate.getObservacionExpediente())
                                        .build())
                                .build()
                        , HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("El registro que intenta actualizar no se encuentra en la base de datos.")
                                .object(null)
                                .build(), HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }


    @DeleteMapping ("muestra/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try{
            Muestra muestraDelete = muestraService.findById(id);
            muestraService.delete(muestraDelete);
            return new ResponseEntity<>(muestraDelete,HttpStatus.NO_CONTENT);
        }catch (DataAccessException exDt){
            return new ResponseEntity<>
                    (MensajeResponse.builder()
                            .mensaje(exDt.getMessage())
                            .object(null)
                            .build(),HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("muestra/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        Muestra muestra = muestraService.findById(id);
        if(muestra==null){
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
                        .object(MuestraDto.builder()
                                .id(muestra.getId())
                                .idSolicitudMuestraMedica(muestra.getIdSolicitudMuestraMedica())
                                .idPresentacionMuestra(muestra.getIdPresentacionMuestra())
                                .idTipoMuestra(muestra.getIdTipoMuestra())
                                .idUnidadMedida(muestra.getIdUnidadMedida())
                                .fechaRecepcionMuestra(muestra.getFechaRecepcionMuestra())
                                .fechaCreacionMuestra(muestra.getFechaCreacionMuestra())
                                .observacionExpediente(muestra.getObservacionExpediente())
                                .build())
                        .build()
                ,HttpStatus.OK);
    }

    @GetMapping("muestras")
    public ResponseEntity<?> findAll() {
        try {
            List<Muestra> muestras = muestraService.findAll();
            List<MuestraDto> muestrasDto = muestras.stream()
                    .map(muestra -> MuestraDto.builder()
                            .id(muestra.getId())
                            .idSolicitudMuestraMedica(muestra.getIdSolicitudMuestraMedica())
                            .idPresentacionMuestra(muestra.getIdPresentacionMuestra())
                            .idTipoMuestra(muestra.getIdTipoMuestra())
                            .idUnidadMedida(muestra.getIdUnidadMedida())
                            .fechaRecepcionMuestra(muestra.getFechaRecepcionMuestra())
                            .fechaCreacionMuestra(muestra.getFechaCreacionMuestra())
                            .observacionExpediente(muestra.getObservacionExpediente())
                            .build())
                    .collect(Collectors.toList());
            return new ResponseEntity<>(muestrasDto, HttpStatus.OK);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Error al obtener la lista de muestras: " + exDt.getMessage())
                            .object(null)
                            .build(),
                    HttpStatus.NOT_FOUND
            );
        }
    }
}
