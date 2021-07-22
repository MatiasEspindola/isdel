/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.services;

import com.sysbye.softIsdel.models.entities.EstadoCuota;
import com.sysbye.softIsdel.repo.IEstadoCuotaRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matia
 */
@Service
public class EstadoCuotaServiceImpl implements IEstadoCuotaService {
    
    @Autowired
    private IEstadoCuotaRepo estadoCuotaRepo;

    @Override
    @Transactional(readOnly = true)
    public List<EstadoCuota> findAll() {

        return (List<EstadoCuota>) estadoCuotaRepo.findAll();
    }

    @Override
    @Transactional
    public void save(EstadoCuota periodo) {
        estadoCuotaRepo.save(periodo);
    }

    @Override
    public EstadoCuota findOne(Long id) {
        return estadoCuotaRepo.findById(id).orElse(null);
    }
    
}
