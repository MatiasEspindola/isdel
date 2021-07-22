package com.sysbye.softIsdel.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sysbye.softIsdel.models.entities.Cuota;
import com.sysbye.softIsdel.services.ICuotaService;
import com.sysbye.softIsdel.services.ICursoService;
import com.sysbye.softIsdel.services.IEstadoCuotaService;

@Controller
public class IndexController {

	@Autowired
	private ICursoService cursoService;

	@Autowired
	private ICuotaService cuotaService;

	@Autowired
	private IEstadoCuotaService estadoCuotaService;

	@GetMapping(value = { "/index", "/" })
	public String index(Model model) {

		if (cuotaService.findAll().size() > 0) {
			for (Cuota cuota : cuotaService.findAll()) {
				if (!(cuota.getFkIdEstadoCuota().getIdEstadoCuota() == 3)) {
					Date fechaActual = new Date();
					if (cuota.getVencimiento().before(fechaActual)) {

						if (cuota.isCobrar_ajuste()) {

							float interes = 0;

							if (cuota.getGrupo() == 1) {
								interes = (float) cuota.getFkIdPlanDeInversion().getValorCuota1() * 20 / 100;
								cuota.setSaldoPendiente((float) cuota.getFkIdPlanDeInversion().getValorCuota1() + interes);

							} else {
								interes = (float) cuota.getFkIdPlanDeInversion().getValorCuota2() * 20 / 100;
								cuota.setSaldoPendiente((float) cuota.getFkIdPlanDeInversion().getValorCuota2() + interes);

							}
							cuota.setFkIdEstadoCuota(estadoCuotaService.findAll().get(2));
							cuotaService.save(cuota);

						}
					}
				}
			}
		}

		model.addAttribute("categoria3", cursoService.buscarCursosAreaEstetica());
		model.addAttribute("categoria1", cursoService.buscarCursosAreaInformatica());
		model.addAttribute("categoria2", cursoService.buscarCursosAreaTecnica());
		model.addAttribute("categoria4", cursoService.buscarCursosTallYPerf());

		return "index";
	}
}
