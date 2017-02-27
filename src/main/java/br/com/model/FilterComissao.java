package br.com.model;

import java.util.Calendar;

/**
 * @author carledwin
 */
public class FilterComissao {

	private Profissional profissional;
	private Calendar dataDe;
	private Calendar dataAte;
	private StatusComissaoEnum status;
	private StatusItemEnum statusItem;

	public StatusItemEnum getStatusItem() {
		return statusItem;
	}

	public void setStatusItem(StatusItemEnum statusItem) {
		this.statusItem = statusItem;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public Calendar getDataDe() {
		return dataDe;
	}

	public void setDataDe(Calendar dataDe) {
		this.dataDe = dataDe;
	}

	public Calendar getDataAte() {
		return dataAte;
	}

	public void setDataAte(Calendar dataAte) {
		this.dataAte = dataAte;
	}

	public StatusComissaoEnum getStatus() {
		return status;
	}

	public void setStatus(StatusComissaoEnum status) {
		this.status = status;
	}

}
