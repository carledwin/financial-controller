package br.com.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * @author carledwin
 */
//@DiscriminatorValue("CLIENTE")
@SuppressWarnings("serial")
@Entity
@PrimaryKeyJoinColumn(name="ID_CLIENTE")
public class Cliente extends Pessoa{

}
