package br.com.model;

/**
 * @author carledwin
 */
public enum StatusComissaoEnum {
	CALCULADA("Calculada"), PAGA("Paga");

	private String descricao;

	private StatusComissaoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
