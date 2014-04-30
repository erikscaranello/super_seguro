package br.com.sousuperseguro.util;

import java.util.List;

import org.apache.commons.fileupload.FileItem;

import br.com.sousuperseguro.entities.recusadas.RecebidoSouSuperSeguroRecusada;

public interface UploadArquivosUtil {
	
	public void fazerUpload(List<FileItem> itens);

	public void fazerUpload(RecebidoSouSuperSeguroRecusada retornoNovaEntidade);
	
}
