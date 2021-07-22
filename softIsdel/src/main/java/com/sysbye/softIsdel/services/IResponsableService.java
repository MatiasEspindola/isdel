/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.services;

import com.sysbye.softIsdel.models.entities.Responsable;
import java.util.List;

/**
 *
 * @author matia
 */
public interface IResponsableService {
    
    public List<Responsable> findAll();

	public void save(Responsable responsable);

	public Responsable findOne(Long id);

	public void delete(Long id);

	public List<Responsable> buscarPorTerm(String term);
    
}
