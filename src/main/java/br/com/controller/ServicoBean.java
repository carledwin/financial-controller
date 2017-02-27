package br.com.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.model.Servico;
import br.com.model.TipoServico;
import br.com.model.enums.StatusServicoEnum;
import br.com.service.ServicoService;
import br.com.service.TipoServicoService;

/**
 * @author carledwin
 */
@Named
@ViewScoped
public class ServicoBean extends AbstractBean<Servico> implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nomeObjeto;
	private List<Servico> todos;
	private List<TipoServico> todosTipos;
	@Inject
	Servico objetoPrincipal;
	@Inject
	Servico objetoSelecionado;
	@Inject
	ServicoService objetoService;
	@Inject
	TipoServicoService objetoTipoServicoService;

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
		todos = objetoService.findByNome(nomeObjeto);
		setConsulta(true);
	}

	@Override
	public void reiniciar() {
		objetoPrincipal = new Servico();
		todos = null;
		todosTipos = null;
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
			String excluido = objetoSelecionado.getDescricao();
			reiniciar();
			todos = objetoService.findByNome(excluido);
			if (getTodos().size() == 0) {
				todos = null;
			}
		}
		setConsulta(true);
	}

	@Override
	public List<Servico> getTodos() {
		if (todos == null) {
			todos = objetoService.listAll();
		}
		return todos;
	}

	public List<TipoServico> getTodosTipos() {
		if (todosTipos == null) {
			todosTipos = objetoTipoServicoService.listAll();
		}
		return todosTipos;
	}

	public StatusServicoEnum[] getStatus() {
		return StatusServicoEnum.values();
	}

	public Servico getObjetoPrincipal() {
		return objetoPrincipal;
	}

	public void setObjetoPrincipal(Servico objetoPrincipal) {
		this.objetoPrincipal = objetoPrincipal;
	}

	public String getNomeObjeto() {
		return nomeObjeto;
	}

	public void setNomeObjeto(String nomeObjeto) {
		this.nomeObjeto = nomeObjeto;
	}

	public Servico getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Servico objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

}