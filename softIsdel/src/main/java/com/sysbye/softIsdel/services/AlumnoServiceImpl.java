package com.sysbye.softIsdel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sysbye.softIsdel.models.entities.Alumno;
import com.sysbye.softIsdel.models.entities.Responsable;
import com.sysbye.softIsdel.repo.IAlumnoRepo;

@Service
public class AlumnoServiceImpl implements IAlumnoService {

    @Autowired
    IAlumnoRepo alumnoRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> buscarPorTerm(String term) {
        return alumnoRepo.buscarPorTerm(term);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> findAll() {

        return (List<Alumno>) alumnoRepo.findAll();
    }

    @Override
    @Transactional
    public void save(Alumno alumno) {
        alumnoRepo.save(alumno);
    }

    @Override
    public Alumno findOne(Long id) {

        return alumnoRepo.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        alumnoRepo.deleteById(id);
    }

    @Override
    public List<Alumno> buscarPorResponsableOTutor(Responsable responsable) {
        return alumnoRepo.buscarPorResponsableOTutor(responsable);
    }

}
