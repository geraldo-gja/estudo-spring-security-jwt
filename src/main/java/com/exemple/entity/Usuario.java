package com.exemple.entity;


import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String login;
	
	private String password;
	
	private String role;
	
	//TODO - não ta funcionando, testar também com lista de roles
	/* Trabalha com autoridades (roles)
	 * nesse exemplo trabalhamos com apenas 1
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role));
	}

	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public String getUsername() {
		return login;
	}

	//Não utilizado nesse exemplo, por isso devolve true
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//Não utilizado nesse exemplo, por isso devolve true
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//Não utilizado nesse exemplo, por isso devolve true
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//Não utilizado nesse exemplo, por isso devolve true
	@Override
	public boolean isEnabled() {
		return true;
	}


}
