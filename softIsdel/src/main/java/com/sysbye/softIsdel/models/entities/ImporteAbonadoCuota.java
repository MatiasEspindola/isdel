/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.models.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author matia
 */
@Entity
@Table(name = "importes_abonados_cuotas")
public class ImporteAbonadoCuota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_importe_abonado_cuota")
    private long IdImporteAbonado;

    @Column(name = "importe_abonado")
    private float ImporteAbonado;

    @Column(name = "observacion")
    private String observacion;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha")
    private Date Fecha;

    @JoinColumn(name = "fk_id_cuota", referencedColumnName = "id_cuota")
    @ManyToOne(fetch = FetchType.EAGER)
    private Cuota fkIdCuota;

    public long getIdImporteAbonado() {
        return IdImporteAbonado;
    }

    public void setIdImporteAbonado(long IdImporteAbonado) {
        this.IdImporteAbonado = IdImporteAbonado;
    }

    public float getImporteAbonado() {
        return ImporteAbonado;
    }

    public void setImporteAbonado(float ImporteAbonado) {
        this.ImporteAbonado = ImporteAbonado;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public Cuota getFkIdCuota() {
        return fkIdCuota;
    }

    public void setFkIdCuota(Cuota fkIdCuota) {
        this.fkIdCuota = fkIdCuota;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

}
