package br.com.sousuperseguro.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sousuperseguro.entities.NumeroDocumento;
import br.com.sousuperseguro.repository.NumeroDocumentoRepository;
import br.com.sousuperseguro.service.NumeroDocumentoService;


@Service
public class NumeroDocumentoServiceImpl implements NumeroDocumentoService{

	@Autowired
	NumeroDocumentoRepository numeroDocumentoRepository;
	
	
	@Override
	public NumeroDocumento obterUltimoNumeroDocumento() {
		return numeroDocumentoRepository.obterUltimoNumeroDocumento();
	}


	@Override
	public void insertNumeroDocumento(NumeroDocumento numeroDocumento) {
		numeroDocumentoRepository.insertNumeroDocumento(numeroDocumento);
	}

}
