package br.com.sousuperseguro.service;

import java.util.List;

import br.com.sousuperseguro.entities.Users;

public interface UsersService {
	
	Users obterUser(String login);
	
	boolean verificarEmail(String email);
	
	boolean verificarEmailInsercao(String email);
	
	List<Users> obterListaDeUsuarios();
	
	boolean verificarUsername(String username);
	
	boolean inserirUser(Users user);
	
	boolean deleteUser(String username);

	Users obterUsuarioLogado();

	boolean verificarRecolocacaoDeSenha(String email);

	Users obterUserporEmail(String email);
}
