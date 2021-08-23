/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author matia
 */
@Entity
@Table(name = "responsables")
public class Responsable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_responsable")
	private long IdResponsable;

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

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "fk_id_responsable")
	private List<Alumno> Alumnos;

	private boolean habilitado;
	
	@JoinColumn(name = "fk_id_usuario2", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.EAGER)
	private Usuario fkIdUsuario;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date alta;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date reestablecido;

	public long getIdResponsable() {
		return IdResponsable;
	}

	public void setIdResponsable(long IdResponsable) {
		this.IdResponsable = IdResponsable;
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

	@JsonIgnore
	public List<Alumno> getAlumnos() {
		return Alumnos;
	}

	public void setAlumnos(List<Alumno> Alumnos) {
		this.Alumnos = Alumnos;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public Usuario getFkIdUsuario() {
		return fkIdUsuario;
	}

	public void setFkIdUsuario(Usuario fkIdUsuario) {
		this.fkIdUsuario = fkIdUsuario;
	}

	public Date getAlta() {
		return alta;
	}

	public void setAlta(Date alta) {
		this.alta = alta;
	}

	public Date getReestablecido() {
		return reestablecido;
	}

	public void setReestablecido(Date reestablecido) {
		this.reestablecido = reestablecido;
	}

	public boolean existeResponsable(boolean editar, List<Responsable> responsables) {
		if (editar) {

			for (Responsable responsable : responsables) {
				if (responsable.getIdResponsable() != this.IdResponsable) {
					if (responsable.getDni().equals(this.Dni)) {
						return true;
					}
				}
			}

		} else {

			for (Responsable responsable : responsables) {
				if (responsable.getDni().equals(this.Dni)) {
					return true;
				}
			}

		}
		
		return false;
	}

}
