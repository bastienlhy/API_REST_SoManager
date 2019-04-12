package fr.eseo.ld.dao;

import static fr.eseo.ld.dao.DAOUtilitaire.fermetures;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import fr.eseo.ld.beans.Sujet;

public class SujetDAOImpl implements SujetDAO{
	
	/* Attributs de l'entité Sujet */
	private static final String ATTRIBUT_ID_SUJET = "idSujet";
	private static final String ATTRIBUT_IDENTIFIANT = "identifiant";
	private static final String ATTRIBUT_TITRE = "titre";
	private static final String ATTRIBUT_DESCRIPTION = "description";
	private static final String ATTRIBUT_MIN_ELEVE = "nbrMinEleves";
	private static final String ATTRIBUT_MAX_ELEVE = "nbrMaxEleves";
	private static final String ATTRIBUT_CONTRAT_PRO = "contratPro";
	private static final String ATTRIBUT_CONFIDENTIALITE = "confidentialite";
	private static final String ATTRIBUT_ETAT_SUJET = "etat";
	private static final String ATTRIBUT_LIENS = "liens";
	private static final String ATTRIBUT_INTERETS = "interets";
	private static final String ATTRIBUT_NOTE_INTERET = "noteInteretTechno";
	private static final String ATTRIBUT_NOTE_INTERET_SUJET = "noteInteretSujet";
	
	/* Requetes SQLS */
	
	private static final String SQL_SELECT_TOUT = "SELECT * FROM Sujet";

	/* Logger */
	private static Logger logger = Logger.getLogger(SujetDAO.class.getName());
	
	/* Initialisation du DAO */
	private DAOFactory daoFactory;

	SujetDAOImpl( DAOFactory daoFactory ) {
	        this.daoFactory = daoFactory;
	    }

	/**
	 * Liste tous les Sujets présents dans la BDD.
	 * 
	 * @return sujets la liste des Sujets présents dans la BDD.
	 */
	@Override
	public List<Sujet> lister() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Sujet> sujets = new ArrayList<>();
		try {
			// cr�ation d'une connexion grâce à la DAOFactory plac�e en attribut de la classe
			connection = this.creerConnexion();
			preparedStatement = connection.prepareStatement(SQL_SELECT_TOUT);
			resultSet = preparedStatement.executeQuery();
			// r�cup�ration des valeurs des attributs de la BDD pour les mettre dans une liste
			while (resultSet.next()) {
				sujets.add(recupererSujet(resultSet));
			}
		} catch (SQLException e) {
			logger.log(Level.WARN, "Échec du listage des objets.", e);
		} finally {
			fermetures(resultSet, preparedStatement, connection);
		}
		return sujets;
	}
	
	// #################################################
		// #               M�thodes priv�es                #
		// #################################################
		
		/**
		 * Cr�e une connexion à la BDD.
		 * 
		 * @return connection la connexion à la BDD.
		 * @throws SQLException
		 */
		protected Connection creerConnexion() throws SQLException {
			return this.daoFactory.getConnection();
		}
		
		/**
		 * Fait la correspondance (le mapping)
		 * entre une ligne issue de la table Sujet (un ResultSet) et un bean Sujet.
		 * 
		 * @param resultSet la ligne issue de la table Sujet.
		 * @return sujet le bean dont on souhaite faire la correspondance.
		 * @throws SQLException
		 */
		public static Sujet recupererSujet(ResultSet resultSet) throws SQLException {
			Sujet sujet = new Sujet();
			sujet.setIdSujet(resultSet.getLong(ATTRIBUT_ID_SUJET));
			sujet.setIdentifiant(resultSet.getString(ATTRIBUT_IDENTIFIANT));
			sujet.setTitre(resultSet.getString(ATTRIBUT_TITRE));
			sujet.setDescription(resultSet.getString(ATTRIBUT_DESCRIPTION));
			sujet.setNbrMinEleves(resultSet.getInt(ATTRIBUT_MIN_ELEVE));
			sujet.setNbrMaxEleves(resultSet.getInt(ATTRIBUT_MAX_ELEVE));
			sujet.setContratPro(resultSet.getBoolean(ATTRIBUT_CONTRAT_PRO));
			sujet.setConfidentialite(resultSet.getBoolean(ATTRIBUT_CONFIDENTIALITE));
			sujet.setEtatString(resultSet.getString(ATTRIBUT_ETAT_SUJET));
			sujet.setLiens(resultSet.getString(ATTRIBUT_LIENS));
			sujet.setInterets(resultSet.getString(ATTRIBUT_INTERETS));
			sujet.setNoteInteretTechno(resultSet.getFloat(ATTRIBUT_NOTE_INTERET));
			sujet.setNoteInteretSujet(resultSet.getFloat(ATTRIBUT_NOTE_INTERET_SUJET));
			return sujet;
		}

}
