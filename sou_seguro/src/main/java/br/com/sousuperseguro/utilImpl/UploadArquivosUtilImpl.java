package br.com.sousuperseguro.utilImpl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jrimum.bopepo.view.BoletoViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sousuperseguro.entities.Proposta;
import br.com.sousuperseguro.entities.RecebidoSouSuperSeguro;
import br.com.sousuperseguro.entities.recusadas.RecebidoSouSuperSeguroRecusada;
import br.com.sousuperseguro.repository.PropostaRepository;
import br.com.sousuperseguro.repository.UploadDeArquivosRepository;
import br.com.sousuperseguro.service.PropostaService;
import br.com.sousuperseguro.util.BoletoBancario;
import br.com.sousuperseguro.util.EnvioDeEmail;
import br.com.sousuperseguro.util.Serializacao;
import br.com.sousuperseguro.util.StringParaArray;
import br.com.sousuperseguro.util.UploadArquivosUtil;

@Component
public class UploadArquivosUtilImpl implements UploadArquivosUtil {
	
	@Autowired
	StringParaArray stringParaArray;
	
	@Autowired
	Serializacao serializacao;
	
	@Autowired
	UploadDeArquivosRepository uploadDeArquivosRepository;
	
	@Autowired
	PropostaRepository propostaRepository;
	
	@Autowired
	PropostaService propostaService;
	
	@Autowired
	BoletoBancario boletoBancario;
	
	@Autowired
	EnvioDeEmail envioDeEmail;
	
