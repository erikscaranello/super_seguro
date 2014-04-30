package br.com.sousuperseguro.util;

import java.lang.reflect.Type;

import com.google.gson.Gson;

import br.com.sousuperseguro.entities.RecebidoSouSuperSeguro;
import br.com.sousuperseguro.entities.recusadas.RecebidoSouSuperSeguroRecusada;

public interface Serializacao {
	
	Gson beanParaGson(RecebidoSouSuperSeguro recebidoSouSuperSeguro);

	RecebidoSouSuperSeguroRecusada gsonParaBean(Gson gsonRetorno, Type tipoObjeto);
}
