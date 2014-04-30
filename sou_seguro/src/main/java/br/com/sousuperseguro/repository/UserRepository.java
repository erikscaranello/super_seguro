package br.com.sousuperseguro.repository;

import java.util.List;

import br.com.sousuperseguro.entities.Role;
import br.com.sousuperseguro.entities.Users;

public interface UserRepository {
	
	Users verificarEmail(String email);
		
	List<Users> obterListaDeUsuarios();

	Users verificarUsername(String username);
	
	boolean inserirUser(Users user);
	
	boolean deleteUser(Users user);

	Role selecionarRolePorAutoridade(String authority);

	void updateUser(Users user);

	Users obterUserporEmail(String email);
}
