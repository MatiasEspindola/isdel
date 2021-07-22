/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.services;

import com.sysbye.softIsdel.models.entities.CategoriaCurso;
import java.util.List;

/**
 *
 * @author matia
 */
public interface ICategoriaCursoService {
    
    public List<CategoriaCurso> findAll();
    
}
