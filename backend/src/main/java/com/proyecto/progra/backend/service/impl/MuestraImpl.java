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
    public Muestra save(Muestra muestra) {
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

    @Transactional(readOnly = true)
    @Override
    public List<Muestra> findAll() {

        return(List<Muestra>) muestraDao.findAll();
    }
    @Transactional
    @Override
    public boolean existById(Integer id) {
        return muestraDao.existsById(id);
    }
}
