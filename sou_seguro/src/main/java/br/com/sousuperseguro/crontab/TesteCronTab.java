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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.sousuperseguro.entities.ArquivosEnvio;
import br.com.sousuperseguro.entities.RecebidoSouSuperSeguro;
import br.com.sousuperseguro.service.ArquivosEnvioService;
import br.com.sousuperseguro.util.MontagemDeArquivo;

@Controller
public class TesteCronTab {
	
	@Autowired
	MontagemDeArquivo montagemDeArquivo;

	@Autowired
	ArquivosEnvioService arquivosEnvioService;
	
	
	@RequestMapping("/test")
	@ResponseBody
	public String executar() {
		
		String saida = "";
		
	
		Date d = new Date();
		Calendar calendario = new GregorianCalendar();
		calendario.setTime(d);
		
		
		saida = saida + "Iniciou execução envio de arquivo para cliente na data: "
				+ calendario.getTime() + " ___ ";
		
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
					
					
//					ftp.changeWorkingDirectory("Producao");
//					ftp.changeWorkingDirectory("Enviados_Super_Seguro");
					
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
					arquivoEnvioInsert.setRecebidoErro(false);
					
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

							
							saida = saida + "Arquivo enviado" + " ___ ";
							

						} else {
							
							saida = saida + "Erro local" + " ___ ";
						}

						ftp.disconnect();

					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					
					ftp.disconnect();
					System.exit(1);
				}

			} catch (SocketException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		return saida;
		
	}
	
	
	
	
}
