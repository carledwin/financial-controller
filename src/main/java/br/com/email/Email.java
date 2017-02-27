package br.com.email;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

	private String mailSMTPServer;
	private String mailSMTPServerPort;
	private String from;
	private String to;
	private String mensagem;
	private String assunto;

	Properties properties;

	public Email() {
		/*
		 * mailSMTPServer="smtp.gmail.com"; mailSMTPServerPort="465";
		 */
		mailSMTPServer = "localhost";
		mailSMTPServerPort = "25";
	}

	public Email(String mailSMTPServer, String mailSMTPServerPort) {
		super();
		this.mailSMTPServer = mailSMTPServer;
		this.mailSMTPServerPort = mailSMTPServerPort;
	}

	@PostConstruct
	public void init() {
		properties = new Properties();
	}

	public void sendMail(String from, Address[] toUser, String subject, String message) {

		/*
		 * props.setProperty("proxySet","true");
		 * props.setProperty("socksProxyHost","192.168.155.1"); // IP do
		 * Servidor Proxy props.setProperty("socksProxyPort","1080"); // Porta
		 * do servidor Proxy
		 */
		properties = new Properties();
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.startls.enable", "true");
		properties.put("mail.smtp.host", mailSMTPServer);
		properties.put("mail.smtp.auth", "false"); // ativa autenticação
		// properties.put("mail.smtp.auth","true"); // ativa autenticação
		// properties.put("mail.smtp.user",from); //usuario DE:/quem envia
		properties.put("mail.smtp.user", "root@localhost"); // usuario DE:/quem
															// envia
		properties.put("mail.smtp.debug", "true");
		properties.put("mail.smtp.port", mailSMTPServerPort);
		properties.put("mail.smtp.socketFactory.port", mailSMTPServerPort);
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.socketFactory.fallback", "false");

		// Cria um autenticador
		Session session = Session.getDefaultInstance(properties,
				/*
				 * new Authenticator() {
				 * 
				 * protected PasswordAuthentication getPasswordAuthentication(){
				 * //return new PasswordAuthentication(Email.FROM, Email.PASS);
				 * return new PasswordAuthentication("root", "root"); } }
				 */ null);

		// ativa debug para a session
		session.setDebug(true);

		try {
			Address[] toUsers2 = InternetAddress.parse("root@localhost");

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			// msg.setRecipients(Message.RecipientType.TO, toUser);
			msg.setRecipients(Message.RecipientType.TO, toUsers2);
			msg.setSubject(subject); // assunto
			msg.setText(message); // Conteudo

			// envia a mensagem
			Transport.send(msg);

			System.out.println("Email enviado com sucesso!");
		} catch (MessagingException e) {
			throw new RuntimeException("Erro no envio de email.");
		}
	}

	public String getMailSMTPServer() {
		return mailSMTPServer;
	}

	public void setMailSMTPServer(String mailSMTPServer) {
		this.mailSMTPServer = mailSMTPServer;
	}

	public String getMailSMTPServerPort() {
		return mailSMTPServerPort;
	}

	public void setMailSMTPServerPort(String mailSMTPServerPort) {
		this.mailSMTPServerPort = mailSMTPServerPort;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
