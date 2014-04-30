package br.com.sousuperseguro.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sousuperseguro.entities.ArquivosEnvio;
import br.com.sousuperseguro.entities.RecebidoSouSuperSeguro;
import br.com.sousuperseguro.repository.ArquivosEnvioRepository;
import br.com.sousuperseguro.service.ArquivosEnvioService;

@Service
public class ArquivosEnvioServiceImpl implements ArquivosEnvioService{

	@Autowired
	ArquivosEnvioRepository arquivosEnvioRepository;
	
	@Override
	public ArquivosEnvio obterUltimoArquivoDeEnvio() {

		return arquivosEnvioRepository.obterUltimoArquivoDeEnvio();
		
	}

	@Override
	public List<RecebidoSouSuperSeguro> selecionarRecebidosSuperSeguro() {
		
		return arquivosEnvioRepository.selecionarRecebidosSuperSeguro();
	
	}

	@Override
	public void insertNovoArquivo(ArquivosEnvio arquivoEnvioInsert) {
		arquivosEnvioRepository.insertNovoArquivo(arquivoEnvioInsert);
	}

	@Override
	public void insertRecebidoEnviado(RecebidoSouSuperSeguro recebidoEnviado) {
		arquivosEnvioRepository.insertRecebidoEnviado(recebidoEnviado);
	}

	@Override
	public List<ArquivosEnvio> obterListaNaoRecebidosErro() {
		return arquivosEnvioRepository.obterListaNaoRecebidosErro();
	}
}
