package br.com.sousuperseguro.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.sousuperseguro.entities.InfosPessoais;
import br.com.sousuperseguro.entities.Role;
import br.com.sousuperseguro.entities.Users;
import br.com.sousuperseguro.service.UsersService;
import br.com.sousuperseguro.util.MD5;

@Controller
public class UsuariosController {
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	MD5 gerarMd5;
	
	
	@RequestMapping("/usuarios")
	public ModelAndView listaDeUsuarios() {
				
		List<Users> resultado = usersService.obterListaDeUsuarios();
		
		ModelAndView modelAndView = new ModelAndView("usuarios/lista");
		modelAndView.addObject("listaUsuarios", resultado);
		return modelAndView;
	}
	
	
	@RequestMapping("/usuarios/novo_usuario")
	public @ResponseBody boolean novoUsuario(HttpServletRequest request) {
		
		Users user = new Users();
		Role role = new Role();
		InfosPessoais infosPessoais = new InfosPessoais();
		
		user.setUsername(request.getParameter("username"));
		
		user.setPassword(gerarMd5.gerarMd5(request.getParameter("password")));
		
		user.setEnabled(true);
		
		role.setAuthority(request.getParameter("role"));
		
		
		infosPessoais.setEmail(request.getParameter("email"));
		infosPessoais.setNome(request.getParameter("nome"));
		infosPessoais.setSobrenome(request.getParameter("sobrenome"));
		
		user.setInfosPessoais(infosPessoais);
		user.setRole(role);
		
		boolean retorno = usersService.inserirUser(user);
		
		
		return retorno;
	}
	
	
	@RequestMapping("/usuarios/alterar_usuario")
	public @ResponseBody Users alterarUsuario(HttpServletRequest request) {
			
		return usersService.obterUser(request.getParameter("username"));
	}
	
	
	@RequestMapping("/usuarios/verificar_login")
	public @ResponseBody boolean verificarLogin(HttpServletRequest request) {
		
		String username = request.getParameter("login");
			
		boolean resultado = usersService.verificarUsername(username);
		
		
		return resultado;
	}
	
	@RequestMapping("/usuarios/excluir_usuario")
	public @ResponseBody boolean excluirUsuario(HttpServletRequest request) {
		
		boolean resultado = usersService.deleteUser(request.getParameter("username"));
		return resultado;
	}
	
	@RequestMapping("/usuarios/verificar_email")
	public @ResponseBody boolean verificarEmail(HttpServletRequest request) {
		
		String email = request.getParameter("email");
			
		boolean resultado = usersService.verificarEmailInsercao(email);
		
		
		return resultado;
	}
}
