package br.com.service;

import java.util.List;

import javax.ejb.Local;

import br.com.model.ItemComanda;
/**
 * @author carledwin
 */
@Local
public interface ItemComandaService {

	public ItemComanda save(ItemComanda itemComanda);
	public void remove(ItemComanda itemComanda);
	public ItemComanda findById(Long id);
	public List<ItemComanda> listAll();
}	
