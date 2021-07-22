/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.repo;

import com.sysbye.softIsdel.models.entities.Asesor;
import com.sysbye.softIsdel.models.entities.Curso;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author matia
 */
public interface ICursoRepo extends JpaRepository<Curso, Long> {

    @Query("Select c from Curso c where c.Nombre like %?1% ")
    public List<Curso> buscarPorTerm(String term);
    
     @Query("Select c from Curso c where c.fkIdCategoriaCurso = 1 ")
    public List<Curso> buscarCursosAreaTecnica();
    
   @Query("Select c from Curso c where c.fkIdCategoriaCurso = 2 ")
    public List<Curso> buscarCursosAreaInformatica();
    
   @Query("Select c from Curso c where c.fkIdCategoriaCurso = 3 ")
    public List<Curso> buscarCursosAreaEstetica();
    
    @Query("Select c from Curso c where c.fkIdCategoriaCurso = 4 ")
    public List<Curso> buscarCursosTallYPerf();

}
