package br.com.model.enums;

public enum ItemTipoEnum {

	CADASTRO("Cadastro"), CONSULTA("Consulta"), EXCLUSAO("Exclusão"), ALTERACAO("Alterao"), PESQUISA("Pesquisa"), 
	
	USUARIOS("Usurios"), PERFIS("Perfis"), BANCOS("Bancos"), COMANDAS("Comandas"), DE("de");
	
	private ItemTipoEnum(String nome) {
		this.nome = nome;
	}

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
