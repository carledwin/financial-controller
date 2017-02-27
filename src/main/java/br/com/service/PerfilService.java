package br.com.service;

import java.util.List;

import javax.ejb.Local;

import br.com.model.Perfil;
/**
 * @author carledwin
 */
@Local
public interface PerfilService {

	public Perfil save(Perfil perfil);
	public void remove(Perfil perfil);
	public Perfil findById(Long id);
	public List<Perfil> findByDescricao(String descricao);
	public List<Perfil> findByDescricaoOrRegra(String descricao, String regra);
	public List<Perfil> findByRegra(String regra);
	public List<Perfil> listAll();
}
