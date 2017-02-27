package br.com.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.dao.AbstractPersistence;
import br.com.model.Comanda;
import br.com.model.ItemComanda;
/**
 * @author carledwin
 */
@Stateless
public class ItemComandaServiceEJB extends AbstractPersistence<ItemComanda, Long> implements ItemComandaService{

	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}	
	
	public ItemComandaServiceEJB() {
		super(ItemComanda.class);
	}
	
	@Override
	public void remove(ItemComanda i) {
		
		StringBuilder s = new StringBuilder("DELETE FROM ItemComanda i WHERE i.id = :id ");
		
		Query q = em.createQuery(s.toString());	
		
		q.setParameter("id", i.getId());
		
		q.executeUpdate();
		
		Comanda c = em.find(Comanda.class, i.getComanda().getId());
		
		System.out.println(c.getItemComandas().size());
		
	}

}
