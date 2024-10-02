package com.nextgame.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Bean
	public PasswordEncoder encodeurMdp() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.csrf(AbstractHttpConfigurer::disable);
	    //TODO: Trouver une vraie solution pour le CSRF et finir la conf 
//	    .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
//	    	authorizationManagerRequestMatcherRegistry
//	    	.anyRequest().authenticated());
////	      .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
////	              authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
////	                      .requestMatchers("/admin/**").hasAnyRole("ADMIN")
////	                      .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
////	                      .requestMatchers("/login/**").permitAll()
////	                      .anyRequest().authenticated())
////	      .httpBasic(Customizer.withDefaults())
////	      .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	    return http.build();
	}
	
}