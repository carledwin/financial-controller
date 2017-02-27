package br.com.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
/**
 * @author carledwin
 */
@Entity
@NamedQueries({
		@NamedQuery(name="View.Comanda.all", query="SELECT vwc FROM ViewComanda vwc "),
		@NamedQuery(name="View.Comanda.By.Param", query="SELECT vwc FROM ViewComanda vwc WHERE 1=1 ")
		})
public class ViewComanda implements Serializable, AbstractEntity{
	
	private static final long serialVersionUID = 7106922176039085304L;

	@Id	
	@Column(name="ID")
	private Long id;
	
	@Column(name="CM_DT_CR")
	private Calendar dataCriacaoComanada;
	    
	@Column(name="CM_TT")
	private BigDecimal total;   
	
	@Enumerated(EnumType.STRING)
	@Column(name="CM_ST")
	private StatusComandaEnum status;
	    
	@Column(name="IT_CM_ID")
	private Long itemComandaId;
	    
	@Column(name="CL_ID")    
	private Long clienteId;
	
	@Column(name="CL_NM")
	private String clienteNome;
	
	@Column(name="IT_DESCO_SV")
	private Long itemDescontoServico;
	
	@Column(name="CO_1_ID")
	private Long colaborador1Id;
	
	@Column(name="CO_1_NM")
	private String colaborador1Nome;
	
	@Column(name="CO_2_ID")
	private Long colaborador2Id;
	
	@Column(name="CO_2_NM")
	private String colaborador2Nome;
	        
	@Column(name="SV_ID")
	private Long servicoId;
	
	@Column(name="SV_DS")
	private String servicoDescricao;
			
	@Column(name="SV_VL")
	private BigDecimal servicoValor;
			
	@Column(name="FP_ID")
	private Long formaPagamentoId;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="FP_VL_TP")
	private TipoFormaPagamentoEnum formaPagamento;
	
	@Column(name="FP_VL_IN")
	private BigDecimal formaPagamentoValorInicial;
	
	@Column(name="FP_VL_FN")
	private BigDecimal formaPagamentoValorFinal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getDataCriacaoComanada() {
		return dataCriacaoComanada;
	}

	public void setDataCriacaoComanada(Calendar dataCriacaoComanada) {
		this.dataCriacaoComanada = dataCriacaoComanada;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public StatusComandaEnum getStatus() {
		return status;
	}

	public void setStatus(StatusComandaEnum status) {
		this.status = status;
	}

	public Long getItemComandaId() {
		return itemComandaId;
	}

	public void setItemComandaId(Long itemComandaId) {
		this.itemComandaId = itemComandaId;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public String getClienteNome() {
		return clienteNome;
	}

	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}

	public Long getItemDescontoServico() {
		return itemDescontoServico;
	}

	public void setItemDescontoServico(Long itemDescontoServico) {
		this.itemDescontoServico = itemDescontoServico;
	}

	public Long getColaborador1Id() {
		return colaborador1Id;
	}

	public void setColaborador1Id(Long colaborador1Id) {
		this.colaborador1Id = colaborador1Id;
	}

	public String getColaborador1Nome() {
		return colaborador1Nome;
	}

	public void setColaborador1Nome(String colaborador1Nome) {
		this.colaborador1Nome = colaborador1Nome;
	}

	public Long getColaborador2Id() {
		return colaborador2Id;
	}

	public void setColaborador2Id(Long colaborador2Id) {
		this.colaborador2Id = colaborador2Id;
	}

	public String getColaborador2Nome() {
		return colaborador2Nome;
	}

	public void setColaborador2Nome(String colaborador2Nome) {
		this.colaborador2Nome = colaborador2Nome;
	}

	public Long getServicoId() {
		return servicoId;
	}

	public void setServicoId(Long servicoId) {
		this.servicoId = servicoId;
	}

	public String getServicoDescricao() {
		return servicoDescricao;
	}

	public void setServicoDescricao(String servicoDescricao) {
		this.servicoDescricao = servicoDescricao;
	}

	
	public BigDecimal getServicoValor() {
		return servicoValor;
	}

	public void setServicoValor(BigDecimal servicoValor) {
		this.servicoValor = servicoValor;
	}

	public Long getFormaPagamentoId() {
		return formaPagamentoId;
	}

	public void setFormaPagamentoId(Long formaPagamentoId) {
		this.formaPagamentoId = formaPagamentoId;
	}

	public TipoFormaPagamentoEnum getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(TipoFormaPagamentoEnum formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public BigDecimal getFormaPagamentoValorInicial() {
		return formaPagamentoValorInicial;
	}

	public void setFormaPagamentoValorInicial(BigDecimal formaPagamentoValorInicial) {
		this.formaPagamentoValorInicial = formaPagamentoValorInicial;
	}

	public BigDecimal getFormaPagamentoValorFinal() {
		return formaPagamentoValorFinal;
	}

	public void setFormaPagamentoValorFinal(BigDecimal formaPagamentoValorFinal) {
		this.formaPagamentoValorFinal = formaPagamentoValorFinal;
	}
	
	
}
