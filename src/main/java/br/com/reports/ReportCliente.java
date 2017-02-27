package br.com.reports;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.model.Cliente;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReportCliente {

	public static void main(String[] args) throws JRException, SQLException {
		System.out.println("Gerando relatrio...");

		// lista com os nossos clientes
		List<Cliente> lista = new ArrayList<Cliente>();
		Cliente c1 = new Cliente();
		c1.setNome("Alexandre Macedo");
		c1.setEmail("alexbmac@gmail.com");
		c1.setTelefone("9999-9999");
		Cliente c2 = new Cliente();
		c2.setNome("Rafael Cosentino");
		c2.setEmail("cosen@gmail.com");
		c2.setTelefone("8888-8888");
		Cliente c3 = new Cliente();
		c3.setNome("Daniel Machado");
		c3.setEmail("daniel@gmail.com");
		c3.setTelefone("7777-7777");
		lista.add(c1);
		lista.add(c2);
		lista.add(c3);
		
		// compilacao do JRXML
		//sgmania\src\main\resources\report
		JasperReport report = JasperCompileManager .compileReport("src/main/resources/report/reportClientes.jrxml");
		
		// preenchimento do relatorio, note que o metodo recebe 3 parametros: 
		// 1 - o relatorio // 
		// 2 - um Map, com parametros que sao passados ao relatorio 
		// no momento do preenchimento. No nosso caso eh null, pois nao 
		// estamos usando nenhum parametro // 
		// 3 - o data source. Note que nao devemos passar a lista diretamente, 
		// e sim "transformar" em um data source utilizando a classe 
		// JRBeanCollectionDataSource
		JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));  
		
		// exportacao do relatorio para outro formato, no caso PDF
		JasperExportManager.exportReportToPdfFile(print, "src/main/resources/report/reportClientes.pdf");  
		System.out.println("Relatrio gerado.");
		
		
	}
}
