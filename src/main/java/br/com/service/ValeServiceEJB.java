package br.com.service;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.dao.AbstractPersistence;
import br.com.model.Vale;
import br.com.model.StatusValeEnum;

/**
 * @author carledwin
 */

@Stateless
public class ValeServiceEJB extends AbstractPersistence<Vale, Long> implements ValeService {

	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

	public ValeServiceEJB() {
		super(Vale.class);
	}

	@Override
	public List<Vale> findByParam(Long codigo, String descricao, Calendar dataVale, StatusValeEnum status) {

		StringBuilder script = new StringBuilder(" SELECT v FROM Vale v where 1=1 ");

		if (codigo != null) {
			script.append(" v.id = :codigo ");
		}

		if (dataVale != null) {
			script.append(" v.dataVale = :dataVale ");
		}

		if (status != null) {
			script.append(" v.status = :status ");
		}

		TypedQuery<Vale> query = em.createQuery(script.toString(), Vale.class);

		if (codigo != null) {
			query.setParameter("codigoObjeto", codigo);
		}

		if (dataVale != null) {
			query.setParameter("dataVencimento", dataVale);
		}

		if (status != null) {
			query.setParameter("status", status);
		}
		List<Vale> vales = query.getResultList();

		return vales;
	}

}
