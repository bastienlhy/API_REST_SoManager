package fr.eseo.ld.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Classe mère des dépôts regroupant les méthodes et attributs utilisés dans
 * les classes filles.
 * 
 * On y retrouve le chargement des paramètres de configuration dépot contenus dans les
 * fichiers de propriété. On peut aussi tester la connexion et renvoyer la
 * liste des attributs du dépot.
 * 
 * @author Tristan JUMEAUX
 */
public class CONFIG {

	protected static final String FICHIER_PROPERTIES = "fr/eseo/ld/config/configChemin.properties";
	protected static final String FICHIER_PROPERTIES_DEFAUT = "fr/eseo/ld/config/configDefault.properties";
	protected static final String PROPERTY_CHEMIN_POSTER = "CHEMIN_POSTER";
	protected static final String PROPERTY_CHEMIN_SLIDE = "CHEMIN_SLIDE";
	protected static final String PROPERTY_TAILLE_MAX = "TAILLE" ;
	protected static final String PROPERTY_CHEMIN_EDT = "CHEMIN_EDT";

	

	protected String cheminPoster;
	protected String cheminEdt;
	protected String cheminSlide;
	protected String tailleMax;
	
	private static Logger logger = Logger.getLogger(CONFIG.class.getName());

	/**
	 * Constructeur du fichier de config.
	 */
	public CONFIG() {
		List<String> liste = lireFichierPropriete(FICHIER_PROPERTIES);
		this.cheminPoster = liste.get(0);
		this.cheminSlide = liste.get(1);
		this.tailleMax = liste.get(2);
		this.cheminEdt = liste.get(3);
	}

	/**
	 * Lit le fichier de configuration.
	 * 
	 * @param fichierURL l'URL du fichier.
	 * @return listeAttributs la liste comportant les attributs contenus dans le fichier de propriété.
	 */
	private List<String> lireFichierPropriete(String fichierURL) {
		List<String> listeAttributs = new ArrayList<>();
		Properties properties = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fichierProperties = classLoader.getResourceAsStream(fichierURL);
		try {
			properties.load(fichierProperties);
			listeAttributs.add(properties.getProperty(PROPERTY_CHEMIN_POSTER));
			listeAttributs.add(properties.getProperty(PROPERTY_CHEMIN_SLIDE));
			listeAttributs.add(properties.getProperty(PROPERTY_TAILLE_MAX));
			listeAttributs.add(properties.getProperty(PROPERTY_CHEMIN_EDT));

		} catch (IOException e) {
			logger.log(Level.WARN, "Echec de la lecture du fichier de propriété : " + e.getMessage(), e);
		}
		return listeAttributs;
	}

	
	/**
	 * Renvoie la liste des attributs du fichier de propriété par défaut.
	 * 
	 * @return la liste des attributs du fichier de propriété par défaut.
	 */
	public List<String> lireFichierProprieteParDefaut() {
		return lireFichierPropriete(FICHIER_PROPERTIES_DEFAUT);
	}

}