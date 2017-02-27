package br.com.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


/**
 * @author carledwin
 */
@Entity
public class Comissao implements Serializable, AbstractEntity{

	private static final long serialVersionUID = -3743920432943339551L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Calendar dataPagamento;
	
	@OneToMany(mappedBy = "comissao", fetch = FetchType.EAGER)
	private List<ItemComanda> itemsComanda;
	
	@OneToOne
	private Profissional profissional;
	
	@Enumerated(EnumType.STRING)
	private StatusComissaoEnum status;

	private BigDecimal valor;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Calendar dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public List<ItemComanda> getItemsComanda() {
		return itemsComanda;
	}

	public void setItemsComanda(List<ItemComanda> itemsComanda) {
		this.itemsComanda = itemsComanda;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public StatusComissaoEnum getStatus() {
		return status;
	}

	public void setStatus(StatusComissaoEnum status) {
		this.status = status;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
}
