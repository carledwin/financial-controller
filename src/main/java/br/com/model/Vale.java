package br.com.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * @author carledwin
 */
@Entity
public class Vale implements Serializable, AbstractEntity {

	private static final long serialVersionUID = 5024275771519676714L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private BigDecimal valor;
	
	@Enumerated(EnumType.STRING)
	private StatusValeEnum status;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataVale;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataDebito;
	
	@ManyToOne
	private Profissional profissional;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public StatusValeEnum getStatus() {
		return status;
	}

	public void setStatus(StatusValeEnum status) {
		this.status = status;
	}

	public Calendar getDataVale() {
		return dataVale;
	}

	public void setDataVale(Calendar dataVale) {
		this.dataVale = dataVale;
	}

	public Calendar getDataDebito() {
		return dataDebito;
	}

	public void setDataDebito(Calendar dataDebito) {
		this.dataDebito = dataDebito;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

}
