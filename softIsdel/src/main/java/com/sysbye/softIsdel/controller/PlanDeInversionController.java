/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.controller;

import com.sysbye.softIsdel.models.entities.Cuota;
import com.sysbye.softIsdel.models.entities.ImporteAbonadoInscripcion;
import com.sysbye.softIsdel.models.entities.Inscripcion;
import com.sysbye.softIsdel.models.entities.PlanDeInversion;
import com.sysbye.softIsdel.services.ICuotaService;
import com.sysbye.softIsdel.services.IEstadoCuotaService;
import com.sysbye.softIsdel.services.IImporteAbonadoCuotaService;
import com.sysbye.softIsdel.services.IImporteAbonadoInscripcionService;
import com.sysbye.softIsdel.services.IInscripcionService;
import com.sysbye.softIsdel.services.IPeriodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.sysbye.softIsdel.services.IPlanDeInversionService;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SessionAttributes("planDeInversion")
@RequestMapping("/planesdeinversion")
public class PlanDeInversionController {

	@Autowired
	private IPlanDeInversionService planDeInversionService;

	@Autowired
	private IImporteAbonadoCuotaService importeAbonadoCuotaService;

	@Autowired
	private IImporteAbonadoInscripcionService importeAbonadoInscripcionService;

	@Autowired
	private ICuotaService cuotaService;

	@Autowired
	private IEstadoCuotaService estadoCuotaService;

	@Autowired
	private IInscripcionService inscripcionService;

	@Autowired
	private IPeriodoService periodoService;

	private boolean editar;

	private boolean abonoInscripcionPendiente;

	private long idInscripcion;

	private int cantCuotas1;
	private int cantCuotas2;

	@GetMapping("/detallesPlanDeInversion/{id}")
	public String detallesPlanDeInversion(@PathVariable(value = "id") Long id, Model model) {

		PlanDeInversion planDeInversion = planDeInversionService.findOne(id);

		if (planDeInversion == null) {
			return "redirect:/inscripciones/listarInscripciones";
		} else {

			Inscripcion inscripcion = planDeInversionService.findOne(id).getFkIdInscripcion();

			model.addAttribute("planesdeinversion", planDeInversion);

			model.addAttribute("inscripcion", inscripcion);

			model.addAttribute("cuotas", cuotaService.buscarPorPlanDeInversion(planDeInversion));

			model.addAttribute("importesInscripcion",
					importeAbonadoInscripcionService.buscarPorInscripcion(inscripcion));

			model.addAttribute("montoAbonado", inscripcion.getAbonado());

			double saldoPendienteTotal = inscripcion.getSaldoPendiente();
			int cuotasVencidas = 0;
			int cuotasAlDia = 0;
			int cuotasPagadas = 0;

			for (Cuota cuota : cuotaService.buscarPorPlanDeInversion(planDeInversionService.findOne(id))) {

				if (cuota.isPagado()) {
					cuotasPagadas++;
				} else {
					if(cuota.getFkIdEstadoCuota().getIdEstadoCuota() == 3) {
						cuotasVencidas++;
					} else {
						Date fechaActual = new Date();
						if (cuota.getVencimiento().before(fechaActual)) {
							
							if (cuota.isCobrar_ajuste()) {

								float interes = 0;

								if (cuota.getGrupo() == 1) {

									interes = (float) cuota.getFkIdPlanDeInversion().getValorCuota1() * 20 / 100;
									cuota.setSaldoPendiente(
											(float) cuota.getFkIdPlanDeInversion().getValorCuota1() + interes);

								} else {

									interes = (float) cuota.getFkIdPlanDeInversion().getValorCuota2() * 20 / 100;
									cuota.setSaldoPendiente(
											(float) cuota.getFkIdPlanDeInversion().getValorCuota2() + interes);

								}

								cuota.setFkIdEstadoCuota(estadoCuotaService.findAll().get(2));
								cuotaService.save(cuota);
								cuotasVencidas++;
							}

						} else {
							cuotasAlDia++;
						}
					}
				}

				saldoPendienteTotal += cuota.getSaldoPendiente();
			}

			model.addAttribute("saldoPendienteTotal", saldoPendienteTotal);

			model.addAttribute("cuotasAlDia", cuotasAlDia);
			model.addAttribute("cuotasVencidas", cuotasVencidas);
			model.addAttribute("cuotasPagadas", cuotasPagadas);

		}

		return "planesdeinversion/detallesPlanDeInversion";
	}

	@GetMapping(value = "/cargar_inscripcion/{term}", produces = { "application/json" })
	public @ResponseBody List<Inscripcion> cargarInscripcion(@PathVariable String term) {
		return inscripcionService.buscarPorTerm(term);
	}

