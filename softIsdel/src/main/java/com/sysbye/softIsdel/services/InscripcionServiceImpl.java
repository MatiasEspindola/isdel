/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.services;

import com.sysbye.softIsdel.models.entities.Alumno;
import com.sysbye.softIsdel.models.entities.Curso;
import com.sysbye.softIsdel.models.entities.Inscripcion;
import com.sysbye.softIsdel.repo.IInscripcionRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InscripcionServiceImpl implements IInscripcionService{

    @Autowired
    private IInscripcionRepo inscripcionRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Inscripcion> listarInscripciones() {
        return inscripcionRepo.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Inscripcion> buscarInscripcionesDelAlumno(Alumno alumno) {
        return inscripcionRepo.buscarInscripcionesDelAlumno(alumno);
    }

    @Override
    public void save(Inscripcion inscripcion) {
        inscripcionRepo.save(inscripcion);
    }

    @Override
    @Transactional
    public Inscripcion findOne(Long id) {
        return inscripcionRepo.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        inscripcionRepo.deleteById(id);
    }
    
    @Override
    public List<Inscripcion> buscarPorTerm(String term) {
        return inscripcionRepo.buscarPorTerm(term);
    }

    @Override
    public List<Inscripcion> buscarInscripcionesPorCurso(Curso curso) {
        return inscripcionRepo.buscarInscripcionesPorCurso(curso);
    }
    
}
