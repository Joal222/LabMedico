package com.proyecto.progra.backend.service.impl;
import com.proyecto.progra.backend.model.dao.SolicitudDao;
import com.proyecto.progra.backend.model.dto.SolicitudDto;
import com.proyecto.progra.backend.model.entity.Solicitud;
import com.proyecto.progra.backend.service.ISolicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
public class SolicitudImpl implements ISolicitud {
    @Autowired
    private SolicitudDao solicitudDao;
    @Transactional
    @Override
    public Solicitud save(SolicitudDto solicitudDto) {
        Solicitud solicitud = Solicitud.builder()
                .id(solicitudDto.getId())
                .idUsuario(solicitudDto.getIdUsuario())
                .idTipoSolicitante(solicitudDto.getIdTipoSolicitante())
                .idTipoSolicitud(solicitudDto.getIdTipoSolicitud())
                .idEstadoSolicitud(solicitudDto.getIdEstadoSolicitud())
                .idTipoSoporte(solicitudDto.getIdTipoSoporte())
                .descripcionSolicitudMuestraMedica(solicitudDto.getDescripcionSolicitudMuestraMedica())
                .fechaCreacionSolicitud(solicitudDto.getFechaCreacionSolicitud())
                .diasVencimientoSolicitud(solicitudDto.getDiasVencimientoSolicitud())
                .build();
        return solicitudDao.save(solicitud);
    }

    @Transactional
    @Override
    public Solicitud findById(Integer id) {
        return solicitudDao.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public void delete(Solicitud solicitud) {
        solicitudDao.delete(solicitud);
    }
    @Transactional
    @Override
    public List<Solicitud> findAll() {
        return (List<Solicitud>) solicitudDao.findAll();
    }

    @Override
    public boolean existById(Integer id) {
        return solicitudDao.existsById(id);
    }
}
