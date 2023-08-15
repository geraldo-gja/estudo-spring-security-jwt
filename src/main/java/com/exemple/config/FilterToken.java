package com.exemple.config;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exemple.entity.security.UserSecurity;
import com.exemple.entity.security.Usuario;
import com.exemple.repository.UsuarioRepository;
import com.exemple.service.security.TokenService;

@Component
public class FilterToken extends OncePerRequestFilter {

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token;
		String authorizationHeader = request.getHeader("Authorization");
		
		if (authorizationHeader != null) {
			token = authorizationHeader.replace("Bearer ", "");   //remover o nome Bearer que vem no início do token
			String subject = this.tokenService.getSubject(token);
			
			Optional<Usuario> user = this.usuarioRepository.findByLogin(subject);
			UserSecurity usuario = new UserSecurity(user);
			Collection authorities = usuario.getAuthorities();
			
			//authentica usuário e roles
			UsernamePasswordAuthenticationToken authentication =
					new UsernamePasswordAuthenticationToken(usuario, null, authorities);
			
			//após a autenticação, informa ao Spring que o usuário está logado
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}	
		filterChain.doFilter(request, response);	
	}

}
