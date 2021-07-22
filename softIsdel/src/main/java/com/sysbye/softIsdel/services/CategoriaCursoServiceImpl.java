/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.services;

import com.sysbye.softIsdel.models.entities.CategoriaCurso;
import com.sysbye.softIsdel.repo.ICategoriaCursoRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author matia
 */
@Service
public class CategoriaCursoServiceImpl implements ICategoriaCursoService {

    @Autowired
    private ICategoriaCursoRepo categoriaCursoRepo;

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaCurso> findAll() {

        return (List<CategoriaCurso>) categoriaCursoRepo.findAll();
    }

}
