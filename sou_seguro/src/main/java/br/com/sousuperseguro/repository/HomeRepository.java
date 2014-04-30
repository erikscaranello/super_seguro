package br.com.sousuperseguro.repository;

import java.math.BigInteger;

import br.com.sousuperseguro.entities.recusadas.RecebidoSouSuperSeguroRecusada;

public interface HomeRepository {

	RecebidoSouSuperSeguroRecusada selecionarRecebidoRecusadoPorId(BigInteger id);

}
