package br.com.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.dao.AbstractPersistence;
import br.com.model.Cargo;
/**
 * @author carledwin
 */
@Stateless
public class CargoServiceEJB extends AbstractPersistence<Cargo, Long> implements CargoService{

	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}	
	
	public CargoServiceEJB() {
		super(Cargo.class);
	}

	@Override
	public List<Cargo> findByDescricao(String descricao) {
		TypedQuery<Cargo> query = em.createQuery("select c from Cargo c where c.descricao LIKE :descricao ORDER BY c.id DESC", Cargo.class);
		query.setParameter("descricao","%" + descricao + "%");
		
		List<Cargo> cargos = query.getResultList(); 
		return cargos;
	}

}
