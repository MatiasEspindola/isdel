/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.repo;

import com.sysbye.softIsdel.models.entities.EstadoCuota;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author matia
 */
public interface IEstadoCuotaRepo extends JpaRepository<EstadoCuota, Long>{
    
}
