package br.com.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.dao.AbstractPersistence;
import br.com.model.Perfil;
/**
 * @author carledwin
 */
@Stateless
public class PerfilServiceEJB extends AbstractPersistence<Perfil, Long> implements PerfilService{

	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}	
	
	public PerfilServiceEJB() {
		super(Perfil.class);
	}

	@Override
	public List<Perfil> findByDescricao(String descricao) {
		TypedQuery<Perfil> query = em.createQuery("select p from Perfil p where p.descricao = :descricao ORDER BY p.id DESC", Perfil.class);
		query.setParameter("descricao","%" + descricao + "%");
		
		List<Perfil> perfis = query.getResultList(); 
		return perfis;
	}

	@Override
	public List<Perfil> findByRegra(String regra) {
		TypedQuery<Perfil> query = em.createQuery("select p from Perfil p where p.regra = :regra ORDER BY p.id DESC", Perfil.class);
		query.setParameter("regra","%" + regra + "%");
		
		List<Perfil> perfis = query.getResultList(); 
		return perfis;
	}
	
	@Override
	public List<Perfil> findByDescricaoOrRegra(String descricao, String regra) {
		
		StringBuilder script = new StringBuilder("select p from Perfil p where 1 = 1 ");
		
		if(descricao != null && !descricao.isEmpty())
			script.append(" p.descricao LIKE :descricao");
		
		if(regra != null && !regra.isEmpty())
			script.append("and p.regra LIKE :regra");
		
		
		TypedQuery<Perfil> query = em.createQuery(script.toString(), Perfil.class);
		
		if(descricao != null && !descricao.isEmpty())
			query.setParameter("descricao", descricao);
		
		if(regra != null && !regra.isEmpty())
			query.setParameter("regra", regra);
		
		
		List<Perfil> perfis = query.getResultList(); 
		return perfis;
	}
}
