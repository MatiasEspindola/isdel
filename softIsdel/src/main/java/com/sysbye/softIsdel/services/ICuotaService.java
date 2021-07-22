/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.services;

import com.sysbye.softIsdel.models.entities.Cuota;
import com.sysbye.softIsdel.models.entities.PlanDeInversion;
import java.util.List;

/**
 *
 * @author matia
 */
public interface ICuotaService {

    public List<Cuota> findAll();

    public void save(Cuota cuota);

    public Cuota findOne(Long id);

    public void delete(Cuota cuota);

    public List<Cuota> buscarPorPlanDeInversion(PlanDeInversion planDeInversion);

}
