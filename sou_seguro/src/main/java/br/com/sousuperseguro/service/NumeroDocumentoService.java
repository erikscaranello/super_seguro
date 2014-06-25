package br.com.sousuperseguro.service;

import br.com.sousuperseguro.entities.NumeroDocumento;
import br.com.sousuperseguro.entities.RecebidoSouSuperSeguro;

public interface NumeroDocumentoService {
	
	NumeroDocumento obterUltimoNumeroDocumento();

	void insertNumeroDocumento(NumeroDocumento numeroDocumento);

	NumeroDocumento verificarEnviadoEmail(RecebidoSouSuperSeguro dadosRecebidoEmailNaoEnviado);

}
