/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.repo;

import com.sysbye.softIsdel.models.entities.ImporteAbonadoInscripcion;
import com.sysbye.softIsdel.models.entities.Inscripcion;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author matia
 */
public interface IImporteAbonadoInscripcionRepo extends CrudRepository<ImporteAbonadoInscripcion, Long> {

    @Query("Select i from ImporteAbonadoInscripcion i where i.fkIdInscripcion = ?1")
    public List<ImporteAbonadoInscripcion> buscarPorInscripcion(Inscripcion inscripcion);

}
