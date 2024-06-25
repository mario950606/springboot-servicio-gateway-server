package com.formacionbdi.springboot.app.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SpringSecurityConfig {

	@Bean
	public SecurityWebFilterChain configure(ServerHttpSecurity http){
		return http.authorizeExchange()
				.pathMatchers("/api/security/oauth/**").permitAll()              //acceso publico
				.pathMatchers(HttpMethod.GET, "/api/productos/listar",
						"/api/items/listar",
						"/api/usuarios/usuarios",
						"/api/items/ver/{id}/cantidad/{cantidad}",
						"/api/productos/ver/{id}").permitAll()
				.pathMatchers(HttpMethod.GET, "/api/usuarios/usuarios/{id}").hasAnyRole("ADMIN","USER")
				.pathMatchers("/api/productos/**", "/api/items/**","/api/usuarios/**").hasRole("ADMIN")
				.anyExchange().authenticated()                  //todas las rutas protegidas
				.and().csrf().disable()                         //deshabilita csrf jsp plantillas
				.build();      
	}
	
	
}
