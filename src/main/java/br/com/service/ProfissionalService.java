package br.com.service;

import java.util.List;

import javax.ejb.Local;

import br.com.model.Profissional;
/**
 * @author carledwin
 */
@Local
public interface ProfissionalService {

	public Profissional save(Profissional colaborador);
	public void remove(Profissional colaborador);
	public Profissional findById(Long id);
	public List<Profissional> findByNome(String nome);
	public List<Profissional> listAll();
}
