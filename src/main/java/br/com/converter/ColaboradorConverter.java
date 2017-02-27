package br.com.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.model.Profissional;
import br.com.service.ProfissionalService;

@FacesConverter(value = "colaborador")
public class ColaboradorConverter implements Converter {

	@Inject	ProfissionalService colaboradorService;

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {

		Profissional colaborador = (Profissional) colaboradorService.findByNome(string);
		return colaborador;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object o) {
		Profissional colaborador = new Profissional();
		colaborador = (Profissional) o;
		return String.valueOf(colaborador.getNome());
	}

}