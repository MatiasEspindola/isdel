package com.sysbye.softIsdel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sysbye.softIsdel.models.entities.ImporteAbonadoCuota;
import com.sysbye.softIsdel.services.IImporteAbonadoCuotaService;

@Controller
@SessionAttributes("importe")
@RequestMapping("/importes")
public class comprobantesImportesCuotaController {

	@Autowired
	private IImporteAbonadoCuotaService importeService;

	@GetMapping("/comprobanteCuota/{idImporte}")
	public String generarComprobante(@PathVariable("idImporte") Long idImporte, Model model) {

		ImporteAbonadoCuota importe = importeService.findOne(idImporte);
		
		model.addAttribute("importe", importe);
		
		return "importes/detallesImportesCuotas";
	}

}
