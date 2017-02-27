package br.com.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.dao.AbstractPersistence;
import br.com.model.Usuario;

/**
 * @author carledwin
 */
@Stateless
public class UsuarioServiceEJB extends AbstractPersistence<Usuario, Long> implements UsuarioService{

	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}	
	
	public UsuarioServiceEJB() {
		super(Usuario.class);
	}

	@Override
	public List<Usuario> findByLogin(String login) {
		TypedQuery<Usuario> query = em.createQuery("select u from Usuario u where u.login LIKE :login ORDER BY u.id DESC", Usuario.class);
		query.setParameter("login","%" + login + "%");
		
		List<Usuario> usuarios = query.getResultList(); 
		return usuarios;
	}

}
