package com.nextgame.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService; 
	
	@Bean
	public PasswordEncoder encodeurMdp() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private JWTFilter jwtFilter;
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http.csrf(csrf -> csrf.disable())
		 	.authorizeHttpRequests(authorize -> authorize
            // Requetes GET sans connexion
            .requestMatchers(HttpMethod.GET, "/jeux/**", "/genres/**", "/plateformes/**", "/franchises/**", "/editeurs/**", "/developpeurs/**").permitAll()
            .requestMatchers("/utilisateurs/inscription", "/authentification/login").permitAll()
            // Protection des autres routes
            .anyRequest().authenticated())
     		.httpBasic(Customizer.withDefaults())
     		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
     		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
     		.build();
    }
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
	 
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }	 	
}