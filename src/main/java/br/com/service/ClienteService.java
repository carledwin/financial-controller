package br.com.service;

import java.util.List;

import javax.ejb.Local;

import br.com.model.Cliente;
/**
 * @author carledwin
 */
@Local
public interface ClienteService {

	public Cliente save(Cliente Cliente);
	public void remove(Cliente Cliente);
	public Cliente findById(Long id);
	public Cliente findByIdFetch(Long id);
	public List<Cliente> findByNome(String nome);
	public Cliente findOneByNome(String nome);
	public List<Cliente> listAll();
}
