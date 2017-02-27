package br.com.model;
/**
 * @author carledwin
 */
public enum StatusComandaEnum {

	EMA("Em Aberto"), FEC("Fechada"), QUI("Quitada"), CPG("Comisso Paga");
	
	private String descricao;
	
	private StatusComandaEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	

}
