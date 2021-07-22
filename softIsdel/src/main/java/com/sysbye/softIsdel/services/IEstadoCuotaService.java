/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.services;

import com.sysbye.softIsdel.models.entities.EstadoCuota;
import java.util.List;

/**
 *
 * @author matia
 */
public interface IEstadoCuotaService {
    
    public List<EstadoCuota> findAll();
    
    public EstadoCuota findOne(Long id);

    public void save(EstadoCuota estadoCuota);
    
}
