package br.com.model;
/**
 * @author carledwin
 */
public enum StatusItemEnum {

	A_CALCULAR("A calcular"), CALCULADO("Calulado"), PAGO("Pago");
	
	private String descricao;
	
	private StatusItemEnum(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
}