	@GetMapping(value = "/formPlanDeInversion/{idInscripcion}")
	public String crearPlanDeInversion(Model model, @PathVariable("idInscripcion") long idInscripcion) {

		editar = false;

		Inscripcion inscripcion = inscripcionService.findOne(idInscripcion);

		if (inscripcion == null) {
			return "redirect:/inscripciones/listarInscripciones";
		}

		if (inscripcion.getPlanes_de_inversion().size() == 1) {
			return "redirect:/inscripciones/detallesInscripcion/" + inscripcion.getIdInscripcion();
		}

		model.addAttribute("idInscripcion", idInscripcion);

		model.addAttribute("inscripcion", inscripcion);
		model.addAttribute("idInscripcion", inscripcion.getIdInscripcion());

		model.addAttribute("planDeInversion", new PlanDeInversion());
		model.addAttribute("periodos", periodoService.findAll());
		model.addAttribute("editar", editar);

		this.idInscripcion = idInscripcion;

		return "planesdeinversion/formPlanDeInversion";
	}

//    @GetMapping("/editar/{id}")
//    public String editar(@PathVariable(value = "id") Long id, Model model) {
//
//        this.cantCuotas1 = planDeInversionService.findOne(id).getCantidadCuotas1();
//        this.cantCuotas2 = planDeInversionService.findOne(id).getCantidadCuotas2();
//
//        editar = true;
//
//        model.addAttribute("planDeInversion", planDeInversionService.findOne(id));
//
//        Inscripcion inscripcion = planDeInversionService.findOne(id).getFkIdInscripcion();
//
//        model.addAttribute("inscripcion", inscripcion);
//
//        idInscripcion = inscripcion.getIdInscripcion();
//
//        model.addAttribute("periodos", periodoService.findAll());
//        model.addAttribute("editar", editar);
//
//        return "planesdeinversion/formPlanDeInversion";
//    }
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {

		idInscripcion = planDeInversionService.findOne(id).getFkIdInscripcion().getIdInscripcion();

		if (planDeInversionService.findOne(id) == null) {
			return "redirect:/inscripciones/detallesInscripcion/" + idInscripcion;
		}

		for (ImporteAbonadoInscripcion importe : importeAbonadoInscripcionService
				.buscarPorInscripcion(planDeInversionService.findOne(id).getFkIdInscripcion())) {
			System.out.println(importe);
			System.out.println("borrado");
			importeAbonadoInscripcionService.delete(importe);
		}

		planDeInversionService.delete(id);

		return "redirect:/inscripciones/detallesInscripcion/" + idInscripcion;
	}

	@RequestMapping(value = "/cobrar_recargo/{id}")
	public String cobrar_recargo(@PathVariable(value = "id") Long id) {

		Cuota cuota = cuotaService.findOne(id);

		if (cuota.isCobrar_ajuste()) {
			cuota.setCobrar_ajuste(false);

			if (cuota.getGrupo() == 1) {

				cuota.setSaldoPendiente((float) cuota.getFkIdPlanDeInversion().getValorCuota1());

			} else {

				cuota.setSaldoPendiente((float) cuota.getFkIdPlanDeInversion().getValorCuota2());

			}

		} else {

			cuota.setCobrar_ajuste(true);

			float interes = cuota.getSaldoPendiente() * 20 / 100;
			cuota.setSaldoPendiente(cuota.getSaldoPendiente() + interes);

		}

		cuotaService.save(cuota);

		return "redirect:/planesdeinversion/detallesPlanDeInversion/"
				+ cuota.getFkIdPlanDeInversion().getIdPlanDeInversion();
	}

	@PostMapping(value = "/formPlanDeInversion")
	public String guardar(@Valid PlanDeInversion planDeInversion) {

		planDeInversion.setFkIdInscripcion(inscripcionService.findOne(idInscripcion));

		planDeInversionService.save(planDeInversion);

		if (!editar) {
			crearCuotas(planDeInversion.getCantidadCuotas1(), planDeInversion.getCantidadCuotas2(),
					planDeInversion.getValorCuota1(), planDeInversion.getValorCuota2(),
					planDeInversion.getFkIdInscripcion().getFechaInicio1(), planDeInversion);
		}

		return "redirect:/inscripciones/detallesInscripcion/" + idInscripcion;
	}

	private void crearCuotas(int cantidadCuotas1, int cantidadCuotas2, double valorCuota1, double valorCuota2,
			Date fechaInicioClases1, PlanDeInversion planDeInversion) {

		int mes = fechaInicioClases1.getMonth();
		int dia = 0;

		/* Se crea únicamente cuando se añade un nuevo Plan de Inversión */
		for (int i = 0; i < cantidadCuotas1; i++) {

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

			Cuota cuota_1 = new Cuota();

			cuota_1.setSaldoPendiente((float) valorCuota1);
			if (i == 0) {
				cuota_1.setVencimiento(planDeInversion.getFkIdInscripcion().getPagoAcordadoCuota1());
			} else {
				cuota_1.setVencimiento(fecha);
			}
			cuota_1.setFkIdEstadoCuota(estadoCuotaService.findAll().get(0));
			cuota_1.setFkIdPlanDeInversion(planDeInversion);
			cuota_1.setGrupo(1);
			cuota_1.setCobrar_ajuste(true);
			cuotaService.save(cuota_1);
		}

		for (int i = 0; i < cantidadCuotas2; i++) {

			Date fecha = new Date();
			fecha.setMonth(mes + i + cantidadCuotas1);

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

			Cuota cuota_2 = new Cuota();

			cuota_2.setSaldoPendiente((float) valorCuota2);
			cuota_2.setVencimiento(fecha);
			cuota_2.setFkIdEstadoCuota(estadoCuotaService.findAll().get(0));
			cuota_2.setFkIdPlanDeInversion(planDeInversion);
			cuota_2.setGrupo(2);
			cuota_2.setCobrar_ajuste(true);
			cuotaService.save(cuota_2);
		}
	}

}
