package br.com.controller;

import java.util.List;
/**
 * @author carledwin
 */
public abstract class AbstractBean<T> {
	
	private String nomeObjeto = "";
	private boolean pesquisa;
	private boolean cadastro;
	private boolean consulta;
	private boolean alteracao;
	private boolean relatorio;
	private boolean consultaView;

	public abstract void prepararAlterar();

	public abstract void alterar();

	public abstract void cadastrar();

	public abstract void pesquisar();
	
	public abstract void reiniciar();
	
	public abstract void excluir();

	public void reiniciarPesquisaRelatorio() {
		// TODO Auto-generated method stub

	}

	public void enviarRelatorioPorEmail() {
		// TODO Auto-generated method stub

	}

	public void gerarRelatorio() {
		// TODO Auto-generated method stub

	}

	public void prepararRelatorio() {
		// TODO Auto-generated method stub

	}
	
	public abstract List<T> getTodos();

	public void prepararNovo() {
		limpar();
		reiniciar();
		cadastro = true;
	}

	public void prepararPesquisa() {
		limpar();
		reiniciar();
		pesquisa = true;
	}

	public void prepararConsulta() {
		limpar();
		reiniciar();
		consulta = true;
	}

	public void prepararAlteracao() {
		limpar();
		reiniciar();
		cadastro = true;
	}

	public void limpar() {
		if (cadastro) {
			reiniciar();
			cadastro = true;
		} else if (pesquisa) {
			reiniciar();
			pesquisa = true;
		}
	}

	public void cancelar() {
		limpar();
		reiniciar();
	}

	public String getNomeObjeto() {
		return nomeObjeto;
	}

	public void setNomeObjeto(String nomeObjeto) {
		this.nomeObjeto = nomeObjeto;
	}

	public boolean isPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(boolean pesquisa) {
		this.pesquisa = pesquisa;
	}

	public boolean isCadastro() {
		return cadastro;
	}

	public void setCadastro(boolean cadastro) {
		this.cadastro = cadastro;
	}

	public boolean isConsulta() {
		return consulta;
	}

	public void setConsulta(boolean consulta) {
		this.consulta = consulta;
	}

	public boolean isAlteracao() {
		return alteracao;
	}

	public void setAlteracao(boolean alteracao) {
		this.alteracao = alteracao;
	}

	public boolean isRelatorio() {
		return relatorio;
	}

	public void setRelatorio(boolean relatorio) {
		this.relatorio = relatorio;
	}

	public boolean isConsultaView() {
		return consultaView;
	}

	public void setConsultaView(boolean consultaView) {
		this.consultaView = consultaView;
	}

}