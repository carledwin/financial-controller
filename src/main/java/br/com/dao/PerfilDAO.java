package br.com.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.model.Perfil;

@Stateless
public class PerfilDAO {

	@PersistenceContext(unitName = "BeautySalonManagement-MySql-PU")
	private EntityManager em;
	
	public List<Perfil> listar(){
		List<Perfil> perfis = null;
		
		try {
			Query query = em.createQuery("SELECT e FROM Perfil e ORDER BY e.descricao");
			perfis = query.getResultList();
		} catch (Exception e) {
		System.out.println("Erro ao tentar consultar perfis.");
		}
		
		return perfis;
	}
}
