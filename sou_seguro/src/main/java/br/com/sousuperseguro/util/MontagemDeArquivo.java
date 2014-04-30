package br.com.sousuperseguro.util;

import java.util.List;

import br.com.sousuperseguro.entities.RecebidoSouSuperSeguro;


public interface MontagemDeArquivo {
	
	String montarArquivoDeEnvio(List<RecebidoSouSuperSeguro> listaRecebidos);
	
}
