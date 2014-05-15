package br.com.sousuperseguro.crontab;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
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
	
	@Scheduled(cron="00 08 * * * *")
	public void executar() {
		
		Date d = new Date();  
		Calendar calendario = new GregorianCalendar();  
		calendario.setTime(d);
		System.out.println("Iniciou execução recebimento de arquivo do cliente na data: " + calendario.getTime());
		
		
		FTPClient ftp = new FTPClient();
		
		try {
			ftp.connect("ftp2.odontoprev.com.br");
			
			if( FTPReply.isPositiveCompletion( ftp.getReplyCode() ) ) {  
                ftp.login( "superseg.bdpf", "$3gur@bdpf%" );  
                
                ftp.enterLocalPassiveMode();
                
                List<ArquivosEnvio> arrayArquivosRecebidos = arquivosEnvioService.obterListaNaoRecebidosErro();
 
                for (ArquivosEnvio arquivoRecebido : arrayArquivosRecebidos ) {
                	
                	String nomeDoArquivo = "SSCCR" + arquivoRecebido.getNomeArquivo() + ".#01";
                	
                	
                	FTPFile[] listaDeArquivos = ftp.listFiles(nomeDoArquivo);
                    
                	
                    if(listaDeArquivos.length != 0) {
                    	
                    	for(int i = 0; i < listaDeArquivos.length; i++) {
                    		
                			InputStream is = new FileInputStream(listaDeArquivos[i].getName());
//                			InputStream is = new FileInputStream("C:\\Users\\Erik Scaranello\\SSCCR00000001.#01");
                    		
                    		InputStreamReader isr = new InputStreamReader(is);
                    		BufferedReader br = new BufferedReader(isr);
                    	    
                    	    while (br.readLine() != null) {
                    	    	String linha = br.readLine();
                    	    	leituraDeArquivo.lerLinha(linha);
                    	    }
                    	    
                    	    br.close();
                    	    isr.close();
                    	}
                    	
                    }
                	
                }
            	
			} else {  
                ftp.disconnect();  
                System.out.println("Conexao recusada");  
                System.exit(1);  
            }
			
		} catch (SocketException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	
	
}
