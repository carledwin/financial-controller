package br.com.reports;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.omnifaces.util.Messages;

import br.com.email.Email;
import br.com.email.SendMail;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class GeraRelatorioViewComanda {

	
	public void geraRelatorioViewComanda(List lista, String relatorio, boolean visualizar, boolean enviarPorEmail, String salvarLocal, Email email){
		try {
			//carregar o arquivo
			InputStream fonte  = GeraRelatorioViewComanda.class.getResourceAsStream(relatorio);

			//	compilando o arquivo
			JasperReport report = JasperCompileManager.compileReport(fonte);
			
			//preencher o arquivo (report, parameters'imagem,....', lista transformando em dataSource
			JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
			
			/*
			 * salvar local
			 * if(salvarLocal != null && !salvarLocal.isEmpty()){
			// exportacao do relatorio para outro formato, no caso PDF SALVAR LOCAL
				JasperExportManager.exportReportToPdfFile(print, salvarLocal);
			}*/
			
			if(visualizar){
				 byte[] b = JasperExportManager.exportReportToPdf(print); 
		           
				 
				 /*
				  * old
				  * HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		            res.setContentType("application/pdf");
		            //Cdigo abaixo gerar o relatrio e disponibiliza diretamente na pgina 
		            res.setHeader("Content-disposition", "inline;filename=arquivo.pdf");
		            //Cdigo abaixo gerar o relatrio e disponibiliza para o cliente baixar ou salvar 
		            //res.setHeader("Content-disposition", "attachment;filename=arquivo.pdf");
		            res.getOutputStream().write(b);
		            res.getCharacterEncoding();
		            FacesContext.getCurrentInstance().responseComplete();*/
				 
				 FacesContext fx = generatePdfFromByte("consultaServicosdeComandas.pdf", b, "application/pdf");
				 fx.responseComplete();
			}
			
			if(enviarPorEmail){
				new SendMail().enviar(salvarLocal, null, email);
				Messages.addInfo(null, "Relatrio enviado por email com sucesso!");
			}
			  
			 
	            
	            System.out.println("saiu do visualizar relatorio");
			  
		} catch (JRException e) {
			Messages.addError(null, "Erro ao tentar gerar relatrio. \n" + e.getMessage() + e.getCause());
		} catch (IOException e) {
			Messages.addError(null, "Erro ao tentar exportar relatorio para pdf e gerar arquivo de sada. \n" + e.getMessage() + e.getCause());
		}
		
			}

	

	public FacesContext generatePdfFromByte(String fileName, byte[] fileByteArray, String contentType) throws IOException {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        response.reset();
        response.setContentType(contentType);
        response.setHeader("Content-disposition", "attachment; filename=\" " + fileName + "\"");

        OutputStream output = response.getOutputStream();
        output.write(fileByteArray);
        output.close();
        return facesContext;
    }
	
}
