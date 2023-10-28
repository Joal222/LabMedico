package com.proyecto.progra.backend.service.impl;
import com.proyecto.progra.backend.model.dao.MuestraItemsDao;
import com.proyecto.progra.backend.model.dto.MuestraItemsDto;
import com.proyecto.progra.backend.model.entity.MuestraItems;
import com.proyecto.progra.backend.service.IMuestraItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
public class MuestraItemsImpl implements IMuestraItems {

    @Autowired
    private MuestraItemsDao muestraItemsDao;
    @Transactional
    @Override
    public MuestraItems save(MuestraItemsDto muestraItemsdto) {
        MuestraItems muestraItems = MuestraItems.builder()
                .id(muestraItemsdto.getId())
                .idMuestraMedica(muestraItemsdto.getIdMuestraMedica())
                .idItems(muestraItemsdto.getIdItems())
                .build();

        return muestraItemsDao.save(muestraItems);
    }

    @Transactional(readOnly = true)
    @Override
    public MuestraItems findById(Integer id) {
        return muestraItemsDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(MuestraItems muestraItems) {

        muestraItemsDao.delete(muestraItems);
    }
    @Transactional
    @Override
    public List<MuestraItems> findAll() {

        return (List<MuestraItems>) muestraItemsDao.findAll();
    }

    @Override
    public boolean existById(Integer id) {

        return muestraItemsDao.existsById(id);
    }
}
