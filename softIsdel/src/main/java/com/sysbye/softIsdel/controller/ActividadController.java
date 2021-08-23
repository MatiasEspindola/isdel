package com.sysbye.softIsdel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ActividadController {

	@GetMapping("/actividad")
	public String actividad() {
		
		return "actividad";
	}
	
}
