package br.com.reports;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;

import br.com.model.ViewComanda;
import br.com.service.ViewComandaService;
import br.com.service.ViewComandaServiceEJB;
import net.sf.jasperreports.engine.JRException;

public class TestConsultaViewComanda {

	
	private ViewComandaServiceEJB viewComandaServiceEJB;
	
	public static void main(String[] args) throws JRException, SQLException {
	
		System.out.println("Gerando relatrio...");

		TestConsultaViewComanda ts = new TestConsultaViewComanda();
		
		ts.viewComandaServiceEJB = new ViewComandaServiceEJB();
		
		List<ViewComanda> comandas = ts.viewComandaServiceEJB.findAll();
		
		for (ViewComanda cm: comandas) {
			//System.out.println("Comanda: " + cm.getComandaId() + "Cliente: "+ cm.getClienteNome());
		}
		
	}
}
