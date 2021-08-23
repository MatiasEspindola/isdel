/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.controller;

import com.sysbye.softIsdel.models.entities.Responsable;
import com.sysbye.softIsdel.models.entities.Usuario;
import com.sysbye.softIsdel.repo.IUsuarioRepo;
import com.sysbye.softIsdel.services.IAlumnoService;
import com.sysbye.softIsdel.services.IResponsableService;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author matia
 */
@Controller
@SessionAttributes("responsable")
@RequestMapping("/responsables")
public class ResponsableController {

	@Autowired
	private IAlumnoService alumnoService;

	@Autowired
	private IResponsableService responsableService;
	
	@Autowired
	private IUsuarioRepo usuarioRepo;

	private boolean editar;

	@GetMapping("/listarResponsables")
	public String listarResponsables(Model model) {
		List<Responsable> responsables = responsableService.findAll();

		model.addAttribute("responsables", responsables);
		model.addAttribute("titulo", "Listar Responsables");

		return "responsables/listarResponsables";
	}

	@GetMapping("/detallesResponsable/{id}")
	public String detallesResponsable(@PathVariable(value = "id") Long id, Model model) {

		if (responsableService.findOne(id) == null) {
			return "redirect:/responsables/listarResponsables";
		}

		model.addAttribute("responsable", responsableService.findOne(id));

		model.addAttribute("alumnos", alumnoService.buscarPorResponsableOTutor(responsableService.findOne(id)));

		return "responsables/detallesResponsable";
	}

	@GetMapping("/formResponsable")
	public String crearResponsable(Model model) {
		Responsable responsable = new Responsable();

		editar = false;

		model.addAttribute("responsable", responsable);
		model.addAttribute("titulo", "Formulario de Responsable o Tutor");
		model.addAttribute("editar", editar);

		return "responsables/formResponsable";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable(value = "id") Long id, Model model) {

		if (responsableService.findOne(id) == null) {
			return "redirect:/responsables/listarResponsables";
		}

		Responsable responsable = responsableService.findOne(id);

		editar = true;

		model.addAttribute("responsable", responsable);
		model.addAttribute("editar", editar);

		model.addAttribute("titulo", "Editar Responsable o Tutor");

		return "/responsables/formResponsable";
	}

	@PostMapping("/formResponsable")
	public String guardarResponsable(@Valid Responsable responsable, RedirectAttributes redirAttrs,
			HttpServletRequest request) {

		if (responsable.existeResponsable(editar, responsableService.findAll())) {
			redirAttrs.addFlashAttribute("error",
					"Error, ya se encuentra registrado un responsable o tutor " + "con el dni " + responsable.getDni());
			return "redirect:/responsables/listarResponsables";
		}

		if (editar) {
			redirAttrs.addFlashAttribute("exito",
					"Se ha editado el responsable o tutor con el dni " + responsable.getDni() + " con éxito");
		} else {
			responsable.setHabilitado(true);
			
			for(Usuario usuario : usuarioRepo.findAll()) {
				if(usuario.getUsername().equals(request.getUserPrincipal().getName())) {
					responsable.setFkIdUsuario(usuario);
				}
			}
			
			Date fecha = new Date();

			responsable.setAlta(fecha);
			
			redirAttrs.addFlashAttribute("exito",
					"Se ha añadido un responsable o tutor con el dni " + responsable.getDni() + " con éxito");
		}
		
		responsableService.save(responsable);
		return "redirect:/responsables/listarResponsables";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, Model model, RedirectAttributes redirAttrs) {

		if (responsableService.findOne(id) == null) {
			redirAttrs.addFlashAttribute("error", "no se encuentró ningún responsable con ese {ID}");
			return "redirect:/responsables/listarResponsables";
		}

		if (responsableService.findOne(id).isHabilitado()) {
			redirAttrs.addFlashAttribute("exito", "Se ha eliminado el responsable o tutor con el dni "
					+ responsableService.findOne(id).getDni() + " con éxito");
			responsableService.findOne(id).setHabilitado(false);
		} else {
			
			Date fecha = new Date();
			
			responsableService.findOne(id).setReestablecido(fecha);
			
			redirAttrs.addFlashAttribute("exito", "Se ha reestablecido el responsable o tutor con el dni "
					+ responsableService.findOne(id).getDni() + " con éxito");
			responsableService.findOne(id).setHabilitado(true);
		}
		
		responsableService.save(responsableService.findOne(id));

		return "redirect:/responsables/listarResponsables";
	}

}
