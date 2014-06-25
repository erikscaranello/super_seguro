package br.com.sousuperseguro.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.sousuperseguro.entities.ErrosRetorno;
import br.com.sousuperseguro.entities.recusadas.RecebidoSouSuperSeguroCobrancaRecusada;
import br.com.sousuperseguro.entities.recusadas.RecebidoSouSuperSeguroPagamentoMensalidadeRecusada;
import br.com.sousuperseguro.entities.recusadas.RecebidoSouSuperSeguroRecusada;
import br.com.sousuperseguro.enums.Banco;
import br.com.sousuperseguro.enums.CancelamentoDoAssociado;
import br.com.sousuperseguro.enums.Categoria;
import br.com.sousuperseguro.enums.EstadoCivil;
import br.com.sousuperseguro.enums.Parentesco;
import br.com.sousuperseguro.enums.Sexo;
import br.com.sousuperseguro.enums.Status;
import br.com.sousuperseguro.enums.TipoCobranca;
import br.com.sousuperseguro.service.ArquivosRecusadosService;
import br.com.sousuperseguro.service.ErrosRetornoService;
import br.com.sousuperseguro.service.HomeService;
import br.com.sousuperseguro.service.Sessoes;
import br.com.sousuperseguro.service.UploadDeArquivosService;
import br.com.sousuperseguro.service.VerificarEnums;

@Controller
public class HomeController {
	
	@Autowired
	private HomeService homeService;
	
	@Autowired
	private ErrosRetornoService errosRetornoService;
	
	@Autowired
	private Sessoes sessoes;
	
	@Autowired
	private ArquivosRecusadosService arquivosRecusadosService;
	
	@Autowired
	private VerificarEnums verificarEnums;
	
	@Autowired
	UploadDeArquivosService uploadDeArquivos;
		
	
	@RequestMapping("/")
	public ModelAndView index() {
			
		ModelAndView modelAndView = new ModelAndView("home/index");
		
		modelAndView.addObject("listaRecusada", arquivosRecusadosService.obterArquivosRecusadosLimitCinco());
		modelAndView.addObject("listaRecusadaBradesco", arquivosRecusadosService.obterArquivosRecusadosBradescoLimitCinco());
		
		return modelAndView;
	}
	
