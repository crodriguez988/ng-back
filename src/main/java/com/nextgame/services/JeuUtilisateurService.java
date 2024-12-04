package com.nextgame.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextgame.dtos.JeuUtilisateurDTO;
import com.nextgame.entities.Jeu;
import com.nextgame.entities.JeuUtilisateur;
import com.nextgame.entities.Utilisateur;
import com.nextgame.enums.EtatJeu;
import com.nextgame.repositories.IJeuRepository;
import com.nextgame.repositories.IJeuUtilisateurRepository;
import com.nextgame.repositories.IUtilisateurRepository;

import io.jsonwebtoken.lang.Arrays;

@Service
public class JeuUtilisateurService {
	
	@Autowired
	IJeuUtilisateurRepository utilisateurJeuRepository;
	
	@Autowired
    IUtilisateurRepository utilisateurRepository;
	
	@Autowired
    IJeuRepository jeuRepository;
	
    public JeuUtilisateur ajoutJeuListeUtilisateur(JeuUtilisateur utilisateurJeu) {
        // Récupération de l'utilisateur et du jeu à partir de leurs id
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurJeu.getUtilisateur().getId())
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable avec l'ID : " + utilisateurJeu.getUtilisateur().getId()));
        Jeu jeu = jeuRepository.findById(utilisateurJeu.getJeu().getId())
                .orElseThrow(() -> new IllegalArgumentException("Jeu introuvable avec l'ID : " + utilisateurJeu.getJeu().getId()));

        if (utilisateurJeu.getNote() < 1 || utilisateurJeu.getNote()> 10) {
            throw new IllegalArgumentException("La note doit être comprise entre 1 et 10.");
        }

        JeuUtilisateur newUtilisateurJeu = new JeuUtilisateur();
        newUtilisateurJeu.setUtilisateur(utilisateur);
        newUtilisateurJeu.setJeu(jeu);
        newUtilisateurJeu.setNote(utilisateurJeu.getNote());
        newUtilisateurJeu.setStatutJeu(utilisateurJeu.getStatutJeu());
        newUtilisateurJeu.setDateAjout(LocalDate.now());
        newUtilisateurJeu.setDateTermine(utilisateurJeu.getDateTermine());

        return utilisateurJeuRepository.save(newUtilisateurJeu);
    }
    
    public List<JeuUtilisateurDTO> getJeuxByUtilisateurId(Long idUtilisateur) {
        List<Object[]> results = utilisateurJeuRepository.getAllJeuxUtilisateur(idUtilisateur);
        
        // Transformation des résultats en DTO
        return results.stream().map(result -> {
            JeuUtilisateurDTO utilisateurJeuDTO = new JeuUtilisateurDTO();
            
            utilisateurJeuDTO.setId(((Number) result[0]).longValue());                  
            utilisateurJeuDTO.setIdUtilisateur(((Number) result[1]).longValue());      
            utilisateurJeuDTO.setIdJeu(((Number) result[2]).longValue());              
            utilisateurJeuDTO.setNote(((Number) result[3]).doubleValue());             
            
            // Conversion de java.sql.Date à LocalDate
            java.sql.Date sqlDateAjout = (java.sql.Date) result[4];
            LocalDate dateAjout = sqlDateAjout.toLocalDate();  
            utilisateurJeuDTO.setDateAjout(dateAjout);                      
            
            utilisateurJeuDTO.setStatutJeu(EtatJeu.valueOf((String) result[5]));        
            utilisateurJeuDTO.setNomJeu((String) result[6]);                            

            java.sql.Date sqlDateTermine = (java.sql.Date) result[7];
            LocalDate dateTermine = sqlDateTermine != null ? sqlDateTermine.toLocalDate() : null; // Vérifie si c'est null
            utilisateurJeuDTO.setDateTermine(dateTermine);                    
            
            utilisateurJeuDTO.setPlateformes(Arrays.asList(((String) result[8]).split(", "))); 

            return utilisateurJeuDTO;
        }).collect(Collectors.toList());
    }
    
	//calcul moyenne des jeux ajoutés par utilisateur
	
	//Count des jeux par etat
	
	//Count jeux ajoutés dans la liste
    
	
}