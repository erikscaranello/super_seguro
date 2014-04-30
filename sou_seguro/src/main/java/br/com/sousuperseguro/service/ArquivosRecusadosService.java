package br.com.sousuperseguro.service;

import java.math.BigInteger;
import java.util.List;

import br.com.sousuperseguro.entities.recusadas.RecebidoSouSuperSeguroRecusada;

public interface ArquivosRecusadosService {
	
	List<RecebidoSouSuperSeguroRecusada> obterArquivosRecusadosLimitCinco();

	RecebidoSouSuperSeguroRecusada obterArquivoRecusado(BigInteger numeroDados);

	List<RecebidoSouSuperSeguroRecusada> obterArquivosRecusadosBradescoLimitCinco();
	
}
