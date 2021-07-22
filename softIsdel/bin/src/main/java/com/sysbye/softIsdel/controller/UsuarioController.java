package com.sysbye.softIsdel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("usuario")
@RequestMapping("/cuentas")
public class UsuarioController {

	@GetMapping(value = "/formCuenta")
	public String crearUsuario(Model model) {
		
		return "cuentas/formCuenta";
	}
	
}
