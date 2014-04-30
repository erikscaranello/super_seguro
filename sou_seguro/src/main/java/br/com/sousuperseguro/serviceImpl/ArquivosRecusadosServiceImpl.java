package br.com.sousuperseguro.serviceImpl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sousuperseguro.entities.recusadas.RecebidoSouSuperSeguroRecusada;
import br.com.sousuperseguro.repository.ArquivosRecusadosRepository;
import br.com.sousuperseguro.service.ArquivosRecusadosService;

@Service
public class ArquivosRecusadosServiceImpl implements ArquivosRecusadosService{

	@Autowired
	ArquivosRecusadosRepository arquivosRecusadosRepository;
	
	@Override
	public List<RecebidoSouSuperSeguroRecusada> obterArquivosRecusadosLimitCinco() {
		
		return arquivosRecusadosRepository.obterArquivosRecusadosLimitCinco();
	}

	@Override
	public RecebidoSouSuperSeguroRecusada obterArquivoRecusado(BigInteger numeroDados) {
		return arquivosRecusadosRepository.obterArquivoRecusado(numeroDados);
	}

	@Override
	public List<RecebidoSouSuperSeguroRecusada> obterArquivosRecusadosBradescoLimitCinco() {
		return arquivosRecusadosRepository.obterArquivosRecusadosBradescoLimitCinco();
	}

}
