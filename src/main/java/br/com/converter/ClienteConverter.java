package br.com.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.model.Cliente;
import br.com.service.ClienteService;

@FacesConverter(value = "cliente")
public class ClienteConverter implements Converter {

	@Inject	ClienteService clienteService;

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {

		if(string != null && !string.equals("null")){
		Cliente cliente = clienteService.findById(Long.parseLong(string));
		return cliente;
		}else{
			return new Cliente();
		}
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		if (o != null && !o.equals("")) {
		Cliente cliente = new Cliente();
		cliente = (Cliente) o;
		return String.valueOf(cliente.getId());
	
	}
	return null;
}
}