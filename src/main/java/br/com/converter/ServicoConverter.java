package br.com.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.model.Servico;
import br.com.service.ServicoService;

@FacesConverter(value = "servico")
public class ServicoConverter implements Converter {

	@Inject	ServicoService servicoService;

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {

		Servico servico= (Servico) servicoService.findById(Long.parseLong(string));
		return servico;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		
		if (o != null && !o.equals("")) {
		Servico servico = new Servico();
		servico = (Servico) o;
		return String.valueOf(servico.getId());
	}
		return null;
	}
}