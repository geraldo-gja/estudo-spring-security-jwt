package com.exemple.controller.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exemple.dto.Login;
import com.exemple.entity.security.UserSecurity;
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

		UserSecurity usuario = (UserSecurity) authentication.getPrincipal();
		
		return tokenService.gerarToken(usuario.getUsuario().get());
	}
}
