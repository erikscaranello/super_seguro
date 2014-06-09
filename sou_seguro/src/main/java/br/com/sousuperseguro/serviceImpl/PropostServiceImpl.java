package br.com.sousuperseguro.serviceImpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.sousuperseguro.service.PropostaService;

@Service
public class PropostServiceImpl implements PropostaService{

//	@Override
	public String calcularProposta(BigInteger novoIdProposta) {
		
		//Valor setado harded coded de acordo com a conta dada pelo Bradesco.
		String  sigla = "SSC";
		int contaSsc = 66;

		String idPropostaString = novoIdProposta.toString();
		
		int tamanhodaString = idPropostaString.length();
		int tamanhoDeZeros = 11 - tamanhodaString;
		
		String novaString = "";
		for(int i = 0; i < tamanhoDeZeros; i++) {
			novaString = 0 + novaString;
		}
		
		novaString = novaString + idPropostaString;
		
		int stringNumeroDoisEum = 1;
		
		ArrayList<Integer> arrayParaSoma = new ArrayList<Integer>();
		
		char[] stringChar = novaString.toCharArray();
		
		
		for( int i = (stringChar.length - 1) ; i >= 0 ; i-- ) {
			if(stringNumeroDoisEum == 2) {
				stringNumeroDoisEum = 1;
			} else if(stringNumeroDoisEum == 1) {
				stringNumeroDoisEum = 2;
			}

			int numeroDaProposta = Integer.parseInt(String.valueOf(stringChar[i]));
			
			
			arrayParaSoma.add(numeroDaProposta * stringNumeroDoisEum); 
		}
		
		
		List<Integer> arrayCadadigito = new ArrayList<Integer>();
		for(Integer numeroCompleto : arrayParaSoma) {
			String numeroCompletoString = numeroCompleto.toString();
			
			for(int i = 0; i < numeroCompletoString.length(); i++ ) {
				char stringCharDigito = numeroCompletoString.charAt(i);
				String stringDigito = String.valueOf(stringCharDigito);
				Integer digitoInteger = new Integer(stringDigito);
				arrayCadadigito.add(digitoInteger);
			}
		}
		
		int numeroFinalNumeros = 0;
		for(int soma : arrayCadadigito) {
			numeroFinalNumeros = numeroFinalNumeros + soma;
		}
		
		int numeroFinal = (contaSsc + numeroFinalNumeros);
		
		int resto = numeroFinal % 10;
		
		
		String digitoVerificador = "";
		if(resto == 0) {
			digitoVerificador = String.valueOf(0);
		} else {
			digitoVerificador = String.valueOf(10 - resto);
		}
		
		
		
		// digito verificador antigo com hifen
		return sigla + novaString + "" + digitoVerificador;
	}

}
