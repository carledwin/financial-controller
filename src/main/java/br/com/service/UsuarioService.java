package br.com.service;

import java.util.List;

import javax.ejb.Local;

import br.com.model.Usuario;
/**
 * @author carledwin
 */
@Local
public interface UsuarioService {

	public Usuario save(Usuario usuario);
	public void remove(Usuario usuario);
	public Usuario findById(Long id);
	public List<Usuario> findByLogin(String login);
	public List<Usuario> listAll();
}
