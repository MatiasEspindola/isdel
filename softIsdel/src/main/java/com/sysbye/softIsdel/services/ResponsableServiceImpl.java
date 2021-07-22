/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.services;

import com.sysbye.softIsdel.models.entities.Responsable;
import com.sysbye.softIsdel.repo.IResponsableRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matia
 */
@Service
public class ResponsableServiceImpl implements IResponsableService {

    @Autowired
    private IResponsableRepo responsableRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Responsable> buscarPorTerm(String term) {
        return responsableRepo.buscarPorTerm(term);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Responsable> findAll() {

        return (List<Responsable>) responsableRepo.findAll();
    }

    @Override
    @Transactional
    public void save(Responsable responsable) {
        responsableRepo.save(responsable);
    }

    @Override
    public Responsable findOne(Long id) {

        return responsableRepo.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        responsableRepo.deleteById(id);
    }

}
