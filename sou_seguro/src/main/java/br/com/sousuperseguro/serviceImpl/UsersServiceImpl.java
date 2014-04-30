package br.com.sousuperseguro.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.sousuperseguro.entities.Role;
import br.com.sousuperseguro.entities.Users;
import br.com.sousuperseguro.repository.UserRepository;
import br.com.sousuperseguro.service.UsersService;
import br.com.sousuperseguro.util.EnvioDeEmail;

@Service
public class UsersServiceImpl implements UsersService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EnvioDeEmail envioDeEmail;
	
	@Override
	public boolean verificarEmail(String email) {
		if(email.isEmpty() || email == null ){
			return false;
		} 
		
		Users user = userRepository.verificarEmail(email);
		
		if (user == null) {
			return false;
		} else {
			
			user.setRepassword(true);
			userRepository.updateUser(user);
			
			envioDeEmail.enviarEmail(user);
			return true;
		}
	}
	
	
	@Override
	public boolean verificarEmailInsercao(String email) {
		if(email.isEmpty() || email == null ){
			return false;
		} 
		
		Users user = userRepository.verificarEmail(email);
		
		if (user == null) {
			return true;
		} else {
			return false;
		}
	}
	

	@Override
	public List<Users> obterListaDeUsuarios() {
	 	List<Users> listUser = userRepository.obterListaDeUsuarios();
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		for (int i = 0; i < listUser.size(); i++) {
			if( listUser.get(i).getUsername().equals(username) ){
				listUser.remove(i);
			}
		}
		return listUser;
	}

	@Override
	public boolean verificarUsername(String username) {
		Users usuario = userRepository.verificarUsername(username);
		if(usuario != null) {
			return false;
		} else {
			if(username.indexOf(" ") != -1) {
				return false;
			} else {
				return true;
			}
		}
	}

	@Override
	public boolean inserirUser(Users user) {
		Users userExistente = userRepository.verificarUsername(user.getUsername());
	
		if(userExistente != null) {
			user.setId(userExistente.getId());
			
			if(user.getRole().getAuthority() == null || user.getRole().getAuthority().isEmpty()) {
				user.setRole(userExistente.getRole());
			} else {
				Role role = userRepository.selecionarRolePorAutoridade(user.getRole().getAuthority());
				user.setRole(role);
			}
					
			user.getInfosPessoais().setId(userExistente.getInfosPessoais().getId());
		} 
		
		user.setRepassword(false);
		return userRepository.inserirUser(user);
	}


	@Override
	public boolean deleteUser(String username) {
		Users user = userRepository.verificarUsername(username);
		return userRepository.deleteUser(user);
	}


	@Override
	public Users obterUser(String login) {
		return userRepository.verificarUsername(login);
	}


	@Override
	public Users obterUsuarioLogado() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		return userRepository.verificarUsername(username);
	}


	@Override
	public boolean verificarRecolocacaoDeSenha(String email) {
		if(email.isEmpty() || email == null ){
			return false;
		} 
		
		Users user = userRepository.verificarEmail(email);
		
		if (user == null) {
			return false;
		} else {
			
			if(user.getRepassword() != true) {
				return false;
			} else {
				return true;
			}
			
		}
	}


	@Override
	public Users obterUserporEmail(String email) {
		return userRepository.obterUserporEmail(email);
	}
	
}
