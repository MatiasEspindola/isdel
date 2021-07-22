package com.sysbye.softIsdel.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sysbye.softIsdel.models.entities.Curso;
import com.sysbye.softIsdel.models.entities.Role;
import com.sysbye.softIsdel.models.entities.Usuario;
import com.sysbye.softIsdel.repo.IAuthoritiesRepo;
import com.sysbye.softIsdel.repo.IRolRepo;
import com.sysbye.softIsdel.repo.IUsuarioRepo;

@Controller
@SessionAttributes("usuario")
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private IUsuarioRepo repo;

	@Autowired
	private IAuthoritiesRepo aut;

	@Autowired
	private IRolRepo rol;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping(value = "/formUsuario")
	public String crearUsuario(Model model) {

		Usuario usuario = new Usuario();

		model.addAttribute("usuario", usuario);

		return "usuarios/formUsuario";
	}

	@PostMapping("/formUsuario")
	public String guardar(@Valid Usuario usuario, @RequestParam(name = "idRol") Long idRol) {

		Date fechaAlta = new Date();

		usuario.setEnabled(true);
		usuario.setFecha_alta(fechaAlta);
		usuario.setPassword1(usuario.getPassword());
		String bcryptPassword = passwordEncoder.encode(usuario.getPassword());
		usuario.setPassword(bcryptPassword);

		repo.save(usuario);

		Role role = new Role();

		role.setFkIdRol(rol.findById(idRol).orElse(null));
		role.setUser_id(usuario.getId());

		aut.save(role);

		return "redirect:/usuarios/listarUsuarios";
	}
	
	

}
