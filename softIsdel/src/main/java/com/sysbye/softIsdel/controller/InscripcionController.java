package com.sysbye.softIsdel.controller;

import com.sysbye.softIsdel.models.entities.Alumno;
import com.sysbye.softIsdel.models.entities.Cuota;
import com.sysbye.softIsdel.models.entities.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sysbye.softIsdel.models.entities.Inscripcion;
import com.sysbye.softIsdel.models.entities.PlanDeInversion;
import com.sysbye.softIsdel.models.entities.Usuario;
import com.sysbye.softIsdel.repo.IUsuarioRepo;
import com.sysbye.softIsdel.services.IAlumnoService;
import com.sysbye.softIsdel.services.ICuotaService;
import com.sysbye.softIsdel.services.ICursoService;
import com.sysbye.softIsdel.services.IInscripcionService;
import com.sysbye.softIsdel.services.IPeriodoService;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.sysbye.softIsdel.services.IPlanDeInversionService;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
@SessionAttributes("inscripcion")
@RequestMapping("/inscripciones")
public class InscripcionController {

	@Autowired
	private IInscripcionService inscripcionService;

	@Autowired
	private IPlanDeInversionService planDeInversionService;

	@Autowired
	private ICursoService cursoService;

	@Autowired
	private IAlumnoService alumnoService;

	@Autowired
	private IPeriodoService periodoService;

	@Autowired
	private ICuotaService cuotaService;

	@Autowired
	private IUsuarioRepo usuarioRepo;

	private boolean editar;

	@GetMapping(value = "/cargar_alumno/{term}", produces = { "application/json" })
	public @ResponseBody List<Alumno> cargarAlumno(@PathVariable String term) {
		return alumnoService.buscarPorTerm(term);
	}

	@GetMapping(value = "/cargar_curso/{term}", produces = { "application/json" })
	public @ResponseBody List<Curso> cargarCurso(@PathVariable String term) {
		return cursoService.buscarPorTerm(term);
	}

	@GetMapping("/detallesInscripcion/{id}")
	public String detallesInscripcion(@PathVariable(value = "id") Long id, Model model) {

		Inscripcion inscripcion = inscripcionService.findOne(id);

		if (inscripcion == null) {
			return "redirect:/inscripciones/listarInscripciones";
		} else {
			PlanDeInversion planesdeinversion = planDeInversionService.buscarPorInscripcion(inscripcion);

			model.addAttribute("inscripcion", inscripcion);
			model.addAttribute("alumno", inscripcion.getFkIdAlumno().getApellido() + ", "
					+ inscripcion.getFkIdAlumno().getNombre() + ", " + inscripcion.getFkIdAlumno().getNroAlumno());
			model.addAttribute("curso", inscripcion.getFkIdCurso());

			model.addAttribute("planesdeinversion", planesdeinversion);
		}

		return "inscripciones/detallesInscripcion";
	}

	@GetMapping(value = "/listarInscripciones")
	public String inscripciones(Model model) {

		List<Inscripcion> inscripciones = inscripcionService.listarInscripciones();

		model.addAttribute("inscripciones", inscripciones);

		return "inscripciones/listarInscripciones";
	}

	@GetMapping(value = "/formInscripcion")
	public String crearInscripcion(Model model) {

		Inscripcion inscripcion = new Inscripcion();

		editar = false;

		model.addAttribute("inscripcion", inscripcion);
		model.addAttribute("editar", editar);

		return "inscripciones/formInscripcion";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable(value = "id") Long id, Model model) {

		Inscripcion inscripcion = inscripcionService.findOne(id);

		if (inscripcion == null) {
			return "redirect:/inscripciones/listarInscripciones";
		}

		editar = true;

		model.addAttribute("editar", editar);
		model.addAttribute("inscripcion", inscripcion);
		model.addAttribute("alumno", inscripcion.getFkIdAlumno().getApellido() + ", "
				+ inscripcion.getFkIdAlumno().getNombre() + ", " + inscripcion.getFkIdAlumno().getNroAlumno());
		model.addAttribute("curso", inscripcion.getFkIdCurso().getNombre());

		return "inscripciones/formInscripcion";
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {

		if (inscripcionService.findOne(id) == null) {
			return "redirect:/inscripciones/listarInscripciones";
		}

		inscripcionService.delete(id);

		return "redirect:/inscripciones/listarInscripciones";
	}

	@PostMapping(value = "/formInscripcion")
	public String guardar(@Valid Inscripcion inscripcion, HttpServletRequest request) {

		for (Usuario usuario : usuarioRepo.findAll()) {
			if (usuario.getUsername().equals(request.getUserPrincipal().getName())) {
				inscripcion.setFkIdUsuario(usuario);
				break;
			}
		}

		inscripcion.setSaldoPendiente(inscripcion.getValorInscripcion() - inscripcion.getAbonado());

		inscripcionService.save(inscripcion);

		if (editar) {
			PlanDeInversion planDeInversion = planDeInversionService.buscarPorInscripcion(inscripcion);

			if (planDeInversion != null ) {
				modificarCuotas(inscripcion.getFechaInicio1(), planDeInversion, planDeInversion.getCuotas());
			}

		}

		return "redirect:/inscripciones/listarInscripciones";
	}

	public boolean comprobarDuplicacionDeDatos(Inscripcion inscripcion, boolean editar) {
		List<Inscripcion> inscripcionesDelAlumno = inscripcionService.listarInscripciones();

		if (editar) {
			for (Inscripcion ins : inscripcionesDelAlumno) {
				if (!inscripcion.getIdInscripcion().equals(ins.getIdInscripcion())) {
					if (inscripcion.getFkIdCurso().equals(ins.getFkIdCurso())
							&& inscripcion.getFkIdAlumno().equals(ins.getFkIdAlumno())) {
						System.out.println("Duplicación de Datos");
						return true;
					}
				}
			}
		} else {

			for (Inscripcion ins : inscripcionesDelAlumno) {
				if (inscripcion.getFkIdCurso().equals(ins.getFkIdCurso())
						&& inscripcion.getFkIdAlumno().equals(ins.getFkIdAlumno())) {
					System.out.println("Duplicación de Datos");
					return true;
				}
			}

		}

		return false;
	}

	private void modificarCuotas(Date fechaInicioClases1, PlanDeInversion planDeInversion, List<Cuota> cuotas) {

		cuotas.get(0).setVencimiento(planDeInversion.getFkIdInscripcion().getPagoAcordadoCuota1());

		int mes = fechaInicioClases1.getMonth();
		int dia = 0;

		/* Se crea únicamente cuando se añade un nuevo Plan de Inversión */
		for (int i = 1; i < cuotas.size(); i++) {

			Date fecha = new Date();
			fecha.setMonth(mes + i);

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fecha);

			switch (planDeInversion.getFkIdPeriodo().getIdPeriodo().intValue()) {
			case 1:
				fecha.setDate(10);
				break;
			case 2:
				fecha.setDate(20);
				break;
			default:
				fecha.setDate(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

			}

			Cuota cuota = cuotas.get(i);

			cuota.setVencimiento(fecha);
			cuotaService.save(cuota);
		}

	}

}
