package br.com.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.event.UnselectEvent;

import br.com.model.Comissao;
import br.com.model.FilterComissao;
import br.com.model.ItemComanda;
import br.com.model.Profissional;
import br.com.model.StatusComissaoEnum;
import br.com.model.StatusItemEnum;
import br.com.service.ComissaoService;
import br.com.service.ProfissionalService;

/**
 * @author carledwin
 */
@Named
@ViewScoped
public class ComissaoBean extends AbstractBean<Comissao> implements Serializable {

	private static final long serialVersionUID = 6848324606894209254L;

	private boolean consultaComissoes;
	
	private String nomeObjeto;
	
	private FilterComissao filter;
	
	public void setTodosProfissionais(List<Profissional> todosProfissionais) {
		this.todosProfissionais = todosProfissionais;
	}

	private List<Profissional> todosProfissionais;

	@Inject
	Comissao objetoPrincipal;
	
	@Inject
	ProfissionalService profissionalService;

	@Inject
	Comissao objetoSelecionado;

	@Inject
	ComissaoService objetoService;

	private List<Comissao> todos;
	
	private List<ItemComanda> items;
	
	private ItemComanda itemSelecionado;
	
	private List<ItemComanda> itemsSelecionados;

	 @PostConstruct
	    public void init() {
		 items = new ArrayList<>();
		 itemSelecionado = new ItemComanda();
		 itemsSelecionados = new ArrayList<>();
		 filter = new FilterComissao();
		 objetoPrincipal.setValor(BigDecimal.ZERO);
	 }
	
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
	public void prepararPesquisa() {
		super.prepararPesquisa();
		 filter = new FilterComissao();
		 filter.setProfissional(new Profissional());
		 filter.setStatusItem(StatusItemEnum.A_CALCULAR);
		 setConsultaComissoes(true);
	}
	
	@Override
	public void prepararConsulta() {
		super.prepararConsulta();
		 filter = new FilterComissao();
		 filter.setProfissional(new Profissional());
	}

	public ItemComanda getItemSelecionado() {
		return itemSelecionado;
	}

	public void setItemSelecionado(ItemComanda itemSelecionado) {
		this.itemSelecionado = itemSelecionado;
	}

	public List<ItemComanda> getItemsSelecionados() {
		return itemsSelecionados;
	}

	public void setItemsSelecionados(List<ItemComanda> itemsSelecionados) {
		this.itemsSelecionados = itemsSelecionados;
	}

	@Override
	public void alterar() {
	}
	
	 public void onRowSelect(SelectEvent event) {
	        FacesMessage msg = new FacesMessage("Item selecionado");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        objetoPrincipal.setValor(BigDecimal.ZERO);
	        for(ItemComanda it : itemsSelecionados){
	        	objetoPrincipal.setValor(objetoPrincipal.getValor().add(it.getServico().getValor()));
			 }
	        objetoPrincipal.setItemsComanda(itemsSelecionados);
	    }
	 
	    public void onRowUnselect(UnselectEvent event) {
	        FacesMessage msg = new FacesMessage("Item deselecionado");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        objetoPrincipal.setValor(objetoPrincipal.getValor().subtract(((ItemComanda) event.getObject()).getServico().getValor()));
	        objetoPrincipal.setItemsComanda(itemsSelecionados);
	    }

	@Override
	public void cadastrar() {
		
		for(ItemComanda ic : objetoPrincipal.getItemsComanda()){
			ic.setStatuItem(StatusItemEnum.CALCULADO);
		}
		objetoPrincipal.setProfissional(filter.getProfissional());
		objetoPrincipal.setStatus(StatusComissaoEnum.CALCULADA);
		objetoService.calcular(objetoPrincipal);

		Messages.addInfo(null, "Comissão paga com sucesso.");
		
		limpar();
		reiniciar();
	}
	
	public void consultarComissoes() {
		items = objetoService.findByFilterPayable(filter);
		setConsultaComissoes(true);
		setPesquisa(false);
		setConsulta(false);
	}

	@Override
	public void pesquisar() {
		todos = objetoService.findByFilter(filter);
	}

	@Override
	public void reiniciar() {
		objetoPrincipal = new Comissao();
		todos = null;
		nomeObjeto = "";
		objetoSelecionado = null;
		setCadastro(false);
		setPesquisa(false);
		setAlteracao(false);
		setConsulta(false);
		setConsultaComissoes(false);
	}
	
	public void pagar() {
		objetoService.pagar(objetoSelecionado);
		Messages.addWarn(null, "Comissão paga com sucesso.");
		reiniciar();
	}

	@Override
	public List<Comissao> getTodos() {
		return todos;
	}

	public Comissao getObjetoPrincipal() {
		return objetoPrincipal;
	}

	public void setObjetoPrincipal(Comissao objetoPrincipal) {
		this.objetoPrincipal = objetoPrincipal;
	}

	public String getNomeObjeto() {
		return nomeObjeto;
	}

	public void setNomeObjeto(String nomeObjeto) {
		this.nomeObjeto = nomeObjeto;
	}

	public Comissao getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Comissao objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	public boolean isConsultaComissoes() {
		return consultaComissoes;
	}

	public void setConsultaComissoes(boolean consultaComissoes) {
		this.consultaComissoes = consultaComissoes;
	}

	public FilterComissao getFilter() {
		return filter;
	}

	public void setFilter(FilterComissao filter) {
		this.filter = filter;
	}

	public List<ItemComanda> getItems() {
		return items;
	}

	public void setItems(List<ItemComanda> items) {
		this.items = items;
	}

	public void setTodos(List<Comissao> todos) {
		this.todos = todos;
	}

	public List<Profissional> getTodosProfissionais() {
		if (todosProfissionais == null) {
			todosProfissionais = profissionalService.listAll();
		}
		return todosProfissionais;
	}

	@Override
	public void excluir() {
	}

	
}