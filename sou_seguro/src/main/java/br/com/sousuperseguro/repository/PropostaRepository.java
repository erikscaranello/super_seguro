package br.com.sousuperseguro.repository;

import br.com.sousuperseguro.entities.Proposta;
import br.com.sousuperseguro.entities.RecebidoSouSuperSeguro;

public interface PropostaRepository {
	
	Proposta selecionarUltimo();

	void insert(Proposta propostaNova);

	Proposta verificarPropostaPeloNome(String nomeCobranca);

	Proposta obterPropostaPorRecebidoSuperSeguro(
			RecebidoSouSuperSeguro dadosRecebidoEmailNaoEnviado);

}
