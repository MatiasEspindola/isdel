package com.sysbye.softIsdel.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.sysbye.softIsdel.models.entities.Alumno;
import com.sysbye.softIsdel.models.entities.Responsable;
import com.sysbye.softIsdel.services.IAlumnoService;
import com.sysbye.softIsdel.services.ICursoService;
import com.sysbye.softIsdel.services.IInscripcionService;
import com.sysbye.softIsdel.services.IResponsableService;
import java.util.List;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SessionAttributes("alumno")
@RequestMapping("/alumnos")
public class AlumnoController {

    @Autowired
    private IAlumnoService alumnoService;

    @Autowired
    private ICursoService cursoService;

    @Autowired
    private IInscripcionService inscripcionService;

    @Autowired
    private IResponsableService responsableService;

    private boolean editar;

    private Alumno alumno;

    @GetMapping(value = "/cargar_responsable/{term}", produces = {"application/json"})
    public @ResponseBody
    List<Responsable> cargarResponsable(@PathVariable String term) {
        return responsableService.buscarPorTerm(term);
    }

    @GetMapping("/listarAlumnos")
    public String listarAlumnos(Model model) {

        model.addAttribute("titulo", "lista de Alumnos");
        model.addAttribute("alumnos", alumnoService.findAll());

        return "alumnos/listarAlumnos";
    }

    @GetMapping("/detallesAlumno/{id}")
    public String detallesAlumno(@PathVariable(value = "id") Long id, Model model) {

    	if(alumnoService.findOne(id) == null) {
    		return "redirect:/alumnos/listarAlumnos";
    	}
    	
        model.addAttribute("alumno", alumnoService.findOne(id));
        model.addAttribute("inscripciones", inscripcionService.buscarInscripcionesDelAlumno(alumnoService.findOne(id)));

        return "alumnos/detallesAlumno";
    }

    @GetMapping("/formAlumno")
    public String crearAlumno(Model model) {
        alumno = new Alumno();
        editar = false;

        model.addAttribute("editar", editar);

        model.addAttribute("alumno", alumno);

        model.addAttribute("titulo", "Formulario de Alumno");
        return "alumnos/formAlumno";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable(value = "id") Long id, Model model) {

    	if(alumnoService.findOne(id) == null) {
    		return "redirect:/alumnos/listarAlumnos";
    	}
    	
        alumno = alumnoService.findOne(id);

        editar = true;

        model.addAttribute("alumno", alumno);
        model.addAttribute("editar", editar);
        model.addAttribute("responsable", alumno.getFkIdResponsable().getApellido() + ", " + alumno.getFkIdResponsable().getNombre());
        model.addAttribute("titulo", "Editar Alumno");

        return "/alumnos/formAlumno";
    }

//
    @RequestMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id) {

    	if(alumnoService.findOne(id) == null) {
    		return "redirect:/alumnos/listarAlumnos";
    	}
    	
        alumnoService.delete(id);
        return "redirect:/alumnos/listarAlumnos";
    }

    @PostMapping("/formAlumno")
    public String guardarAlumno(@Valid Alumno alumno) {
        alumnoService.save(alumno);
        return "redirect:/alumnos/listarAlumnos";
    }
}
