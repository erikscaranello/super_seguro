package br.com.sousuperseguro.utilImpl;

import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
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
		
		
		HtmlEmail email = new HtmlEmail();
		email.setHostName("smtp.supersegurocorretora.com.br");
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator("bradescodental@supersegurocorretora.com.br", "35jkg6w1"));
//		email.setSSLOnConnect(true);
		email.setSubject("Boleto de Pagamento, Sou Super Seguro Corretora");
		
		try {
			email.setFrom("bradescodental@supersegurocorretora.com.br");
			
			email.setHtmlMsg("<!DOCTYPE html>"
					+ "<html>"
					+ "<head>"
					+ "<meta charset='utf-8' />"
					+ "<title>Plano Bradesco Dental Ideal</title>"
					+ "</head>"
					+ "<body>"
					+ "<p>Sr(a) " + cliente.getNome() + "</p>"
					+ "<p>Numero da Proposta: " + cliente.getNroProposta() + "</p>"
					+ "<p>Parabéns!!  Você acaba de contratar o Plano Bradesco Dental Ideal.</p>"
					+ "<p>Após o pagamento da primeira parcela, você já poderá utilizar o atendimento de urgência e emergência, para as demais coberturas não deixe de acessar o link abaixo e consultar a íntegra das Condições Gerais do seu plano.</p>"
					+ "<p><a href='http://portal.bradescodental.com.br/bdpf/pdf/welcome_kit_miolo_rev02_me_virtual.pdf'>http://portal.bradescodental.com.br/bdpf/pdf/welcome_kit_miolo_rev02_me_virtual.pdf</a></p>"
					+ "<p>Não deixe de acessar também o portal <a href='http://www.bradescodental.com.br'>bradescodental.com.br</a> e usufrua dos inúmeros benefícios disponibilizados:</p>"
					
					+ "<p><ul>"
					+ "<li>Consulta de Rede Credenciada;</li>"
					+ "<li>Consulta às condições gerais do plano contratado e procedimentos cobertos;</li>"
					+ "<li>Acompanhamento do tratamento da sua família;</li>"
					+ "<li>Informativo de Imposto de Renda;</li>"
					+ "</ul></p>"
					+ "<p>Em caso de dúvidas sobre seu plano ligue para Central de Relacionamento com o cliente 4004-2700 ( Capitais e Regiões Metropolitana ) ou 0800 701 2700 ( Demais Localidades )</p>"
					+ "<p>Atenciosamente,</p>");
			
			
//					email.addTo("erikscaranello@gmail.com");
					email.addTo(cliente.getRecebidoSouSuperSeguroCobranca().getEmail());
					email.addBcc("anapaula.martins@supersegurocorretora.com.br");
			email.attach(new ByteArrayDataSource(boleto.getPdfAsByteArray(), "application/pdf"),
				      "boleto de: " + cliente.getRecebidoSouSuperSeguroCobranca().getNmCobr() + ".pdf", 
				      "",
				       EmailAttachment.ATTACHMENT);
			email.send();
			
			
			cliente.setEmailEnviado(true);
			
			
		} catch (EmailException e) {
			e.printStackTrace();
		}		
	}
}
