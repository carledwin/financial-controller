package br.com.service;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import br.com.model.ViewComanda;
/**
 * @author carledwin
 */
@Local
public interface ViewComandaService {
	List<ViewComanda> findByParam(Long reportIdComanda, Long reportIdCliente, Long reportIdColaborador1, Calendar reportComandaDataCriacao, Calendar reportComandaDataAlteracao);
	List<ViewComanda> findAll();
}
