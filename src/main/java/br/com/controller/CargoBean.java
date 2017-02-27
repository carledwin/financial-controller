package br.com.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.model.Cargo;
import br.com.service.CargoService;

/**
 * @author carledwin
 */
@Named
@ViewScoped
public class CargoBean extends AbstractBean<Cargo> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	Cargo objetoPrincipal;

	private String nomeObjeto;

	@Inject
	Cargo objetoSelecionado;

	@Inject
	CargoService objetoService;

	private List<Cargo> todos;

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
		objetoService.save(objetoPrincipal);

		if (objetoPrincipal.getId() == null)
			Messages.addInfo(null, "Registro incluido com sucesso.");
		else
			Messages.addInfo(null, "Registro alterado com sucesso.");

		limpar();
		reiniciar();
	}

	@Override
	public void pesquisar() {
		todos = objetoService.findByDescricao(nomeObjeto);
		setConsulta(true);
	}

	@Override
	public void reiniciar() {
		objetoPrincipal = new Cargo();
		todos = null;
		nomeObjeto = "";
		objetoSelecionado = null;
		getTodos();
		setCadastro(false);
		setPesquisa(false);
		setAlteracao(false);
		setConsulta(false);
	}

	@Override
	public void excluir() {
		objetoService.remove(objetoSelecionado);
		Messages.addWarn(null, "Registro excluido com sucesso.");
		if (isPesquisa() && !nomeObjeto.isEmpty()) {
			reiniciar();
			setPesquisa(true);
			todos = null;
		} else {
			String excluido = objetoSelecionado.getDescricao();
			reiniciar();
			todos = objetoService.findByDescricao(excluido);
			if (getTodos().size() == 0) {
				todos = null;
			}
		}
		setConsulta(true);
	}

	@Override
	public List<Cargo> getTodos() {
		if (todos == null) {
			todos = objetoService.listAll();
		}
		return todos;
	}

	public Cargo getObjetoPrincipal() {
		return objetoPrincipal;
	}

	public void setObjetoPrincipal(Cargo objetoPrincipal) {
		this.objetoPrincipal = objetoPrincipal;
	}

	public String getNomeObjeto() {
		return nomeObjeto;
	}

	public void setNomeObjeto(String nomeObjeto) {
		this.nomeObjeto = nomeObjeto;
	}

	public Cargo getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Cargo objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

}