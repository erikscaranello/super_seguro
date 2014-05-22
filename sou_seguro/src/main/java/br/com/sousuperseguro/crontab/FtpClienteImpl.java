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
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
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
	
	static Logger logger = Logger.getLogger(FtpClienteImpl.class);

	@Scheduled(cron = "30 22 * * * *")
	public void executar() {		
		BasicConfigurator.configure();  
        
//		FileAppender fileAppender = new RollingFileAppender();
//		fileAppender.setLayout(new PatternLayout(PatternLayout.TTCC_CONVERSION_PATTERN));
//		fileAppender.setFile(localServidor + "\\sou_seguro\\WEB-INF\\logs\\logEnvioBradesco.log");
		
//		logger.addAppender(fileAppender);
		
		logger.setLevel(Level.INFO);
		
		
		Date d = new Date();
		Calendar calendario = new GregorianCalendar();
		calendario.setTime(d);
		
		logger.info("Iniciou execução envio de arquivo para cliente na data: "
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

					try {

						String nomeDoArquivoFinal = "SSCCD" + nomeDoArquivo
								+ ".#01";

						InputStream readerInputStream = new ByteArrayInputStream(
								retornoArquivoMontado.getBytes());

						if (ftp.storeFile(nomeDoArquivoFinal, readerInputStream)) {

							arquivosEnvioService
									.insertNovoArquivo(arquivoEnvioInsert);

							for (RecebidoSouSuperSeguro recebido : listaRecebidos) {
								recebido.setEnviado(true);
								arquivosEnvioService
										.insertRecebidoEnviado(recebido);
							}

							logger.info("Arquivo enviado");
							
//							saida = saida + "Arquivo enviado" + " ___ ";
							

						} else {
							logger.error("Erro");
//							saida = saida + "Erro local" + " ___ ";
						}

						ftp.disconnect();

					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					logger.error("Conexao recusada");
					ftp.disconnect();
					System.exit(1);
				}

			} catch (SocketException e) {
				logger.error("penultimo erro");
				e.printStackTrace();
			} catch (IOException e) {
				logger.error("ultimo erro");
				e.printStackTrace();
			}
		}
		
	}

}
