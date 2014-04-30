package br.com.sousuperseguro.utilImpl;

import java.lang.reflect.Type;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import br.com.sousuperseguro.entities.RecebidoSouSuperSeguro;
import br.com.sousuperseguro.entities.recusadas.RecebidoSouSuperSeguroRecusada;
import br.com.sousuperseguro.util.Serializacao;

@Component
public class SerializacaoImpl implements Serializacao {

	@Override
	public Gson beanParaGson(RecebidoSouSuperSeguro recebidoSouSuperSeguro) {

		ArrayList<RecebidoSouSuperSeguro> listaRetorno = new ArrayList<RecebidoSouSuperSeguro>();
		listaRetorno.add(recebidoSouSuperSeguro);
		Gson gson = new Gson();
		gson.toJson(listaRetorno);		
		
		return gson;
	}

	@Override
	public RecebidoSouSuperSeguroRecusada gsonParaBean(Gson gsonRetorno, Type tipoObjeto) {
		Gson gson = new Gson();		
		
		return gson.fromJson(gsonRetorno.toString(), tipoObjeto);
	}

}
