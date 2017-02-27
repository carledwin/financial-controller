package br.com.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import br.com.dao.AbstractPersistence;
import br.com.model.Comissao;
import br.com.model.FilterComissao;
import br.com.model.ItemComanda;
import br.com.model.StatusComissaoEnum;
import br.com.model.StatusItemEnum;
/**
 * @author carledwin
 */
@Stateless
public class ComissaoServiceEJB extends AbstractPersistence<Comissao, Long> implements ComissaoService{

	@PersistenceContext
	private EntityManager em;
	
	@EJB
	private ItemComandaService itemComandaService;

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}	
	
	public ComissaoServiceEJB() {
		super(Comissao.class);
	}

	@Override
	public List<Comissao> findByFilter(FilterComissao filter) {
		TypedQuery<Comissao> query = em.createQuery("select c from Comissao c ORDER BY c.id", Comissao.class);
		
		List<Comissao> items = query.getResultList(); 
		return items;
	}
	
	@Override
	public List<ItemComanda> findByFilterPayable(FilterComissao filter) {
		StringBuilder jpql = new StringBuilder("SELECT ic FROM ItemComanda ic WHERE 1=1 ");
		jpql.append(" AND ic.profissional = :profissional ");
		jpql.append(" AND ic.statuItem = :status ");
		jpql.append(" ORDER BY ic.id ");
		
		TypedQuery<ItemComanda> query = em.createQuery(jpql.toString(), ItemComanda.class);
		
		query.setParameter("status", filter.getStatusItem());
		query.setParameter("profissional", filter.getProfissional());
		
		List<ItemComanda> items = query.getResultList(); 
		return items;
	}

	@Override
	public void calcular(Comissao comissao) {
		getEntityManager().persist(comissao);
		
		for(ItemComanda ic : comissao.getItemsComanda()){
			itemComandaService.findById(ic.getId());
			ic.setComissao(comissao);
			itemComandaService.save(ic);
		}
		
	}
	
	@Override
	public void pagar(Comissao comissao) {
		getEntityManager().merge(comissao);
		
		for(ItemComanda ic : comissao.getItemsComanda()){
			itemComandaService.findById(ic.getId());
			ic.setStatuItem(StatusItemEnum.PAGO);
			ic.setComissao(comissao);
			itemComandaService.save(ic);
		}
		
	}

}
