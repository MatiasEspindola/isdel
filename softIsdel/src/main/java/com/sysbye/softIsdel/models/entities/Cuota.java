/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysbye.softIsdel.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import javax.persistence.Transient;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author matia
 */
@Entity
@Table(name = "cuotas")
public class Cuota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuota")
    private long IdCuota;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "vencimiento")
    private Date Vencimiento;

    @Column(name = "saldo_pendiente")
    private float saldoPendiente;
    
    @Column(name = "grupo")
    private int grupo;

    @JoinColumn(name = "fk_id_plan_de_inversion", referencedColumnName = "id_plan_de_inversion")
    @ManyToOne(fetch = FetchType.EAGER)
    private PlanDeInversion fkIdPlanDeInversion;


    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "fk_id_cuota")
    @JsonManagedReference
    @JsonIgnore
    private List<ImporteAbonadoCuota> importes_abonados;
    
    @JoinColumn(name = "fk_id_estado_cuota", referencedColumnName = "id_estado_cuota")
    @ManyToOne(fetch = FetchType.EAGER)
    private EstadoCuota fkIdEstadoCuota;
    
    @Column(name = "pagado")
    private boolean pagado;
    
    @Column(name = "cobrar_ajuste")
    private boolean cobrar_ajuste;

    public EstadoCuota getFkIdEstadoCuota() {
        return fkIdEstadoCuota;
    }

    public void setFkIdEstadoCuota(EstadoCuota fkIdEstadoCuota) {
        this.fkIdEstadoCuota = fkIdEstadoCuota;
    }
    
    

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public long getIdCuota() {
        return IdCuota;
    }

    public void setIdCuota(long IdCuota) {
        this.IdCuota = IdCuota;
    }

    public Date getVencimiento() {
        return Vencimiento;
    }

    public void setVencimiento(Date Vencimiento) {
        this.Vencimiento = Vencimiento;
    }

    public float getSaldoPendiente() {
        return saldoPendiente;
    }

    public void setSaldoPendiente(float saldoPendiente) {
        this.saldoPendiente = saldoPendiente;
    }

    public PlanDeInversion getFkIdPlanDeInversion() {
        return fkIdPlanDeInversion;
    }

    public void setFkIdPlanDeInversion(PlanDeInversion fkIdPlanDeInversion) {
        this.fkIdPlanDeInversion = fkIdPlanDeInversion;
    }

    public List<ImporteAbonadoCuota> getImportes_abonados() {
        return importes_abonados;
    }

    public void setImportes_abonados(List<ImporteAbonadoCuota> importes_abonados) {
        this.importes_abonados = importes_abonados;
    }

	public boolean isCobrar_ajuste() {
		return cobrar_ajuste;
	}

	public void setCobrar_ajuste(boolean cobrar_ajuste) {
		this.cobrar_ajuste = cobrar_ajuste;
	}

}
