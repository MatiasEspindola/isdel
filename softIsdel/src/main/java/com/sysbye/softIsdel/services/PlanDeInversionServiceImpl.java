package com.sysbye.softIsdel.services;

import com.sysbye.softIsdel.models.entities.Inscripcion;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sysbye.softIsdel.models.entities.PlanDeInversion;
import com.sysbye.softIsdel.repo.IPlanDeInversionRepo;

@Service
public class PlanDeInversionServiceImpl implements IPlanDeInversionService {

    @Autowired
    IPlanDeInversionRepo planDeInversionRepo;

    @Override
    @Transactional(readOnly = true)
    public List<PlanDeInversion> findAll() {

        return (List<PlanDeInversion>) planDeInversionRepo.findAll();
    }

    @Override
    @Transactional
    public void save(PlanDeInversion plandepago) {
        planDeInversionRepo.save(plandepago);

    }

    @Override
    @Transactional
    public PlanDeInversion findOne(Long id) {

        return planDeInversionRepo.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {

        planDeInversionRepo.deleteById(id);

    }

    @Override
    public PlanDeInversion buscarPorInscripcion(Inscripcion inscripcion) {
        return planDeInversionRepo.buscarPorInscripcion(inscripcion);
    }

}
