package br.com.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.model.enums.StatusServicoEnum;
/**
 * @author carledwin
 */
@Entity
public class Servico implements Serializable, AbstractEntity {

	private static final long serialVersionUID = -2534476807002983421L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String descricao;
	
	private BigDecimal valor;
	
	private double commissaoC;
	
	@Enumerated(EnumType.STRING)
	private StatusServicoEnum status;
	
	@OneToOne
	@JoinColumn(name="tipoServico_id")
	private TipoServico tipoServico;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public double getCommissaoC() {
		return commissaoC;
	}
	public void setCommissaoC(double commissaoC) {
		this.commissaoC = commissaoC;
	}
	public StatusServicoEnum getStatus() {
		return status;
	}
	public void setStatus(StatusServicoEnum status) {
		this.status = status;
	}
	public TipoServico getTipoServico() {
		return tipoServico;
	}
	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Servico other = (Servico) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	}
