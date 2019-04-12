package fr.eseo.ld.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.dao.DAOFactory;

/**
 * Classe d'Initialisation de la DAOFactory.
 * 
 * <p>Cette classe gère l'instanciation de la DAOFactory au démarrage de l'application.</p>
 * 
 * @author Thomas MENARD et Maxime LENORMAND
 * @see fr.eseo.ld.dao.DAOFactory
 */
@WebListener
public class InitialisationDAOFactory implements ServletContextListener {
	
	private static final String ATT_DAO_FACTORY = "daoFactory";

	@Override
	public void contextInitialized(ServletContextEvent event) {
		/* Récupération du ServletContext lors du chargement de l'application */
		ServletContext servletContext = event.getServletContext();
		/* Instanciation de notre DAOFactory */
		DAOFactory daoFactory = DAOFactory.getInstance();
		/* Enregistrement dans un attribut ayant pour portée toute l'application */
		servletContext.setAttribute(ATT_DAO_FACTORY, daoFactory);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		/* Rien à réaliser lors de la fermeture de l'application... */
	}
	
}