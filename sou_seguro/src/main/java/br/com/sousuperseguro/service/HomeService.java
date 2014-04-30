package br.com.sousuperseguro.service;

import java.math.BigInteger;

import br.com.sousuperseguro.entities.recusadas.RecebidoSouSuperSeguroCobrancaRecusada;
import br.com.sousuperseguro.entities.recusadas.RecebidoSouSuperSeguroPagamentoMensalidadeRecusada;
import br.com.sousuperseguro.entities.recusadas.RecebidoSouSuperSeguroRecusada;

public interface HomeService {


	void alteracaoSouSuperSeguro(RecebidoSouSuperSeguroRecusada retornoEntidade);
	
	void alteracaoSouSuperSeguro(RecebidoSouSuperSeguroCobrancaRecusada retornoEntidade);
	
	RecebidoSouSuperSeguroRecusada selecionarRecebidoRecusadoPorId(BigInteger id);

	void alteracaoSouSuperSeguro(RecebidoSouSuperSeguroPagamentoMensalidadeRecusada recebidoSouSuperSeguro);
	
}
