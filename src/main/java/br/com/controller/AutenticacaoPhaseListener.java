package br.com.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

/**
 * @author carledwin
 */
public class AutenticacaoPhaseListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent arg0) {
		// TODO No implementado
		
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {

		FacesContext context = FacesContext.getCurrentInstance();
		
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		
		if(session != null){
			
			String mensagem = (String) session.getAttribute("msg");
			
			if(mensagem != null){
				
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, null));
				
				session.setAttribute("msg", null);
			}
		}
		
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
