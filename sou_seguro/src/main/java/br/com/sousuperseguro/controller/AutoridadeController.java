package br.com.sousuperseguro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.sousuperseguro.util.VerificarRole;

@Controller
public class AutoridadeController {
	
	@Autowired
	VerificarRole verificarRole;
	
	@RequestMapping("/verificar_admin")
	public @ResponseBody boolean verificarAdmin() {

		return verificarRole.verificar();
	}
}
