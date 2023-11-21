package com.proyecto.progra.backend.controller;
import com.proyecto.progra.backend.model.dto.MuestraCreatedDto;
import com.proyecto.progra.backend.model.dto.MuestraDto;
import com.proyecto.progra.backend.model.entity.Muestra;
import com.proyecto.progra.backend.model.entity.MuestraItems;
import com.proyecto.progra.backend.model.payload.MensajeResponse;
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
public class MuestraController {

    @Autowired
    private ISolicitud solicitudService;

    @Autowired
    private IMuestra muestraService;

    @Autowired
    private ITipoMuestra tipoMuestraService;

    @Autowired
    private IPTMuestra iptMuestraService;

    @Autowired
    private IUMedida unidadMedidaService;

    @Autowired
    private IMuestraItems muestraItemsService;

    @PostMapping("muestra/created")
    public ResponseEntity<?> created(@RequestBody MuestraCreatedDto muestraCreatedDto){
        Muestra muestra = new Muestra();

        muestra.setIdSolicitudMuestraMedica(solicitudService.findById(muestraCreatedDto.getIdSolicitudMuestraMedica()));
        muestra.setIdPresentacionMuestra(iptMuestraService.findById(muestraCreatedDto.getIdPresentacionMuestra()));
        muestra.setIdTipoMuestra(tipoMuestraService.findById(muestraCreatedDto.getIdTipoMuestra()));
        muestra.setIdUnidadMedida(unidadMedidaService.findById(muestraCreatedDto.getIdUnidadMedida()));
        muestra.setObservacionExpediente(muestraCreatedDto.getObservacionExpediente());

        Muestra muestraResponse = muestraService.save(muestra);
        muestraCreatedDto.getMuestraItemsList().forEach(muestraItemsDto -> {
            MuestraItems muestraItems= new MuestraItems();
        //AQUI ME QUEDE VER EJEMPLO CREATED SOLICITUD CONTROLLER

        });


        return ResponseEntity.ok(muestraResponse);

        }

    /*
    @PutMapping("muestra/{id}")
    public ResponseEntity<?> update(@RequestBody Muestra muestra, @PathVariable Integer id) {
        Muestra muestraUpdate = null;
        try {
            if (muestraService.existById(id)) {
                muestra.setId(id);
                muestraUpdate = muestraService.save(muestra);
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
     */

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
                                //.idSolicitudMuestraMedica(muestra.getIdSolicitudMuestraMedica())
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
                            //.idSolicitudMuestraMedica(muestra.getIdSolicitudMuestraMedica())
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
