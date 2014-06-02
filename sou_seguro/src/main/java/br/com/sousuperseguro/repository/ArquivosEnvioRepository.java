package br.com.sousuperseguro.repository;

import java.util.List;

import br.com.sousuperseguro.entities.ArquivosEnvio;
import br.com.sousuperseguro.entities.RecebidoSouSuperSeguro;

public interface ArquivosEnvioRepository {
	
	ArquivosEnvio obterUltimoArquivoDeEnvio();

	List<RecebidoSouSuperSeguro> selecionarRecebidosSuperSeguro();
	
	void insertNovoArquivo(ArquivosEnvio arquivoEnvioInsert);
	
	void insertRecebidoEnviado(RecebidoSouSuperSeguro recebidoEnviado);

	List<ArquivosEnvio> obterListaNaoRecebidosErro();

	void updateArquivosParaLido(ArquivosEnvio arquivoEnvioInsert);

}
