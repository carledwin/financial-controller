package br.com.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * @author carledwin
 */
@Entity
public class Comanda implements Serializable, AbstractEntity {

	private static final long serialVersionUID = 892732932416701532L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String resumo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao", nullable = true)
	private Calendar dataCriacao;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataAlteracao;

	@OneToOne(fetch = FetchType.EAGER)
	private Usuario usuarioAlteracao;

	@JoinColumn(name = "cliente_id")
	@ManyToOne(fetch = FetchType.EAGER)
	private Cliente cliente;

	@Enumerated(EnumType.STRING)
	private StatusComandaEnum status;

	@OneToMany(mappedBy = "comanda", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemComanda> itemComandas;

	@Enumerated(EnumType.STRING)
	private TipoFormaPagamentoEnum tipo;

	private BigDecimal total;

	private BigDecimal valor;

	private BigDecimal desconto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public Calendar getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Calendar getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Calendar dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public StatusComandaEnum getStatus() {
		return status;
	}

	public void setStatus(StatusComandaEnum status) {
		this.status = status;
	}

	public List<ItemComanda> getItemComandas() {
		return itemComandas;
	}

	public void setItemComandas(List<ItemComanda> itemComandas) {
		this.itemComandas = itemComandas;
	}

	public TipoFormaPagamentoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoFormaPagamentoEnum tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

}
