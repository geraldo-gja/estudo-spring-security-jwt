package com.exemple.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.exemple.service.DBService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)   //fazer as anotações das roles funcionarem
@Profile("test")  //não feito no video
public class Configurations {
	
	//não feito no video
	@Autowired
	private DBService dbService;
	
	//não feito no video
	@Bean
	public void instanciaBaseDeDados() {
		this.dbService.instanciaBaseDeDados();
	}
	
	@Autowired
	private FilterToken filter;
	
	/*
	 * Informar as rotas que precisam ou não ser atenticadas
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		SecurityFilterChain security;
		
		security = http.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().authorizeHttpRequests()
				.antMatchers(HttpMethod.POST, "/login")
				.permitAll()
				.antMatchers(HttpMethod.GET, "/home")
				.permitAll()
			//  .anyRequest().authenticated().and()   //utilizar essa linha se não usar o H2-console
					
				/*
				 * INICIO H2-console
				 * liberar o acesso ao Banco H2
				 * trecho de código não disponibilizado no vídeo
				 */
				
				.antMatchers("/h2-console/**") 
	            .permitAll()
	            .anyRequest().authenticated()
	            .and().csrf().disable()
	            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	            .and().headers().frameOptions().sameOrigin()
	            .and()
	            
	            //FIM H2-console
	            
	            .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
	            .build();  //sempre finalizar com esse comando.
		
		
		return security;
	}
		
	/*
	 * Para o Spring injetar a propriedade na classe AuthController
	 */
	@Bean
	public AuthenticationManager authenticationManager 
		(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	/*
	 * Descriptografar a senha no BD 
	 */
	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
