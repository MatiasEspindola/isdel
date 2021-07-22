package com.sysbye.softIsdel.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sysbye.softIsdel.models.entities.Alumno;
import com.sysbye.softIsdel.models.entities.Cuota;
import com.sysbye.softIsdel.models.entities.Curso;
import com.sysbye.softIsdel.models.entities.Inscripcion;
import com.sysbye.softIsdel.models.entities.PlanDeInversion;
import com.sysbye.softIsdel.services.IAlumnoService;
import com.sysbye.softIsdel.services.ICursoService;
import com.sysbye.softIsdel.services.IInscripcionService;

@Controller
@Secured({ "ROLE_ADMIN" })
public class TableroController {

	@Autowired
	private IInscripcionService inscripcionService;

	@Autowired
	private ICursoService cursoService;

	@Autowired
	private IAlumnoService alumnoService;

	@GetMapping("/tablero")
	public String tablero(Model model) {

		List<Curso> cursos = cursoService.findAll();

		model.addAttribute("cursos", cursos);

		// CUANTOS INSCRIPTOS HAY CADA MES
		List<Inscripcion> listarTodo = inscripcionService.listarInscripciones();

		Map<String, Integer> areaInf = new LinkedHashMap<>();
		Map<String, Integer> areaTec = new LinkedHashMap<>();
		Map<String, Integer> areaEst = new LinkedHashMap<>();
		Map<String, Integer> areaTall = new LinkedHashMap<>();

		for (int i = 1; i < 13; i++) {
			int inscriptosAreaTecnica = 0;
			int inscriptosAreaInformatica = 0;
			int inscriptosAreaEstetica = 0;
			int inscriptosTallYPerf = 0;
			for (Inscripcion inscripcion : listarTodo) {
				int mesInscripcion = inscripcion.getFechaInscripcion().getMonth() + 1;

				if (i == mesInscripcion) {
					int idCategoriaCurso = (int) inscripcion.getFkIdCurso().getFkIdCategoriaCurso()
							.getIdCategoriaCurso();
					switch (idCategoriaCurso) {
					case 1:
						inscriptosAreaTecnica++;
						break;
					case 2:
						inscriptosAreaInformatica++;
						break;
					case 3:
						inscriptosAreaEstetica++;
						break;
					case 4:
						inscriptosTallYPerf++;
						break;
					}
				}

			}

			areaInf.put("Mes " + i, inscriptosAreaInformatica);
			areaTec.put("Mes " + i, inscriptosAreaTecnica);
			areaEst.put("Mes " + i, inscriptosAreaEstetica);
			areaTall.put("Mes " + i, inscriptosTallYPerf);

		}

		model.addAttribute("areaInf", areaInf);
		model.addAttribute("areaTec", areaTec);
		model.addAttribute("areaEst", areaEst);
		model.addAttribute("areaTall", areaTall);

		// INSCRIPTOS MES

		List<Inscripcion> inscriptosMesActual = new ArrayList<>();
		List<Cuota> pendientesPP = new ArrayList<>();
		List<Cuota> pendientesSP = new ArrayList<>();
		List<Cuota> pendientesTP = new ArrayList<>();

		Date fechaActual = new Date();
		int mesActual = fechaActual.getMonth() + 1;

		for (Inscripcion inscripcion : listarTodo) {
			int mesInscripcion = inscripcion.getFechaInscripcion().getMonth() + 1;

			if (mesInscripcion == mesActual) {
				inscriptosMesActual.add(inscripcion);

				for (PlanDeInversion planDeInversion : inscripcion.getPlanes_de_inversion()) {
					for (Cuota cuota : planDeInversion.getCuotas()) {
						if (cuota.getFkIdEstadoCuota().getIdEstadoCuota() == 3 && cuota.getVencimiento().getMonth() == mesActual) {
							switch (planDeInversion.getFkIdPeriodo().getIdPeriodo().intValue()) {
							case 1:
								pendientesPP.add(cuota);
								break;
							case 2:
								pendientesSP.add(cuota);
								break;
							default:
								pendientesTP.add(cuota);
							}
						}
					}
				}

			}

		}

		model.addAttribute("listarTodo", listarTodo);

		model.addAttribute("inscriptosMesActual", inscriptosMesActual);
		
		model.addAttribute("pendientesPP", pendientesPP);
		model.addAttribute("pendientesSP", pendientesSP);
		model.addAttribute("pendientesTP", pendientesTP);
		
		double totalPP = 0, totalSP = 0, totalTP = 0;
		
		if(pendientesPP.size() > 0) {
			for(Cuota cuota : pendientesPP) {
				totalPP += cuota.getSaldoPendiente();
			}
		}
		
		if(pendientesPP.size() > 0) {
			for(Cuota cuota : pendientesSP) {
				totalSP += cuota.getSaldoPendiente();
			}
		}
		
		if(pendientesPP.size() > 0) {
			for(Cuota cuota : pendientesTP) {
				totalTP += cuota.getSaldoPendiente();
			}
		}
		
		model.addAttribute("totalPP", totalPP);
		model.addAttribute("totalSP", totalSP);
		model.addAttribute("totalTP", totalTP);

		model.addAttribute("totalInscripciones", calcularTotalInscripciones(listarTodo));
		model.addAttribute("actInscripciones", calcularTotalInscripciones(inscriptosMesActual));

		// CUANTOS ALUMNOS TIENEN MORA

		List<Alumno> alumnos = alumnoService.findAll();

		if (alumnos.size() > 0) {
			model.addAttribute("alumnosMora", calcularMoraAlumnos(alumnos));
			model.addAttribute("listaAlumnosEnMora", listaAlumnosEnMora(alumnos));
		}

		return "tablero";
	}

	private double calcularTotalInscripciones(List<Inscripcion> inscripciones) {

		double total = 0.0;

		for (Inscripcion inscripcion : inscripciones) {
			total += inscripcion.getAbonado();
		}

		return total;
	}

	private int calcularMoraAlumnos(List<Alumno> alumnos) {

		int cantidad = 0;

		for (Alumno alumno : alumnos) {
			if (alumno.existeDeuda()) {
				cantidad++;
			}
		}

		return cantidad;
	}

	private List<Alumno> listaAlumnosEnMora(List<Alumno> alumnos) {

		List<Alumno> listaActualizada = new ArrayList<>();

		for (Alumno alumno : alumnos) {
			if (alumno.existeDeuda()) {
				listaActualizada.add(alumno);
			}
		}

		return listaActualizada;
	}

}
