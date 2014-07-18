package br.com.sousuperseguro.utilImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.view.BoletoViewer;
import org.jrimum.domkee.comum.pessoa.endereco.CEP;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Banco;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo.Aceite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sousuperseguro.entities.NumeroDocumento;
import br.com.sousuperseguro.entities.RecebidoSouSuperSeguro;
import br.com.sousuperseguro.service.NumeroDocumentoService;
import br.com.sousuperseguro.util.BoletoBancario;
import br.com.sousuperseguro.util.NossoNumero;


@Component
public class BoletoBancarioImpl implements BoletoBancario {
	
	@Autowired
	NossoNumero nossoNumero;
	
	@Autowired
	NumeroDocumentoService numeroDocumentoService;
	
	static final private String NUMERO_CARTEIRA = "09";

	@SuppressWarnings("deprecation")
	@Override
	public BoletoViewer gerarBoleto(RecebidoSouSuperSeguro dadosDoCliente, BigInteger idProposta) {
		
		
		Cedente cedente = new Cedente("OdontoPrev S.A. - CNPJ: 58.119.199/0001-51 - Al. Tocantins, 125 15º andar Barueri/SP CEP: 06455-010", "58.119.199/0001-51");
		String cpf = dadosDoCliente.getRecebidoSouSuperSeguroPagamentoMensalidade().getCpfTitCorrente();
		
		cpf = cpf.replace(" ", "");
		
 		while(cpf.length() < 11) {
 			cpf = "0" + cpf;
 		}
		
		
		String cpfPrimeiro = cpf.substring(0, 3);
		String cpfSegundo = cpf.substring(3, 6);
		String cpfTerceiro = cpf.substring(6, 9);
		String cpfdigito = cpf.substring(9, 11);
		String cpfFinal = cpfPrimeiro + "." + cpfSegundo + "." + cpfTerceiro + "-" + cpfdigito;
		
		Sacado sacado = new Sacado(dadosDoCliente.getRecebidoSouSuperSeguroPagamentoMensalidade().getNmTitCorrente(), cpfFinal);
		
		
		Endereco enderecoSac = new Endereco();
		enderecoSac.setUF(unidadeFederativa(dadosDoCliente.getRecebidoSouSuperSeguroCobranca().getUfCobr()));
		enderecoSac.setLocalidade(dadosDoCliente.getRecebidoSouSuperSeguroCobranca().getCidadeCobr());
		
		enderecoSac.setCep(new CEP(dadosDoCliente.getRecebidoSouSuperSeguroCobranca().getCepCobr()));
		enderecoSac.setBairro(dadosDoCliente.getRecebidoSouSuperSeguroCobranca().getBairroCobr());
		enderecoSac.setLogradouro(dadosDoCliente.getRecebidoSouSuperSeguroCobranca().getrLogradCobr());
		
		enderecoSac.setNumero(dadosDoCliente.getRecebidoSouSuperSeguroCobranca().getrNumeroCobr());
		sacado.addEndereco(enderecoSac);

		ContaBancaria contaBancaria = new ContaBancaria(banco(237));
		contaBancaria.setAgencia(new Agencia(2842, "8"));
		contaBancaria.setNumeroDaConta(new NumeroDaConta(8490, "5"));
		contaBancaria.setCarteira(new Carteira(Integer.valueOf(NUMERO_CARTEIRA)));
		
		
		Titulo titulo = new Titulo(contaBancaria, sacado, cedente);
		//???
		
		
		
		String idNumeroDocumento = dadosDoCliente.getId().toString();
		

		for(int i = idNumeroDocumento.length(); i <= 9; i++){
			idNumeroDocumento = "0" + idNumeroDocumento;
		}
		
		titulo.setNumeroDoDocumento(idNumeroDocumento);
		
		String[] arrayNossoNumero = nossoNumero.gerarNossoNumero(idProposta, NUMERO_CARTEIRA);
		

		
		NumeroDocumento numeroDocumento = new NumeroDocumento();
		numeroDocumento.setNumeroDocumento(idNumeroDocumento);
		numeroDocumento.setNossoNumero(NUMERO_CARTEIRA + arrayNossoNumero[1] + "-" + arrayNossoNumero[2]);
		numeroDocumento.setIdRecebidoSouSuperSeguro(dadosDoCliente);
		numeroDocumentoService.insertNumeroDocumento(numeroDocumento);
		
		
		titulo.setNossoNumero(arrayNossoNumero[1]);
		titulo.setDigitoDoNossoNumero(arrayNossoNumero[2]);
		
		Date data = new Date();
		
		//???
		
		if(dadosDoCliente.getRecebidoSouSuperSeguroPagamentoMensalidade().getValor() == null) {
			titulo.setValor(new BigDecimal("35.90"));
		} else {
			titulo.setValor(dadosDoCliente.getRecebidoSouSuperSeguroPagamentoMensalidade().getValor());
		}
		
		
		titulo.setDataDoDocumento(data);
		
		int dataMais3diasInteger = data.getDate() + 3;
		
		Date dataMais3Dias = new Date();
		dataMais3Dias.setDate(dataMais3diasInteger);
		titulo.setDataDoVencimento(dataMais3Dias);
		
		
		
		titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
		titulo.setAceite(Aceite.N);
		
		//???
		titulo.setDesconto(null);
		titulo.setDeducao(null);
		titulo.setMora(null);
		titulo.setAcrecimo(null);
		
//		if(dadosDoCliente.getRecebidoSouSuperSeguroPagamentoMensalidade().getValor() == null) {
//			titulo.setValorCobrado(new BigDecimal("30.00"));
//		} else {
//			titulo.setValorCobrado(dadosDoCliente.getRecebidoSouSuperSeguroPagamentoMensalidade().getValor());
//		}
		
		titulo.setValorCobrado(null);
		
		
		Boleto boleto = new Boleto(titulo);
        
		boleto.setLocalPagamento("Pagável preferencialmente na Rede Bradesco ou Bradesco Expresso.");

		
		boleto.setInstrucao1("ATENÇÃO SR. CAIXA: Não receber se o campo Pagador não estiver preenchido");
		
		
		boleto.setInstrucao8("APÓS o Vencimento, Pagável Somente no Banco Bradesco.");
		
		BoletoViewer boletoViewer = new BoletoViewer(boleto);
		boletoViewer.setTemplate("/home/boleto/pagadorBeneficiario.pdf");
//		/home/boleto/pagadorBeneficiario.pdf
//		C:/Users/Pc6/pagadorBeneficiario.pdf
//		C:/pagadorBeneficiario.pdf
		
//		File file = new File("C:\\Users\\Erik Scaranello\\Documents\\boleto.pdf"); //Criamos um nome para o arquivo  
//		BufferedOutputStream bos = null;
//		try {
//			bos = new BufferedOutputStream(new FileOutputStream(file));
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} //Criamos o arquivo  
//		try {
//			bos.write(boletoViewer.getPdfAsByteArray());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} //Gravamos os bytes lï¿½  
//		try {
//			bos.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		return boletoViewer;
		
	}
	
	
	
	
	private UnidadeFederativa unidadeFederativa(String uf) {
		UnidadeFederativa retornoUnidadeFederativa;
		switch (uf) {
		case "AC":
			retornoUnidadeFederativa = UnidadeFederativa.AC;
			break;
		case "AL":
			retornoUnidadeFederativa = UnidadeFederativa.AL;
			break;
		case "AP":
			retornoUnidadeFederativa = UnidadeFederativa.AP;
			break;	
		case "AM":
			retornoUnidadeFederativa = UnidadeFederativa.AM;
			break;
		case "BA":
			retornoUnidadeFederativa = UnidadeFederativa.BA;
			break;
		case "CE":
			retornoUnidadeFederativa = UnidadeFederativa.CE;
			break;	
		case "DF":
			retornoUnidadeFederativa = UnidadeFederativa.DF;
			break;
		case "ES":
			retornoUnidadeFederativa = UnidadeFederativa.ES;
			break;
		case "GO":
			retornoUnidadeFederativa = UnidadeFederativa.GO;
			break;
		case "MA":
			retornoUnidadeFederativa = UnidadeFederativa.MA;
			break;
		case "MT":
			retornoUnidadeFederativa = UnidadeFederativa.MT;
			break;	
		case "MS":
			retornoUnidadeFederativa = UnidadeFederativa.MS;
			break;	
		case "MG":
			retornoUnidadeFederativa = UnidadeFederativa.MG;
			break;
		case "PR":
			retornoUnidadeFederativa = UnidadeFederativa.PR;
			break;
		case "PB":
			retornoUnidadeFederativa = UnidadeFederativa.PB;
			break;
		case "PA":
			retornoUnidadeFederativa = UnidadeFederativa.PA;
			break;
		case "PE":
			retornoUnidadeFederativa = UnidadeFederativa.PE;
			break;
		case "PI":
			retornoUnidadeFederativa = UnidadeFederativa.PI;
			break;	
		case "RJ":
			retornoUnidadeFederativa = UnidadeFederativa.RJ;
			break;
		case "RN":
			retornoUnidadeFederativa = UnidadeFederativa.RN;
			break;
		case "RS":
			retornoUnidadeFederativa = UnidadeFederativa.RS;
			break;
		case "RO":
			retornoUnidadeFederativa = UnidadeFederativa.RO;
			break;
		case "RR":
			retornoUnidadeFederativa = UnidadeFederativa.RR;
			break;
		case "SC":
			retornoUnidadeFederativa = UnidadeFederativa.SC;
			break;
		case "SE":
			retornoUnidadeFederativa = UnidadeFederativa.SE;
			break;
		case "SP":
			retornoUnidadeFederativa = UnidadeFederativa.SP;
			break;
		case "TO":
			retornoUnidadeFederativa = UnidadeFederativa.TO;
			break;	
		default:
			retornoUnidadeFederativa = null;
			break;
		}
		return retornoUnidadeFederativa;
	}
	
	
	private Banco banco(Integer numeroBanco) {
		Banco retornoDeBanco;
		switch (numeroBanco) {
		case 237:
			retornoDeBanco = BancosSuportados.BANCO_BRADESCO.create();
			break;
		case 341:
			retornoDeBanco = BancosSuportados.BANCO_ITAU.create();
			break;	
		case 001:
			retornoDeBanco = BancosSuportados.BANCO_DO_BRASIL.create();
			break;	
		default:
			retornoDeBanco = null;
			break;
		}
		return retornoDeBanco;
	}

}
