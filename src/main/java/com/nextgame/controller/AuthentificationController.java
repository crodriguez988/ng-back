package com.nextgame.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextgame.dtos.LoginRequest;
import com.nextgame.services.UtilisateurService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/authentification")
public class AuthentificationController {
	
	@Autowired
    private UtilisateurService utilisateurService;
    
    private static final Logger logger = LogManager.getLogger(AuthentificationController.class);
    
    private static final String NOT_LOGGED = "NOT LOGGED";
    
    /**
     * Permet de faire l'authentification de l'utilisateur
     * @param loginRequest
     * @return ResponseEntity<String>
     */
    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody LoginRequest loginRequest) { 
    	logger.info("AuthController - login");
    	String utilisateurVerif = utilisateurService.verificactionAuthentification(loginRequest);
    	
    	if(NOT_LOGGED.equals(utilisateurVerif)) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    	}
    	return ResponseEntity.ok(utilisateurService.verificactionAuthentification(loginRequest));
    }
}