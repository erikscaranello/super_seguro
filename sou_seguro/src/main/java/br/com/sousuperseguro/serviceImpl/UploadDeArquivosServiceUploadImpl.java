package br.com.sousuperseguro.serviceImpl;

import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sousuperseguro.entities.recusadas.RecebidoSouSuperSeguroRecusada;
import br.com.sousuperseguro.repository.UploadDeArquivosRepository;
import br.com.sousuperseguro.service.UploadDeArquivosService;
import br.com.sousuperseguro.util.UploadArquivosUtil;

@Service
public class UploadDeArquivosServiceUploadImpl implements UploadDeArquivosService{

	@Autowired
	UploadDeArquivosRepository uploadDeArquivosRepository;
	
	@Autowired
	UploadArquivosUtil uploadArquivosUtil;
	
	@Override
	public void fazerUpload(List<FileItem> itens) {
		
		uploadArquivosUtil.fazerUpload(itens);
	}

	@Override
	public void fazerUpload(RecebidoSouSuperSeguroRecusada retornoNovaEntidade) {
		
		uploadArquivosUtil.fazerUpload(retornoNovaEntidade);
	}

}
