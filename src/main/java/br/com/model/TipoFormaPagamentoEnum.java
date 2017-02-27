package br.com.model;
/**
 * @author carledwin
 */
public enum TipoFormaPagamentoEnum {

	DINHEIRO("Dinheiro", false), DEBITO("Débido", false), CREDITOAV("Cartão de Crédito  vista", false), CHEQUEAV("Cheque  à vista", false), CREDITOPA("Crédito Parcelado", true), CHEQUEPA("Cheque Parcelado", true);
	
	private String descricao;
	private boolean parcelado;
	
	private TipoFormaPagamentoEnum(String descricao, boolean parcelado) {
		this.descricao = descricao;
		this.parcelado = parcelado;
	}

	public String getDescricao() {
		return descricao;
	}

	public boolean isParcelado() {
		return parcelado;
	}
}
