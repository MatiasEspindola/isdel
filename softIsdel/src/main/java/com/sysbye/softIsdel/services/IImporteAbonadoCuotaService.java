/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.services;

import com.sysbye.softIsdel.models.entities.Cuota;
import com.sysbye.softIsdel.models.entities.ImporteAbonadoCuota;
import java.util.List;

/**
 *
 * @author matia
 */
public interface IImporteAbonadoCuotaService {

    public List<ImporteAbonadoCuota> findAll();

    public void save(ImporteAbonadoCuota importeAbonado);

    public ImporteAbonadoCuota findOne(Long id);
    
    public List<ImporteAbonadoCuota> buscarPorCuota(Cuota cuota);

    public void delete(ImporteAbonadoCuota importeAbonado);
    

}
