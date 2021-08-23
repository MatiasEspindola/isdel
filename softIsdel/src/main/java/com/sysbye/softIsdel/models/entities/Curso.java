package com.sysbye.softIsdel.models.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalTime;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "Cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private long IdCurso;

    @Column(name = "nombre")
    private String Nombre;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "fk_id_curso")
    private List<Inscripcion> Inscripciones;

    @JoinColumn(name = "fk_id_categoria_curso", referencedColumnName = "id_categoria_curso")
    @ManyToOne(fetch = FetchType.EAGER)
    private CategoriaCurso fkIdCategoriaCurso;

    public CategoriaCurso getFkIdCategoriaCurso() {
        return fkIdCategoriaCurso;
    }

    public void setFkIdCategoriaCurso(CategoriaCurso fkIdCategoriaCurso) {
        this.fkIdCategoriaCurso = fkIdCategoriaCurso;
    }

    public long getIdCurso() {
        return IdCurso;
    }

    public void setIdCurso(long idCurso) {
        IdCurso = idCurso;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

   
    @JsonIgnore
    public List<Inscripcion> getInscripciones() {
        return Inscripciones;
    }

    public void setInscripciones(List<Inscripcion> inscripciones) {
        Inscripciones = inscripciones;
    }
    
public boolean existeCurso(boolean editar, List<Curso> cursos) {
		
		if(editar) {
			
			for(Curso curso : cursos) {
				if(this.IdCurso != curso.IdCurso) {
					if(this.Nombre.equals(curso.getNombre())) {
						return true;
					}
				}
			}
			
		}else {
			
			for(Curso curso : cursos) {
				
					if(this.Nombre.equals(curso.getNombre())) {
						return true;
					}
				
			}
			
		}
		
		return false;
	}

}
