package br.com.model;
/**
 * @author carledwin
 */
public enum StatusValeEnum {

	A_DEBITAR("A debitar"), DEBITADO("Debitado");
	
	private String descricao;
	
	private StatusValeEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	

}
