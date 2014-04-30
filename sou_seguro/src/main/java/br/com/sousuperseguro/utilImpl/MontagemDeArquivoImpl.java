package br.com.sousuperseguro.utilImpl;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sousuperseguro.entities.ArquivosEnvio;
import br.com.sousuperseguro.entities.RecebidoSouSuperSeguro;
import br.com.sousuperseguro.service.ArquivosEnvioService;
import br.com.sousuperseguro.util.MontagemDeArquivo;


@Component
public class MontagemDeArquivoImpl implements MontagemDeArquivo {

	
	@Autowired
	ArquivosEnvioService arquivosEnvioService;
	
	
	@Override
	public String montarArquivoDeEnvio(List<RecebidoSouSuperSeguro> listaRecebidos) {
		
		
		String header = "";

		header = header + "0";

		header = header + "SUPERSEGURO                                       ";

		header = header + "ODONTOPREV                                        ";
		
		ArquivosEnvio ultimoarquivo = arquivosEnvioService.obterUltimoArquivoDeEnvio();

		String dataDeHoje = "";
		BigInteger numeroArquivo;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

		if (ultimoarquivo == null) {

			Calendar cal = Calendar.getInstance();
			dataDeHoje = simpleDateFormat.format(cal.getTime());
			numeroArquivo = new BigInteger("1");

		} else {

			numeroArquivo = ultimoarquivo.getId().add(new BigInteger("1"));
			dataDeHoje = simpleDateFormat.format(ultimoarquivo.getDataArquivo()
					.getTime());
		}

		header = header + dataDeHoje;

		String numeroDoArquivoString = String.valueOf(numeroArquivo);

		int contagem = 8 - numeroDoArquivoString.length();

		for (int i = 0; i < contagem; i++) {
			numeroDoArquivoString = "0" + numeroDoArquivoString;
		}

		header = header + numeroDoArquivoString;

		String filler = "";
		for (int i = 0; i < 1032; i++) {
			filler = filler + " ";
		}

		header = header + filler;

				
		
		header = header + System.getProperty("line.separator"); 
		
		String stringRetornoArquivo = "";
		for (RecebidoSouSuperSeguro recebido : listaRecebidos) {
			


			stringRetornoArquivo = stringRetornoArquivo
					+ recebido.getContrato() + "    ";
			stringRetornoArquivo = stringRetornoArquivo + recebido.getcStatus();
			stringRetornoArquivo = stringRetornoArquivo
					+ recebido.getcCategoria().getCategoria();
			stringRetornoArquivo = stringRetornoArquivo
					+ recebido.getNroProposta();

			String matriculaString = String.valueOf(recebido.getId());
			int contageMatricula = 20 - matriculaString.length();
			
			for (int j = 0; j < contageMatricula; j++) {
				matriculaString = "0" + matriculaString;
			}

			stringRetornoArquivo = stringRetornoArquivo + matriculaString;
			
			
			stringRetornoArquivo = recebido.getcParentesco() != null ? (stringRetornoArquivo + recebido.getcParentesco().getParentesco()) : stringRetornoArquivo + "  ";
			
			
			stringRetornoArquivo = stringRetornoArquivo
					+ acertoDeCamposFor(recebido.getNome(), 70);

			simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = recebido.getDtNascimento();
			String dataNascimento = simpleDateFormat.format(cal.getTime());

			stringRetornoArquivo = stringRetornoArquivo + dataNascimento;
			stringRetornoArquivo = stringRetornoArquivo + recebido.getcSexo().getSexo();

			/*
			 * CPF 
			 * 
			 */
			
			if(recebido.getCpf().length() < 11) {
				String cpfCobranca = recebido.getCpf();	
				for( int i = 0; i < (11 - recebido.getCpf().length()) ; i++) {
					cpfCobranca = "0" + cpfCobranca;
				}
				
				stringRetornoArquivo = stringRetornoArquivo
						+ cpfCobranca;
				
			} else {
				stringRetornoArquivo = stringRetornoArquivo
						+ recebido.getCpf();
			}
			
			
			
			// pis pasep
			stringRetornoArquivo = stringRetornoArquivo + "           ";

			// cns
			stringRetornoArquivo = stringRetornoArquivo + "               ";

			stringRetornoArquivo = stringRetornoArquivo
					+ acertoDeCamposFor(recebido.getNomeMae(), 70);

			// dnv
			stringRetornoArquivo = stringRetornoArquivo + "           ";

			// Estado civil
			if (recebido.getcEstCivil() != null) {
				stringRetornoArquivo = stringRetornoArquivo
						+ recebido.getcEstCivil().getEstadoCivil();
			} else {
				stringRetornoArquivo = stringRetornoArquivo + "  ";
			}

			stringRetornoArquivo = stringRetornoArquivo
					+ acertoDeCamposFor(recebido.getrLogradores(), 50);

			stringRetornoArquivo = stringRetornoArquivo
					+ acertoDeCamposFor(recebido.getrNumeroRes(), 10);

			stringRetornoArquivo = stringRetornoArquivo
					+ acertoDeCamposFor(recebido.getCompRes() != null ? recebido.getCompRes() : "", 15);

			stringRetornoArquivo = stringRetornoArquivo
					+ acertoDeCamposFor(recebido.getBairroRes(), 30);

			stringRetornoArquivo = stringRetornoArquivo
					+ acertoDeCamposFor(recebido.getCidadeRes(), 30);

			stringRetornoArquivo = stringRetornoArquivo
					+ acertoDeCamposFor(recebido.getIdCidadeRes().toString(), 7);

			stringRetornoArquivo = stringRetornoArquivo + recebido.getUfRes();
			stringRetornoArquivo = stringRetornoArquivo + recebido.getCepRes();

			stringRetornoArquivo = stringRetornoArquivo
					+ acertoDeCamposFor(recebido.getReferenciaRes() != null ? recebido.getReferenciaRes() : "", 30);

			stringRetornoArquivo = stringRetornoArquivo + "0"
					+ recebido.getdFone1();

			stringRetornoArquivo = stringRetornoArquivo
					+ acertoDeCamposFor(recebido.getnFone1(), 15);

			stringRetornoArquivo = stringRetornoArquivo
					+ acertoDeCamposFor(recebido.getcFone1() != null ? recebido.getcFone1() : "", 20);

			if (recebido.getdFone2() == null || recebido.getdFone2().isEmpty()) {
				stringRetornoArquivo = stringRetornoArquivo + "   ";
			} else {
				stringRetornoArquivo = stringRetornoArquivo + "0"
						+ recebido.getdFone2();
			}

			if (recebido.getnFone2() == null ||recebido.getnFone2().isEmpty()) {
				stringRetornoArquivo = stringRetornoArquivo + "               ";
			} else {
				stringRetornoArquivo = stringRetornoArquivo
						+ acertoDeCamposFor(recebido.getnFone2(), 15);
			}

			if (recebido.getcFone2() == null || recebido.getcFone2().isEmpty()) {
				stringRetornoArquivo = stringRetornoArquivo
						+ "                    ";
			} else {
				stringRetornoArquivo = stringRetornoArquivo
						+ acertoDeCamposFor(recebido.getcFone2(), 20);
			}

			if (recebido.getdFone3() == null || recebido.getdFone3().isEmpty()) {
				stringRetornoArquivo = stringRetornoArquivo + "   ";
			} else {
				stringRetornoArquivo = stringRetornoArquivo + "0"
						+ recebido.getdFone3();
			}

			if (recebido.getnFone3() == null || recebido.getnFone3().isEmpty()) {
				stringRetornoArquivo = stringRetornoArquivo + "               ";
			} else {
				stringRetornoArquivo = stringRetornoArquivo
						+ acertoDeCamposFor(recebido.getnFone3(), 15);
			}

			if (recebido.getcFone3() == null || recebido.getcFone3().isEmpty()) {
				stringRetornoArquivo = stringRetornoArquivo
						+ "                    ";
			} else {
				stringRetornoArquivo = stringRetornoArquivo
						+ acertoDeCamposFor(recebido.getcFone3(), 20);
			}

			stringRetornoArquivo = stringRetornoArquivo
					+ acertoDeCamposFor(recebido.getEmail() != null ? recebido.getEmail() : "", 50);

			// C Plano
			stringRetornoArquivo = stringRetornoArquivo + "000000001";

		
			
//			// Data adesao
//			stringRetornoArquivo = stringRetornoArquivo + "  ";

			simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar calAdesao = Calendar.getInstance();
			String dataAdesao = simpleDateFormat.format(calAdesao.getTime());

			stringRetornoArquivo = stringRetornoArquivo + dataAdesao;
			
			
			/*
			 * 
			 * 
			 */
			
			
			if (recebido.getDtCancelamento() == null) {
				stringRetornoArquivo = stringRetornoArquivo + "          ";
			} else {
				stringRetornoArquivo = stringRetornoArquivo
						+ recebido.getDtCancelamento();
			}

			if (recebido.getcMotivoCan() == null) {
				stringRetornoArquivo = stringRetornoArquivo + "  ";
			} else {
				stringRetornoArquivo = stringRetornoArquivo
						+ recebido.getcMotivoCan();
			}

			
			stringRetornoArquivo = stringRetornoArquivo
					+ acertoDeCamposFor(recebido
							.getRecebidoSouSuperSeguroCobranca().getNmCobr(),
							70);

			simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String calDtNascCobr = simpleDateFormat.format(recebido
					.getRecebidoSouSuperSeguroCobranca().getDtNascCobr().getTime());

			stringRetornoArquivo = stringRetornoArquivo + calDtNascCobr;

			
			/*
			 * cpf cobranca
			 */
			
			if(recebido.getRecebidoSouSuperSeguroCobranca().getCpfCobr().length() < 11) {
				String cpfCobranca = recebido.getRecebidoSouSuperSeguroCobranca().getCpfCobr();	
				for( int i = 0; i < (11 - recebido.getRecebidoSouSuperSeguroCobranca().getCpfCobr().length()) ; i++) {
					cpfCobranca = "0" + cpfCobranca;
				}
				
				stringRetornoArquivo = stringRetornoArquivo
						+ cpfCobranca;
				
			} else {
				stringRetornoArquivo = stringRetornoArquivo
						+ recebido.getRecebidoSouSuperSeguroCobranca().getCpfCobr();
			}
			
			
			
			/////////
			
			stringRetornoArquivo = stringRetornoArquivo
					+ acertoDeCamposFor(recebido.getRecebidoSouSuperSeguroCobranca().getrLogradCobr(), 50);

			stringRetornoArquivo = stringRetornoArquivo
					+ acertoDeCamposFor(recebido.getRecebidoSouSuperSeguroCobranca().getrNumeroCobr(), 5);

			stringRetornoArquivo = stringRetornoArquivo
					+ acertoDeCamposFor(recebido.getRecebidoSouSuperSeguroCobranca().getCompCobr() != null ? recebido.getRecebidoSouSuperSeguroCobranca().getCompCobr() : "", 15);

			stringRetornoArquivo = stringRetornoArquivo
					+ acertoDeCamposFor(recebido.getRecebidoSouSuperSeguroCobranca().getBairroCobr(), 30);

			stringRetornoArquivo = stringRetornoArquivo
					+ acertoDeCamposFor(recebido.getRecebidoSouSuperSeguroCobranca().getCidadeCobr(), 30);

			stringRetornoArquivo = stringRetornoArquivo
					+ acertoDeCamposFor(recebido.getRecebidoSouSuperSeguroCobranca().getIdCidadeCobr().toString(), 7);

			stringRetornoArquivo = stringRetornoArquivo + recebido.getRecebidoSouSuperSeguroCobranca().getUfCobr();
			stringRetornoArquivo = stringRetornoArquivo + recebido.getRecebidoSouSuperSeguroCobranca().getCepCobr();
			
			
			
			stringRetornoArquivo = stringRetornoArquivo + "0"
					+ recebido.getRecebidoSouSuperSeguroCobranca().getdFone1();

			stringRetornoArquivo = stringRetornoArquivo
					+ acertoDeCamposFor(recebido.getRecebidoSouSuperSeguroCobranca().getnFone1(), 15);

			stringRetornoArquivo = stringRetornoArquivo
					+ acertoDeCamposFor(recebido.getRecebidoSouSuperSeguroCobranca().getcFone1() != null ? recebido.getRecebidoSouSuperSeguroCobranca().getcFone1() : "", 20);

			if (recebido.getdFone2() == null || recebido.getdFone2().isEmpty()) {
				stringRetornoArquivo = stringRetornoArquivo + "   ";
			} else {
				stringRetornoArquivo = stringRetornoArquivo + "0"
						+ recebido.getRecebidoSouSuperSeguroCobranca().getdFone2();
			}

			if (recebido.getnFone2() == null || recebido.getnFone2().isEmpty()) {
				stringRetornoArquivo = stringRetornoArquivo + "               ";
			} else {
				stringRetornoArquivo = stringRetornoArquivo
						+ acertoDeCamposFor(recebido.getRecebidoSouSuperSeguroCobranca().getnFone2(), 15);
			}

			if (recebido.getcFone2() == null || recebido.getcFone2().isEmpty()) {
				stringRetornoArquivo = stringRetornoArquivo
						+ "                    ";
			} else {
				stringRetornoArquivo = stringRetornoArquivo
						+ acertoDeCamposFor(recebido.getRecebidoSouSuperSeguroCobranca().getcFone2(), 20);
			}

			if (recebido.getdFone3() == null || recebido.getdFone3().isEmpty()) {
				stringRetornoArquivo = stringRetornoArquivo + "   ";
			} else {
				stringRetornoArquivo = stringRetornoArquivo + "0"
						+ recebido.getRecebidoSouSuperSeguroCobranca().getdFone3();
			}

			if (recebido.getnFone3() == null || recebido.getnFone3().isEmpty()) {
				stringRetornoArquivo = stringRetornoArquivo + "               ";
			} else {
				stringRetornoArquivo = stringRetornoArquivo
						+ acertoDeCamposFor(recebido.getRecebidoSouSuperSeguroCobranca().getnFone3(), 15);
			}

			if (recebido.getcFone3() == null || recebido.getcFone3().isEmpty()) {
				stringRetornoArquivo = stringRetornoArquivo
						+ "                    ";
			} else {
				stringRetornoArquivo = stringRetornoArquivo
						+ acertoDeCamposFor(recebido.getRecebidoSouSuperSeguroCobranca().getcFone3(), 20);
			}
			
			
			stringRetornoArquivo = stringRetornoArquivo
					+ acertoDeCamposFor(recebido.getRecebidoSouSuperSeguroCobranca().getEmail() != null ? recebido.getRecebidoSouSuperSeguroCobranca().getEmail() : "", 50);
			
			
			//dia do vencimento
			stringRetornoArquivo = stringRetornoArquivo + "  ";
			
			
			simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataInicioCobranca = Calendar.getInstance();
			String inicioCobranca = simpleDateFormat.format(dataInicioCobranca.getTime());
			
			stringRetornoArquivo = stringRetornoArquivo + inicioCobranca;
			
			
			String numeroBanco = (recebido.getRecebidoSouSuperSeguroPagamentoMensalidade().getNroBanco() != null ? String.valueOf(recebido.getRecebidoSouSuperSeguroPagamentoMensalidade().getNroBanco().getBanco()) : "   ");
			stringRetornoArquivo = stringRetornoArquivo + numeroBanco;
			
			String numeroAgencia = (recebido.getRecebidoSouSuperSeguroPagamentoMensalidade().getNroAgencia() != null ? acertoDeCamposForComZeros(recebido.getRecebidoSouSuperSeguroPagamentoMensalidade().getNroAgencia(), 4) : "    "); 
			stringRetornoArquivo = stringRetornoArquivo + numeroAgencia;
			
			String dvAgencia = (recebido.getRecebidoSouSuperSeguroPagamentoMensalidade().getDvAgencia() != null ? recebido.getRecebidoSouSuperSeguroPagamentoMensalidade().getDvAgencia() : " ");
			stringRetornoArquivo = stringRetornoArquivo + dvAgencia;
			
			String contaCorrente = (recebido.getRecebidoSouSuperSeguroPagamentoMensalidade().getcCorrente() != null ? acertoDeCamposForComZeros(recebido.getRecebidoSouSuperSeguroPagamentoMensalidade().getcCorrente(), 14) : "              ");
			stringRetornoArquivo = stringRetornoArquivo + contaCorrente;
			
			String dvConta = (recebido.getRecebidoSouSuperSeguroPagamentoMensalidade().getDvConta() != null ? recebido.getRecebidoSouSuperSeguroPagamentoMensalidade().getDvConta() : " ");
			stringRetornoArquivo = stringRetornoArquivo + dvConta;
			
			
			stringRetornoArquivo = stringRetornoArquivo + recebido.getRecebidoSouSuperSeguroPagamentoMensalidade().getTpCobr().getTipoCobranca();
			
			stringRetornoArquivo = stringRetornoArquivo + acertoDeCamposFor(recebido.getRecebidoSouSuperSeguroPagamentoMensalidade().getNmTitCorrente(), 70);
			
			/*
			 * 
			 * CPF mensalidade
			 * 
			 */
			
			if(recebido.getRecebidoSouSuperSeguroPagamentoMensalidade().getCpfTitCorrente().length() < 11) {
				String cpfCobranca = recebido.getRecebidoSouSuperSeguroPagamentoMensalidade().getCpfTitCorrente();	
				for( int i = 0; i < (11 - recebido.getRecebidoSouSuperSeguroPagamentoMensalidade().getCpfTitCorrente().length()) ; i++) {
					cpfCobranca = "0" + cpfCobranca;
				}
				
				stringRetornoArquivo = stringRetornoArquivo
						+ cpfCobranca;
				
			} else {
				stringRetornoArquivo = stringRetornoArquivo
						+ recebido.getRecebidoSouSuperSeguroPagamentoMensalidade().getCpfTitCorrente();
			}
			
			
			stringRetornoArquivo = recebido.getRecebidoSouSuperSeguroPagamentoMensalidade().getcParentescoCobr() != null ? 
					stringRetornoArquivo + recebido.getRecebidoSouSuperSeguroPagamentoMensalidade().getcParentescoCobr().getParentesco() : stringRetornoArquivo + "  ";
					
			
			//Tp Negociacao
			stringRetornoArquivo = stringRetornoArquivo + "1";
			
			//Tp comissao
			stringRetornoArquivo = stringRetornoArquivo + "00";
			
		
			
			stringRetornoArquivo = stringRetornoArquivo + System.getProperty("line.separator");	
						
		}

		
		
		//C tipo registro
		String trailer  = "9";
		
		//Numero total de arquivos
		String quantRecebidos = "";
		int tamanhoListaDeArquivos = String.valueOf(listaRecebidos.size()).length();
		for(int i = 0; i < (10 - tamanhoListaDeArquivos); i++) {
			quantRecebidos = "0" + quantRecebidos;
		}
		trailer = trailer + quantRecebidos + (listaRecebidos.size() + 2); 
		
		
		
		for(int i = 0; i < 1140 ; i++) {
			trailer = trailer + " ";
		}
	
		return header + stringRetornoArquivo + trailer;
	}

	
	
	
	
	
	
	
	
	
	private String acertoDeCamposFor(String campo, int numeroDeColunas) {

			
		if(campo == null || campo.isEmpty()) {
			for (int j = 0; j < numeroDeColunas; j++) {
				campo = campo + " ";
			}
			
			return campo;
			
		} else {
			
			int contagem = numeroDeColunas - campo.length();
			for (int j = 0; j < contagem; j++) {
				campo = campo + " ";
			}

			return campo;
			
		}
	}
	
	private String acertoDeCamposForComZeros(String campo, int numeroDeColunas) {

		
		if(campo == null || campo.isEmpty()) {
			for (int j = 0; j < numeroDeColunas; j++) {
				campo = "0" + campo;
			}
			
			return campo;
			
		} else {
			
			int contagem = numeroDeColunas - campo.length();
			for (int j = 0; j < contagem; j++) {
				campo = "0" + campo;
			}

			return campo;
			
		}
	}

}
