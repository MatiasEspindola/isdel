/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.repo;

import com.sysbye.softIsdel.models.entities.Alumno;
import com.sysbye.softIsdel.models.entities.Curso;
import com.sysbye.softIsdel.models.entities.Inscripcion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author matia
 */
public interface IInscripcionRepo extends JpaRepository<Inscripcion, Long>{
    
    @Query("Select i from Inscripcion i where i.fkIdAlumno = ?1 ")
    public List<Inscripcion> buscarInscripcionesDelAlumno(Alumno alumno);
    
    @Query("Select i from Inscripcion i where i.fkIdCurso = ?1")
    public List<Inscripcion> buscarInscripcionesPorCurso(Curso curso);
    
    @Query("Select i from Inscripcion i where (i.fkIdAlumno.Apellido like %?1% or i.fkIdAlumno.Nombre like %?1%) or i.fkIdCurso.Nombre like %?1%")
    public List<Inscripcion> buscarPorTerm(String term);
    
}
