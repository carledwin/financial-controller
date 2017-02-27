package br.com.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Messages;

import br.com.model.Perfil;
import br.com.model.Profissional;
import br.com.model.Usuario;
import br.com.service.ProfissionalService;
import br.com.service.PerfilService;
import br.com.service.UsuarioService;
/**
 * @author carledwin
 */
@Named
@ViewScoped
public class UsuarioBean extends AbstractBean<Usuario> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Objeto principal
	 */
	@Inject Usuario objetoPrincipal;
	
	/**
	 * Objeto selecionado na grid
	 */
	@Inject UsuarioService objetoService;
	@Inject PerfilService perfilService;
	@Inject ProfissionalService colaboradorService;
	@Inject Usuario objetoSelecionado;
	@Inject private Profissional colaboradorSelecionado;
	@Inject private Perfil perfilSelecionado;

	private String nomeObjeto;
	private Usuario usuarioLogado;
	private String confirmacaoSenha;
	private boolean reiniciarSenha;
	private List<Usuario> todos;
	private List<Perfil> perfis;
	private List<Profissional> colaboradores;
	
	@Override
	public void prepararAlterar() {
		objetoPrincipal = objetoSelecionado;
		perfilSelecionado = objetoPrincipal.getPerfil();
		colaboradorSelecionado = objetoPrincipal.getProfissional();
		confirmacaoSenha = objetoPrincipal.getSenha();
		setAlteracao(true);
		setPesquisa(false);
		setCadastro(false);
		setPesquisa(false);
		setConsulta(false);

	}

	@Override
	public void alterar() {
		
		if(reiniciarSenha)
			reiniciarSenha();
			
		objetoService.save(objetoPrincipal);
		limpar();
		reiniciar();
	}

	@Override
	public void cadastrar() {
		
		if(objetoPrincipal.getSenha().compareTo(confirmacaoSenha) == 0){
		
			if(reiniciarSenha)
				reiniciarSenha();
			
		objetoPrincipal.setProfissional(colaboradorSelecionado);	
		objetoPrincipal.setPerfil(perfilSelecionado);	
		objetoService.save(objetoPrincipal);

				if(objetoPrincipal.getId() == null)
					Messages.addInfo(null, "Registro incluido com sucesso.");
				else
					Messages.addInfo(null,"Registro alterado com sucesso.");
				
				limpar();
				reiniciar();
		}else{
			Messages.addError(null, "A senha e a confirmao de senha so diferentes.");
		}
	}

	@Override
	public void pesquisar() {
		todos = objetoService.findByLogin(nomeObjeto);
		setConsulta(true);
	}

	@Override
	public void reiniciar() {
		objetoPrincipal = new Usuario();
		todos = null;
		colaboradores = null;
		perfis = null;
		nomeObjeto = "";
		objetoSelecionado = null;
		perfilSelecionado = new Perfil();
		colaboradorSelecionado = new Profissional();
		getTodos();
		setCadastro(false);
		setPesquisa(false);
		setAlteracao(false);
		setConsulta(false);
		reiniciarSenha = false;
	}

	@Override
	public void excluir() {
		objetoService.remove(objetoSelecionado);
		Messages.addWarn(null, "Registro excluido com sucesso.");
		if(isPesquisa() && !nomeObjeto.isEmpty()){
			reiniciar();
			setPesquisa(true);
			todos = null;
			} else{
			String excluido = objetoSelecionado.getLogin();
			reiniciar();
			todos = objetoService.findByLogin(excluido);
			if(getTodos().size() == 0){
				todos = null;
			}
		}
		setConsulta(true);	
	}

	@Override
	public List<Usuario> getTodos() {
		if ( todos == null)
			todos = objetoService.listAll();
		
		return todos;
	}
	
	public List<Profissional> getColaboradores() {
		if(colaboradores == null)
			colaboradores = colaboradorService.listAll();
		
		return colaboradores;
	}

	public void setColaboradores(List<Profissional> colaboradores) {
		this.colaboradores = colaboradores;
	}

	public Profissional getColaboradorSelecionado() {
		return colaboradorSelecionado;
	}

	public void setColaboradorSelecionado(Profissional colaboradorSelecionado) {
		this.colaboradorSelecionado = colaboradorSelecionado;
	}

	
	private void reiniciarSenha(){
		objetoPrincipal.setSenha(objetoPrincipal.getLogin());
	}
	
	public void adicionarMensagemReiniciar(){
		String summary = reiniciarSenha ? "A senha do usurio selecionado ser reiniciada!" : "A senha do usurio selecionada no ser mais reinicada.";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
	}
	
	/**
	 * Captura usuario logado na sessao
	 * @return
	 */
	public Usuario getUsuarioLogado() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		
		//captura o usuario logado na sessao
		usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		
		return usuarioLogado;
	}

	public static Usuario getUsuarioSessao(){

		FacesContext context = FacesContext.getCurrentInstance();
		
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		
		//captura o usuario logado na sessao
		return  (Usuario) session.getAttribute("usuarioLogado");
		
	}
	
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	
	public List<Perfil> getPerfis() {
		
		if(perfis == null)
			perfis = perfilService.listAll();
		
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public Perfil getPerfilSelecionado() {
		return perfilSelecionado;
	}

	public void setPerfilSelecionado(Perfil perfilSelecionado) {
		this.perfilSelecionado = perfilSelecionado;
	}

	
	public Usuario getObjetoPrincipal() {
		return objetoPrincipal;
	}

	public void setObjetoPrincipal(Usuario objetoPrincipal) {
		this.objetoPrincipal = objetoPrincipal;
	}

	public String getNomeObjeto() {
		return nomeObjeto;
	}

	public void setNomeObjeto(String nomeObjeto) {
		this.nomeObjeto = nomeObjeto;
	}

	public Usuario getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Usuario objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}

	public boolean isReiniciarSenha() {
		return reiniciarSenha;
	}

	public void setReiniciarSenha(boolean reiniciarSenha) {
		this.reiniciarSenha = reiniciarSenha;
	}

	@Override
	public void reiniciarPesquisaRelatorio() {
	}

	@Override
	public void enviarRelatorioPorEmail() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gerarRelatorio() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void prepararRelatorio() {
		// TODO Auto-generated method stub
		
	}
}

