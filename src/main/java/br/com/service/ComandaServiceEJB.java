package br.com.service;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.dao.AbstractPersistence;
import br.com.model.Comanda;
/**
 * @author carledwin
 */
@Stateless
public class ComandaServiceEJB extends AbstractPersistence<Comanda, Long> implements ComandaService{

	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}	
	
	public ComandaServiceEJB() {
		super(Comanda.class);
	}

	@Override
	public List<Comanda> findByParam(Long codigoObjeto, String nomeCliente, String nomeColaborador, Calendar dataInicial, Calendar dataFinal) {
		
		StringBuilder script = new StringBuilder(" SELECT c FROM Comanda c where ");
		
		if(codigoObjeto != null)
			script.append(" c.id = :codigoObjeto ");
		
		if(dataInicial != null)
			script.append(" c.dataInicial = :dataInicial ");
		
		if(dataInicial != null)
			script.append(" c.dataInicial = :dataInicial ");

		TypedQuery<Comanda> query = em.createQuery(script.toString(), Comanda.class);

		if(codigoObjeto != null)
			query.setParameter("codigoObjeto", codigoObjeto);
		
		if(dataInicial != null)
		query.setParameter("dataInicial", dataInicial);
		
		if(dataInicial != null)
			query.setParameter("nomeCliente", nomeCliente);
		
		List<Comanda> comandas = query.getResultList();
		return comandas;
	}
	
}
