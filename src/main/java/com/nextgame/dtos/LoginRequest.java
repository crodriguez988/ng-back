package com.nextgame.dtos;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
	
	@NotBlank(message = "L'email est obligatoire")
	private String email;
	
	@NotBlank(message = "Le mot de passe est obligatoire")
	private String motDePasse;
}
