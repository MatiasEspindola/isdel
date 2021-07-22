package com.sysbye.softIsdel.services;

import java.util.List;

import com.sysbye.softIsdel.models.entities.Curso;

public interface ICursoService {

    public List<Curso> buscarPorTerm(String term);

    public List<Curso> findAll();

    public void save(Curso curso);

    public Curso findOne(Long id);

    public void delete(Long id);

    public List<Curso> buscarCursosAreaTecnica();

    public List<Curso> buscarCursosAreaInformatica();

    public List<Curso> buscarCursosAreaEstetica();

    public List<Curso> buscarCursosTallYPerf();

}
