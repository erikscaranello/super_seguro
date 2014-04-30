package br.com.sousuperseguro.utilImpl;

import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.jrimum.bopepo.view.BoletoViewer;
import org.springframework.stereotype.Component;

import br.com.sousuperseguro.entities.RecebidoSouSuperSeguro;
import br.com.sousuperseguro.entities.Users;
import br.com.sousuperseguro.util.EnvioDeEmail;

@Component
public class EnvioDeEmailImpl implements EnvioDeEmail {

	@Override
	public void enviarEmail(Users user) {
		
		HtmlEmail email = new HtmlEmail();
		email.setHostName("smtp.supersegurocorretora.com.br");
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator("nao_responder@supersegurocorretora.com.br", "35jkg6w1"));
		email.setSSLOnConnect(true);
		try {
			email.setFrom("nao_responder@supersegurocorretora.com.br");
		} catch (EmailException e) {
			e.printStackTrace();
		}
		
		//senha
		//35jkg6w1
		
		email.setSubject("Recuperacao de senha sistema de integracao Sou Super Seguro - Odontoprev");
		
		try {
		
			email.setHtmlMsg("<html><head></head><body><p>Ola "+ user.getInfosPessoais().getNome() + " " + user.getInfosPessoais().getSobrenome() + "</p>" +
					"<p>Acesse este link para refazer sua senha: </p>"
					+ "<p><a href='http://cpro20821.publiccloud.com.br/sou_seguro/recuperacao_de_senha/nova_senha?email="+ user.getInfosPessoais().getEmail() +"'>Recuperacao de senha de " + user.getInfosPessoais().getNome() + " " + user.getInfosPessoais().getSobrenome() + "</a></p>" +
					"</body></html>");
		
		} catch (EmailException e) {
			e.printStackTrace();
		}
		try {
			
			email.addTo(user.getInfosPessoais().getEmail());
		
		} catch (EmailException e) {
			e.printStackTrace();
		}
		try {
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void enviarEmailComBoleto(RecebidoSouSuperSeguro cliente, BoletoViewer boleto) {
		
		
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath("" + boleto);
//		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("boleto para " + cliente.getRecebidoSouSuperSeguroCobranca().getNmCobr());
		attachment.setName(cliente.getRecebidoSouSuperSeguroCobranca().getNmCobr());
		
		
		MultiPartEmail  email = new MultiPartEmail ();
		email.setHostName("smtp.supersegurocorretora.com.br");
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator("nao_responder@supersegurocorretora.com.br", "35jkg6w1"));
//		email.setSSLOnConnect(true);
		email.setSubject("Boleto de Pagamento, Sou Super Seguro Corretora");
		
		try {
			email.setFrom("nao_responder@supersegurocorretora.com.br");
			email.setMsg("Olá, este é um e-mail da Sou Super Seguro Corretora com o seu boleto.");
			email.addTo(cliente.getRecebidoSouSuperSeguroCobranca().getEmail());
			
			email.attach(new ByteArrayDataSource(boleto.getPdfAsByteArray(), "application/pdf"),
				      "boleto de: " + cliente.getRecebidoSouSuperSeguroCobranca().getNmCobr() + ".pdf", 
				      "",
				       EmailAttachment.ATTACHMENT);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}		
	}
}
