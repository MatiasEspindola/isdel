package com.sysbye.softIsdel.services;

import com.sysbye.softIsdel.models.entities.Inscripcion;
import java.util.List;

import com.sysbye.softIsdel.models.entities.PlanDeInversion;

public interface IPlanDeInversionService {

    public List<PlanDeInversion> findAll();

    public void save(PlanDeInversion plandepago);

    public PlanDeInversion findOne(Long id);

    public void delete(Long id);

    public PlanDeInversion buscarPorInscripcion(Inscripcion inscripcion);

}
