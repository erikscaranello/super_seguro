package br.com.sousuperseguro.crontab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import br.com.sousuperseguro.service.ArquivosEnvioService;
import br.com.sousuperseguro.util.LeituraDeArquivo;

@Component
public class FtpSuperSeguroImpl{
	
	@Autowired
	ArquivosEnvioService arquivosEnvioService;
	
	@Autowired
	LeituraDeArquivo leituraDeArquivo;
	
	@Scheduled(cron="00 13 * * * *")
	public void executar() {
		
		Date d = new Date();  
		Calendar calendario = new GregorianCalendar();  
		calendario.setTime(d);
		System.out.println("Iniciou execução recebimento de arquivo do cliente na data: " + calendario.getTime());
		
		FTPClient ftp = new FTPClient();
		
		try {
			ftp.connect("ftp2.odontoprev.com.br");
			
			if( FTPReply.isPositiveCompletion( ftp.getReplyCode() ) ) {  
				//troca de lugares com passive mode
				ftp.login( "superseg.bdpf", "$3gur@bdpf%" );  
    
				ftp.enterLocalPassiveMode();
				
				
				ftp.changeWorkingDirectory("Producao");
				ftp.changeWorkingDirectory("Retorno_ODPV");
				
				
                List<ArquivosEnvio> arrayArquivosRecebidos = arquivosEnvioService.obterListaNaoRecebidosErro();
 
                for (ArquivosEnvio arquivoRecebido : arrayArquivosRecebidos ) {
                	
                	String nomeDoArquivo = "SSCCR" + arquivoRecebido.getNomeArquivo() + ".#01";
                	
                	InputStream is = ftp.retrieveFileStream(nomeDoArquivo);
            		if(is != null) {
            			InputStreamReader isr = new InputStreamReader(is);
                		BufferedReader br = new BufferedReader(isr);
                	    
                	    while (br.readLine() != null) {
                	    	String linha = br.readLine();
                	    	leituraDeArquivo.lerLinha(linha);
                	    }
                	    
                	    br.close();
                	    isr.close();
                	   
            		}
            		
            		arquivoRecebido.setRecebidoErro(true);
            		arquivosEnvioService.updateArquivosParaLido(arquivoRecebido); 
                }
                
                System.out.println(" Arquivo recebido");
            	
			} else {  
                ftp.disconnect();  
                System.out.println(" Conexao recusada"); 
                System.exit(1);  
            }
			
		} catch (SocketException e) {
			System.out.println(" Socket exception");
			
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(" IO Exception");
			e.printStackTrace();
		}	
	}	
}
