/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.services;

import com.sysbye.softIsdel.models.entities.ImporteAbonadoInscripcion;
import com.sysbye.softIsdel.models.entities.Inscripcion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sysbye.softIsdel.repo.IImporteAbonadoInscripcionRepo;

/**
 *
 * @author matia
 */
@Service
public class ImporteAbonadoInscripcionServiceImpl implements IImporteAbonadoInscripcionService {

    @Autowired
    private IImporteAbonadoInscripcionRepo repo;

    @Override
    @Transactional(readOnly = true)
    public List<ImporteAbonadoInscripcion> findAll() {
        return (List<ImporteAbonadoInscripcion>) repo.findAll();
    }

    @Override
    @Transactional
    public void save(ImporteAbonadoInscripcion importeAbonado) {
        repo.save(importeAbonado);
    }

    @Override
    @Transactional(readOnly = true)
    public ImporteAbonadoInscripcion findOne(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(ImporteAbonadoInscripcion importeAbonado) {
        repo.delete(importeAbonado);
    }

    @Override
    public List<ImporteAbonadoInscripcion> buscarPorInscripcion(Inscripcion inscripcion) {
       return repo.buscarPorInscripcion(inscripcion);
    }

}
