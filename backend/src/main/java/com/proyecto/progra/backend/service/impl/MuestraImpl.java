package com.proyecto.progra.backend.service.impl;
import com.proyecto.progra.backend.model.dao.MuestraDao;
import com.proyecto.progra.backend.model.dto.MuestraDto;
import com.proyecto.progra.backend.model.entity.Muestra;
import com.proyecto.progra.backend.service.IMuestra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
public class MuestraImpl implements IMuestra {
    @Autowired
    private MuestraDao muestraDao;
    @Transactional
    @Override
    public Muestra save(MuestraDto muestraDto) {
        Muestra muestra = Muestra.builder()
                .idSolicitudMuestraMedica(muestraDto.getIdSolicitudMuestraMedica())
                .idPresentacionMuestra(muestraDto.getIdPresentacionMuestra())
                .idTipoMuestra(muestraDto.getIdTipoMuestra())
                .idDocumentoMuestraAdjunto(muestraDto.getIdDocumentoMuestraAdjunto())
                .idUnidadMedida(muestraDto.getIdUnidadMedida())
                .fechaRecepcionMuestra(muestraDto.getFechaRecepcionMuestra())
                .fechaRecepcionMuestra(muestraDto.getFechaCreacionMuestra())
                .observacionExpediente(muestraDto.getObservacionExpediente())
                .build();
        return muestraDao.save(muestra);
    }

    @Transactional(readOnly = true)
    @Override
    public Muestra findById(Integer id) {
        return muestraDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Muestra muestra) {
        muestraDao.delete(muestra);
    }

    @Transactional
    @Override
    public List<Muestra> findAll() {

        return(List<Muestra>) muestraDao.findAll();
    }

    @Override
    public boolean existById(Integer id) {
        return muestraDao.existsById(id);
    }
}
