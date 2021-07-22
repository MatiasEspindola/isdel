package com.sysbye.softIsdel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sysbye.softIsdel.models.entities.Curso;
import com.sysbye.softIsdel.repo.ICursoRepo;

@Service
public class CursoServiceImpl implements ICursoService{
	
	@Autowired
	ICursoRepo cursoRepo;
        
        @Override
        @Transactional(readOnly = true)
        public List<Curso> buscarPorTerm(String term){
            return cursoRepo.buscarPorTerm(term);
        }

	@Override
	@Transactional(readOnly = true)
	public List<Curso> findAll() {
		
		return (List<Curso>) cursoRepo.findAll();
	}

	@Override
	@Transactional
	public void save(Curso curso) {
		
		cursoRepo.save(curso);
		
	}

	@Override
        @Transactional(readOnly = true)
	public Curso findOne(Long id) {
		return cursoRepo.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		cursoRepo.deleteById(id);
		
	}

    @Override
    public List<Curso> buscarCursosAreaTecnica() {
        return cursoRepo.buscarCursosAreaTecnica();
    }

    @Override
    public List<Curso> buscarCursosAreaInformatica() {
        return cursoRepo.buscarCursosAreaInformatica();
    }

    @Override
    public List<Curso> buscarCursosAreaEstetica() {
        return cursoRepo.buscarCursosAreaEstetica();
    }

    @Override
    public List<Curso> buscarCursosTallYPerf() {
       return cursoRepo.buscarCursosTallYPerf();
    }

	
}
