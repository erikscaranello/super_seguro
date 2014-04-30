package br.com.sousuperseguro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	
	@RequestMapping("/login")
	public ModelAndView index() {
		
		ModelAndView modelAndView = new ModelAndView("login/index");
		return modelAndView;
	}
}
