package br.com.sousuperseguro.utilImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sousuperseguro.entities.RecebidoSouSuperSeguro;
import br.com.sousuperseguro.entities.recusadas.RecebidoSouSuperSeguroRecusada;
import br.com.sousuperseguro.repository.UploadDeArquivosRepository;
import br.com.sousuperseguro.util.LeituraDeArquivo;
import br.com.sousuperseguro.util.StringParaArray;

@Component
public class LeituraDeArquivoImpl implements LeituraDeArquivo {

	@Autowired
	UploadDeArquivosRepository uploadDeArquivosRepository;
	
	@Autowired
	StringParaArray stringParaArray;
	
	@Override
	public void lerLinha(String linha) {
		if(linha != null) {
			
			if(! linha.substring(0, 1).equals("0") && ! linha.substring(0, 1).equals("9")) {
				
				if(!linha.substring(147, 151).equals("0000")) {
					String cpf = linha.substring(127, 138);
					RecebidoSouSuperSeguro recebido = uploadDeArquivosRepository.obterRecebidoPorCpf(cpf);
					
					if(recebido != null) {
						RecebidoSouSuperSeguroRecusada recebidoRecusado = stringParaArray.paraRecusados(recebido);
						recebidoRecusado.setRecebidoBradesco(true);
						recebidoRecusado.setCodigoErro(linha.substring(147, 151));
						
						uploadDeArquivosRepository.delete(recebido);
						uploadDeArquivosRepository.insertDados(recebidoRecusado);
					}
				}
			}
		} 
	}


}