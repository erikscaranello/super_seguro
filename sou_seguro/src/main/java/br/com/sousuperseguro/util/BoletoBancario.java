package br.com.sousuperseguro.util;

import java.math.BigInteger;

import org.jrimum.bopepo.view.BoletoViewer;

import br.com.sousuperseguro.entities.RecebidoSouSuperSeguro;

public interface BoletoBancario {

	BoletoViewer gerarBoleto(RecebidoSouSuperSeguro dadosDoCliente, BigInteger idProposta);	
	
}
