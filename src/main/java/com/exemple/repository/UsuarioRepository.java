package com.exemple.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemple.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	//public Optional<Usuario> findByLogin(String login);
	public Usuario findByLogin(String login);
}
