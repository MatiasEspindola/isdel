/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.services;

import com.sysbye.softIsdel.models.entities.Cuota;
import com.sysbye.softIsdel.models.entities.ImporteAbonadoCuota;
import com.sysbye.softIsdel.repo.IImporteAbonadoCuotaRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matia
 */
@Service
public class ImporteAbonadoCuotaServiceImpl implements IImporteAbonadoCuotaService {

    @Autowired
    private IImporteAbonadoCuotaRepo repo;

    @Override
    @Transactional(readOnly = true)
    public List<ImporteAbonadoCuota> findAll() {
        return (List<ImporteAbonadoCuota>) repo.findAll();
    }

    @Override
    @Transactional
    public void save(ImporteAbonadoCuota importeAbonado) {
        repo.save(importeAbonado);
    }

    @Override
    @Transactional(readOnly = true)
    public ImporteAbonadoCuota findOne(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(ImporteAbonadoCuota importeAbonado) {
        repo.delete(importeAbonado);
    }

    @Override
    public List<ImporteAbonadoCuota> buscarPorCuota(Cuota cuota) {
        return repo.buscarPorCuota(cuota);
    }

}
