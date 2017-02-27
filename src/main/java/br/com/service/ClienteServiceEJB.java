package br.com.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.dao.AbstractPersistence;
import br.com.model.Cliente;
/**
 * @author carledwin
 */
@Stateless
public class ClienteServiceEJB extends AbstractPersistence<Cliente, Long> implements ClienteService{

	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}	
	
	public ClienteServiceEJB() {
		super(Cliente.class);
	}

	@Override
	public List<Cliente> findByNome(String nome) {
		TypedQuery<Cliente> query = em.createQuery("select c from Cliente c where c.nome LIKE :nome ", Cliente.class);
		query.setParameter("nome","%" + nome + "%");
		
		List<Cliente> clientes = query.getResultList(); 
		return clientes;
	}

	@Override
	public Cliente findOneByNome(String nome) {
		TypedQuery<Cliente> query = em.createQuery("select c from Cliente c where c.nome = :nome ", Cliente.class);
		query.setParameter("nome","%" + nome + "%");
		
		Cliente cliente = query.getSingleResult(); 
		return cliente;
	}

	@Override
	public Cliente findByIdFetch(Long id) {
		TypedQuery<Cliente> query = em.createQuery("select c from Cliente c left join c.endereco  where c.id = ? ", Cliente.class);
		query.setParameter(1, id);
		Cliente cliente = query.getSingleResult();
		
		return cliente;
	}

}
