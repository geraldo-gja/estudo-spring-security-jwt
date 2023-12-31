package com.exemple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.exemple.entity.Product;
import com.exemple.entity.security.Usuario;
import com.exemple.repository.ProductRepository;
import com.exemple.repository.UsuarioRepository;

@Service
public class DBService {

	//OBS: Toda role precisa ter a abreviação 'ROLE_', caso contrário não funciona  
	private static final String ROLE_ADMIN = "ROLE_ADMIN";
	private static final String ROLE_USER = "ROLE_USER";
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public void instanciaBaseDeDados() {
		
		/*
		 *  new BCryptPasswordEncoder().encode("123")
		 *  serve para criptografar a senha
		 */
		Usuario u1 = new Usuario(null, "geraldo", new BCryptPasswordEncoder().encode("123"), ROLE_ADMIN ); 
		Usuario u2 = new Usuario(null, "user", new BCryptPasswordEncoder().encode("123"), ROLE_USER ); 
		this.usuarioRepository.save(u1);
		this.usuarioRepository.save(u2);
		
		Product p1 = new Product(null, "PS5", 4500.00, "Videogame");
		Product p2 = new Product(null, "TV 70", 4800.00, "TV Samsung");
		productRepository.save(p1);
		productRepository.save(p2);
	}
}
