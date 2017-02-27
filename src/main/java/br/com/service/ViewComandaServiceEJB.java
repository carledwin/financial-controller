package br.com.service;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.dao.AbstractPersistence;
import br.com.model.ViewComanda;

/**
 * @author carledwin
 */
@Stateless
public class ViewComandaServiceEJB extends AbstractPersistence<ViewComanda, Long> implements ViewComandaService {

	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

	public ViewComandaServiceEJB() {
		super(ViewComanda.class);
	}

	@Override
	public List<ViewComanda> findAll() {
		TypedQuery<ViewComanda> query = em.createQuery("SELECT vwc FROM ViewComanda vwc ", ViewComanda.class);

		List<ViewComanda> comandas = query.getResultList();
		return comandas;
	}

	@Override
	public List<ViewComanda> findByParam(Long reportIdComanda, Long reportIdCliente, Long reportIdColaborador1,
			Calendar reportComandaDataCriacao, Calendar reportComandaDataAlteracao) {

		TypedQuery<ViewComanda> query = em.createNamedQuery("View.Comanda.all", ViewComanda.class);
		List<ViewComanda> comandas = query.getResultList();
		return comandas;

	}

}
