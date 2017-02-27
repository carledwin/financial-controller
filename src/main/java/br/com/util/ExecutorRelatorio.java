package br.com.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.jdbc.Work;
import org.omnifaces.util.Messages;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class ExecutorRelatorio implements Work{

	private String caminhoRelatorio;
	private HttpServletResponse response;
	private Map<String, Object> paramentros;
	private String nomeArquivoSaida;
	private boolean relatorioGerado;
	
	public ExecutorRelatorio(String caminhoRelatorio, HttpServletResponse response, Map<String, Object> parametros, String nomeArquivoSaida) {
		this.caminhoRelatorio = caminhoRelatorio;
		this.response = response;
		this.paramentros = parametros;
		this.nomeArquivoSaida = nomeArquivoSaida;
		this.paramentros.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
	}

	@Override
	public void execute(Connection con) throws SQLException {
		try {
		InputStream relatorioStream = this.getClass().getResourceAsStream(this.caminhoRelatorio);
		JasperPrint print;
		
			print = JasperFillManager.fillReport(relatorioStream, this.paramentros, con);
		
			this.setRelatorioGerado(print.getPages().size() > 0);
		
			if(this.isRelatorioGerado()){
				JRExporter exportador = new JRPdfExporter();
				exportador.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
				exportador.setParameter(JRExporterParameter.JASPER_PRINT, print);
				
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition",  "attachment; filename=\"" + this.nomeArquivoSaida +"\"");
				
				exportador.exportReport();
			}
			
		} catch (JRException | IOException e) {
			System.out.println("Erro ao tentar gerar relatorio.");
			Messages.addError(null, "Erro ao tentar gerar relatrio.");
		}
		
	}

	public boolean isRelatorioGerado() {
		return relatorioGerado;
	}

	public void setRelatorioGerado(boolean relatorioGerado) {
		this.relatorioGerado = relatorioGerado;
	}
}
