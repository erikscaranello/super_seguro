package br.com.sousuperseguro.crontab;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.SocketException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.sousuperseguro.entities.ArquivosEnvio;
import br.com.sousuperseguro.entities.RecebidoSouSuperSeguro;
import br.com.sousuperseguro.service.ArquivosEnvioService;
import br.com.sousuperseguro.util.MontagemDeArquivo;

@Component
public class FtpClienteImpl {

	@Autowired
	MontagemDeArquivo montagemDeArquivo;

	@Autowired
	ArquivosEnvioService arquivosEnvioService;
	
	@Scheduled(cron = "30 22 * * * *")
	public void executar() {		  
        
		Date d = new Date();
		Calendar calendario = new GregorianCalendar();
		calendario.setTime(d);
		
		System.out.println("Iniciou execução envio de arquivo para cliente na data: "
				+ calendario.getTime());
		
//		saida = saida + "Iniciou execução envio de arquivo para cliente na data: "
//				+ calendario.getTime() + " ___ ";
		
		List<RecebidoSouSuperSeguro> listaRecebidos = arquivosEnvioService
				.selecionarRecebidosSuperSeguro();

		if (!listaRecebidos.isEmpty()) {

			FTPClient ftp = new FTPClient();

			try {
				ftp.connect("ftp2.odontoprev.com.br");

				if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
					ftp.login("superseg.bdpf", "$3gur@bdpf%");

//					ftp.enterRemotePassiveMode();
					ftp.enterLocalPassiveMode();
					
					
					ftp.changeWorkingDirectory("Producao");
					ftp.changeWorkingDirectory("Enviados_Super_Seguro");
					
					String retornoArquivoMontado = montagemDeArquivo
							.montarArquivoDeEnvio(listaRecebidos);
					ArquivosEnvio ultimoArquivoEnviado = arquivosEnvioService
							.obterUltimoArquivoDeEnvio();

					String nomeDoArquivo = "";
					if (ultimoArquivoEnviado == null) {
						nomeDoArquivo = "00000001";
					} else {

						BigInteger novoId = ultimoArquivoEnviado.getId().add(
								new BigInteger("1"));
						String idString = novoId.toString();

						for (int i = idString.length(); i < 8; i++) {
							idString = "0" + idString;
						}

						nomeDoArquivo = idString;
					}

					ArquivosEnvio arquivoEnvioInsert = new ArquivosEnvio();

					Calendar cal = new GregorianCalendar();

					arquivoEnvioInsert.setDataArquivo(cal);
					arquivoEnvioInsert.setNomeArquivo(nomeDoArquivo);

					System.setProperty("file.encoding", "ISO-8859-1");
					
					try {

						String nomeDoArquivoFinal = "SSCCD" + nomeDoArquivo
								+ ".#01";

						InputStream readerInputStream = new ByteArrayInputStream(
								retornoArquivoMontado.getBytes("ISO-8859-1"));

						if (ftp.storeFile(nomeDoArquivoFinal, readerInputStream)) {

							arquivosEnvioService
									.insertNovoArquivo(arquivoEnvioInsert);

							for (RecebidoSouSuperSeguro recebido : listaRecebidos) {
								recebido.setEnviado(true);
								arquivosEnvioService
										.insertRecebidoEnviado(recebido);
							}

							System.out.println("Arquivo enviado");
							
//							saida = saida + "Arquivo enviado" + " ___ ";
							

						} else {
							System.out.println("Erro");
//							saida = saida + "Erro local" + " ___ ";
						}

						ftp.disconnect();

					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("Conexao recusada");
					ftp.disconnect();
					System.exit(1);
				}

			} catch (SocketException e) {
				System.out.println("penultimo erro");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("ultimo erro");
				e.printStackTrace();
			}
		}
		
	}

}
