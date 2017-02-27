package br.com.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.model.Usuario;
/**
 * @author carledwin
 */
@ManagedBean
@SessionScoped
public class UsuarioMBean {
	
	private List<Usuario> usuarios;
	
	private Usuario usuario = new Usuario();

	public void consultar(){
	}
	
	public String adicionar(){
		return "cadastrarUsuarios.jsf?redirect=true";
	}
	
	public void limpar(){
		this.usuario = new Usuario();
	}
	
	public void cancelar(){
		this.usuario = new Usuario();
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
}
