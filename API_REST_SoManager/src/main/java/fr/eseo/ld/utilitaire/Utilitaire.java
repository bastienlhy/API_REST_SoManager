package fr.eseo.ld.utilitaire;

import java.io.Closeable;
import java.io.IOException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import fr.eseo.ld.config.CONFIG;

/**
 * Classe d'utilitaire pour l'intégralité du projet
 * @author Lilian BRAUD
 *
 */
public final class Utilitaire {

	private static Logger logger = Logger.getLogger(CONFIG.class.getName());
	
	private Utilitaire() {}
	
	/**
	 * Ferme la ressource en gérant les exceptions IOException
	 * @param closeable Ressource à fermer
	 */
	public static void fermerRessource(Closeable closeable) {
		Utilitaire.fermerRessource(closeable, "Echec de la fermeture d'une ressource (fuite de mémoire potentielle) : ");		
	}

	/**
	 * Ferme la ressource en gérant les exceptions IOException
	 * @param closeable Ressource à fermer
	 * @param messageErreur Message d'erreur pour les logs
	 */
	public static void fermerRessource(Closeable closeable, String messageErreur) {
		if (closeable != null) 
		{
			try {
				closeable.close();
			} catch (IOException e) {
				logger.log(Level.WARN, messageErreur + e.getMessage(), e);
			}		
		}
	}
}
