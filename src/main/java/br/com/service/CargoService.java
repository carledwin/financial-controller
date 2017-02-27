package br.com.service;

import java.util.List;

import javax.ejb.Local;

import br.com.model.Cargo;
/**
 * @author carledwin
 */
@Local
public interface CargoService {

	public Cargo save(Cargo cargo);
	public void remove(Cargo cargo);
	public Cargo findById(Long id);
	public List<Cargo> findByDescricao(String descricao);
	public List<Cargo> listAll();
}
