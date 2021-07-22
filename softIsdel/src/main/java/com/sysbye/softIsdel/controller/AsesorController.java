package com.sysbye.softIsdel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sysbye.softIsdel.models.entities.Asesor;
import com.sysbye.softIsdel.services.IAsesorService;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
//@RequestMapping("/asesores")
public class AsesorController {

//	@Autowired
//	IAsesorService asesorService;
//
//	@GetMapping(value = "listarAsesores")
//	public String listarAsesores(Model model) {
//
//		model.addAttribute("titulo", "lista de Asesores");
//		model.addAttribute("asesores", asesorService.findAll());
//
//		return "asesores/listarAsesores";
//	}
//
//	@GetMapping(value = "formAsesor")
//	public String crearAsesor(Model model) {
//
//		Asesor asesor = new Asesor();
//		model.addAttribute("asesor", asesor); // hola aca esta la referencia que se pasa en el form
//
//		return "formAsesor";
//
//	}
//
//	@GetMapping("/formAsesor/{id}")
//	public String editar(@PathVariable(value = "id") Long id, Model model) {
//
//		Asesor asesor = new Asesor();
//		asesor = asesorService.findOne(id);
//
//		model.addAttribute("asesor", asesor);
//
//		return "formAsesor";
//	}
//
//	@RequestMapping(value = "/eliminar/{id}")
//	public String eliminar(@PathVariable(value = "id") Long id) {
//
//		asesorService.delete(id);
//
//		return "redirect:/listarAsesores";
//	}
//
//	@PostMapping("formAsesor")
//	public String guardar(@Valid Asesor asesor) {
//
//		asesorService.save(asesor);
//		return "redirect:/listarAsesores";
//	}
}
