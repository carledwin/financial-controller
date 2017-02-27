package br.com.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.model.Profissional;
import br.com.service.ProfissionalService;
/**
 * @author carledwin
 */
@Named
@ViewScoped
public class ColaboradorBean extends AbstractBean<Profissional> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	Profissional objetoPrincipal;

	private String nomeObjeto;

	@Inject
	Profissional objetoSelecionado;

	@Inject
	ProfissionalService objetoService;

	private List<Profissional> todos;

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
		objetoPrincipal.setTipoPessoa('P');
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
		todos = objetoService.findByNome(nomeObjeto);
		setConsulta(true);
	}

	@Override
	public void reiniciar() {
		objetoPrincipal = new Profissional();
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
			String excluido = nomeObjeto;
			reiniciar();
			setPesquisa(true);
			todos = objetoService.findByNome(excluido);
			if (getTodos().size() == 0) {
				todos = null;
			}
		} else {
			String excluido = objetoSelecionado.getNome();
			reiniciar();
			todos = objetoService.findByNome(excluido);
			if (getTodos().size() == 0) {
				todos = null;
			}
		}
		setConsulta(true);
	}

	@Override
	public List<Profissional> getTodos() {
		if (todos == null) {
			todos = objetoService.listAll();
		}
		return todos;
	}

	public Profissional getObjetoPrincipal() {
		return objetoPrincipal;
	}

	public void setObjetoPrincipal(Profissional objetoPrincipal) {
		this.objetoPrincipal = objetoPrincipal;
	}

	public String getNomeObjeto() {
		return nomeObjeto;
	}

	public void setNomeObjeto(String nomeObjeto) {
		this.nomeObjeto = nomeObjeto;
	}

	public Profissional getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Profissional objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	public void setTodos(List<Profissional> todos) {
		this.todos = todos;
	}

}