package br.com.service;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import br.com.model.Vale;
import br.com.model.StatusValeEnum;
/**
 * @author carledwin
 */
@Local
public interface ValeService {

	public Vale save(Vale vale);
	public void remove(Vale vale);
	public Vale findById(Long id);
	public List<Vale> findByParam(Long codigo, String descricao, Calendar dataVale, StatusValeEnum status);
	public List<Vale> listAll();
}
