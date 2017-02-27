package br.com.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
/**
 * @author carledwin
 */
@Entity
@PrimaryKeyJoinColumn(name="ID_COLABORADOR")
public class Profissional  extends Pessoa{

	private static final long serialVersionUID = -1408655867455898789L;
	
	@OneToMany(mappedBy="profissional")
	private List<Usuario> usuarios;
	
	@OneToOne
	private Cargo cargo;
	
	@OneToMany(mappedBy="profissional", cascade = CascadeType.ALL)
	private List<Vale> vales;
	
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public List<Vale> getVales() {
		return vales;
	}
	
	public void setVales(List<Vale> vales) {
		this.vales = vales;
	}
	
}
