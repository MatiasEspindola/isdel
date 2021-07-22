/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.controller;

import com.sysbye.softIsdel.models.entities.ImporteAbonadoCuota;
import com.sysbye.softIsdel.models.entities.ImporteAbonadoInscripcion;
import com.sysbye.softIsdel.models.entities.Inscripcion;
import com.sysbye.softIsdel.models.entities.PlanDeInversion;
import com.sysbye.softIsdel.services.IImporteAbonadoInscripcionService;
import com.sysbye.softIsdel.services.IInscripcionService;
import com.sysbye.softIsdel.services.IPlanDeInversionService;
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
@SessionAttributes("importeAbonadoInscripcion")
@RequestMapping("importes")
public class ImportesInscripcionController {
    
    @Autowired
    private IImporteAbonadoInscripcionService importeService;
    
    @Autowired
    private IInscripcionService inscripcionService;
    
    @Autowired
    private IPlanDeInversionService planDeInversionService;
    
    private Inscripcion inscripcion;
    
    @GetMapping("/detallesImportesInscripcion/{idInscripcion}")
    public String detallesImportesInscripcion(@PathVariable(value = "idInscripcion") long idInscripcion,
            Model model) {
        
        this.inscripcion = inscripcionService.findOne(idInscripcion);
        
        inscripcionService.findOne(idInscripcion);
        model.addAttribute("inscripcion", inscripcionService.findOne(idInscripcion));
        
       // planDeInversionService.buscarPorInscripcion(inscripcion);
        
        model.addAttribute("planDeInversion", planDeInversionService.buscarPorInscripcion(inscripcion));
        
        model.addAttribute("importes", importeService.buscarPorInscripcion(inscripcion));
        
        
        List<ImporteAbonadoInscripcion> importes = importeService.buscarPorInscripcion(inscripcion);
        
        float montoAbonado = 0;
        int cantidadImportes = 0;
        
        if (!importes.isEmpty()) {
        	
            for (ImporteAbonadoInscripcion importe : importes) {
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
        
        model.addAttribute("saldoPendiente", inscripcionService.findOne(idInscripcion).getSaldoPendiente());
        
        return "importes/detallesImportesInscripcion";
    }
    
    @GetMapping("/formImporteInscripcion/{idInscripcion}")
    public String formImporte(@PathVariable(value = "idInscripcion") Long idInscripcion, Model model) {
        
        this.inscripcion = inscripcionService.findOne(idInscripcion);
        
        if(inscripcionService.findOne(idInscripcion) == null) {
        	return "redirect:/inscripciones/listarInscripciones";
        }
        
        if(inscripcion.getSaldoPendiente() == 0) {
        	System.out.println("se ha abonado todo");
        	return "redirect:/importes/detallesImportesInscripcion/" + inscripcion.getIdInscripcion();
        }
        
        System.out.println(inscripcion);
        
        ImporteAbonadoInscripcion importeAbonadoInscripcion = new ImporteAbonadoInscripcion();
        
        PlanDeInversion planDeInversion = planDeInversionService.buscarPorInscripcion(inscripcion);
        
        model.addAttribute("inscripcion", inscripcion);
        
        model.addAttribute("planDeInversion", planDeInversion);
        
        model.addAttribute("importeAbonadoInscripcion", importeAbonadoInscripcion);
        
        return "importes/formImporteInscripcion";
    }
    
    @PostMapping("/formImporteInscripcion")
    public String guardarImporte(@Valid @ModelAttribute("importeAbonadoInscripcion") ImporteAbonadoInscripcion importeAbonadoInscripcion, Model model) {
        
        System.out.println(inscripcion);
        
        importeAbonadoInscripcion.setFkIdInscripcion(inscripcion);
        
        inscripcion.setAbonado((double) importeAbonadoInscripcion.getImporteAbonado());
        
        inscripcion.setSaldoPendiente(inscripcion.getSaldoPendiente() - importeAbonadoInscripcion.getImporteAbonado());
        
        inscripcionService.save(inscripcion);
        
        importeService.save(importeAbonadoInscripcion);
        
        return "redirect:/importes/detallesImportesInscripcion/" + inscripcion.getIdInscripcion();
    }
    
    @GetMapping("/eliminarImporteInscripcion/{idImporte}")
    private String eliminarImporte(@PathVariable(value = "idImporte") Long idImporte) {
        
    	if(importeService.findOne(idImporte) == null) {
    		 return "redirect:/inscripciones/listarInscripciones";
    	}
    	
    	ImporteAbonadoInscripcion importe = importeService.findOne(idImporte); 
    	
    	inscripcion.setSaldoPendiente(inscripcion.getSaldoPendiente() + importe.getImporteAbonado());
    	
    	inscripcionService.save(inscripcion);
    	
    	importeService.delete(importe);
    	
    	 return "redirect:/importes/detallesImportesInscripcion/" + inscripcion.getIdInscripcion();
    }
    
}
