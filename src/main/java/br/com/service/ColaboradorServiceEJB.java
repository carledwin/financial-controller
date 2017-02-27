package br.com.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.dao.AbstractPersistence;
import br.com.model.Profissional;
/**
 * @author carledwin
 */
@Stateless
public class ColaboradorServiceEJB extends AbstractPersistence<Profissional, Long> implements ProfissionalService{

	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}	
	
	public ColaboradorServiceEJB() {
		super(Profissional.class);
	}

	@Override
	public List<Profissional> findByNome(String nome) {
		TypedQuery<Profissional> query = em.createQuery("select c from Colaborador c where c.nome LIKE :nome ORDER BY c.id DESC", Profissional.class);
		query.setParameter("nome","%" + nome + "%");
		
		List<Profissional> colaboradores = query.getResultList(); 
		return colaboradores;
	}

}
