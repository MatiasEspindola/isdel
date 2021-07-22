package com.sysbye.softIsdel.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Date;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "alumnos")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alumno")
    private long IdAlumno;

    @Column(name = "nombre")
    private String Nombre;

    @Column(name = "apellido")
    private String Apellido;

    @Column(name = "dni")
    private String Dni;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_nacimiento")
    private Date Fecha_nacimiento;

    @Column(name = "direccion")
    private String Direccion;

    @Column(name = "barrio")
    private String Barrio;

    @Column(name = "localidad")
    private String Localidad;

    @Column(name = "tel_1")
    private String Tel_1;

    @Column(name = "tel_2")
    private String Tel_2;

    @Column(name = "cel_1")
    private String Cel_1;

    @Column(name = "cel_2")
    private String Cel_2;

    @Column(name = "nro_alumno")
    private String NroAlumno;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "fk_id_alumno")
    private List<Inscripcion> Inscripciones;

    @JoinColumn(name = "fk_id_responsable", referencedColumnName = "id_responsable")
    @ManyToOne(fetch = FetchType.EAGER)
    private Responsable fkIdResponsable;

    public long getIdAlumno() {
        return IdAlumno;
    }

    public void setIdAlumno(long IdAlumno) {
        this.IdAlumno = IdAlumno;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String Dni) {
        this.Dni = Dni;
    }

    public Date getFecha_nacimiento() {
        return Fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date Fecha_nacimiento) {
        this.Fecha_nacimiento = Fecha_nacimiento;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getBarrio() {
        return Barrio;
    }

    public void setBarrio(String Barrio) {
        this.Barrio = Barrio;
    }

    public String getLocalidad() {
        return Localidad;
    }

    public void setLocalidad(String Localidad) {
        this.Localidad = Localidad;
    }

    public String getTel_1() {
        return Tel_1;
    }

    public void setTel_1(String Tel_1) {
        this.Tel_1 = Tel_1;
    }

    public String getTel_2() {
        return Tel_2;
    }

    public void setTel_2(String Tel_2) {
        this.Tel_2 = Tel_2;
    }

    public String getCel_1() {
        return Cel_1;
    }

    public void setCel_1(String Cel_1) {
        this.Cel_1 = Cel_1;
    }

    public String getCel_2() {
        return Cel_2;
    }

    public void setCel_2(String Cel_2) {
        this.Cel_2 = Cel_2;
    }

    public String getNroAlumno() {
        return NroAlumno;
    }

    public void setNroAlumno(String NroAlumno) {
        this.NroAlumno = NroAlumno;
    }

    @JsonIgnore
    public List<Inscripcion> getInscripciones() {
        return Inscripciones;
    }

    public void setInscripciones(List<Inscripcion> Inscripciones) {
        this.Inscripciones = Inscripciones;
    }

    public Responsable getFkIdResponsable() {
        return fkIdResponsable;
    }

    public void setFkIdResponsable(Responsable fkIdResponsable) {
        this.fkIdResponsable = fkIdResponsable;
    }
    
    public boolean existeDeuda() {
    	if(this.getInscripciones().size() > 0) {
    		for(Inscripcion inscripcion : this.getInscripciones()) {
    			for(PlanDeInversion planDeInversion : inscripcion.getPlanes_de_inversion()) {
    				for(Cuota cuota : planDeInversion.getCuotas()) {
    					if(cuota.getFkIdEstadoCuota().getIdEstadoCuota() == 3) {
    						return true;
    					}
    				}
    			}
    		}
    	}
    	
    	return false;
    }

}
