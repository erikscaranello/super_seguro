package br.com.sousuperseguro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sousuperseguro.service.UsersService;

@Controller
public class AlterarSuasInformacoes {
	
	@Autowired
	UsersService userService;
	
	@RequestMapping("/alterar_proprias_infos")
	public ModelAndView index() {
		
		ModelAndView modelAndView = new ModelAndView("alterarSuasInformacoes/index");
		
		modelAndView.addObject("usuarioLogado", userService.obterUsuarioLogado());
		return modelAndView;
	}
}
