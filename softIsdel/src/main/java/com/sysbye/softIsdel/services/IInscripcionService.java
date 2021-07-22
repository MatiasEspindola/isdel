/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.services;

import com.sysbye.softIsdel.models.entities.Alumno;
import com.sysbye.softIsdel.models.entities.Asesor;
import com.sysbye.softIsdel.models.entities.Curso;
import com.sysbye.softIsdel.models.entities.Inscripcion;
import com.sysbye.softIsdel.models.entities.PlanDeInversion;
import java.util.List;

/**
 *
 * @author matia
 */
public interface IInscripcionService {

    public List<Inscripcion> listarInscripciones();

    public List<Inscripcion> buscarInscripcionesDelAlumno(Alumno alumno);

    public void save(Inscripcion inscripcion);

    public Inscripcion findOne(Long id);

    public void delete(Long id);
    
    public List<Inscripcion> buscarPorTerm(String term);
    
    public List<Inscripcion> buscarInscripcionesPorCurso(Curso curso);

}
