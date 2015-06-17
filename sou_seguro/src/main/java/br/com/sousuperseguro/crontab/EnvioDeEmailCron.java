package br.com.sousuperseguro.crontab;

import java.util.List;

import org.jrimum.bopepo.view.BoletoViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.sousuperseguro.entities.Proposta;
import br.com.sousuperseguro.entities.RecebidoSouSuperSeguro;
import br.com.sousuperseguro.entities.recusadas.RecebidoSouSuperSeguroRecusada;
import br.com.sousuperseguro.repository.PropostaRepository;
import br.com.sousuperseguro.repository.UploadDeArquivosRepository;
import br.com.sousuperseguro.service.NumeroDocumentoService;
import br.com.sousuperseguro.util.BoletoBancario;
import br.com.sousuperseguro.util.EnvioDeEmail;
import br.com.sousuperseguro.util.StringParaArray;

@Component
public class EnvioDeEmailCron {
	
	@Autowired
	NumeroDocumentoService numeroDocumentoService;
	
	@Autowired
	UploadDeArquivosRepository uploadDeArquivosRepository;
	
	@Autowired
	PropostaRepository propostaRepository;
	
	@Autowired
	BoletoBancario boletoBancario;
	
	@Autowired
	EnvioDeEmail envioDeEmail;
	
	@Autowired
	StringParaArray stringParaArray;
	
	
	@Scheduled(cron = "0 0/60 * * * *")
	public void executar() {
		
		this.mesclaDeDados();
		
		
		List<RecebidoSouSuperSeguro> listaNaoEnviadaEmail = uploadDeArquivosRepository.obterDadosNaoEnviadoCobrancaTitular();
		
		for(RecebidoSouSuperSeguro dadosRecebidoEmailNaoEnviado : listaNaoEnviadaEmail) {
			
//			int resultado = dadosRecebidoEmailNaoEnviado.getId().compareTo(new BigInteger("376"));
			
//			if(numeroDocumentoService.verificarEnviadoEmail(dadosRecebidoEmailNaoEnviado) == null) {
				this.enviarEmail(dadosRecebidoEmailNaoEnviado);
//			}
		}
	}
	
	private void enviarEmail(RecebidoSouSuperSeguro dados) {
		
		Proposta proposta = propostaRepository.obterPropostaPorRecebidoSuperSeguro(dados);
		
//		if (proposta == null) {
//			proposta = propostaRepository.obterPropostaPorNumeroDeProposta(dados.getNroProposta());
//		}
		
		BoletoViewer boleto = boletoBancario.gerarBoleto(dados, proposta.getId());
		
		try{
			envioDeEmail.enviarEmailComBoleto(dados, boleto);
//			dados.setEnvioEmail(true);
			uploadDeArquivosRepository.insertDados(dados);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	
	public void mesclaDeDados() {
		List<RecebidoSouSuperSeguro> listaRecebidosSemProposta = uploadDeArquivosRepository.obterDadosSemProposta();
		
		for(RecebidoSouSuperSeguro dadoSemProposta: listaRecebidosSemProposta) {
			
			RecebidoSouSuperSeguro dadosTitularDoSeguro = uploadDeArquivosRepository.obterRecebidoPorCpf(dadoSemProposta.getRecebidoSouSuperSeguroCobranca().getCpfCobr());
			
			/* Aqui mesclo os dados do titular com dependente. 
			 * Coloco o n�mero de proposta no dependente e mudo o valor do boleto do titular
			 */
			
			if(dadosTitularDoSeguro != null) {
			
				dadoSemProposta.setNroProposta(dadosTitularDoSeguro.getNroProposta());
				uploadDeArquivosRepository.insertDados(dadoSemProposta);
			
				
			} else {
				
				RecebidoSouSuperSeguroRecusada retornoRecusado = stringParaArray.paraRecusados(dadoSemProposta);
				
				uploadDeArquivosRepository.insertDados(retornoRecusado);
				uploadDeArquivosRepository.delete(dadoSemProposta);
			}
				
		}
	
//		this.verificarListaDeNaoEnviados();
	/*
	 * metodo movido para cron
	 */
	}
	
	
}	
