package br.com.sousuperseguro.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.sousuperseguro.entities.Users;
import br.com.sousuperseguro.service.UsersService;
import br.com.sousuperseguro.util.MD5;

@Controller
public class RecuperacaoDeSenhaController {
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	MD5 gerarMd5;
	
	@RequestMapping("/recuperacao_de_senha")
	public ModelAndView index() {
		
		
		ModelAndView modelAndView = new ModelAndView("recuperacaoDeSenha/index");
		return modelAndView;
	}
	
	@RequestMapping("/recuperacao_de_senha/recuperar")
	public @ResponseBody boolean recuperar(HttpServletRequest request) {
		
		return usersService.verificarEmail(request.getParameter("email"));
	
	}
	
	@RequestMapping(value="/recuperacao_de_senha/nova_senha")
	public ModelAndView novaSenha(HttpServletRequest request) {
		
		boolean retornoBoolean = usersService.verificarRecolocacaoDeSenha(request.getParameter("email"));
		
		if(retornoBoolean == true) {
			
			Users user = usersService.obterUserporEmail(request.getParameter("email"));
			
			ModelAndView modelAndView = new ModelAndView("recuperacaoDeSenha/escrever_nova_senha");
			
			modelAndView.addObject("user", user);
			return modelAndView;
			
		} else {
			ModelAndView modelAndView = new ModelAndView("recuperacaoDeSenha/erro");
			return modelAndView;
		}

	}
	
	
	@RequestMapping(value="/recuperacao_de_senha/escrever_nova_senha")
	public @ResponseBody boolean escreverNovaSenha(HttpServletRequest request) {
		
		Users usuario = usersService.obterUser(request.getParameter("username"));
		
		usuario.setPassword(gerarMd5.gerarMd5(request.getParameter("senha")));
		
		return usersService.inserirUser(usuario);
	}
	
}
