/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.services;

import com.sysbye.softIsdel.models.entities.Periodo;
import java.util.List;

/**
 *
 * @author matia
 */
public interface IPeriodoService {

    public List<Periodo> findAll();
    
    public Periodo findOne(Long id);

    public void save(Periodo periodo);

}
