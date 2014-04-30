package br.com.sousuperseguro.service;

import java.util.List;

import org.apache.commons.fileupload.FileItem;

import br.com.sousuperseguro.entities.recusadas.RecebidoSouSuperSeguroRecusada;

public interface UploadDeArquivosService {
	
	void fazerUpload(List<FileItem> items);

	void fazerUpload(RecebidoSouSuperSeguroRecusada retornoNovaEntidade);
}
