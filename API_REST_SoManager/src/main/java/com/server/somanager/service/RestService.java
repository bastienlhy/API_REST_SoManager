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

import fr.eseo.ld.beans.Sujet;
import fr.eseo.ld.beans.Utilisateur;
import fr.eseo.ld.dao.DAOFactory;
import fr.eseo.ld.dao.SujetDAO;
import fr.eseo.ld.dao.UtilisateurDAO;

/**
 * Classe de requêtes API - Ccontroleur 
 * 
 * <p>Utilisation du modèle DAO.</p>
 * 
 * @version 1.0
 * @author Thessalène JEAN-LOUIS
 * 
 */


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
     * @Description  Renvoie la liste de tous les utilisateurs
     * @Path  /get
     * @Params  none
     * @Result  Liste<Utilisateur>
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
	
	///get/user/connect?identifiant=test&password=test
	
	 /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
     * 										SUJETS 
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
	
	/**
     * @Description  Renvoie la liste de tous les utilisateurs
     * @Path  /get/sujets
     * @Params none
     * @Result Liste<Sujet>
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
	
	/**
     * @Description : Renvoie les informations relatives à un sujet
     * @Path : /get
     * @params id l'id du sujet recherché
     * @return Liste<Sujet> liste contenant le sujet recherché
     */

	@CrossOrigin
	@RequestMapping(value = "/get/sujetById", method = RequestMethod.GET)
	@ResponseBody
	public Sujet getSujetById(@RequestParam(required = true, value = "idSujet") Long value) {

		Sujet sujet = new Sujet();

		this.sujetDao = daoFactory.getSujetDao();
		
		System.out.println("Valeur : "+ value);
		sujet = sujetDao.trouver(value);
		return sujet;
	}
	
	// ###########################################################################################
	// #                     Méthodes  
	// ###########################################################################################


}
