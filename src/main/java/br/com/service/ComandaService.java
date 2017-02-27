package br.com.service;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import br.com.model.Comanda;
/**
 * @author carledwin
 */
@Local
public interface ComandaService {

	Comanda save(Comanda comanda);
	void remove(Comanda comanda);
	Comanda findById(Long id);
	List<Comanda> findByParam(Long codigoObjeto, String nomeCliente, String nomeColaborador, Calendar dataInicial, Calendar dataFinal);
	List<Comanda> listAll();
}
