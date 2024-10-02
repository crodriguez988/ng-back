package com.nextgame.services;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nextgame.entities.Utilisateur;
import com.nextgame.repositories.IUtilisateurRepository;

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
		
		if(existePseudonyme(utilisateur.getPseudonyme())) {
			 throw new IllegalArgumentException("Ce pseudonyme existe déjà.");
		}
		
		utilisateurSave.setPseudonyme(utilisateur.getPseudonyme());
		
	    return utilisateurRepository.saveAndFlush(utilisateurSave);
	}
	
	public Utilisateur updateMdp(long id, String motDePasse) {
		Utilisateur utilisateurSauve = new Utilisateur();
		if(!utilisateurRepository.existsById(id)) {
			String mdpHache = passwordEncoder.encode(motDePasse);
			utilisateurSauve.setMotDePasse(mdpHache);
		}
		
		return utilisateurSauve;
	}
	
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
	
	public boolean existeMail(String eMail) {
		return utilisateurRepository.existsByEmail(eMail);
	}
	
	public boolean existePseudonyme(String pseudo) {
		return utilisateurRepository.existsByEmail(pseudo);
	}

	@Override
	public List<Utilisateur> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur getById(Long id) {
		return utilisateurRepository.getReferenceById(id);
	}
	
	public Utilisateur authentification(String email, String motDePasse) {
        Utilisateur utilisateur = new Utilisateur();
        //Vérification du mail
        if(utilisateurRepository.findByEmail(email) == null) {
        	 throw new IllegalArgumentException("Utilisateur non trouvé"); 
        }
        else {
        	utilisateur = utilisateurRepository.findByEmail(email);
        }

        // Vérification du mot de passe
        if (passwordEncoder.matches(motDePasse, utilisateur.getMotDePasse())) {
            return utilisateur;  
        } else {
            throw new IllegalArgumentException("Mot de passe incorrect");
        }
    }
}