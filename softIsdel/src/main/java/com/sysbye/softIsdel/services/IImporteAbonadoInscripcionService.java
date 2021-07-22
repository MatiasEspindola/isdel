/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.services;

import com.sysbye.softIsdel.models.entities.ImporteAbonadoInscripcion;
import com.sysbye.softIsdel.models.entities.Inscripcion;
import java.util.List;

/**
 *
 * @author matia
 */
public interface IImporteAbonadoInscripcionService {

    public List<ImporteAbonadoInscripcion> findAll();

    public void save(ImporteAbonadoInscripcion importeAbonado);

    public ImporteAbonadoInscripcion findOne(Long id);
    
    public List<ImporteAbonadoInscripcion> buscarPorInscripcion(Inscripcion inscripcion);

    public void delete(ImporteAbonadoInscripcion importeAbonado);
    

}
