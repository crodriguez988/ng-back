package com.nextgame.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nextgame.entities.UserPrincipal;
import com.nextgame.entities.Utilisateur;
import com.nextgame.repositories.IUtilisateurRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	IUtilisateurRepository iUtilisateurRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Utilisateur utilisateur =  iUtilisateurRepository.findByEmail(email);
		
		if(utilisateur == null) {
			throw new UsernameNotFoundException("Utilisateur non trouvé avec le email " + email);
		}
		return new UserPrincipal(utilisateur);
	}
}