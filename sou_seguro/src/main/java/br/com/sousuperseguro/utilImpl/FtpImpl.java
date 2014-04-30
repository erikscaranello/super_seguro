package br.com.sousuperseguro.utilImpl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.SocketException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sousuperseguro.entities.ArquivosEnvio;
import br.com.sousuperseguro.entities.RecebidoSouSuperSeguro;
import br.com.sousuperseguro.service.ArquivosEnvioService;
import br.com.sousuperseguro.util.Ftp;
import br.com.sousuperseguro.util.LeituraDeArquivo;
import br.com.sousuperseguro.util.MontagemDeArquivo;

@Component
public class FtpImpl implements Ftp {
	
	@Autowired
	MontagemDeArquivo montagemDeArquivo;
	
	@Autowired
	ArquivosEnvioService arquivosEnvioService;
	
	@Autowired
	LeituraDeArquivo leituraDeArquivo;
	
	
	@Override
	public void enviarArquivosFtpCliente() {
		
		List<RecebidoSouSuperSeguro> listaRecebidos = arquivosEnvioService.selecionarRecebidosSuperSeguro();
        
        if(! listaRecebidos.isEmpty()) {
        	
        	FTPClient ftp = new FTPClient();
    		
    		try {
    			ftp.connect("ftp2.odontoprev.com.br");
    			
    			if( FTPReply.isPositiveCompletion( ftp.getReplyCode() ) ) {  
                    ftp.login( "superseg.bdpf", "$3gur@bdpf%" );  
                    
                    String retornoArquivoMontado = montagemDeArquivo.montarArquivoDeEnvio(listaRecebidos);
                    ArquivosEnvio ultimoArquivoEnviado = arquivosEnvioService.obterUltimoArquivoDeEnvio();
                    
                    
                    
                    String nomeDoArquivo = "";
                    if(ultimoArquivoEnviado == null) {
                    	nomeDoArquivo = "00000001";
                    } else {
                    	
                    	BigInteger novoId = ultimoArquivoEnviado.getId().add(new BigInteger("1"));
                    	String idString = novoId.toString();
                    	
                    	for(int i = idString.length(); i < 8; i++) {
                    		idString = "0" + idString;
                    	}
                    	
                    	nomeDoArquivo = idString;
                    }
     
                	ArquivosEnvio arquivoEnvioInsert = new ArquivosEnvio();
                    
                    Calendar cal = new GregorianCalendar();  
                    
                    arquivoEnvioInsert.setDataArquivo(cal);
                    arquivoEnvioInsert.setNomeArquivo(nomeDoArquivo);
                     
                    try {
                    	
                        String nomeDoArquivoFinal = "SSCCD" + nomeDoArquivo + ".#01";      
                        
                        
//                        OutputStream os = new FileOutputStream("C:\\Users\\Erik Scaranello\\Documents\\" + nomeDoArquivoFinal);
//                        OutputStreamWriter osw = new OutputStreamWriter(os);
//                        BufferedWriter bw = new BufferedWriter(osw);
//                    
//                        bw.write(retornoArquivoMontado);
//                        
//                        bw.close();
                        
                        
//                        for(RecebidoSouSuperSeguro recebido :listaRecebidos) {
//                    		recebido.setEnviado(true);
//                    		arquivosEnvioService.insertRecebidoEnviado(recebido);
//                    	}
                        
                        InputStream readerInputStream = new ByteArrayInputStream(retornoArquivoMontado.getBytes());
                                            
                        if(ftp.storeFile(nomeDoArquivoFinal, readerInputStream)) {
                        	
                        	arquivosEnvioService.insertNovoArquivo(arquivoEnvioInsert);
                        	
                        	for(RecebidoSouSuperSeguro recebido :listaRecebidos) {
                        		recebido.setEnviado(true);
                        		arquivosEnvioService.insertRecebidoEnviado(recebido);
                        	}
                        	
                        	System.out.println("Arquivo enviado");
                        	
                        } else {
                        	System.out.println("Erro");
                        }
                        
                        ftp.disconnect();
                        
                    } catch (Exception e) {
                    	e.printStackTrace();
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

	
	
	@Override
	public void receberArquivosFtpCliente() {
		
		FTPClient ftp = new FTPClient();
		
		try {
			ftp.connect("ftp2.odontoprev.com.br");
			
			if( FTPReply.isPositiveCompletion( ftp.getReplyCode() ) ) {  
                ftp.login( "superseg.bdpf", "$3gur@bdpf%" );  
                
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