	@Override
	public void fazerUpload(List<FileItem> itens) {
		
		for(FileItem item : itens) {
			
			try {
				InputStream inputStream = item.getInputStream();
				HSSFWorkbook workBook = new HSSFWorkbook(inputStream);
				HSSFSheet workSheet = workBook.getSheet("Relatorio");
							
				for (int i = 0; i < workSheet.getLastRowNum() + 1; i++) {
					HSSFRow row = workSheet.getRow(i);
									
					RecebidoSouSuperSeguro retorno = stringParaArray.souSuperSeguro(row);
					
					if(retorno != null) {
						
						String numeroDaProposta = "";
						BigInteger novoIdProposta;
						
						if(retorno.getcCategoria().name().equals("TITULAR")) {
							
							Proposta proposta = propostaRepository.selecionarUltimo();
							
							if(proposta == null) {
								
								novoIdProposta = new BigInteger("1");
								
							} else {
								novoIdProposta = proposta.getId().add(new BigInteger("1"));	
							}
							
							numeroDaProposta = propostaService.calcularProposta(novoIdProposta);
							retorno.setNroProposta(numeroDaProposta);
							
							Proposta propostaNova = new Proposta();
							propostaNova.setPropostaPronta(numeroDaProposta);

							try {

								RecebidoSouSuperSeguro ultimoRecebido = uploadDeArquivosRepository.insertDadosComSelect(retorno);
								propostaNova.setIdRecebidoSouSuperSeguro(ultimoRecebido);
								
								propostaRepository.insert(propostaNova);
								
							} catch(Exception e) {
								e.printStackTrace();
								
								RecebidoSouSuperSeguroRecusada retornoRecusado = stringParaArray.paraRecusados(retorno);
													
								uploadDeArquivosRepository.insertDados(retornoRecusado);
							}
							
						} else {
							
							try{
							
								uploadDeArquivosRepository.insertDados(retorno);
							
							} catch(Exception e) {
								e.printStackTrace();
								
								RecebidoSouSuperSeguroRecusada retornoRecusado = stringParaArray.paraRecusados(retorno);
													
								uploadDeArquivosRepository.insertDados(retornoRecusado);
							}
							
						}			
					}
				}
				
				
				this.mesclaDeDados();
				
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}

	
	private void mesclaDeDados() {
		List<RecebidoSouSuperSeguro> listaRecebidosSemProposta = uploadDeArquivosRepository.obterDadosSemProposta();
		
		for(RecebidoSouSuperSeguro dadoSemProposta: listaRecebidosSemProposta) {
			
			RecebidoSouSuperSeguro dadosTitularDoSeguro = uploadDeArquivosRepository.obterRecebidoPorCpf(dadoSemProposta.getRecebidoSouSuperSeguroCobranca().getCpfCobr());
			
			/* Aqui mesclo os dados do titular com dependente. 
			 * Coloco o número de proposta no dependente e mudo o valor do boleto do titular
			 */
			
			dadoSemProposta.setNroProposta(dadosTitularDoSeguro.getNroProposta());
			uploadDeArquivosRepository.insertDados(dadoSemProposta);
			
//			if(dadosTitularDoSeguro.isEnvioEmail()) {
//				
//				this.enviarEmail(dadoSemProposta);
//					
//			} else {
//				BigDecimal valorSemProposta = dadoSemProposta.getRecebidoSouSuperSeguroPagamentoMensalidade().getValor();
//				dadosTitularDoSeguro.getRecebidoSouSuperSeguroPagamentoMensalidade().setValor(
//						valorSemProposta.add(
//								dadosTitularDoSeguro.getRecebidoSouSuperSeguroPagamentoMensalidade().getValor()));
//				
//				uploadDeArquivosRepository.insertDados(dadosTitularDoSeguro);	
//			}
	
		}
	
		this.verificarListaDeNaoEnviados();
	}
	
	
	private void verificarListaDeNaoEnviados() {
		List<RecebidoSouSuperSeguro> listaNaoEnviadaEmail = uploadDeArquivosRepository.obterDadosNaoEnviadoCobrancaTitular();
		
		for(RecebidoSouSuperSeguro dadosRecebidoEmailNaoEnviado : listaNaoEnviadaEmail) {
			this.enviarEmail(dadosRecebidoEmailNaoEnviado);
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


	@Override
	public void fazerUpload(RecebidoSouSuperSeguroRecusada retornoNovaEntidade) {
		
		RecebidoSouSuperSeguro retorno = stringParaArray.paraCorretos(retornoNovaEntidade);
		
		if(retornoNovaEntidade.isRecebidoBradesco()) {
			if(retorno != null) {
				uploadDeArquivosRepository.insertDados(retorno);
				uploadDeArquivosRepository.delete(retornoNovaEntidade);
			}
			
		} else {
			if(retorno != null) {
				
				if(retorno.getcCategoria().name().equals("TITULAR")) {
					
					
					Proposta proposta = propostaRepository.selecionarUltimo();
					BigInteger novoIdProposta;
					
					if(proposta == null) {
						
						novoIdProposta = new BigInteger("1");
						
					} else {
						
						novoIdProposta = proposta.getId().add(new BigInteger("1"));
						
					}
					
					String numeroDaProposta = propostaService.calcularProposta(novoIdProposta);
					retorno.setNroProposta(numeroDaProposta);
					
					Proposta propostaNova = new Proposta();
					propostaNova.setPropostaPronta(numeroDaProposta);
					
					
					
					try {
						RecebidoSouSuperSeguro ultimoRecebido = uploadDeArquivosRepository.insertDadosComSelect(retorno);
						propostaNova.setIdRecebidoSouSuperSeguro(ultimoRecebido);
						
						propostaRepository.insert(propostaNova);
						
						BoletoViewer boleto = boletoBancario.gerarBoleto(retorno, novoIdProposta);
						envioDeEmail.enviarEmailComBoleto(ultimoRecebido, boleto);
						
						uploadDeArquivosRepository.delete(retornoNovaEntidade);
						
					} catch(Exception e) {
						
						RecebidoSouSuperSeguroRecusada retornoRecusado = stringParaArray.paraRecusados(retorno);
											
						uploadDeArquivosRepository.insertDados(retornoRecusado);
						uploadDeArquivosRepository.delete(retornoNovaEntidade);
					}
					
				} else {
					
					try{
						
						uploadDeArquivosRepository.insertDados(retorno);
					
					} catch(Exception e) {
						e.printStackTrace();
						
						RecebidoSouSuperSeguroRecusada retornoRecusado = stringParaArray.paraRecusados(retorno);
											
						uploadDeArquivosRepository.insertDados(retornoRecusado);
					}
					
				}
			}
			
			this.mesclaDeDados();
			
		}
	}

}
