package br.com.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.model.Profissional;
import br.com.model.StatusValeEnum;
import br.com.model.Vale;
import br.com.service.ProfissionalService;
import br.com.service.ValeService;

/**
 * @author carledwin
 */
@Named
@ViewScoped
public class ValeBean extends AbstractBean<Vale> implements Serializable {

	private static final long serialVersionUID = 1L;

	public ValeBean() {
	}

	@Inject
	private Vale objetoPrincipal;
	
	@Inject
	private Vale objetoSelecionado;
	
	@Inject
	private ValeService objetoService;
	
	@Inject
	ProfissionalService profissionalService;

	private Long codigo;
	private String descricao;
	private StatusValeEnum status;
	private Calendar dataVale;
	private List<Vale> todos;
	private List<Profissional> todosProfissionais;
	private List<StatusValeEnum> statusVale;

	@Override
	public void reiniciar() {
		objetoPrincipal = new Vale();
		objetoPrincipal.setDataVale(Calendar.getInstance());
		todos = null;
		codigo = new Long(0l);
		objetoSelecionado = null;
		setCadastro(false);
		setPesquisa(false);
		setAlteracao(false);
		setConsulta(false);
		setRelatorio(false);
		setConsultaView(false);
	}

	@Override
	public void prepararAlterar() {
		objetoPrincipal = objetoSelecionado;
		objetoPrincipal.setValor(new BigDecimal("0.00"));

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
	public void cancelar() {
		reiniciar();
	}

	@Override
	public void cadastrar() {

		if (objetoPrincipal.getId() == null) {
			objetoPrincipal.setStatus(StatusValeEnum.A_DEBITAR);
			Messages.addInfo(null, "Registro incluido com sucesso.");
		} else {
			Messages.addInfo(null, "Registro alterado com sucesso.");
		}
		objetoPrincipal = objetoService.save(objetoPrincipal);

		limpar();
		reiniciar();
	}

	@Override
	public void pesquisar() {
		todos = objetoService.findByParam(codigo, descricao, dataVale, status);
		setConsulta(true);
	}

	@Override
	public void gerarRelatorio() {
	}

	public void enviarRelatorioPorEmail() {
	}

	@Override
	public void prepararRelatorio() {
		limpar();
		reiniciar();
		reiniciarPesquisaRelatorio();
		setRelatorio(true);
	}

	public void pesquisarCliente() {

	}

	public void emitir() {
		Messages.addInfo(null, "Relatrio gerado com sucesso.");
	}

	@Override
	public void excluir() {
		objetoService.remove(objetoSelecionado);
		Messages.addWarn(null, "Registro excluido com sucesso.");
		if (isPesquisa() && codigo != null) {
			reiniciar();
			setPesquisa(true);
			todos = null;
		} else {
			Long excluido = objetoSelecionado.getId();
			reiniciar();
			todos = objetoService.findByParam(excluido, descricao, dataVale, status);
			if (getTodos().size() == 0) {
				todos = null;
			}
		}
		setConsulta(true);
	}

	@Override
	public void reiniciarPesquisaRelatorio() {
	}

	@Override
	public List<Vale> getTodos() {
		if (todos == null)
			todos = objetoService.listAll();

		for (Vale d : todos) {
			if (d.getDataVale().getTime().before(Calendar.getInstance().getTime())
					&& !d.getStatus().equals(StatusValeEnum.DEBITADO))
				d.setStatus(StatusValeEnum.A_DEBITAR);
		}

		return todos;
	}

	public List<Profissional> getTodosProfissionais() {
		if (todosProfissionais == null) {
			todosProfissionais = profissionalService.listAll();
		}
		return todosProfissionais;
	}
	
	

	public List<StatusValeEnum> getStatusVale() {
		return Arrays.asList(StatusValeEnum.values());
	}

	public void setStatusVale(List<StatusValeEnum> statusVale) {
		this.statusVale = statusVale;
	}

	public void setTodosProfissionais(List<Profissional> todosProfissionais) {
		this.todosProfissionais = todosProfissionais;
	}

	public Vale getObjetoPrincipal() {
		return objetoPrincipal;
	}

	public void setObjetoPrincipal(Vale objetoPrincipal) {
		this.objetoPrincipal = objetoPrincipal;
	}

	public Vale getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Vale objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	public ValeService getObjetoService() {
		return objetoService;
	}

	public void setObjetoService(ValeService objetoService) {
		this.objetoService = objetoService;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public StatusValeEnum getStatus() {
		return status;
	}

	public void setStatus(StatusValeEnum status) {
		this.status = status;
	}

	public Calendar getDataVale() {
		return dataVale;
	}

	public void setDataVale(Calendar dataVale) {
		this.dataVale = dataVale;
	}

	public void setTodos(List<Vale> todos) {
		this.todos = todos;
	}

}