package br.com.sousuperseguro.repository;

import br.com.sousuperseguro.entities.Proposta;

public interface PropostaRepository {
	
	Proposta selecionarUltimo();

	void insert(Proposta propostaNova);

}
