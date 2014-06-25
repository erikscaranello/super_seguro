package br.com.sousuperseguro.repository;

import br.com.sousuperseguro.entities.ErrosRetorno;

public interface ErrosRetornoRepository {

	ErrosRetorno obterErro(String codigoErro);

}
