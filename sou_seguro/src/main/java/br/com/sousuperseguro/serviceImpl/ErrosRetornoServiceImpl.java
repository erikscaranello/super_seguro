package br.com.sousuperseguro.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sousuperseguro.entities.ErrosRetorno;
import br.com.sousuperseguro.repository.ErrosRetornoRepository;
import br.com.sousuperseguro.service.ErrosRetornoService;

@Service
public class ErrosRetornoServiceImpl implements ErrosRetornoService{
	
	@Autowired
	ErrosRetornoRepository errosRetornoRepository;
	
	@Override
	public ErrosRetorno obterErro(String codigoErro) {
		return errosRetornoRepository.obterErro(codigoErro);
	}
	
}
