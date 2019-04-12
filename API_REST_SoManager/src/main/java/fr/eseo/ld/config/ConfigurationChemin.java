package fr.eseo.ld.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.dao.DAOException;


/**
 * Classe implémentant les méthodes et fonctions permettant de modifier le
 * fichier de configuration des dépôts.
 * 
 * @author Tristan JUMEAUX
 */

public class ConfigurationChemin extends CONFIG {

	
	private Properties properties = new Properties();
	private URL url;
	
	private static Logger logger = Logger.getLogger(ConfigurationChemin.class.getName());
	
	/**
	 * Constructeur par défaut.
	 */
	public ConfigurationChemin() {
		super();
	}
	
	/**
	 * Renvoie la liste des attributs contenus dans le fichier de configuration.
	 * 
	 * @return listeAttributs la liste des attributs contenus dans le fichier de configuration.
	 */
	public List<String> lireFichier() {
		List<String> listeAttributs = new ArrayList<>();
		listeAttributs.add(this.cheminPoster);
		listeAttributs.add(this.cheminSlide);
		listeAttributs.add(this.tailleMax);
		listeAttributs.add(this.cheminEdt);
		return listeAttributs;
	}

	/**
	 * Modifie les attributs du fichier de propriété avec les attributs passés en paramètres.
	 * @param nouveauxAttributs les nouveaux paramètres à insérer
	 * 
	 */
	public void modifierFichierChemin(String[] nouveauxAttributs) {
		/* Création des différentes variables utilisées lors de la lecture du fichier de propriétés */
		properties = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fichierProperties = classLoader.getResourceAsStream(CONFIG.FICHIER_PROPERTIES);
		try {
			
			// on charge le fichier de propriétés
			properties.load(fichierProperties);

			// on change les différentes propriétés avec les attributs rentrés en paramètre de la méthode
			changerAttributs(nouveauxAttributs);

			// on récupère le chemin du fichier de paramètres
			this.url = this.getClass().getResource("configChemin.properties");
			

			// on remplace les attributs de l'annuaire avec les nouvelles valeurs
			this.ecrireDansFichier();
		} catch (IOException | URISyntaxException e) {
			logger.log(Level.WARN, "Impossible de lire ou de modifier le fichier ", e);
		}
	}
	
	/**
	 * Change les paramètres de la classe avec les attributs présents dans le tableau de Strings mis en paramètre.
	 * @param nouveauxAttributs les nouveaux paramètres de la classe.
	 */
	private void changerAttributs(String[] nouvelAttribut) {
		properties.setProperty(CONFIG.PROPERTY_CHEMIN_POSTER, nouvelAttribut[0]);
		this.cheminPoster = nouvelAttribut[0];
		properties.setProperty(CONFIG.PROPERTY_CHEMIN_SLIDE, nouvelAttribut[1]);
		this.cheminSlide = nouvelAttribut[1];
		properties.setProperty(CONFIG.PROPERTY_TAILLE_MAX, nouvelAttribut[2]);
		this.tailleMax = nouvelAttribut[2];
		properties.setProperty(CONFIG.PROPERTY_CHEMIN_EDT, nouvelAttribut[2]);
	}
	
	/**
	 * Ecrit dans un fichier.
	 * 
	 * @param url chemin du fichier.
	 * @param properties fichier de propriétés à réécrire.
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	private void ecrireDansFichier() throws URISyntaxException, IOException {
		/* Changement des paramètres présent dans annuaire.properties */
		File fileObject = new File(this.url.toURI());
		FileOutputStream out = new FileOutputStream(fileObject);
		this.properties.store(out, null);
		out.close();
	}
	
	/**
	 * Charge les propriétés de connexion à la base de données.
	 * 
	 * @return le tableau de Strings contenant les propriétés de connexion à la base de données.
	 * @throws DAOConfigurationException
	 */
	public static String[] chargerConfiguration(String cheminProperties) {
		Properties properties = new Properties();
		
		String cheminPoster;
		String cheminSlide;
		String tailleMax;
		String cheminEdt;
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fichierProperties = classLoader.getResourceAsStream(cheminProperties);

		try {
			properties.load(fichierProperties);
			cheminPoster = properties.getProperty(CONFIG.PROPERTY_CHEMIN_POSTER);
			cheminSlide = properties.getProperty(CONFIG.PROPERTY_CHEMIN_SLIDE);
			cheminEdt = properties.getProperty(CONFIG.PROPERTY_CHEMIN_EDT);
			tailleMax = properties.getProperty(CONFIG.PROPERTY_TAILLE_MAX);

		}catch (IOException e) {
			throw new DAOException("Impossible de charger le fichier properties " + cheminProperties, e);
		}

		

		return new String[] { cheminPoster, cheminSlide, cheminEdt, tailleMax };
	}
	
	public static String[] chargerConfigurationDepot() {
		return chargerConfiguration(FICHIER_PROPERTIES);
	}
	
	public static String[] chargerConfigurationDefautDepot() {
		return chargerConfiguration(FICHIER_PROPERTIES_DEFAUT);
	}
	
}
