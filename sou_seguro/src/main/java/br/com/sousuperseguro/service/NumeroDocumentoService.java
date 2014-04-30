package br.com.sousuperseguro.service;

import br.com.sousuperseguro.entities.NumeroDocumento;

public interface NumeroDocumentoService {
	
	NumeroDocumento obterUltimoNumeroDocumento();

	void insertNumeroDocumento(NumeroDocumento numeroDocumento);

}
