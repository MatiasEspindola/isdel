/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.services;

import com.sysbye.softIsdel.models.entities.Cuota;
import com.sysbye.softIsdel.models.entities.PlanDeInversion;
import com.sysbye.softIsdel.repo.ICuotaRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CuotaServiceImpl implements ICuotaService{

    @Autowired
    private ICuotaRepo repo;
    
    @Override
    @Transactional(readOnly = true)
    public List<Cuota> findAll() {
        return (List<Cuota>) repo.findAll();
    }

    @Override
    @Transactional
    public void save(Cuota cuota) {
       repo.save(cuota);
    }

    @Override
    @Transactional(readOnly = true)
    public Cuota findOne(Long id) {
        return repo.findById(id).orElse(null);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Cuota> buscarPorPlanDeInversion(PlanDeInversion planDeInversion){
        return repo.buscarPorPlanDeInversion(planDeInversion);
    }

    @Override
    @Transactional
    public void delete(Cuota cuota) {
        repo.delete(cuota);
    }
    
}
