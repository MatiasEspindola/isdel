/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.repo;

import com.sysbye.softIsdel.models.entities.Alumno;
import com.sysbye.softIsdel.models.entities.Responsable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author matia
 */
public interface IAlumnoRepo extends JpaRepository<Alumno, Long>{
    
    @Query("Select a from Alumno a where (a.Nombre like %?1% or a.Apellido like %?1%) or a.Dni like %?1% ")
    public List<Alumno> buscarPorTerm(String term);
    
    @Query("Select a from Alumno a where a.fkIdResponsable = ?1")
    public List<Alumno> buscarPorResponsableOTutor(Responsable responsable);
    
}
