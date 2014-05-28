package br.com.sousuperseguro.crontab;

import org.springframework.stereotype.Component;

@Component
public class FtpSuperSeguroImpl{
	
//	@Autowired
//	ArquivosEnvioService arquivosEnvioService;
//	
//	@Autowired
//	LeituraDeArquivo leituraDeArquivo;
//	
//	@Scheduled(cron="00 08 * * * *")
//	public void executar() {
//		
//		Date d = new Date();  
//		Calendar calendario = new GregorianCalendar();  
//		calendario.setTime(d);
//		System.out.println("Iniciou execução recebimento de arquivo do cliente na data: " + calendario.getTime());
//		
//		
//		FTPClient ftp = new FTPClient();
//		
//		try {
//			ftp.connect("ftp2.odontoprev.com.br");
//			
//			if( FTPReply.isPositiveCompletion( ftp.getReplyCode() ) ) {  
//				ftp.enterLocalPassiveMode();
//				
//				//troca de lugares com passive mode
//				ftp.login( "superseg.bdpf", "$3gur@bdpf%" );  
//                
//                List<ArquivosEnvio> arrayArquivosRecebidos = arquivosEnvioService.obterListaNaoRecebidosErro();
// 
//                for (ArquivosEnvio arquivoRecebido : arrayArquivosRecebidos ) {
//                	
//                	String nomeDoArquivo = "SSCCR" + arquivoRecebido.getNomeArquivo() + ".#01";
//                	
//                	
//                	FTPFile[] listaDeArquivos = ftp.listFiles(nomeDoArquivo);
//                    
//                	
//                    if(listaDeArquivos.length != 0) {
//                    	
//                    	for(int i = 0; i < listaDeArquivos.length; i++) {
//                    		
//                			InputStream is = new FileInputStream(listaDeArquivos[i].getName());
////                			InputStream is = new FileInputStream("C:\\Users\\Erik Scaranello\\SSCCR00000001.#01");
//                    		
//                    		InputStreamReader isr = new InputStreamReader(is);
//                    		BufferedReader br = new BufferedReader(isr);
//                    	    
//                    	    while (br.readLine() != null) {
//                    	    	String linha = br.readLine();
//                    	    	leituraDeArquivo.lerLinha(linha);
//                    	    }
//                    	    
//                    	    br.close();
//                    	    isr.close();
//                    	}
//                    	
//                    }
//                	
//                }
//            	
//			} else {  
//                ftp.disconnect();  
//                System.out.println("Conexao recusada");  
//                System.exit(1);  
//            }
//			
//		} catch (SocketException e) {
//			
//			e.printStackTrace();
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
//		
//		
//	}
	
	
	
}
