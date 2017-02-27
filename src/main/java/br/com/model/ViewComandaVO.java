package br.com.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
/**
 * @author carledwin
 */
public class ViewComandaVO implements Serializable {

	private static final long serialVersionUID = -1796267957477725274L;

	private Long id;
	private String dataCriacaoComanda;
	private BigDecimal total;
	private String status;
	private Long itemComandaId;
	private Long clienteId;
	private String clienteNome;
	private Long itemDescontoServico;
	private Long colaborador1Id;
	private String colaborador1Nome;
	private Long colaborador2Id;
	private String colaborador2Nome;
	private Long servicoId;
	private String servicoDescricao;
	private BigDecimal servicoValor;
	private Long formaPagamentoId;
	private String formaPagamento;
	private BigDecimal formaPagamentoValorInicial;
	private BigDecimal formaPagamentoValorFinal;
	
	
	public static List<ViewComandaVO> parseViewComandaForVO(List<ViewComanda> lista) {
		 
		List<ViewComandaVO> listaVO = new ArrayList<>();
		
		for (ViewComanda view : lista) {
			
			ViewComandaVO vo = new ViewComandaVO();
			
			vo.setClienteId(view.getClienteId());
			vo.setClienteNome(view.getClienteNome());
			vo.setColaborador1Id(view.getColaborador1Id());
			vo.setColaborador2Id(view.getColaborador2Id());
			vo.setColaborador1Nome(view.getColaborador1Nome());
			vo.setColaborador2Nome(view.getColaborador2Nome());
			
			vo.setId(view.getId());
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			vo.setDataCriacaoComanda(sdf.format(view.getDataCriacaoComanada().getTime()));
			vo.setFormaPagamentoId(view.getFormaPagamentoId());
			vo.setFormaPagamento(view.getFormaPagamento().getDescricao());
			vo.setFormaPagamentoValorInicial(view.getFormaPagamentoValorInicial());
			vo.setFormaPagamentoValorFinal(view.getFormaPagamentoValorFinal());
			vo.setItemComandaId(view.getItemComandaId());
			
			vo.setItemDescontoServico(view.getItemDescontoServico());
			vo.setServicoId(view.getServicoId());
			vo.setServicoDescricao(view.getServicoDescricao());
			vo.setServicoValor(view.getServicoValor());
			vo.setStatus(view.getStatus().getDescricao());
			vo.setTotal(view.getTotal());
			
			listaVO.add(vo);
		}
		return listaVO;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDataCriacaoComanda() {
		return dataCriacaoComanda;
	}

	public void setDataCriacaoComanda(String dataCriacaoComanda) {
		this.dataCriacaoComanda = dataCriacaoComanda;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
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

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clienteId == null) ? 0 : clienteId.hashCode());
		result = prime * result + ((clienteNome == null) ? 0 : clienteNome.hashCode());
		result = prime * result + ((colaborador1Id == null) ? 0 : colaborador1Id.hashCode());
		result = prime * result + ((colaborador1Nome == null) ? 0 : colaborador1Nome.hashCode());
		result = prime * result + ((colaborador2Id == null) ? 0 : colaborador2Id.hashCode());
		result = prime * result + ((colaborador2Nome == null) ? 0 : colaborador2Nome.hashCode());
		result = prime * result + ((dataCriacaoComanda == null) ? 0 : dataCriacaoComanda.hashCode());
		result = prime * result + ((formaPagamento == null) ? 0 : formaPagamento.hashCode());
		result = prime * result + ((formaPagamentoId == null) ? 0 : formaPagamentoId.hashCode());
		result = prime * result + ((formaPagamentoValorFinal == null) ? 0 : formaPagamentoValorFinal.hashCode());
		result = prime * result + ((formaPagamentoValorInicial == null) ? 0 : formaPagamentoValorInicial.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((itemComandaId == null) ? 0 : itemComandaId.hashCode());
		result = prime * result + ((itemDescontoServico == null) ? 0 : itemDescontoServico.hashCode());
		result = prime * result + ((servicoDescricao == null) ? 0 : servicoDescricao.hashCode());
		result = prime * result + ((servicoId == null) ? 0 : servicoId.hashCode());
		result = prime * result + ((servicoValor == null) ? 0 : servicoValor.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((total == null) ? 0 : total.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ViewComandaVO other = (ViewComandaVO) obj;
		if (clienteId == null) {
			if (other.clienteId != null)
				return false;
		} else if (!clienteId.equals(other.clienteId))
			return false;
		if (clienteNome == null) {
			if (other.clienteNome != null)
				return false;
		} else if (!clienteNome.equals(other.clienteNome))
			return false;
		if (colaborador1Id == null) {
			if (other.colaborador1Id != null)
				return false;
		} else if (!colaborador1Id.equals(other.colaborador1Id))
			return false;
		if (colaborador1Nome == null) {
			if (other.colaborador1Nome != null)
				return false;
		} else if (!colaborador1Nome.equals(other.colaborador1Nome))
			return false;
		if (colaborador2Id == null) {
			if (other.colaborador2Id != null)
				return false;
		} else if (!colaborador2Id.equals(other.colaborador2Id))
			return false;
		if (colaborador2Nome == null) {
			if (other.colaborador2Nome != null)
				return false;
		} else if (!colaborador2Nome.equals(other.colaborador2Nome))
			return false;
		if (dataCriacaoComanda == null) {
			if (other.dataCriacaoComanda != null)
				return false;
		} else if (!dataCriacaoComanda.equals(other.dataCriacaoComanda))
			return false;
		if (formaPagamento == null) {
			if (other.formaPagamento != null)
				return false;
		} else if (!formaPagamento.equals(other.formaPagamento))
			return false;
		if (formaPagamentoId == null) {
			if (other.formaPagamentoId != null)
				return false;
		} else if (!formaPagamentoId.equals(other.formaPagamentoId))
			return false;
		if (formaPagamentoValorFinal == null) {
			if (other.formaPagamentoValorFinal != null)
				return false;
		} else if (!formaPagamentoValorFinal.equals(other.formaPagamentoValorFinal))
			return false;
		if (formaPagamentoValorInicial == null) {
			if (other.formaPagamentoValorInicial != null)
				return false;
		} else if (!formaPagamentoValorInicial.equals(other.formaPagamentoValorInicial))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itemComandaId == null) {
			if (other.itemComandaId != null)
				return false;
		} else if (!itemComandaId.equals(other.itemComandaId))
			return false;
		if (itemDescontoServico == null) {
			if (other.itemDescontoServico != null)
				return false;
		} else if (!itemDescontoServico.equals(other.itemDescontoServico))
			return false;
		if (servicoDescricao == null) {
			if (other.servicoDescricao != null)
				return false;
		} else if (!servicoDescricao.equals(other.servicoDescricao))
			return false;
		if (servicoId == null) {
			if (other.servicoId != null)
				return false;
		} else if (!servicoId.equals(other.servicoId))
			return false;
		if (servicoValor == null) {
			if (other.servicoValor != null)
				return false;
		} else if (!servicoValor.equals(other.servicoValor))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (total == null) {
			if (other.total != null)
				return false;
		} else if (!total.equals(other.total))
			return false;
		return true;
	}
	
}
