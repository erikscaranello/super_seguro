package br.com.sousuperseguro.utilImpl;

import java.util.Iterator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.sousuperseguro.util.VerificarRole;

@Component
public class VerificarRoleImpl implements VerificarRole{

	@Override
	public boolean verificar() {
		Iterator<? extends GrantedAuthority> iterator = SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator(); 
		
		while(iterator.hasNext()) {
			GrantedAuthority next = iterator.next();
			if(next.getAuthority().equals("ROLE_ADMIN")) {
				return true;
			}
		}
		
		return false;
	}

}
