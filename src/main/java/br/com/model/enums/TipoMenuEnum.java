package br.com.model.enums;

public enum TipoMenuEnum {

	MN("Menu"), SB_M("SubMenu");
	
	private String descricao;
	
	private TipoMenuEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
