package br.com.sousuperseguro.service;

import br.com.sousuperseguro.enums.Banco;
import br.com.sousuperseguro.enums.CancelamentoDoAssociado;
import br.com.sousuperseguro.enums.Categoria;
import br.com.sousuperseguro.enums.Parentesco;
import br.com.sousuperseguro.enums.Sexo;
import br.com.sousuperseguro.enums.TipoCobranca;

public interface VerificarEnums {
	Categoria verificarCategoria(String stringRecebida);

	Parentesco verificarParentesco(String stringRecebida);
	
	CancelamentoDoAssociado verificarCancelamentoDoAssociado(String stringRecebida);
	
	Banco verificarBanco(String stringRecebida);
	
	TipoCobranca verificarTipoCobranca(String stringRecebida);
	
	Sexo verificarSexo(String stringRecebida);

}