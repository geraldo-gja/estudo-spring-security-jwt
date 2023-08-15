package com.exemple.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exemple.repository.UsuarioRepository;

@Service
public class AuthenticationServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
//		Optional<Usuario> usuario = usuarioRepository.findByLogin(username);
//		if( usuario.isEmpty() ) {
//			throw new UsernameNotFoundException("Usuário [" + username + "] não encontrado.");
//		}
//		return new UserSecurity(usuario);
		
		return usuarioRepository.findByLogin(username);
	}

}
