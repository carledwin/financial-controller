package br.com.service;

import java.util.List;

import javax.ejb.Local;

import br.com.model.Comissao;
import br.com.model.FilterComissao;
import br.com.model.ItemComanda;
/**
 * @author carledwin
 */
@Local
public interface ComissaoService {

	public void calcular(Comissao comissao);
	public void pagar(Comissao comissao);
	public List<Comissao> findByFilter(FilterComissao filter);
	public Comissao findById(Long id);
	public List<ItemComanda> findByFilterPayable(FilterComissao filter);
	public List<Comissao> listAll();
}
