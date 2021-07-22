/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.repo;

import com.sysbye.softIsdel.models.entities.Periodo;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author matia
 */
public interface IPeriodoRepo extends CrudRepository<Periodo, Long>{
    
}
