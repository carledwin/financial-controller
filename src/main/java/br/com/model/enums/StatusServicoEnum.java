package br.com.model.enums;

public enum StatusServicoEnum {

	ATIVO("1"),INATIVO("0");
	
	private String descricao;
	
	private StatusServicoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
