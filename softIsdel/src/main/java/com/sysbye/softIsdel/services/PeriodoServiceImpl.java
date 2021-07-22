/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.services;

import com.sysbye.softIsdel.models.entities.Periodo;
import com.sysbye.softIsdel.repo.IPeriodoRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matia
 */
@Service
public class PeriodoServiceImpl implements IPeriodoService {

    @Autowired
    private IPeriodoRepo periodoRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Periodo> findAll() {

        return (List<Periodo>) periodoRepo.findAll();
    }

    @Override
    @Transactional
    public void save(Periodo periodo) {
        periodoRepo.save(periodo);
    }

    @Override
    public Periodo findOne(Long id) {
        return periodoRepo.findById(id).orElse(null);
    }

}
