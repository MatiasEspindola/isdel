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
@Table(name = "periodos")
public class Periodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_periodo")
    private Long IdPeriodo;

    @Column(name = "periodo")
    private String Periodo;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    @JoinColumn(name = "fk_id_periodo")
    private List<PlanDeInversion> planes_de_pago;

    public Long getIdPeriodo() {
        return IdPeriodo;
    }

    public void setIdPeriodo(Long IdPeriodo) {
        this.IdPeriodo = IdPeriodo;
    }

    public String getPeriodo() {
        return Periodo;
    }

    public void setPeriodo(String Periodo) {
        this.Periodo = Periodo;
    }

    public List<PlanDeInversion> getPlanes_de_pago() {
        return planes_de_pago;
    }

    public void setPlanes_de_pago(List<PlanDeInversion> planes_de_pago) {
        this.planes_de_pago = planes_de_pago;
    }

}
