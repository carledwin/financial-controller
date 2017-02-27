package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.model.AbstractEntity;



/**
 * A classe resolve os mtodos bsicos de cadastro (CRUD) com API da <code>JPA</code>.
 * 
 * @author ceanascimento
 *
 * @param <T>
 * @param <PK>
 */
public abstract class AbstractPersistence<T extends AbstractEntity, PK extends Number>{
	
	/**
	 * A classe da entidade, necessrio para o mtodo <code>EntityManager.find</code>.
	 */
	
	private Class<T> entityClass;
	
	/**
	 * Exige a definio do <code>EntityManager</code> responsvel pelas operaes de persistncia.
	 * @return
	 */
	protected abstract EntityManager getEntityManager();
	
	public AbstractPersistence(Class<T> entityClass){
		this.entityClass = entityClass;
	}
	
	public T save(T e){
		if(e.getId() != null){
			return getEntityManager().merge(e);
		}else{
			getEntityManager().persist(e);
			return e;
		}
	}
	
	public void remove(T e){
		getEntityManager().remove(getEntityManager().merge(e));
	}
	
	public T findById(PK id){
		return getEntityManager().find(entityClass, id);
	}
	
	public List<T> listAll(){
		
		CriteriaQuery criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery();
		Root<T> root = criteriaQuery.from(entityClass);
		criteriaQuery.select(root);
		
		
		return getEntityManager().createQuery(criteriaQuery).getResultList();
	}
	

	public List<T> findRange(int[] range){
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		
		Query q = getEntityManager().createQuery(cq);
		q.setMaxResults(range[1] - range[0]);
		q.setFirstResult(range[0]);
		return q.getResultList();
	}
	
	public int count(){
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		
		Root<T> rt = cq.from(entityClass);
		
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		
		Query q  = getEntityManager().createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}
	
}
