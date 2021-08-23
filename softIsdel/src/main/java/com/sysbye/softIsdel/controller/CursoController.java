package com.sysbye.softIsdel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sysbye.softIsdel.models.entities.Curso;
import com.sysbye.softIsdel.models.entities.Inscripcion;
import com.sysbye.softIsdel.models.entities.PlanDeInversion;
import com.sysbye.softIsdel.services.ICategoriaCursoService;
import com.sysbye.softIsdel.services.ICursoService;
import com.sysbye.softIsdel.services.IInscripcionService;
import com.sysbye.softIsdel.services.IPlanDeInversionService;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("curso")
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private ICursoService cursoService;

    @Autowired
    private ICategoriaCursoService categoriaCursoService;

    @Autowired
    private IInscripcionService inscripcionService;

    @Autowired
    private IPlanDeInversionService planDeInversionService;

    private boolean agregar;

    @GetMapping(value = "/detallesCurso/{id}")
    public String detallesCurso(@PathVariable("id") long id, Model model) {

    	if(cursoService.findOne(id) == null) {
    		return "redirect:/cursos/listarCursos";
    	}
    	
        model.addAttribute("curso", cursoService.findOne(id).getNombre());
        model.addAttribute("categoriaCurso", cursoService.findOne(id).getFkIdCategoriaCurso().getCategoria());

        //Buscar Inscripciones de este Curso
        List<Inscripcion> inscripcionesPorCurso = inscripcionService.buscarPorTerm(cursoService.findOne(id).getNombre());

        System.out.println(inscripcionesPorCurso);

        model.addAttribute("inscripcionesPorCurso", inscripcionesPorCurso);

        return "cursos/detallesCurso";
    }

    @GetMapping(value = "/listarCursos")
    public String listarCursos(Model model) {

        model.addAttribute("titulo", "Lista de Cursos");
        model.addAttribute("cursos", cursoService.findAll());

        return "cursos/listarCursos";

    }

    @GetMapping("/formCurso")
    public String crearCurso(Model model) {

        agregar = true;

        Curso curso = new Curso();
        model.addAttribute("curso", curso);
        model.addAttribute("titulo", "Formulario de Curso");
        model.addAttribute("categorias_cursos", categoriaCursoService.findAll());

        return "cursos/formCurso";

    }

    @GetMapping("/formCurso/{id}")
    public String editar(@PathVariable(value = "id") Long id, Model model) {

    	if(cursoService.findOne(id) == null) {
    		return "redirect:/cursos/listarCursos";
    	}
    	
        agregar = false;

        Curso curso = cursoService.findOne(id);
        model.addAttribute("categorias_cursos", categoriaCursoService.findAll());

        model.addAttribute("curso", curso);
        model.addAttribute("titulo", "Editar Curso");

        return "cursos/formCurso";
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id) {

    	if(cursoService.findOne(id) == null) {
    		return "redirect:/cursos/listarCursos";
    	}
    	
        cursoService.delete(id);

        return "redirect:/cursos/listarCursos";
    }

    @PostMapping("/formCurso")
    public String guardar(@Valid Curso curso, RedirectAttributes redirAttrs) {
    	
    		if ( curso.existeCurso(agregar, cursoService.findAll())) {
    			
    			redirAttrs.addFlashAttribute("error",
    					"Error, ya se encuentra el curso " + curso.getNombre() + " registrado en el Sistema");
    			
    			return "redirect:/cursos/listarCursos";
    			
    		} else {
    		
    			if(agregar) {
    				
    				redirAttrs.addFlashAttribute("exito",
    						"Se ha añadido el alumno con el dni " + curso.getNombre() + " con éxito");
    				
    			}else {
    				redirAttrs.addFlashAttribute("exito",
        					"Se ha editado el curso " + curso.getNombre() + " con éxito");
    			}
    			
    		}
       
            cursoService.save(curso);

//            List<Inscripcion> inscripciones = inscripcionService.buscarInscripcionesPorCurso(curso);
//
//            for (Inscripcion inscripcion : inscripciones) {
//
//                for (PlanDeInversion planDeInversion : inscripcion.getPlanes_de_inversion()) {
//
//                    int cont = 0;
//                    int mes = inscripcion.getFechaInicio().getMonth() + 1;
//
//                    for (Cuota cuota : planDeInversion.getCuotas()) {
//                        Date fecha = new Date();
//                        fecha.setMonth(mes + cont);
//
//                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//                        Calendar calendar = Calendar.getInstance();
//                        calendar.setTime(fecha);
//
//                        switch (planDeInversion.getFkIdPeriodo().getIdPeriodo().intValue()) {
//                            case 1:
//                                fecha.setDate(10);
//                                break;
//                            case 2:
//                                fecha.setDate(20);
//                                break;
//                            default:
//                                fecha.setDate(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//
//                        }
//
//                        cuota.setVencimiento(fecha);
//                        cuotaService.save(cuota);
//                        cont++;
//
//                    }
//
//                }

//            }

        

        return "redirect:/cursos/listarCursos";

    }

}
