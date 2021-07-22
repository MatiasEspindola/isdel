/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.repo;

import com.sysbye.softIsdel.models.entities.Responsable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author matia
 */
public interface IResponsableRepo extends JpaRepository<Responsable, Long>{
    
    @Query("Select r from Responsable r where (r.Nombre like %?1% or r.Apellido like %?1%) or r.Dni like %?1% ")
    public List<Responsable> buscarPorTerm(String term);
    
}