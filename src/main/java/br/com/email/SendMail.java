package br.com.email;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.omnifaces.util.Messages;

public class SendMail {
	
	private static final String TRUE = "true";
	private static final String SMTP = "smtp";
	private static final String MAIL_SMTP_SOCKET_FACTORY_FALLBACK = "mail.smtp.socketFactory.fallback";
	private static final String MAIL_SMTP_SOCKET_FACTORY_CLASS = "mail.smtp.socketFactory.class";
	private static final String MAIL_SMTP_SOCKET_FACTORY_PORT = "mail.smtp.socketFactory.port";
	private static final String MAIL_SMTP_PORT = "mail.smtp.port";
	private static final String MAIL_DEBUG = "mail.debug";
	private static final String MAIL_SMTP_USER = "mail.smtp.user";
	private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
	private static final String MAIL_SMTP_HOST = "mail.smtp.host";
	private static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
	private static final String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
	private static final String EMAIL_SMTP_USER = "carlinstr@gmail.com";
	private static final String PASSWORD = "XXXXXX";
		
	public void enviar(String arquivo, String imagem, Email email) {

		try {
			Properties mailProps = getPropertiesServer();
			Session mailSession = getSession(EMAIL_SMTP_USER, PASSWORD, mailProps);
			Message mailMessage = getMessage(EMAIL_SMTP_USER, mailSession, arquivo, imagem, email.getAssunto(), email.getMensagem());

			// send email
			Transport.send(mailMessage);
		} catch (IOException e) {
			Messages.addError(null,"Erro ao tentar anexar arquivo no email. Error: " + e.getMessage() + "   **** Cause: " + e.getCause());
			e.printStackTrace();
		} catch (Exception e) {
			Messages.addError(null,"Erro ao tentar enviar email. Error: " + e.getMessage() + "   **** Cause: " + e.getCause());
		}
	}

	private Message getMessage(String login, Session mailSession, String arquivo, String imagem, String assunto, String mensagem)
			throws MessagingException, AddressException, IOException {
		// config. da mensagem
		Message mailMessage = new MimeMessage(mailSession);

		// remetente
		mailMessage.setFrom(new InternetAddress(login));

		// destinatario
		mailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(login));

		// mensagem que vai no corpo do email
		MimeBodyPart mbpMensagem = new MimeBodyPart();
		mbpMensagem.setText(mensagem);

		// partes do email
		Multipart mp = new MimeMultipart();
		mp.addBodyPart(mbpMensagem);
		
		if(imagem != null &&!imagem.isEmpty()){
			// imagem que sera enviada em anexo, ta no mesmo diretorio da classe.
			InputStream is = getClass().getResourceAsStream(imagem);
	
			// setando o anexo
			MimeBodyPart mbpAnexo = new MimeBodyPart();
			mbpAnexo.setDataHandler(new DataHandler(new ByteArrayDataSource(is, "application/image")));
	
			mbpAnexo.setFileName(imagem);
			mp.addBodyPart(mbpAnexo);
		}
		
		if(arquivo != null && !arquivo.isEmpty()){
			anexarArquivo(mp, arquivo);
		}

		// assunto do email
		mailMessage.setSubject(assunto);

		// seleciona o conteudo
		mailMessage.setContent(mp);
		return mailMessage;
	}

	private void anexarArquivo(Multipart mp, String arquivo) throws MessagingException {
		// anexa o arquivo na mensagem
		// cria a segunda parte da mensage
		MimeBodyPart mbp2 = new MimeBodyPart();
		FileDataSource fds = new FileDataSource(new File(arquivo));
		mbp2.setDataHandler(new DataHandler(fds));
		mbp2.setFileName(fds.getName());
		mp.addBodyPart(mbp2);
	}

	private Session getSession(final String login, final String password, Properties mailProps) {
		// autenticar
		Session mailSession = Session.getInstance(mailProps, new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(login, password);
			}
		});
		mailSession.setDebug(false);
		return mailSession;
	}

	private Properties getPropertiesServer() {
		// config. gmail
		Properties mailProps = new Properties();
		mailProps.put(MAIL_TRANSPORT_PROTOCOL, SMTP);
		mailProps.put(MAIL_SMTP_STARTTLS_ENABLE, TRUE);
		mailProps.put(MAIL_SMTP_HOST, "smtp.gmail.com");
		mailProps.put(MAIL_SMTP_AUTH, TRUE);
		mailProps.put(MAIL_SMTP_USER, EMAIL_SMTP_USER);
		mailProps.put(MAIL_DEBUG, TRUE);
		mailProps.put(MAIL_SMTP_PORT, "465");
		mailProps.put(MAIL_SMTP_SOCKET_FACTORY_PORT, "465");
		mailProps.put(MAIL_SMTP_SOCKET_FACTORY_CLASS, "javax.net.ssl.SSLSocketFactory");
		mailProps.put(MAIL_SMTP_SOCKET_FACTORY_FALLBACK, "false");
		return mailProps;
	}
	}