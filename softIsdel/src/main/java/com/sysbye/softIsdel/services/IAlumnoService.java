package com.sysbye.softIsdel.services;

import java.util.List;

import com.sysbye.softIsdel.models.entities.Alumno;
import com.sysbye.softIsdel.models.entities.Responsable;

public interface IAlumnoService {

	public List<Alumno> findAll();

	public void save(Alumno alumno);

	public Alumno findOne(Long id);

	public void delete(Long id);

	public List<Alumno> buscarPorTerm(String term);
        
        public List<Alumno> buscarPorResponsableOTutor(Responsable responsable);

}
