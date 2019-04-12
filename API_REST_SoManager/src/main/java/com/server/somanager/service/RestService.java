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
import com.dao.UtilisateurDAO;
import com.dao.VilleDAO;
import com.models.Ville;

import fr.eseo.ld.beans.Utilisateur;

@RestController
public class RestService {
    
    private static final Logger logger = LoggerFactory.getLogger(RestService.class);
	private VilleDAO villeDAO;
	private UtilisateurDAO utilisateurDao;
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
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public List<Utilisateur> get(@RequestParam(required = false, value = "value") String value) {

		List<Utilisateur> listeUtilisateur = new ArrayList<Utilisateur>();

		this.utilisateurDao = daoFactory.getUtilisateurDao();
		
		listeUtilisateur = utilisateurDao.lister();
		System.out.println("listeUtilisateur : " + listeUtilisateur);
		return listeUtilisateur;
	}
	
	@CrossOrigin
	@RequestMapping(value = "/get2", method = RequestMethod.GET)
	@ResponseBody
	public List<Ville> get2(@RequestParam(required = false, value = "value") String value) {

		List<Ville> listeVille = new ArrayList<Ville>();

		this.villeDAO = daoFactory.getVilleDao();
		
		listeVille = villeDAO.lister();
		System.out.println("Liste ville : " + listeVille);
		return listeVille;
	}
	
	// ###########################################################################################
	// #                     Méthodes  
	// ###########################################################################################


}
