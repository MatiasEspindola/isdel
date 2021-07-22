package com.sysbye.softIsdel.services;

import java.util.List;

import com.sysbye.softIsdel.models.entities.Asesor;

public interface IAsesorService {

//        public List<Asesor> buscarPorTerm(String term);
    
	public List<Asesor> findAll();

	public void save(Asesor asesor);

	public Asesor findOne(Long id);

	public void delete(Long id);
}
