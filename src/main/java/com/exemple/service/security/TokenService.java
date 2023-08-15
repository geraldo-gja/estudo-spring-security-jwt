package com.exemple.service.security;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.exemple.entity.security.Usuario;

@Service
public class TokenService {

	public static final int TOKEN_EXPIRACAO = 10;
	public static final String TOKEN_SENHA = "Estudo-Spring-Security";
	
	public String gerarToken(Usuario usuario) {
		
		String token = JWT.create()
				.withIssuer("Produtos")
				.withSubject(usuario.getLogin())
				.withClaim("id", usuario.getId())
				.withExpiresAt(LocalDateTime.now()   //vai esperar o token por 10 minutos
						.plusMinutes(TOKEN_EXPIRACAO)
						.toInstant(ZoneOffset.of("-03:00")))
				.sign(Algorithm.HMAC512(TOKEN_SENHA));
		
		return token;
	}
	
	public String getSubject(String token) {
		
		String subject = JWT.require(Algorithm.HMAC512(TOKEN_SENHA))
				.withIssuer("Produtos")
				.build()
				.verify(token) //verifica a expiração
				.getSubject();
		
		return subject;
	}
}
