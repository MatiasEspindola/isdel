/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

/**
 *
 * @author matia
 */
@Entity
@Table(name="estados_cuotas")
public class EstadoCuota {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_cuota")
    private long idEstadoCuota;
    
    @Column(name = "estado_cuota")
    private String EstadoCuota;
    
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    @JoinColumn(name = "fk_id_estado_cuota")
    private List<Cuota> Cuotas;

    public long getIdEstadoCuota() {
        return idEstadoCuota;
    }

    public void setIdEstadoCuota(long idEstadoCuota) {
        this.idEstadoCuota = idEstadoCuota;
    }

    public String getEstadoCuota() {
        return EstadoCuota;
    }

    public void setEstadoCuota(String EstadoCuota) {
        this.EstadoCuota = EstadoCuota;
    }

    public List<Cuota> getCuotas() {
        return Cuotas;
    }

    public void setCuotas(List<Cuota> Cuotas) {
        this.Cuotas = Cuotas;
    }
    
    
    
}
