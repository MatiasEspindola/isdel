/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.controller;

import com.sysbye.softIsdel.models.entities.Cuota;
import com.sysbye.softIsdel.models.entities.ImporteAbonadoCuota;
import com.sysbye.softIsdel.services.ICuotaService;
import com.sysbye.softIsdel.services.IEstadoCuotaService;
import com.sysbye.softIsdel.services.IImporteAbonadoCuotaService;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author matia
 */
@Controller
@SessionAttributes("importeAbonadoCuota")
@RequestMapping("importes")
public class ImportesCuotasController {

    @Autowired
    private IImporteAbonadoCuotaService importeService;

    @Autowired
    private ICuotaService cuotaService;

    @Autowired
    private IEstadoCuotaService estadoCuotaService;

    private int index;

    private Cuota cuota;

    @GetMapping("/detallesImportesCuotas/{idCuota}/{index}")
    public String detallesImportesCuotas(@PathVariable(value = "idCuota") long idCuota,
            @PathVariable(value = "index") int index, Model model) {

        cuotaService.findOne(idCuota).getFkIdPlanDeInversion();

        model.addAttribute("inscripcion", cuotaService.findOne(idCuota).getFkIdPlanDeInversion().getFkIdInscripcion());

        model.addAttribute("idPlanDeInversion", cuotaService.findOne(idCuota).getFkIdPlanDeInversion().getIdPlanDeInversion());

        importeService.buscarPorCuota(cuotaService.findOne(idCuota));

        model.addAttribute("idCuota", idCuota);

        model.addAttribute("index", index);

        cuota = cuotaService.findOne(idCuota);

        this.index = index;

        model.addAttribute("importes", importeService.buscarPorCuota(cuotaService.findOne(idCuota)));

        List<ImporteAbonadoCuota> importes = importeService.buscarPorCuota(cuotaService.findOne(idCuota));

        float montoAbonado = 0;
        int cantidadImportes = 0;

        if (!importes.isEmpty()) {

            for (ImporteAbonadoCuota importe : importes) {
                montoAbonado += importe.getImporteAbonado();
                cantidadImportes++;
            }

            System.out.println("Monto abonado: " + montoAbonado);
            System.out.println("Cantidad Importes: " + cantidadImportes);

            model.addAttribute("montoAbonado", montoAbonado);
            model.addAttribute("cantidadImportes", cantidadImportes);
        } else {

            System.out.println("Monto abonado: " + montoAbonado);
            System.out.println("Cantidad Importes: " + cantidadImportes);

            model.addAttribute("montoAbonado", montoAbonado);
            model.addAttribute("cantidadImportes", cantidadImportes);
        }

        model.addAttribute("saldoPendiente", cuotaService.findOne(idCuota).getSaldoPendiente());

        model.addAttribute("cuota", cuota);

        return "importes/detallesImportesCuotas";
    }

    @GetMapping("/formImporteCuota/{idCuota}/{index}")
    public String formImporte(@PathVariable(value = "idCuota") Long idCuota,
            @PathVariable(value = "index") int index, Model model) {
    	
    	 cuota = cuotaService.findOne(idCuota);
         this.index = index;
    	

       if(cuota == null) {
    	   return "redirect:/planesdeinversion/listarInscripciones";
       }else {
    	   if(cuota.getSaldoPendiente() == 0) {
    		   return "redirect:/importes/detallesImportesCuotas/" + cuota.getIdCuota() + "/" + index;
    	   }
    	  
           ImporteAbonadoCuota importeAbonadoCuota = new ImporteAbonadoCuota();

           model.addAttribute("index", index);

           model.addAttribute("inscripcion", cuotaService.findOne(idCuota).getFkIdPlanDeInversion().getFkIdInscripcion());

           model.addAttribute("idPlanDeInversion", cuotaService.findOne(idCuota).getFkIdPlanDeInversion().getIdPlanDeInversion());

           model.addAttribute("cuota", cuota);
           model.addAttribute("max", cuota.getSaldoPendiente());
           model.addAttribute("importeAbonadoCuota", importeAbonadoCuota);
       }

        return "importes/formImporteCuota";
    }

    @PostMapping("/formImporteCuota")
    public String guardarImporte(@Valid @ModelAttribute("importeAbonadoCuota") ImporteAbonadoCuota importeAbonadoCuota, Model model) {

        System.out.println(cuota);

        importeAbonadoCuota.setFkIdCuota(cuota);
        importeService.save(importeAbonadoCuota);

        cuota.setSaldoPendiente(cuota.getSaldoPendiente() - importeAbonadoCuota.getImporteAbonado());

        if (cuota.getSaldoPendiente() == 0) {
            cuota.setFkIdEstadoCuota(estadoCuotaService.findAll().get(1));
            cuota.setPagado(true);
        }

        cuotaService.save(cuota);

        return "redirect:/importes/detallesImportesCuotas/" + importeAbonadoCuota.getFkIdCuota().getIdCuota() + "/" + index;
    }

    @GetMapping("/eliminar/{idImporte}")
    private String eliminarImporte(@PathVariable(value = "idImporte") Long idImporte) {

    	if(importeService.findOne(idImporte) == null) {
     	   return "redirect:/inscripciones/listarInscripciones";
        }
    	
        Cuota cuota = importeService.findOne(idImporte).getFkIdCuota();
 
        cuota.setSaldoPendiente(cuota.getSaldoPendiente() + importeService.findOne(idImporte).getImporteAbonado());
        
        Date fechaActual = new Date();
        
        if(cuota.getVencimiento().after(fechaActual)){
            cuota.setFkIdEstadoCuota(estadoCuotaService.findAll().get(0));
        }else{
            cuota.setFkIdEstadoCuota(estadoCuotaService.findAll().get(2));
        }
        
        importeService.delete(importeService.findOne(idImporte));

        return "redirect:/importes/detallesImportesCuotas/" + cuota.getIdCuota() + "/" + index;
    }

}
