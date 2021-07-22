package com.sysbye.softIsdel.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.LocalTime;
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

@Entity
@Table(name = "Inscripciones")
public class Inscripcion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_inscripcion")
	private Long IdInscripcion;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fecha_inscripcion")
	private Date fechaInscripcion;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "pago_acordado_cuota_1")
	private Date pagoAcordadoCuota1;

	@JoinColumn(name = "fk_id_alumno", referencedColumnName = "id_alumno")
	@ManyToOne(fetch = FetchType.EAGER)
	private Alumno fkIdAlumno;

	@JoinColumn(name = "fk_id_curso", referencedColumnName = "id_curso")
	@ManyToOne(fetch = FetchType.EAGER)
	private Curso fkIdCurso;

	@JoinColumn(name = "fk_id_usuario", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.EAGER)
	private Usuario fkIdUsuario;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JoinColumn(name = "fk_id_inscripcion")
	private List<PlanDeInversion> planes_de_inversion;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JsonIgnore
	@JoinColumn(name = "fk_id_insc")
	private List<ImporteAbonadoInscripcion> importes_abonados;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fecha_inicio_clases1")
	private Date FechaInicio1;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fecha_fin_clases1")
	private Date FechaFin1;

	@Column(name = "horario_clases1")
	private String Horario1;

	/*
	@Column(name = "horario_clases2")
	private String Horario2;
	*/

	@Column(name = "dias_de_cursada_clases1")
	private String DiasDeCursada1;

	/*
	@Column(name = "dias_de_cursada_clases2")
	private String DiasDeCursada2;
	*/

	@Column(name = "valor_inscripcion")
	private Double valorInscripcion;

	@Column(name = "abonado")
	private Double abonado;

	@Column(name = "saldo_pendiente")
	private Double saldoPendiente;

	public Double getSaldoPendiente() {
		return saldoPendiente;
	}

	public void setSaldoPendiente(Double saldoPendiente) {
		this.saldoPendiente = saldoPendiente;
	}

	public Double getValorInscripcion() {
		return valorInscripcion;
	}

	public void setValorInscripcion(Double valorInscripcion) {
		this.valorInscripcion = valorInscripcion;
	}

	public Double getAbonado() {
		return abonado;
	}

	public void setAbonado(Double abonado) {
		this.abonado = abonado;
	}

	public Date getFechaInicio1() {
		return FechaInicio1;
	}

	public void setFechaInicio1(Date FechaInicio1) {
		this.FechaInicio1 = FechaInicio1;
	}

	public Date getFechaFin1() {
		return FechaFin1;
	}

	public void setFechaFin1(Date fechaFin1) {
		FechaFin1 = fechaFin1;
	}

	public String getHorario1() {
		return Horario1;
	}

	public void setHorario1(String Horario1) {
		this.Horario1 = Horario1;
	}

	public String getDiasDeCursada1() {
		return DiasDeCursada1;
	}

	public void setDiasDeCursada1(String DiasDeCursada1) {
		this.DiasDeCursada1 = DiasDeCursada1;
	}


	public Long getIdInscripcion() {
		return IdInscripcion;
	}

	public void setIdInscripcion(Long IdInscripcion) {
		this.IdInscripcion = IdInscripcion;
	}

	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public Alumno getFkIdAlumno() {
		return fkIdAlumno;
	}

	public void setFkIdAlumno(Alumno fkIdAlumno) {
		this.fkIdAlumno = fkIdAlumno;
	}

	public Curso getFkIdCurso() {
		return fkIdCurso;
	}

	public void setFkIdCurso(Curso fkIdCurso) {
		this.fkIdCurso = fkIdCurso;
	}

	@JsonIgnore
	public List<PlanDeInversion> getPlanes_de_inversion() {
		return planes_de_inversion;
	}

	public void setPlanes_de_inversion(List<PlanDeInversion> planes_de_inversion) {
		this.planes_de_inversion = planes_de_inversion;
	}

	public List<ImporteAbonadoInscripcion> getImportes_abonados() {
		return importes_abonados;
	}

	public void setImportes_abonados(List<ImporteAbonadoInscripcion> importes_abonados) {
		this.importes_abonados = importes_abonados;
	}

	public Date getPagoAcordadoCuota1() {
		return pagoAcordadoCuota1;
	}

	public void setPagoAcordadoCuota1(Date pagoAcordadoCuota1) {
		this.pagoAcordadoCuota1 = pagoAcordadoCuota1;
	}

	public Usuario getFkIdUsuario() {
		return fkIdUsuario;
	}

	public void setFkIdUsuario(Usuario fkIdUsuario) {
		this.fkIdUsuario = fkIdUsuario;
	}

	@Override
	public String toString() {
		return "de " + fkIdAlumno.getApellido() + ", " + fkIdAlumno.getNombre() + ", " + fkIdAlumno.getNroAlumno()
				+ " en " + fkIdCurso.getNombre();
	}
	
	public boolean existeDeuda() {
		if(this.saldoPendiente > 0) {
			return true;
		}else {
			if(this.getPlanes_de_inversion().size() > 0) {
				for(PlanDeInversion planDeInversion : getPlanes_de_inversion()) {
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
