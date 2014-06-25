package br.com.sousuperseguro.repository;

import br.com.sousuperseguro.entities.NumeroDocumento;
import br.com.sousuperseguro.entities.RecebidoSouSuperSeguro;

public interface NumeroDocumentoRepository {
	
	NumeroDocumento obterUltimoNumeroDocumento();

	void insertNumeroDocumento(NumeroDocumento numeroDocumento);

	NumeroDocumento verificarEnviadoEmail(RecebidoSouSuperSeguro dadosRecebidoEmailNaoEnviado);
}
