package com.exemple.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exemple.dto.Login;
import com.exemple.entity.Usuario;
import com.exemple.service.security.TokenService;

@RestController
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired TokenService tokenService;
	
	@PostMapping("/login")
	public String login(@RequestBody Login login) {
		
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
				new UsernamePasswordAuthenticationToken(login.login(), login.password());

		//faz autenticação
		Authentication authentication = this.authenticationManager
				.authenticate(usernamePasswordAuthenticationToken);
		
		//var usuario;  //Não consegui usar o 'var'
		Usuario usuario = (Usuario) authentication.getPrincipal();
		
		String s = tokenService.gerarToken(usuario);
		return s;
	}
}
