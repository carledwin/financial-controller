package br.com.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.dao.AbstractPersistence;
import br.com.model.TipoServico;

/**
 * @author carledwin
 */
@Stateless
public class TipoServicoServiceEJB extends AbstractPersistence<TipoServico, Long> implements TipoServicoService{

	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}	
	
	public TipoServicoServiceEJB() {
		super(TipoServico.class);
	}

	@Override
	public List<TipoServico> findByNome(String nome) {
		TypedQuery<TipoServico> query = em.createQuery("select ts from TipoServico ts where ts.nome LIKE :nome ORDER BY ts.id DESC", TipoServico.class);
		query.setParameter("nome","%" + nome + "%");
		
		List<TipoServico> tiposServico = query.getResultList(); 
		return tiposServico;
	}

}
