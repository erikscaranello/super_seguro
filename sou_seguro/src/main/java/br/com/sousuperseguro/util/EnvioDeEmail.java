package br.com.sousuperseguro.util;

import org.jrimum.bopepo.view.BoletoViewer;

import br.com.sousuperseguro.entities.RecebidoSouSuperSeguro;
import br.com.sousuperseguro.entities.Users;

public interface EnvioDeEmail {
	
	void enviarEmail (Users user);
	
	void enviarEmailComBoleto(RecebidoSouSuperSeguro cliente, BoletoViewer boleto);
}
