package br.com.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
/**
 * @author carledwin
 */
@Entity
public class ItemComanda implements Serializable, AbstractEntity {

	private static final long serialVersionUID = 1513310569381995846L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne(fetch=FetchType.EAGER)
	private Servico servico;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Comanda comanda;

	@ManyToOne
	private Comissao comissao;
	
	@OneToOne(fetch=FetchType.EAGER)
	private Profissional profissional;
	
	private double desconto;
	
	@Enumerated(EnumType.STRING)
	private StatusItemEnum statuItem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Comanda getComanda() {
		return comanda;
	}

	public void setComanda(Comanda comanda) {
		this.comanda = comanda;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public StatusItemEnum getStatuItem() {
		return statuItem;
	}

	public void setStatuItem(StatusItemEnum statuItem) {
		this.statuItem = statuItem;
	}

	public Comissao getComissao() {
		return comissao;
	}

	public void setComissao(Comissao comissao) {
		this.comissao = comissao;
	}
	
	
}
