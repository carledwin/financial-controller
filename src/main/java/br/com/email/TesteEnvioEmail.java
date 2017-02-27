package br.com.email;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class TesteEnvioEmail {

	public TesteEnvioEmail() {}
	
	public static void main(String[] args) {
		
		Email email = new Email();
		
		 
		try {
			
		Address[] toUsers = InternetAddress.parse("carlinstr@gmail.com");
		
		String subject = "Assunto    4";
		
		String message = "http://www.d.umn.edu/~gshute/cs4531/project/2012/mail/mail.xhtml."
				+ "\n\n"
				+ "http://www.mkyong.com/spring/spring-sending-e-mail-via-gmail-smtp-server-with-mailsender/"
				+ "\n\n"
				+ "http://crunchify.com/java-mailapi-example-send-an-email-via-gmail-smtp/";
		
		email.sendMail("", toUsers, subject, message);
		
		} catch (AddressException e) {
			System.out.println("Erro ao tentar converter endereos de email.");
		}
	}

}
