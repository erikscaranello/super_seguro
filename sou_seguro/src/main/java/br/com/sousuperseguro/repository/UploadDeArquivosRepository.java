package br.com.sousuperseguro.repository;

import java.util.List;

import br.com.sousuperseguro.entities.RecebidoSouSuperSeguro;
import br.com.sousuperseguro.entities.recusadas.RecebidoSouSuperSeguroCobrancaRecusada;
import br.com.sousuperseguro.entities.recusadas.RecebidoSouSuperSeguroPagamentoMensalidadeRecusada;
import br.com.sousuperseguro.entities.recusadas.RecebidoSouSuperSeguroRecusada;


public interface UploadDeArquivosRepository {
	
	void insertDados(RecebidoSouSuperSeguro infosContratante);
	
	void insertDados(RecebidoSouSuperSeguroRecusada infosContratante);

	void insertDadosCobranca(RecebidoSouSuperSeguroCobrancaRecusada retornoEntidade);

	void insertDadosCobranca(RecebidoSouSuperSeguroPagamentoMensalidadeRecusada recebidoSouSuperSeguro);

	void delete(RecebidoSouSuperSeguroRecusada retornoNovaEntidade);

	RecebidoSouSuperSeguro insertDadosComSelect(RecebidoSouSuperSeguro retorno);
	
	RecebidoSouSuperSeguro obterRecebidoPorCpf(String cpf);

	void delete(RecebidoSouSuperSeguro recebido);

	List<RecebidoSouSuperSeguro> obterDadosSemProposta();
	
	List<RecebidoSouSuperSeguro> obterDadosNaoEnviadoCobrancaTitular();
}
