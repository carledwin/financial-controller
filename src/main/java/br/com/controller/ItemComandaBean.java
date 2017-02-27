package br.com.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.model.ItemComanda;
import br.com.model.enums.StatusServicoEnum;
import br.com.service.ItemComandaService;
import br.com.service.ServicoService;

/**
 * @author carledwin
 */
@Named
@ViewScoped
public class ItemComandaBean extends AbstractBean<ItemComanda> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	ItemComanda objetoPrincipal;

	private String nomeObjeto;

	@Inject
	ItemComanda objetoSelecionado;

	@Inject
	ItemComandaService objetoService;

	@Inject
	ServicoService servicoService;

	@Override
	public void prepararAlterar() {
		objetoPrincipal = objetoSelecionado;
		setAlteracao(true);
		setPesquisa(false);
		setCadastro(false);
		setPesquisa(false);
		setConsulta(false);
	}

	@Override
	public void alterar() {
		objetoService.save(objetoPrincipal);
		limpar();
		reiniciar();
	}

	@Override
	public void cadastrar() {
	}

	@Override
	public void pesquisar() {
	}

	@Override
	public void reiniciar() {
		objetoPrincipal = new ItemComanda();
		objetoSelecionado = null;
	}

	@Override
	public void excluir() {
	}

	@Override
	public List<ItemComanda> getTodos() {
		return new ArrayList<>();
	}

	public StatusServicoEnum[] getStatus() {
		return StatusServicoEnum.values();
	}

	public ItemComanda getObjetoPrincipal() {
		return objetoPrincipal;
	}

	public void setObjetoPrincipal(ItemComanda objetoPrincipal) {
		this.objetoPrincipal = objetoPrincipal;
	}

	public String getNomeObjeto() {
		return nomeObjeto;
	}

	public void setNomeObjeto(String nomeObjeto) {
		this.nomeObjeto = nomeObjeto;
	}

	public ItemComanda getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(ItemComanda objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

}