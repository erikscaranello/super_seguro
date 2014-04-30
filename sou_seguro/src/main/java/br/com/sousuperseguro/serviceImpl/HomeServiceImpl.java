package br.com.sousuperseguro.serviceImpl;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sousuperseguro.entities.recusadas.RecebidoSouSuperSeguroCobrancaRecusada;
import br.com.sousuperseguro.entities.recusadas.RecebidoSouSuperSeguroPagamentoMensalidadeRecusada;
import br.com.sousuperseguro.entities.recusadas.RecebidoSouSuperSeguroRecusada;
import br.com.sousuperseguro.repository.HomeRepository;
import br.com.sousuperseguro.repository.UploadDeArquivosRepository;
import br.com.sousuperseguro.service.HomeService;
import br.com.sousuperseguro.service.VerificarEnums;

@Service
public class HomeServiceImpl implements HomeService{
	
	@Autowired
	private HomeRepository homeRepository;

	@Autowired
	private VerificarEnums verificarEnums;
	
	@Autowired
	private UploadDeArquivosRepository uploadDeArquivosRepository;
	
	
	@Override
	public void alteracaoSouSuperSeguro(RecebidoSouSuperSeguroRecusada retornoEntidade) {
			
		uploadDeArquivosRepository.insertDados(retornoEntidade);
	}


	@Override
	public RecebidoSouSuperSeguroRecusada selecionarRecebidoRecusadoPorId(BigInteger id) {
		return homeRepository.selecionarRecebidoRecusadoPorId(id);
	}


	@Override
	public void alteracaoSouSuperSeguro(
			RecebidoSouSuperSeguroCobrancaRecusada retornoEntidade) {
		
		uploadDeArquivosRepository.insertDadosCobranca(retornoEntidade);
	}


	@Override
	public void alteracaoSouSuperSeguro(RecebidoSouSuperSeguroPagamentoMensalidadeRecusada recebidoSouSuperSeguro) {
		uploadDeArquivosRepository.insertDadosCobranca(recebidoSouSuperSeguro);
	}
	
}
