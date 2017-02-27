package br.com.service;

import java.util.List;

import javax.ejb.Local;

import br.com.model.TipoServico;
/**
 * @author carledwin
 */
@Local
public interface TipoServicoService {

	public TipoServico save(TipoServico service);
	public void remove(TipoServico service);
	public TipoServico findById(Long id);
	public List<TipoServico> findByNome(String nome);
	public List<TipoServico> listAll();
}
