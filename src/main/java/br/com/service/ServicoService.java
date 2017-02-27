package br.com.service;

import java.util.List;

import javax.ejb.Local;

import br.com.model.Servico;
/**
 * @author carledwin
 */
@Local
public interface ServicoService {

	public Servico save(Servico service);
	public void remove(Servico service);
	public Servico findById(Long id);
	public List<Servico> findByNome(String nome);
	public List<Servico> listAll();
}
