package br.com.sousuperseguro.repository;

import br.com.sousuperseguro.entities.NumeroDocumento;

public interface NumeroDocumentoRepository {
	
	NumeroDocumento obterUltimoNumeroDocumento();

	void insertNumeroDocumento(NumeroDocumento numeroDocumento);
}
