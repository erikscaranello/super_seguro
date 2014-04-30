package br.com.sousuperseguro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.sousuperseguro.enums.Categoria;
import br.com.sousuperseguro.enums.Status;

@Controller
public class PegarEnums {
	
	
	@RequestMapping("/status")
	public @ResponseBody Status[] pegarStatus() {
		
		return Status.values();
	}
	
	@RequestMapping("/categoria")
	public @ResponseBody Categoria[] pegarCategoria() {
		
		return Categoria.values();
	}
}
