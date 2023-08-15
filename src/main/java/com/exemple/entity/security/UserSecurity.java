package com.exemple.entity.security;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class UserSecurity implements UserDetails {

	private static final long serialVersionUID = 1L;

	private final Optional<Usuario> usuario;

	/*
	 * Trabalha com autoridades (roles) nesse exemplo usa apenas 1
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(usuario.get().getRole()));
	}

	@Override
	public String getPassword() {
		return usuario.orElse(new Usuario()).getPassword();
	}

	@Override
	public String getUsername() {
		return usuario.orElse(new Usuario()).getLogin();
	}

	// Não utilizado
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// Não utilizado
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// Não utilizado
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// Não utilizado
	@Override
	public boolean isEnabled() {
		return true;
	}

}
