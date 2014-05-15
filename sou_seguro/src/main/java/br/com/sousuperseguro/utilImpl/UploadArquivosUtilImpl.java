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
import br.com.sousuperseguro.enums.Categoria;
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
						
						if(retorno.getcCategoria().name().equals("DEPENDENTES")) {
							
							Proposta proposta = propostaRepository.verificarPropostaPeloNome(retorno.getRecebidoSouSuperSeguroCobranca().getNmCobr());
							retorno.setNroProposta(proposta.getPropostaPronta());
							numeroDaProposta = proposta.getPropostaPronta();
							
							
							// verificar esse trecho de código
							novoIdProposta = new BigInteger("1");
							
						} else {
							
							Proposta proposta = propostaRepository.selecionarUltimo();
							
							if(proposta == null) {
								
								novoIdProposta = new BigInteger("1");
								
							} else {
								novoIdProposta = proposta.getId().add(new BigInteger("1"));	
							}
							
							numeroDaProposta = propostaService.calcularProposta(novoIdProposta);
							retorno.setNroProposta(numeroDaProposta);
						}
						
						Proposta propostaNova = new Proposta();
						propostaNova.setPropostaPronta(numeroDaProposta);
						
						
						
						try {

							RecebidoSouSuperSeguro ultimoRecebido = uploadDeArquivosRepository.insertDadosComSelect(retorno);
							propostaNova.setIdRecebidoSouSuperSeguro(ultimoRecebido);
							
							propostaRepository.insert(propostaNova);
							
							if(retorno.getRecebidoSouSuperSeguroPagamentoMensalidade().getTpCobr().getTipoCobranca() == 1) {
								BoletoViewer boleto = boletoBancario.gerarBoleto(retorno, novoIdProposta);
								envioDeEmail.enviarEmailComBoleto(retorno, boleto);
							}
							
						} catch(Exception e) {
							e.printStackTrace();
							
							RecebidoSouSuperSeguroRecusada retornoRecusado = stringParaArray.paraRecusados(retorno);
												
							uploadDeArquivosRepository.insertDados(retornoRecusado);
						}		
					}
				}
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}

	@Override
	public void fazerUpload(RecebidoSouSuperSeguroRecusada retornoNovaEntidade) {
		
		
		
		RecebidoSouSuperSeguro retorno = stringParaArray.paraCorretos(retornoNovaEntidade);
		
		
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
		
		
		if(retorno != null) {
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
		}
		
	}

}
