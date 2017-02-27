package br.com.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.omnifaces.util.Messages;

import br.com.model.Cliente;
import br.com.model.Endereco;
import br.com.model.UfEnum;
import br.com.service.ClienteService;
/**
 * @author carledwin
 */
@Named
@ViewScoped
public class ClienteBean extends AbstractBean<Cliente> implements Serializable {

private static final long serialVersionUID = 4778851611001151061L;

	@Inject
	Cliente objetoPrincipal;

	private String nomeObjeto;

	@Inject
	Cliente objetoSelecionado;

	@Inject
	ClienteService objetoService;

	private String cepConsultado = "";

	@PersistenceContext
	private EntityManager manager;

	private List<Cliente> todos;

	private boolean cadastrarCepManualmente;

	@PostConstruct	
	private void init() {
		setAlteracao(false);
		setPesquisa(false);
		setCadastro(false);
		setPesquisa(false);
		setConsulta(true);
	}

	@Override
	public void prepararAlterar() {
		objetoPrincipal = objetoSelecionado;
		objetoPrincipal = objetoService.findByIdFetch(objetoPrincipal.getId());

		setAlteracao(true);
		setPesquisa(false);
		setCadastro(false);
		setPesquisa(false);
		setConsulta(false);
	}

	@Override
	public void prepararNovo() {
		super.prepararNovo();
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

		reiniciarObjetoPrincipal();

		todos = null;
		nomeObjeto = "";
		objetoSelecionado = null;
		getTodos();
		setCadastro(false);
		setPesquisa(false);
		setAlteracao(false);
		setConsulta(false);
	}

	private void reiniciarObjetoPrincipal() {
		objetoPrincipal = new Cliente();
		objetoPrincipal.setCelular("");
		objetoPrincipal.setDataCadastro(Calendar.getInstance());
		objetoPrincipal.setEmail("");
		objetoPrincipal.setNome("");
		objetoPrincipal.setTelefone("");
		objetoPrincipal.setTipoPessoa(Cliente.CLIENTE);
		
		objetoPrincipal.setEndereco(new Endereco());
		objetoPrincipal.getEndereco().setBairro("");
		objetoPrincipal.getEndereco().setCep("");
		objetoPrincipal.getEndereco().setCidade("");
		objetoPrincipal.getEndereco().setComplemento("");
		objetoPrincipal.getEndereco().setLogradouro("");
		objetoPrincipal.getEndereco().setNumero("");
		objetoPrincipal.getEndereco().setPais("");
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
	public List<Cliente> getTodos() {
		if (todos == null) {
			todos = objetoService.listAll();
		}
		return todos;
	}

	public Cliente getObjetoPrincipal() {
		return objetoPrincipal;
	}

	public void setObjetoPrincipal(Cliente objetoPrincipal) {
		this.objetoPrincipal = objetoPrincipal;
	}

	public String getNomeObjeto() {
		return nomeObjeto;
	}

	public void setNomeObjeto(String nomeObjeto) {
		this.nomeObjeto = nomeObjeto;
	}

	public Cliente getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Cliente objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	public ClienteService getObjetoService() {
		return objetoService;
	}

	public void setObjetoService(ClienteService objetoService) {
		this.objetoService = objetoService;
	}

	public String getCepConsultado() {
		return cepConsultado;
	}

	public void setCepConsultado(String cepConsultado) {
		this.cepConsultado = cepConsultado;
	}

	public boolean isCadastrarCepManualmente() {
		return cadastrarCepManualmente;
	}

	public void setCadastrarCepManualmente(boolean cadastrarCepManualmente) {
		this.cadastrarCepManualmente = cadastrarCepManualmente;
	}

	public List<UfEnum> getUfs(){
		return Arrays.asList(UfEnum.values());
	}
}
