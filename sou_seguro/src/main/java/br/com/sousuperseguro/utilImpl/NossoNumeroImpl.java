package br.com.sousuperseguro.utilImpl;

import java.math.BigInteger;

import org.springframework.stereotype.Component;

import br.com.sousuperseguro.util.NossoNumero;

@Component
public class NossoNumeroImpl implements NossoNumero {
	
	@Override
	public String[] gerarNossoNumero(BigInteger idProposta, String carteira) {
		
		int[] multiplicadores = {2, 7, 6, 5, 4, 3, 2, 7, 6, 5, 4, 3, 2};
		
		String[] retorno = new String[3];
		
		String idNossoNumero = idProposta.toString();
		
		for(int i = idNossoNumero.length(); i < 11; i++) {
			 idNossoNumero = "0"+ idNossoNumero ;
		}
		
		
		retorno[0] = carteira;
		retorno[1] = idNossoNumero;
		
		
		idNossoNumero = carteira + idNossoNumero;
		
		int somaNossoNumero = 0;
		
		for(int j = 0; j < idNossoNumero.length(); j++ ) {
			
			String substring = idNossoNumero.substring(j, j + 1);
			int multiplicacao = multiplicadores[j] * Integer.parseInt(substring);
			somaNossoNumero = somaNossoNumero + multiplicacao;
		}
		
		
		int resto = somaNossoNumero % 11;
		
		if(resto == 1) {
			retorno[2] = "P";
			return retorno; 
		} else if(resto == 0) {
			retorno[2] = "0";
			return retorno;
		} else {
			int digitoDeAutoConfierencia = 11 - resto;
			retorno[2] = String.valueOf(digitoDeAutoConfierencia);
			return retorno;
		}
	}
}
