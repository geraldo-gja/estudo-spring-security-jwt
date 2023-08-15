package com.exemple.service.security;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.exemple.entity.Usuario;

@Service
public class TokenService {

	public String gerarToken(Usuario usuario) {
		
		String token = JWT.create()
				.withIssuer("Produtos")
				.withSubject(usuario.getUsername())
				.withClaim("id", usuario.getId())
				.withExpiresAt(LocalDateTime.now()   //vai esperar o token por 10 minutos
						.plusMinutes(10)
				//		.plusSeconds(30)   //apenas para testar
						.toInstant(ZoneOffset.of("-03:00")))
				.sign(Algorithm.HMAC256("secreta"));
		
		return token;
	}
	
	public String getSubject(String token) {
		
		String subject = JWT.require(Algorithm.HMAC256("secreta"))
				.withIssuer("Produtos")
				.build()
				.verify(token) //verifica a expiração
				.getSubject();
		
		return subject;
	}
}
