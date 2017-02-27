package br.com.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.dao.AbstractPersistence;
import br.com.model.Servico;
/**
 * @author carledwin
 */
@Stateless
public class ServicoServiceEJB extends AbstractPersistence<Servico, Long> implements ServicoService{

	@PersistenceContext
	private EntityManager em;

	@Override 
	protected EntityManager getEntityManager() {
		return this.em;
	}	
	
	public ServicoServiceEJB() {
		super(Servico.class);
	}

	@Override
	public List<Servico> findByNome(String descricao) {
		TypedQuery<Servico> query = em.createQuery("select s from Servico s where s.descricao LIKE :descricao ORDER BY s.id DESC", Servico.class);
		query.setParameter("descricao","%" + descricao + "%");
		
		List<Servico> servicos = query.getResultList(); 
		return servicos;
	}

}
