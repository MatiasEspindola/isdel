package com.sysbye.softIsdel.models.entities;

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

//@Entity
//@Table(name = "Asesores")
public class Asesor {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id_asesor")
//    private long IdAsesor;
//
//    @Column(name = "nombre")
//    private String Nombre;
//
//    @Column(name = "apellido")
//    private String Apellido;
//
//    @Column(name = "dni")
//    private String Dni;
//
//    @Column(name = "tel")
//    private String Tel;
//
//    @Column(name = "cel")
//    private String Cel;
//
//    @LazyCollection(LazyCollectionOption.FALSE)
//    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
//    @JsonIgnore
//    @JoinColumn(name = "fk_id_asesor")
//    private List<Inscripcion> Inscripciones;
//
//    public long getIdAsesor() {
//        return IdAsesor;
//    }
//
//    public void setIdAsesor(long idAsesor) {
//        IdAsesor = idAsesor;
//    }
//
//    public String getNombre() {
//        return Nombre;
//    }
//
//    public void setNombre(String nombre) {
//        Nombre = nombre;
//    }
//
//    public String getApellido() {
//        return Apellido;
//    }
//
//    public void setApellido(String apellido) {
//        Apellido = apellido;
//    }
//
//    public String getDni() {
//        return Dni;
//    }
//
//    public void setDni(String dni) {
//        Dni = dni;
//    }
//
//    public String getTel() {
//        return Tel;
//    }
//
//    public void setTel(String tel) {
//        Tel = tel;
//    }
//
//    public String getCel() {
//        return Cel;
//    }
//
//    public void setCel(String cel) {
//        Cel = cel;
//    }
//
//    public List<Inscripcion> getInscripciones() {
//        return Inscripciones;
//    }
//
//    public void setInscripciones(List<Inscripcion> inscripciones) {
//        Inscripciones = inscripciones;
//    }

}
