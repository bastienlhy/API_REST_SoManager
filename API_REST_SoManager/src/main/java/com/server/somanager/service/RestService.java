package com.server.somanager.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.DAOFactory;
import com.dao.SujetDAO;
import com.dao.UtilisateurDAO;
import com.dao.VilleDAO;
import com.models.Ville;

import fr.eseo.ld.beans.Sujet;
import fr.eseo.ld.beans.Utilisateur;

@RestController
public class RestService {
    
    private static final Logger logger = LoggerFactory.getLogger(RestService.class);
    
	private UtilisateurDAO utilisateurDao;
	private SujetDAO sujetDao;
	
	private DAOFactory daoFactory = DAOFactory.getInstance();
    
    @GetMapping(value = "/")
    public ResponseEntity<String> pong() 
    {
        logger.info("Démarrage des services OK .....");
        return new ResponseEntity<String>("Réponse du serveur: "+HttpStatus.OK.name(), HttpStatus.OK);
    }
    
	// ###########################################################################################
	// #                     Méthodes  GET 
	// ###########################################################################################

    
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
     * 										UTILISATEURS 
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    
    /**
     * Description : Renvoie la liste de tous les utilisateurs
     * Path : /get
     * Paramètres : none
     * Résultat : Liste<Ville>
     */

	@CrossOrigin
	@RequestMapping(value = "/get/users", method = RequestMethod.GET)
	@ResponseBody
	public List<Utilisateur> getUsers(@RequestParam(required = false, value = "value") String value) {

		List<Utilisateur> listeUtilisateur = new ArrayList<Utilisateur>();

		this.utilisateurDao = daoFactory.getUtilisateurDao();
		
		listeUtilisateur = utilisateurDao.lister();
		return listeUtilisateur;
	}
	
	 /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
     * 										SUJETS 
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	/**
     * Description : Renvoie la liste de tous les utilisateurs
     * Path : /get
     * Paramètres : none
     * Résultat : Liste<Ville>
     */

	@CrossOrigin
	@RequestMapping(value = "/get/sujets", method = RequestMethod.GET)
	@ResponseBody
	public List<Sujet> getSujets(@RequestParam(required = false, value = "value") String value) {

		List<Sujet> listeSujets = new ArrayList<Sujet>();

		this.sujetDao = daoFactory.getSujetDao();
		
		listeSujets = sujetDao.lister();
		return listeSujets;
	}
	
	// ###########################################################################################
	// #                     Méthodes  
	// ###########################################################################################


}
