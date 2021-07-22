/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.repo;

import com.sysbye.softIsdel.models.entities.Cuota;
import com.sysbye.softIsdel.models.entities.ImporteAbonadoCuota;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author matia
 */
public interface IImporteAbonadoCuotaRepo extends CrudRepository<ImporteAbonadoCuota, Long> {

    @Query("Select i from ImporteAbonadoCuota i where i.fkIdCuota = ?1")
    public List<ImporteAbonadoCuota> buscarPorCuota(Cuota cuota);

}
