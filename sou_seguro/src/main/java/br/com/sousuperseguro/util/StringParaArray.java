package br.com.sousuperseguro.util;

import org.apache.poi.hssf.usermodel.HSSFRow;

import br.com.sousuperseguro.entities.RecebidoSouSuperSeguro;
import br.com.sousuperseguro.entities.recusadas.RecebidoSouSuperSeguroRecusada;

public interface StringParaArray {
	RecebidoSouSuperSeguro souSuperSeguro(HSSFRow linhaRecebida);

	RecebidoSouSuperSeguroRecusada paraRecusados(RecebidoSouSuperSeguro retorno);

	RecebidoSouSuperSeguro paraCorretos(RecebidoSouSuperSeguroRecusada retornoNovaEntidade);
}
