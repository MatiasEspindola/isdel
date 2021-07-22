/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.repo;

import com.sysbye.softIsdel.models.entities.Inscripcion;
import com.sysbye.softIsdel.models.entities.PlanDeInversion;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface IPlanDeInversionRepo extends CrudRepository<PlanDeInversion, Long>{
        
        @Query("select p from PlanDeInversion p where p.fkIdInscripcion=?1")
	public PlanDeInversion buscarPorInscripcion(Inscripcion inscripcion);
}
