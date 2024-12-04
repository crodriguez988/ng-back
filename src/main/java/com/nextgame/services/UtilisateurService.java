package com.nextgame.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nextgame.dtos.LoginRequest;
import com.nextgame.entities.Utilisateur;
import com.nextgame.repositories.IUtilisateurRepository;
import com.nextgame.utils.JwtService;

import jakarta.persistence.EntityNotFoundException;

/**
 * Ce service contient tous les appels CRUD et plus, du repository Utilisateur.
 */
@Service
public class UtilisateurService implements IService<Utilisateur, Long>{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private IUtilisateurRepository utilisateurRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	public Utilisateur save(Utilisateur utilisateur) {
		// Hashage du mot de passe
        if (!StringUtils.isBlank(utilisateur.getMotDePasse())) {
            String mdpHache = passwordEncoder.encode(utilisateur.getMotDePasse());
            utilisateur.setMotDePasse(mdpHache);
        }
        
        if (existeMail(utilisateur.getEmail())) {
            throw new IllegalArgumentException("L'utilisateur avec cet email existe déjà.");
        }
        		
		return utilisateurRepository.save(utilisateur);
	}
	
	/**
	 * Met à jour l'utilisateur passé dans le body.
	 * @param utilisateur
	 * @return utilisateur
	 */
	public Utilisateur update(Utilisateur utilisateur) {
		
		Utilisateur utilisateurSave = new Utilisateur();
		
		if (!utilisateurRepository.existsById(utilisateur.getId())) {
	        throw new EntityNotFoundException("Utilisateur introuvable avec l'id : " + utilisateur.getId());
	    }
		
		if(existeMail(utilisateur.getEmail())) {
			 throw new IllegalArgumentException("Ce adresse email existe est déjà utilisée.");
		}
		
		utilisateurSave.setEmail(utilisateur.getEmail());
		
		utilisateurSave.setNomUtilisateur(utilisateur.getNomUtilisateur());
		
	    return utilisateurRepository.saveAndFlush(utilisateurSave);
	}
	
	/**
	 * Mise à jour du mot de passe pour l'utilisateur
	 * @param id
	 * @param motDePasse
	 * @return Utilisateur
	 */
//	public Utilisateur updateMdp(long id, String motDePasse) {
//		Utilisateur utilisateurSauve = new Utilisateur();
//		if(utilisateurRepository.existsById(id)) {
//			String mdpHache = passwordEncoder.encode(motDePasse);
//			utilisateurSauve.setMotDePasse(mdpHache);
//		}
//		
//		return utilisateurSauve;
//	}
	
	/**
	 * Supprime l'élément correpondant à l'id passé en paramètre.
	 * @param id de l'utilisateur
	 */
	public void delete(Long id) {
		if (!utilisateurRepository.existsById(id)) {
            throw new EntityNotFoundException("Utilisateur introuvable avec l'id : " + id);
        }
        utilisateurRepository.deleteById(id);
	}
	
	/**
	 * Verifie si l'id existe.
	 * @param id
	 * @return boolean
	 */
	public boolean existById(Long id) {
		return utilisateurRepository.existsById(id);
	}
	
	/**
	 * Vérifie si l'adresse existe déjà
	 * @param eMail
	 * @return
	 */
	public boolean existeMail(String eMail) {
		return utilisateurRepository.existsByEmail(eMail);
	}
	
	@Override
	public List<Utilisateur> getAll() {
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		utilisateurs.addAll(utilisateurRepository.findAll());
		return utilisateurs;
	}

	@Override
	public Utilisateur getById(Long id) {
		return utilisateurRepository.getReferenceById(id);
	}
	
	/**
	 * Retourne un JWT Token
	 * @param loginRequest
	 * @return String
	 */
	public String verificactionAuthentification (LoginRequest loginRequest) {
		
		Utilisateur utilisateur = utilisateurRepository.findByEmail(loginRequest.getEmail());
		
		if(utilisateur == null) {
			throw new EntityNotFoundException("Utilisateur introuvable avec le mail : " + loginRequest.getEmail());
		}
		
		Authentication authentication = 
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getMotDePasse()));
		
		if (authentication.isAuthenticated()) {
			return jwtService.genererToken(utilisateur);
		}
		else {
			return "NOT LOGGED";
		}
	}
	
}