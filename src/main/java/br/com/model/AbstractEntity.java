package br.com.model;

import java.io.Serializable;

/**
 * Esipula um contrato base para as entidade persistentes da aplicao.
 * 
 * <p>Esse contrao  utilizado pelo componente base de persistncia: <code>AbstractPersistence</code>.</p>
 * @author ceanascimento
 *
 */
public interface AbstractEntity extends Serializable{

	/**
	 * 
	 * @return A referencia para a chave primaria(primary key) de cada objeto persistido.
	 * 			Caso o objeto ainda no tenha sido persistido, deve retornar <code>null</code>
	 */
	public Number getId();
}
