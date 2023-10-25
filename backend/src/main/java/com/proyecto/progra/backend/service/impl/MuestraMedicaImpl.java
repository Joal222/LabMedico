package com.proyecto.progra.backend.service.impl;
import com.proyecto.progra.backend.model.dao.MuestraMedicaDao;
import com.proyecto.progra.backend.model.dto.MuestraMedicaDto;
import com.proyecto.progra.backend.model.entity.MuestraMedica;
import com.proyecto.progra.backend.service.IMuestraMedica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
public class MuestraMedicaImpl implements IMuestraMedica {
    @Autowired
    private MuestraMedicaDao muestraMedicaDao;
    @Transactional
    @Override
    public MuestraMedica save(MuestraMedicaDto muestraMedicaDto) {
        MuestraMedica muestraMedica = MuestraMedica.builder()
                .idSolicitudMuestraMedica(muestraMedicaDto.getIdSolicitudMuestraMedica())
                .idPresentacionMuestra(muestraMedicaDto.getIdPresentacionMuestra())
                .idTipoMuestra(muestraMedicaDto.getIdTipoMuestra())
                .idDocumentoMuestraAdjunto(muestraMedicaDto.getIdDocumentoMuestraAdjunto())
                .idUnidadMedida(muestraMedicaDto.getIdUnidadMedida())
                .fechaRecepcionMuestra(muestraMedicaDto.getFechaRecepcionMuestra())
                .fechaRecepcionMuestra(muestraMedicaDto.getFechaCreacionMuestra())
                .observacionExpediente(muestraMedicaDto.getObservacionExpediente())
                .build();
        return muestraMedicaDao.save(muestraMedica);
    }

    @Transactional(readOnly = true)
    @Override
    public MuestraMedica findById(Integer id) {
        return muestraMedicaDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(MuestraMedica muestraMedica) {
        muestraMedicaDao.delete(muestraMedica);
    }

    @Transactional
    @Override
    public List<MuestraMedica> findAll() {

        return(List<MuestraMedica>) muestraMedicaDao.findAll();
    }

    @Override
    public boolean existById(Integer id) {
        return muestraMedicaDao.existsById(id);
    }
}
