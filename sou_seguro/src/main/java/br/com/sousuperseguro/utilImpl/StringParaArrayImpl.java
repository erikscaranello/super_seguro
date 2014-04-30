package br.com.sousuperseguro.utilImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sousuperseguro.entities.RecebidoSouSuperSeguro;
import br.com.sousuperseguro.entities.RecebidoSouSuperSeguroCobranca;
import br.com.sousuperseguro.entities.RecebidoSouSuperSeguroPagamentoMensalidade;
import br.com.sousuperseguro.entities.recusadas.RecebidoSouSuperSeguroCobrancaRecusada;
import br.com.sousuperseguro.entities.recusadas.RecebidoSouSuperSeguroPagamentoMensalidadeRecusada;
import br.com.sousuperseguro.entities.recusadas.RecebidoSouSuperSeguroRecusada;
import br.com.sousuperseguro.enums.Status;
import br.com.sousuperseguro.service.VerificarEnums;
import br.com.sousuperseguro.util.Datas;
import br.com.sousuperseguro.util.StringParaArray;

@Component
public class StringParaArrayImpl implements StringParaArray{

	@Autowired
	VerificarEnums verificarEnums;
	
	@Autowired
	Datas datas;
	
	@Override
	public RecebidoSouSuperSeguro souSuperSeguro(HSSFRow linhaRecebida) {
		
		if (linhaRecebida != null) {
			HSSFCell cell = linhaRecebida.getCell(0);
			String cellValue = cell.getStringCellValue();
			
			if(cellValue != null && !cellValue.contains("CSTATUS")) {
				
				for(int i = 0; i < linhaRecebida.getLastCellNum(); i++) {
					if (linhaRecebida.getCell(i).getCellType() == 0) {
						linhaRecebida.getCell(i).setCellType(1);
					}
				}
				
				
				
				
				RecebidoSouSuperSeguro recebidoSouSuperSeguro = new RecebidoSouSuperSeguro();
				
				recebidoSouSuperSeguro.setContrato("057946");
				
				recebidoSouSuperSeguro.setcStatus(linhaRecebida.getCell(0).getStringCellValue().isEmpty() ? null : Status.valueOf(linhaRecebida.getCell(0).getStringCellValue()));
				recebidoSouSuperSeguro.setcCategoria(linhaRecebida.getCell(1).getStringCellValue().isEmpty() ? null : verificarEnums.verificarCategoria(linhaRecebida.getCell(1).getStringCellValue()));
				recebidoSouSuperSeguro.setcParentesco(linhaRecebida.getCell(2).getStringCellValue().isEmpty() ? null : verificarEnums.verificarParentesco(linhaRecebida.getCell(2).getStringCellValue()));
				recebidoSouSuperSeguro.setNome(linhaRecebida.getCell(3).getStringCellValue().isEmpty() ? null : linhaRecebida.getCell(3).getStringCellValue());
				
				
				if(!linhaRecebida.getCell(4).getStringCellValue().isEmpty()) {
							
					if(!linhaRecebida.getCell(4).getStringCellValue().contains("/")) {
						Calendar cal = datas.transformarNumeroEmData(linhaRecebida.getCell(4));
						recebidoSouSuperSeguro.setDtNascimento(cal);
					} else {
						
						Calendar cal = Calendar.getInstance();
						String[] dataString = linhaRecebida.getCell(4).getStringCellValue().split("/");
						
						for(int i = 0; i < dataString.length; i++) {
							if(dataString[i].length() < 2) {
								dataString[i] = "0" + dataString[i];
							}
						}
						
						String dataStringFinal = dataString[0] + "/" + dataString[1] + "/" + dataString[2];
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						try {
							cal.setTime(sdf.parse(dataStringFinal));
							recebidoSouSuperSeguro.setDtNascimento(cal);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					
					
					
				}
				

				
				recebidoSouSuperSeguro.setcSexo(verificarEnums.verificarSexo(linhaRecebida.getCell(5).getStringCellValue().isEmpty() ? null : linhaRecebida.getCell(5).getStringCellValue()));
				recebidoSouSuperSeguro.setCpf(linhaRecebida.getCell(6).getStringCellValue().isEmpty() ? null : linhaRecebida.getCell(6).getStringCellValue().replace(".", "").replace("-", ""));
				recebidoSouSuperSeguro.setNomeMae(linhaRecebida.getCell(7).getStringCellValue().isEmpty() ? null : linhaRecebida.getCell(7).getStringCellValue());
				
				recebidoSouSuperSeguro.setrLogradores(linhaRecebida.getCell(8).getStringCellValue().isEmpty() ? null : linhaRecebida.getCell(8).getStringCellValue());
				recebidoSouSuperSeguro.setrNumeroRes(linhaRecebida.getCell(9).getStringCellValue().isEmpty() ? null : linhaRecebida.getCell(9).getStringCellValue());
				recebidoSouSuperSeguro.setBairroRes(linhaRecebida.getCell(10).getStringCellValue().isEmpty() ? null : linhaRecebida.getCell(10).getStringCellValue());
				recebidoSouSuperSeguro.setCidadeRes(linhaRecebida.getCell(11).getStringCellValue().isEmpty() ? null : linhaRecebida.getCell(11).getStringCellValue());
				recebidoSouSuperSeguro.setIdCidadeRes(linhaRecebida.getCell(12).getStringCellValue().isEmpty() ? null : new BigInteger(linhaRecebida.getCell(12).getStringCellValue()));
				recebidoSouSuperSeguro.setUfRes(linhaRecebida.getCell(13).getStringCellValue().isEmpty() ? null : linhaRecebida.getCell(13).getStringCellValue());
				recebidoSouSuperSeguro.setCepRes(linhaRecebida.getCell(14).getStringCellValue().isEmpty() ? null : linhaRecebida.getCell(14).getStringCellValue().replace("-", ""));
				recebidoSouSuperSeguro.setdFone1(linhaRecebida.getCell(15).getStringCellValue().isEmpty() ? null : linhaRecebida.getCell(15).getStringCellValue());
				recebidoSouSuperSeguro.setnFone1(linhaRecebida.getCell(16).getStringCellValue().isEmpty() ? null : linhaRecebida.getCell(16).getStringCellValue().replace("-", ""));
				recebidoSouSuperSeguro.setEmail(linhaRecebida.getCell(17).getStringCellValue().isEmpty() ? null : linhaRecebida.getCell(17).getStringCellValue());
				
				RecebidoSouSuperSeguroCobranca recebidoSouSuperSeguroCobranca = new RecebidoSouSuperSeguroCobranca();
				
				recebidoSouSuperSeguroCobranca.setNmCobr(linhaRecebida.getCell(18).getStringCellValue().isEmpty() ? null : linhaRecebida.getCell(18).getStringCellValue());
				
				
				if(!linhaRecebida.getCell(19).getStringCellValue().isEmpty()) {
					
					if(!linhaRecebida.getCell(19).getStringCellValue().contains("/")) {
						Calendar cal = datas.transformarNumeroEmData(linhaRecebida.getCell(19));
						
						recebidoSouSuperSeguroCobranca.setDtNascCobr(cal);
					} else {
						
						Calendar cal = Calendar.getInstance();
						String[] dataString = linhaRecebida.getCell(19).getStringCellValue().split("/");
						
						for(int i = 0; i < dataString.length; i++) {
							if(dataString[i].length() < 2) {
								dataString[i] = "0" + dataString[i];
							}
						}
						
						String dataStringFinal = dataString[0] + "/" + dataString[1] + "/" + dataString[2];
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						try {
							cal.setTime(sdf.parse(dataStringFinal));
							recebidoSouSuperSeguroCobranca.setDtNascCobr(cal);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					
				}
				
				recebidoSouSuperSeguroCobranca.setCpfCobr(linhaRecebida.getCell(20).getStringCellValue().isEmpty() ? null : linhaRecebida.getCell(20).getStringCellValue().replace(".", "").replace("-", ""));
				recebidoSouSuperSeguroCobranca.setrLogradCobr(linhaRecebida.getCell(21).getStringCellValue().isEmpty() ? null : linhaRecebida.getCell(21).getStringCellValue());
				recebidoSouSuperSeguroCobranca.setrNumeroCobr(linhaRecebida.getCell(22).getStringCellValue().isEmpty() ? null : linhaRecebida.getCell(22).getStringCellValue());
				recebidoSouSuperSeguroCobranca.setBairroCobr(linhaRecebida.getCell(23).getStringCellValue().isEmpty() ? null : linhaRecebida.getCell(23).getStringCellValue());
				recebidoSouSuperSeguroCobranca.setCidadeCobr(linhaRecebida.getCell(24).getStringCellValue().isEmpty() ? null : linhaRecebida.getCell(24).getStringCellValue());
				recebidoSouSuperSeguroCobranca.setIdCidadeCobr(linhaRecebida.getCell(25).getStringCellValue().isEmpty() ? null : new BigInteger(linhaRecebida.getCell(25).getStringCellValue()));
				recebidoSouSuperSeguroCobranca.setUfCobr(linhaRecebida.getCell(26).getStringCellValue().isEmpty() ? null : linhaRecebida.getCell(26).getStringCellValue());
				recebidoSouSuperSeguroCobranca.setCepCobr(linhaRecebida.getCell(27).getStringCellValue().isEmpty() ? null : linhaRecebida.getCell(27).getStringCellValue().replace("-", ""));
				recebidoSouSuperSeguroCobranca.setdFone1(linhaRecebida.getCell(28).getStringCellValue().isEmpty() ? null : linhaRecebida.getCell(28).getStringCellValue());
				recebidoSouSuperSeguroCobranca.setnFone1(linhaRecebida.getCell(29).getStringCellValue().isEmpty() ? null : linhaRecebida.getCell(29).getStringCellValue().replace("-", ""));
				recebidoSouSuperSeguroCobranca.setEmail(linhaRecebida.getCell(30).getStringCellValue().isEmpty() ? null : linhaRecebida.getCell(30).getStringCellValue());	
				
				
				if(!linhaRecebida.getCell(31).getStringCellValue().isEmpty()) {
					
					if(!linhaRecebida.getCell(31).getStringCellValue().contains("/")) {
						Calendar cal = datas.transformarNumeroEmData(linhaRecebida.getCell(31));
						recebidoSouSuperSeguroCobranca.setDataInicioCobr(cal);
					} else {
						
						Calendar cal = Calendar.getInstance();
						String[] dataString = linhaRecebida.getCell(31).getStringCellValue().split("/");
						
						for(int i = 0; i < dataString.length; i++) {
							if(dataString[i].length() < 2) {
								dataString[i] = "0" + dataString[i];
							}
						}
						
						String dataStringFinal = dataString[0] + "/" + dataString[1] + "/" + dataString[2];
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						try {
							cal.setTime(sdf.parse(dataStringFinal));
							recebidoSouSuperSeguroCobranca.setDataInicioCobr(cal);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					
				}
				
				RecebidoSouSuperSeguroPagamentoMensalidade recebidoSouSuperSeguroMensalidade = new RecebidoSouSuperSeguroPagamentoMensalidade();
				recebidoSouSuperSeguroMensalidade.setNroBanco(linhaRecebida.getCell(32).getStringCellValue().isEmpty() ? null : verificarEnums.verificarBanco(linhaRecebida.getCell(32).getStringCellValue()));
				recebidoSouSuperSeguroMensalidade.setNroAgencia(linhaRecebida.getCell(33).getStringCellValue().isEmpty() ? null : linhaRecebida.getCell(33).getStringCellValue());
				recebidoSouSuperSeguroMensalidade.setDvAgencia(linhaRecebida.getCell(34).getStringCellValue().isEmpty() ? null : linhaRecebida.getCell(34).getStringCellValue());
				recebidoSouSuperSeguroMensalidade.setcCorrente(linhaRecebida.getCell(35).getStringCellValue().isEmpty() ? null : linhaRecebida.getCell(35).getStringCellValue());
				recebidoSouSuperSeguroMensalidade.setDvConta(linhaRecebida.getCell(36).getStringCellValue().isEmpty() ? null : linhaRecebida.getCell(36).getStringCellValue());
				recebidoSouSuperSeguroMensalidade.setTpCobr(linhaRecebida.getCell(37).getStringCellValue().isEmpty() ? null : verificarEnums.verificarTipoCobranca(linhaRecebida.getCell(37).getStringCellValue()));
				recebidoSouSuperSeguroMensalidade.setNmTitCorrente(linhaRecebida.getCell(38).getStringCellValue().isEmpty() ? null : linhaRecebida.getCell(38).getStringCellValue());
				recebidoSouSuperSeguroMensalidade.setCpfTitCorrente(linhaRecebida.getCell(39).getStringCellValue().isEmpty() ? null : linhaRecebida.getCell(39).getStringCellValue().replace(".", "").replace("-", ""));
				recebidoSouSuperSeguroMensalidade.setcParentescoCobr(linhaRecebida.getCell(40).getStringCellValue().isEmpty() ? null : verificarEnums.verificarParentesco(linhaRecebida.getCell(40).getStringCellValue()));
				
				recebidoSouSuperSeguroMensalidade.setValor(linhaRecebida.getCell(41).getStringCellValue().isEmpty() ? null : new BigDecimal( linhaRecebida.getCell(41).getStringCellValue().replace(",", ".") ) );
				
				
				recebidoSouSuperSeguro.setRecebidoSouSuperSeguroCobranca(recebidoSouSuperSeguroCobranca);
				recebidoSouSuperSeguro.setRecebidoSouSuperSeguroPagamentoMensalidade(recebidoSouSuperSeguroMensalidade);			
				
				return recebidoSouSuperSeguro;
				
			} else {
				return null;
			}
		} else {
			return null;
		}
		
		
	}

	@Override
	public RecebidoSouSuperSeguroRecusada paraRecusados(RecebidoSouSuperSeguro retorno) {
		RecebidoSouSuperSeguroRecusada recusado = new RecebidoSouSuperSeguroRecusada();

		recusado.setContrato(retorno.getContrato());
		recusado.setcStatus(retorno.getcStatus());
		recusado.setcCategoria(retorno.getcCategoria());
		recusado.setcParentesco(retorno.getcParentesco());
		recusado.setNome(retorno.getNome());
		recusado.setDtNascimento(retorno.getDtNascimento());
		recusado.setcSexo(retorno.getcSexo());
		recusado.setCpf(retorno.getCpf());
		recusado.setNomeMae(retorno.getNomeMae());
		recusado.setrLogradores(retorno.getrLogradores());
		recusado.setrNumeroRes(retorno.getrNumeroRes());
		recusado.setBairroRes(retorno.getBairroRes());
		recusado.setCidadeRes(retorno.getCidadeRes());
		recusado.setIdCidadeRes(retorno.getIdCidadeRes());
		recusado.setUfRes(retorno.getUfRes());
		recusado.setCepRes(retorno.getCepRes());
		recusado.setdFone1(retorno.getdFone1());
		recusado.setnFone1(retorno.getnFone1());
		recusado.setEmail(retorno.getEmail());
		
		RecebidoSouSuperSeguroCobrancaRecusada cobrancaRecusada = new RecebidoSouSuperSeguroCobrancaRecusada();
		cobrancaRecusada.setNmCobr(retorno.getRecebidoSouSuperSeguroCobranca().getNmCobr());
		cobrancaRecusada.setDtNascCobr(retorno.getRecebidoSouSuperSeguroCobranca().getDtNascCobr());
		cobrancaRecusada.setCpfCobr(retorno.getRecebidoSouSuperSeguroCobranca().getCpfCobr());
		cobrancaRecusada.setrLogradCobr(retorno.getRecebidoSouSuperSeguroCobranca().getrLogradCobr());
		cobrancaRecusada.setrNumeroCobr(retorno.getRecebidoSouSuperSeguroCobranca().getrNumeroCobr());
		cobrancaRecusada.setBairroCobr(retorno.getRecebidoSouSuperSeguroCobranca().getBairroCobr());	
		cobrancaRecusada.setCidadeCobr(retorno.getRecebidoSouSuperSeguroCobranca().getCidadeCobr());
		cobrancaRecusada.setIdCidadeCobr(retorno.getRecebidoSouSuperSeguroCobranca().getIdCidadeCobr());
		cobrancaRecusada.setUfCobr(retorno.getRecebidoSouSuperSeguroCobranca().getUfCobr());
		cobrancaRecusada.setCepCobr(retorno.getRecebidoSouSuperSeguroCobranca().getCepCobr());
		cobrancaRecusada.setdFone1(retorno.getRecebidoSouSuperSeguroCobranca().getdFone1());
		cobrancaRecusada.setnFone1(retorno.getRecebidoSouSuperSeguroCobranca().getnFone1());
		cobrancaRecusada.setEmail(retorno.getRecebidoSouSuperSeguroCobranca().getEmail());
		cobrancaRecusada.setDataInicioCobr(retorno.getRecebidoSouSuperSeguroCobranca().getDataInicioCobr());
		
		
		recusado.setRecebidoSouSuperSeguroCobranca(cobrancaRecusada);
		
		
		RecebidoSouSuperSeguroPagamentoMensalidadeRecusada mensalidadeRecusada = new RecebidoSouSuperSeguroPagamentoMensalidadeRecusada();
		mensalidadeRecusada.setNroBanco(retorno.getRecebidoSouSuperSeguroPagamentoMensalidade().getNroBanco());
		mensalidadeRecusada.setNroAgencia(retorno.getRecebidoSouSuperSeguroPagamentoMensalidade().getNroAgencia());
		mensalidadeRecusada.setDvAgencia(retorno.getRecebidoSouSuperSeguroPagamentoMensalidade().getDvAgencia());
		mensalidadeRecusada.setcCorrente(retorno.getRecebidoSouSuperSeguroPagamentoMensalidade().getcCorrente());
		mensalidadeRecusada.setDvConta(retorno.getRecebidoSouSuperSeguroPagamentoMensalidade().getDvConta());
		mensalidadeRecusada.setTpCobr(retorno.getRecebidoSouSuperSeguroPagamentoMensalidade().getTpCobr());
		mensalidadeRecusada.setNmTitCorrente(retorno.getRecebidoSouSuperSeguroPagamentoMensalidade().getNmTitCorrente());
		mensalidadeRecusada.setCpfTitCorrente(retorno.getRecebidoSouSuperSeguroPagamentoMensalidade().getCpfTitCorrente());
		mensalidadeRecusada.setcParentescoCobr(retorno.getRecebidoSouSuperSeguroPagamentoMensalidade().getcParentescoCobr());
		
		mensalidadeRecusada.setValor(retorno.getRecebidoSouSuperSeguroPagamentoMensalidade().getValor());
	
		recusado.setRecebidoSouSuperSeguroPagamentoMensalidade(mensalidadeRecusada);
		
		
		return recusado;
	}

	
	
	
	
	
	
	
	@Override
	public RecebidoSouSuperSeguro paraCorretos(RecebidoSouSuperSeguroRecusada retornoNovaEntidade) {
			
		RecebidoSouSuperSeguro recebido = new RecebidoSouSuperSeguro();
		recebido.setContrato(retornoNovaEntidade.getContrato());
		recebido.setcStatus(retornoNovaEntidade.getcStatus());
		recebido.setcCategoria(retornoNovaEntidade.getcCategoria());
		recebido.setcParentesco(retornoNovaEntidade.getcParentesco());
		recebido.setNome(retornoNovaEntidade.getNome());
		recebido.setDtNascimento(retornoNovaEntidade.getDtNascimento());
		recebido.setcSexo(retornoNovaEntidade.getcSexo());
		recebido.setCpf(retornoNovaEntidade.getCpf());
		recebido.setNomeMae(retornoNovaEntidade.getNomeMae());
		recebido.setrLogradores(retornoNovaEntidade.getrLogradores());
		recebido.setrNumeroRes(retornoNovaEntidade.getrNumeroRes());
		recebido.setBairroRes(retornoNovaEntidade.getBairroRes());
		recebido.setCidadeRes(retornoNovaEntidade.getCidadeRes());
		recebido.setIdCidadeRes(retornoNovaEntidade.getIdCidadeRes());
		recebido.setUfRes(retornoNovaEntidade.getUfRes());
		recebido.setCepRes(retornoNovaEntidade.getCepRes());
		recebido.setdFone1(retornoNovaEntidade.getdFone1());
		recebido.setnFone1(retornoNovaEntidade.getnFone1());
		recebido.setEmail(retornoNovaEntidade.getEmail());
		
		recebido.setPisPasepNit(retornoNovaEntidade.getPisPasepNit());
		recebido.setCns(retornoNovaEntidade.getCns());
		recebido.setDnv(retornoNovaEntidade.getDnv());
		recebido.setcEstCivil(retornoNovaEntidade.getcEstCivil());
		recebido.setCompRes(retornoNovaEntidade.getCompRes());
		recebido.setReferenciaRes(retornoNovaEntidade.getReferenciaRes());
		
		recebido.setcFone1(retornoNovaEntidade.getcFone1());
		
		recebido.setdFone2(retornoNovaEntidade.getdFone2());
		recebido.setnFone2(retornoNovaEntidade.getnFone2());
		recebido.setcFone2(retornoNovaEntidade.getcFone2());
		
		recebido.setdFone3(retornoNovaEntidade.getdFone3());
		recebido.setnFone3(retornoNovaEntidade.getnFone3());
		recebido.setcFone3(retornoNovaEntidade.getcFone3());
		
		recebido.setcPlano(retornoNovaEntidade.getcPlano());
		recebido.setDtAdesao(retornoNovaEntidade.getDtAdesao());
		recebido.setDtCancelamento(retornoNovaEntidade.getDtCancelamento());
		recebido.setcMotivoCan(retornoNovaEntidade.getcMotivoCan());
		
		RecebidoSouSuperSeguroCobranca cobranca = new RecebidoSouSuperSeguroCobranca();
		cobranca.setNmCobr(retornoNovaEntidade.getRecebidoSouSuperSeguroCobranca().getNmCobr());
		cobranca.setDtNascCobr(retornoNovaEntidade.getRecebidoSouSuperSeguroCobranca().getDtNascCobr());
		cobranca.setCpfCobr(retornoNovaEntidade.getRecebidoSouSuperSeguroCobranca().getCpfCobr());
		cobranca.setrLogradCobr(retornoNovaEntidade.getRecebidoSouSuperSeguroCobranca().getrLogradCobr());
		cobranca.setrNumeroCobr(retornoNovaEntidade.getRecebidoSouSuperSeguroCobranca().getrNumeroCobr());
		cobranca.setBairroCobr(retornoNovaEntidade.getRecebidoSouSuperSeguroCobranca().getBairroCobr());	
		cobranca.setCidadeCobr(retornoNovaEntidade.getRecebidoSouSuperSeguroCobranca().getCidadeCobr());
		cobranca.setIdCidadeCobr(retornoNovaEntidade.getRecebidoSouSuperSeguroCobranca().getIdCidadeCobr());
		cobranca.setUfCobr(retornoNovaEntidade.getRecebidoSouSuperSeguroCobranca().getUfCobr());
		cobranca.setCepCobr(retornoNovaEntidade.getRecebidoSouSuperSeguroCobranca().getCepCobr());
		cobranca.setdFone1(retornoNovaEntidade.getRecebidoSouSuperSeguroCobranca().getdFone1());
		cobranca.setnFone1(retornoNovaEntidade.getRecebidoSouSuperSeguroCobranca().getnFone1());
		cobranca.setEmail(retornoNovaEntidade.getRecebidoSouSuperSeguroCobranca().getEmail());
		cobranca.setDataInicioCobr(retornoNovaEntidade.getRecebidoSouSuperSeguroCobranca().getDataInicioCobr());
		
		cobranca.setCompCobr(retornoNovaEntidade.getRecebidoSouSuperSeguroCobranca().getCompCobr());
		
		cobranca.setcFone1(retornoNovaEntidade.getRecebidoSouSuperSeguroCobranca().getcFone1());
		
		cobranca.setdFone2(retornoNovaEntidade.getRecebidoSouSuperSeguroCobranca().getdFone2());
		cobranca.setnFone2(retornoNovaEntidade.getRecebidoSouSuperSeguroCobranca().getnFone2());
		cobranca.setcFone2(retornoNovaEntidade.getRecebidoSouSuperSeguroCobranca().getcFone2());
		
		cobranca.setdFone3(retornoNovaEntidade.getRecebidoSouSuperSeguroCobranca().getdFone3());
		cobranca.setnFone3(retornoNovaEntidade.getRecebidoSouSuperSeguroCobranca().getnFone3());
		cobranca.setcFone3(retornoNovaEntidade.getRecebidoSouSuperSeguroCobranca().getcFone3());
		cobranca.setDiaVenc(retornoNovaEntidade.getRecebidoSouSuperSeguroCobranca().getDiaVenc());
		
		recebido.setRecebidoSouSuperSeguroCobranca(cobranca);
		
		
		RecebidoSouSuperSeguroPagamentoMensalidade mensalidade = new RecebidoSouSuperSeguroPagamentoMensalidade();
		mensalidade.setNroBanco(retornoNovaEntidade.getRecebidoSouSuperSeguroPagamentoMensalidade().getNroBanco());
		mensalidade.setNroAgencia(retornoNovaEntidade.getRecebidoSouSuperSeguroPagamentoMensalidade().getNroAgencia());
		mensalidade.setDvAgencia(retornoNovaEntidade.getRecebidoSouSuperSeguroPagamentoMensalidade().getDvAgencia());
		mensalidade.setcCorrente(retornoNovaEntidade.getRecebidoSouSuperSeguroPagamentoMensalidade().getcCorrente());
		mensalidade.setDvConta(retornoNovaEntidade.getRecebidoSouSuperSeguroPagamentoMensalidade().getDvConta());
		mensalidade.setTpCobr(retornoNovaEntidade.getRecebidoSouSuperSeguroPagamentoMensalidade().getTpCobr());
		mensalidade.setNmTitCorrente(retornoNovaEntidade.getRecebidoSouSuperSeguroPagamentoMensalidade().getNmTitCorrente());
		mensalidade.setCpfTitCorrente(retornoNovaEntidade.getRecebidoSouSuperSeguroPagamentoMensalidade().getCpfTitCorrente());


		mensalidade.setValor(retornoNovaEntidade.getRecebidoSouSuperSeguroPagamentoMensalidade().getValor());
	
		recebido.setRecebidoSouSuperSeguroPagamentoMensalidade(mensalidade);
		
		
		return recebido;
		
	}

}