	@RequestMapping(value="/alterar_dados/{numeroDados}", method = RequestMethod.GET)
	public ModelAndView alterarDados(@PathVariable BigInteger numeroDados) {
			
		ModelAndView modelAndView = new ModelAndView("home/alterar");
		
		RecebidoSouSuperSeguroRecusada arquivoRecusado = arquivosRecusadosService.obterArquivoRecusado(numeroDados);
		
		if (arquivoRecusado != null ) {
			if(arquivoRecusado.getCodigoErro() != null && !arquivoRecusado.getCodigoErro().isEmpty()) {
				ErrosRetorno erro = errosRetornoService.obterErro(arquivoRecusado.getCodigoErro());
				modelAndView.addObject("erroRetorno", erro);
			}
		}
		
		modelAndView.addObject("arquivoRecusado", arquivoRecusado);
		
		return modelAndView;
	}
	
	
	@RequestMapping(value="/receber_alterar_dados", method = RequestMethod.POST)
	public ModelAndView receberAlterarDados(HttpServletRequest request) {

		RecebidoSouSuperSeguroRecusada recebidoSouSuperSeguro = new RecebidoSouSuperSeguroRecusada();
			
		if(!request.getParameter("id").isEmpty()) {
			recebidoSouSuperSeguro.setId(new BigInteger(request.getParameter("id")));
		} 
		
		recebidoSouSuperSeguro.setContrato("057946");
		
		if(!request.getParameter("nroProposta").isEmpty()) {
			recebidoSouSuperSeguro.setNroProposta(request.getParameter("nroProposta"));	
		}
		
		if(!request.getParameter("codigoErro").isEmpty()) {
			recebidoSouSuperSeguro.setCodigoErro(request.getParameter("codigoErro"));	
		}
		
		
		recebidoSouSuperSeguro.setCdMatricula(request.getParameter("cdMatricula"));
		
		if(Boolean.parseBoolean(request.getParameter("recebidoBradesco")) == true) {
			recebidoSouSuperSeguro.setcStatus(Status.A);
		} else {
			recebidoSouSuperSeguro.setcStatus(request.getParameter("cStatus").isEmpty() ? null : Status.valueOf(request.getParameter("cStatus")));
		}
		
		recebidoSouSuperSeguro.setcCategoria(request.getParameter("cCategoria").isEmpty() ? null : Categoria.valueOf(request.getParameter("cCategoria")));
		recebidoSouSuperSeguro.setcParentesco(request.getParameter("cParentesco").isEmpty() ? null : Parentesco.valueOf(request.getParameter("cParentesco")));
		recebidoSouSuperSeguro.setNome(request.getParameter("nome").isEmpty() ? null : request.getParameter("nome"));
		
		
		if(!request.getParameter("dtNascimento").isEmpty()) { 
			
			SimpleDateFormat simpleDateFormat;
			if( request.getParameter("dtNascimento").contains("/")){
				simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			} else {
				simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
			}
			
			Calendar cal = Calendar.getInstance(); 
			
			try {
				cal.setTime(simpleDateFormat.parse(request.getParameter("dtNascimento")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			recebidoSouSuperSeguro.setDtNascimento(cal);
		}
		
		recebidoSouSuperSeguro.setcSexo(request.getParameter("cSexo").isEmpty() ? null : Sexo.valueOf(request.getParameter("cSexo")));
		recebidoSouSuperSeguro.setCpf(request.getParameter("cpf").isEmpty() ? null : request.getParameter("cpf").replace(".", "").replace("-", ""));
		recebidoSouSuperSeguro.setNomeMae(request.getParameter("nomeMae").isEmpty() ? null : request.getParameter("nomeMae"));
		
		recebidoSouSuperSeguro.setrLogradores(request.getParameter("rLogradores").isEmpty() ? null : request.getParameter("rLogradores"));
		recebidoSouSuperSeguro.setrNumeroRes(request.getParameter("rNumeroRes").isEmpty() ? null : request.getParameter("rNumeroRes"));
		recebidoSouSuperSeguro.setBairroRes(request.getParameter("bairroRes").isEmpty() ? null : request.getParameter("bairroRes"));
		recebidoSouSuperSeguro.setCidadeRes(request.getParameter("cidadeRes").isEmpty() ? null : request.getParameter("cidadeRes"));
		recebidoSouSuperSeguro.setIdCidadeRes(request.getParameter("idCidadeRes").isEmpty() ? null : new BigInteger(request.getParameter("idCidadeRes")));
		recebidoSouSuperSeguro.setUfRes(request.getParameter("ufRes").isEmpty() ? null : request.getParameter("ufRes"));
		recebidoSouSuperSeguro.setCepRes(request.getParameter("cepRes").isEmpty() ? null : request.getParameter("cepRes").replace("-", ""));
		recebidoSouSuperSeguro.setdFone1(request.getParameter("dFone1").isEmpty() ? null : request.getParameter("dFone1"));
		recebidoSouSuperSeguro.setnFone1(request.getParameter("nFone1").isEmpty() ? null : request.getParameter("nFone1").replace("-", ""));
		recebidoSouSuperSeguro.setEmail(request.getParameter("email").isEmpty() ? null : request.getParameter("email"));
		
		
		recebidoSouSuperSeguro.setPisPasepNit(request.getParameter("pisPasepNit").isEmpty() ? null : request.getParameter("pisPasepNit"));
		recebidoSouSuperSeguro.setCns(request.getParameter("cns").isEmpty() ? null : request.getParameter("cns"));
		recebidoSouSuperSeguro.setDnv(request.getParameter("dnv").isEmpty() ? null : request.getParameter("dnv"));
		recebidoSouSuperSeguro.setcEstCivil(request.getParameter("cEstCivil").isEmpty() ? null : EstadoCivil.valueOf(request.getParameter("cEstCivil")));
		recebidoSouSuperSeguro.setCompRes(request.getParameter("compRes").isEmpty() ? null : request.getParameter("compRes"));
		recebidoSouSuperSeguro.setReferenciaRes(request.getParameter("referenciaRes").isEmpty() ? null : request.getParameter("referenciaRes"));
		
		recebidoSouSuperSeguro.setcFone1(request.getParameter("cFone1").isEmpty() ? null : request.getParameter("cFone1"));
		
		recebidoSouSuperSeguro.setdFone2(request.getParameter("dFone2").isEmpty() ? null : request.getParameter("dFone2"));
		recebidoSouSuperSeguro.setnFone2(request.getParameter("nFone2").isEmpty() ? null : request.getParameter("nFone2").replace("-", ""));
		recebidoSouSuperSeguro.setcFone2(request.getParameter("cFone2").isEmpty() ? null : request.getParameter("cFone2"));
		
		recebidoSouSuperSeguro.setdFone3(request.getParameter("dFone3").isEmpty() ? null : request.getParameter("dFone3"));
		recebidoSouSuperSeguro.setnFone3(request.getParameter("nFone3").isEmpty() ? null : request.getParameter("nFone3").replace("-", ""));
		recebidoSouSuperSeguro.setcFone3(request.getParameter("cFone3").isEmpty() ? null : request.getParameter("cFone3"));
		
		recebidoSouSuperSeguro.setcPlano(request.getParameter("cPlano").isEmpty() ? null : request.getParameter("cPlano"));
		

		
		if(!request.getParameter("dtAdesao").isEmpty()) { 
			
			SimpleDateFormat simpleDateFormat;
			if( request.getParameter("dtAdesao").contains("/")){
				simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			} else {
				simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
			}
			
			Calendar cal = Calendar.getInstance(); 
			
			try {
				cal.setTime(simpleDateFormat.parse(request.getParameter("dtAdesao")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			recebidoSouSuperSeguro.setDtAdesao(cal);
		}
		
		if(!request.getParameter("dtCancelamento").isEmpty()) { 
			SimpleDateFormat simpleDateFormat;
			if( request.getParameter("dtCancelamento").contains("/")){
				simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			} else {
				simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
			}
		
			Calendar cal = Calendar.getInstance(); 
			
			try {
				cal.setTime(simpleDateFormat.parse(request.getParameter("dtCancelamento")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			recebidoSouSuperSeguro.setDtNascimento(cal);
		}
		
		
		recebidoSouSuperSeguro.setcMotivoCan(request.getParameter("cMotivoCan").isEmpty() ? null : CancelamentoDoAssociado.valueOf(request.getParameter("cMotivoCan")));
	
		
		ModelAndView modelAndView = new ModelAndView("home/alterar_passo2");
		
		if(recebidoSouSuperSeguro.getId() != null) {
			RecebidoSouSuperSeguroRecusada retornoNovaEntidade = homeService.selecionarRecebidoRecusadoPorId(recebidoSouSuperSeguro.getId());
			recebidoSouSuperSeguro.setRecebidoSouSuperSeguroCobranca(retornoNovaEntidade.getRecebidoSouSuperSeguroCobranca());
			recebidoSouSuperSeguro.setRecebidoSouSuperSeguroDadosFinais(retornoNovaEntidade.getRecebidoSouSuperSeguroDadosFinais());
			recebidoSouSuperSeguro.setRecebidoSouSuperSeguroPagamentoMensalidade(retornoNovaEntidade.getRecebidoSouSuperSeguroPagamentoMensalidade());
			
			modelAndView.addObject("arquivoRecusado", retornoNovaEntidade.getRecebidoSouSuperSeguroCobranca());
		}
		
		recebidoSouSuperSeguro.setRecebidoBradesco(Boolean.parseBoolean(request.getParameter("recebidoBradesco")));
		
		homeService.alteracaoSouSuperSeguro(recebidoSouSuperSeguro);
		
		modelAndView.addObject("idPai", recebidoSouSuperSeguro.getId());
		
		return modelAndView;
	}
	
	
	
	@RequestMapping(value="/receber_alterar_dados_passo2", method = RequestMethod.POST)
	public ModelAndView receberAlterarDadosPasso2(HttpServletRequest request) {

		RecebidoSouSuperSeguroCobrancaRecusada recebidoSouSuperSeguro = new RecebidoSouSuperSeguroCobrancaRecusada();
			
		if(!request.getParameter("id").isEmpty()) {
			recebidoSouSuperSeguro.setId(new BigInteger(request.getParameter("id")));
		}
		
		
		recebidoSouSuperSeguro.setNmCobr(request.getParameter("nmCobr").isEmpty() ? null : request.getParameter("nmCobr"));
		recebidoSouSuperSeguro.setCpfCobr(request.getParameter("cpfCobr").isEmpty() ? null : request.getParameter("cpfCobr").replace(".", "").replace("-", ""));
		
		
		if(!request.getParameter("dtNascCobr").isEmpty()) { 
			SimpleDateFormat simpleDateFormat;
			if( request.getParameter("dtNascCobr").contains("/")){
				simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			} else {
				simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
			}
			
			Calendar cal = Calendar.getInstance(); 
			
			try {
				cal.setTime(simpleDateFormat.parse(request.getParameter("dtNascCobr")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			recebidoSouSuperSeguro.setDtNascCobr(cal);
		}
	
		
		recebidoSouSuperSeguro.setrLogradCobr(request.getParameter("rLogradCobr").isEmpty() ? null : request.getParameter("rLogradCobr").toString());
		recebidoSouSuperSeguro.setrNumeroCobr(request.getParameter("rNumeroCobr").isEmpty() ? null : request.getParameter("rNumeroCobr"));
		recebidoSouSuperSeguro.setBairroCobr(request.getParameter("bairroCobr").isEmpty() ? null : request.getParameter("bairroCobr"));
		recebidoSouSuperSeguro.setCidadeCobr(request.getParameter("cidadeCobr").isEmpty() ? null : request.getParameter("cidadeCobr").toString());
		recebidoSouSuperSeguro.setIdCidadeCobr(request.getParameter("idCidadeCobr").isEmpty() ? null : new BigInteger(request.getParameter("idCidadeCobr")));
		recebidoSouSuperSeguro.setUfCobr(request.getParameter("ufCobr").isEmpty() ? null : request.getParameter("ufCobr"));
		recebidoSouSuperSeguro.setCepCobr(request.getParameter("cepCobr").isEmpty() ? null : request.getParameter("cepCobr").replace("-", ""));
		recebidoSouSuperSeguro.setdFone1(request.getParameter("dFone1").isEmpty() ? null : request.getParameter("dFone1"));
		recebidoSouSuperSeguro.setnFone1(request.getParameter("nFone1").isEmpty() ? null : request.getParameter("nFone1").replace("-", ""));
		recebidoSouSuperSeguro.setEmail(request.getParameter("email").isEmpty() ? null : request.getParameter("email"));
		
		
		recebidoSouSuperSeguro.setCompCobr(request.getParameter("compCobr").isEmpty() ? null : request.getParameter("compCobr"));
		
		recebidoSouSuperSeguro.setcFone1(request.getParameter("cFone1").isEmpty() ? null : request.getParameter("cFone1"));
		
		recebidoSouSuperSeguro.setdFone2(request.getParameter("dFone2").isEmpty() ? null : request.getParameter("dFone2"));
		recebidoSouSuperSeguro.setnFone2(request.getParameter("nFone2").isEmpty() ? null : request.getParameter("nFone2").replace("-", ""));
		recebidoSouSuperSeguro.setcFone2(request.getParameter("cFone2").isEmpty() ? null : request.getParameter("cFone2"));
		
		recebidoSouSuperSeguro.setdFone3(request.getParameter("dFone3").isEmpty() ? null : request.getParameter("dFone3"));
		recebidoSouSuperSeguro.setnFone3(request.getParameter("nFone3").isEmpty() ? null : request.getParameter("nFone3").replace("-", ""));
		recebidoSouSuperSeguro.setcFone3(request.getParameter("cFone3").isEmpty() ? null : request.getParameter("cFone3"));
					
		if(!request.getParameter("diaVenc").isEmpty()) { 
			
			SimpleDateFormat simpleDateFormat;
			if( request.getParameter("diaVenc").contains("/")){
				simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			} else {
				simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
			}
			
			Calendar cal = Calendar.getInstance(); 
			
			try {
				cal.setTime(simpleDateFormat.parse(request.getParameter("diaVenc")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			recebidoSouSuperSeguro.setDiaVenc(cal);
		}
		
		if(!request.getParameter("dataInicioCobr").isEmpty()) { 
			
			
			SimpleDateFormat simpleDateFormat;
			if( request.getParameter("dataInicioCobr").contains("/")){
				simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			} else {
				simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
			}
			
			Calendar cal = Calendar.getInstance(); 
			
			try {
				cal.setTime(simpleDateFormat.parse(request.getParameter("dataInicioCobr")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			recebidoSouSuperSeguro.setDataInicioCobr(cal);
		}
		
		
		RecebidoSouSuperSeguroRecusada retornoNovaEntidade = homeService.selecionarRecebidoRecusadoPorId(
				new BigInteger(request.getParameter("idPai")));
		
		retornoNovaEntidade.setRecebidoSouSuperSeguroCobranca(recebidoSouSuperSeguro);
		
		homeService.alteracaoSouSuperSeguro(retornoNovaEntidade);
		
		
		
		
		ModelAndView modelAndView = new ModelAndView("home/alterar_passo3");
		
		modelAndView.addObject("idPai", retornoNovaEntidade.getId());
		modelAndView.addObject("arquivoRecusado", retornoNovaEntidade.getRecebidoSouSuperSeguroPagamentoMensalidade());
		modelAndView.addObject("principal", retornoNovaEntidade);
		return modelAndView;
	}
	
	
	@RequestMapping(value="/receber_alterar_dados_passo3", method = RequestMethod.POST)
	public void receberAlterarDadosPasso3(HttpServletRequest request, HttpServletResponse response) {

		RecebidoSouSuperSeguroPagamentoMensalidadeRecusada recebidoSouSuperSeguro = new RecebidoSouSuperSeguroPagamentoMensalidadeRecusada();
		
		if(!request.getParameter("id").isEmpty()) {	
			recebidoSouSuperSeguro.setId(new BigInteger(request.getParameter("id")));
		}
			
		recebidoSouSuperSeguro.setNroBanco(request.getParameter("nroBanco").isEmpty() ? null : Banco.valueOf(request.getParameter("nroBanco")));
		recebidoSouSuperSeguro.setNroAgencia(request.getParameter("nroAgencia").isEmpty() ? null : request.getParameter("nroAgencia"));
		recebidoSouSuperSeguro.setDvAgencia(request.getParameter("dvAgencia").isEmpty() ? null : request.getParameter("dvAgencia"));
		
		recebidoSouSuperSeguro.setcCorrente(request.getParameter("cCorrente").isEmpty() ? null : request.getParameter("cCorrente"));
		recebidoSouSuperSeguro.setDvConta(request.getParameter("dvConta").isEmpty() ? null : request.getParameter("dvConta"));
		
		recebidoSouSuperSeguro.setTpCobr(request.getParameter("tpCobr").isEmpty() ? null : TipoCobranca.valueOf(request.getParameter("tpCobr")));
		recebidoSouSuperSeguro.setNmTitCorrente(request.getParameter("nmTitCorrente").isEmpty() ? null : request.getParameter("nmTitCorrente"));
		
		recebidoSouSuperSeguro.setCpfTitCorrente(request.getParameter("cpfTitCorrente").isEmpty() ? null : request.getParameter("cpfTitCorrente").replace(".", "").replace("-", ""));
		recebidoSouSuperSeguro.setcParentescoCobr(request.getParameter("cParentescoCobr").isEmpty() ? null : Parentesco.valueOf(request.getParameter("cParentescoCobr")));
		
		recebidoSouSuperSeguro.setValor(request.getParameter("valor").isEmpty() ? null : new BigDecimal(request.getParameter("valor")));
		
		
		RecebidoSouSuperSeguroRecusada retornoNovaEntidade = homeService.selecionarRecebidoRecusadoPorId(
				new BigInteger(request.getParameter("idPai")));
		
		
		retornoNovaEntidade.setRecebidoSouSuperSeguroPagamentoMensalidade(recebidoSouSuperSeguro);
		
		homeService.alteracaoSouSuperSeguro(retornoNovaEntidade);
			
		
		uploadDeArquivos.fazerUpload(retornoNovaEntidade);
		
		try {
			response.sendRedirect("/sou_seguro");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		ModelAndView modelAndView = new ModelAndView("home/alterar_passo3");
//		
//		modelAndView.addObject("idPai", retornoNovaEntidade.getId());
//		modelAndView.addObject("arquivoRecusado", retornoNovaEntidade.getRecebidoSouSuperSeguroPagamentoMensalidade());
//		return modelAndView;
	}
	
	
	@RequestMapping("/acessoNegado")
	public ModelAndView acessoNegado() {
		
		ModelAndView modelAndView = new ModelAndView("home/acesso_negado");
		return modelAndView;
		
	}
	
	
	
}
