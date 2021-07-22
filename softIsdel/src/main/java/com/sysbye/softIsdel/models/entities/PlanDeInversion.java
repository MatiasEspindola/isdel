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
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "planes_de_inversion")
public class PlanDeInversion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plan_de_inversion")
    private Long IdPlanDeInversion;

    @JoinColumn(name = "fk_id_inscripcion", referencedColumnName = "id_inscripcion")
    @ManyToOne(fetch = FetchType.EAGER)
    private Inscripcion fkIdInscripcion;

    @Column(name = "cantidad_cuotas_1")
    private int cantidadCuotas1;

    @Column(name = "cantidad_cuotas_2")
    private int cantidadCuotas2;

    @Column(name = "valor_cuota_1")
    private double valorCuota1;

    @Column(name = "valor_cuota_2")
    private double valorCuota2;

    @Column(name = "costo_total")
    private double costoTotal;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "fk_id_plan_de_inversion")
    private List<Cuota> cuotas;

    @JoinColumn(name = "fk_id_periodo", referencedColumnName = "id_periodo")
    @ManyToOne(fetch = FetchType.EAGER)
    private Periodo fkIdPeriodo;

    public List<Cuota> getCuotas() {
        return cuotas;
    }

    public void setCuotas(List<Cuota> cuotas) {
        this.cuotas = cuotas;
    }

    public Long getIdPlanDeInversion() {
        return IdPlanDeInversion;
    }

    public void setIdPlanDeInversion(Long IdPlanDeInversion) {
        this.IdPlanDeInversion = IdPlanDeInversion;
    }

    public Inscripcion getFkIdInscripcion() {
        return fkIdInscripcion;
    }

    public void setFkIdInscripcion(Inscripcion fkIdInscripcion) {
        this.fkIdInscripcion = fkIdInscripcion;
    }

    public int getCantidadCuotas1() {
        return cantidadCuotas1;
    }

    public void setCantidadCuotas1(int cantidadCuotas1) {
        this.cantidadCuotas1 = cantidadCuotas1;
    }

    public int getCantidadCuotas2() {
        return cantidadCuotas2;
    }

    public void setCantidadCuotas2(int cantidadCuotas2) {
        this.cantidadCuotas2 = cantidadCuotas2;
    }

    public double getValorCuota1() {
        return valorCuota1;
    }

    public void setValorCuota1(double valorCuota1) {
        this.valorCuota1 = valorCuota1;
    }

    public double getValorCuota2() {
        return valorCuota2;
    }

    public void setValorCuota2(double valorCuota2) {
        this.valorCuota2 = valorCuota2;
    }

    public Periodo getFkIdPeriodo() {
        return fkIdPeriodo;
    }

    public void setFkIdPeriodo(Periodo fkIdPeriodo) {
        this.fkIdPeriodo = fkIdPeriodo;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

}
