package fr.eseo.ld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class DAOUtilitaire {
	
	private static Logger logger = Logger.getLogger(DAOUtilitaire.class.getName());


	/*
	 * Initialise la requête préparée basée sur la connexion passée en argument,
	 * avec la requête SQL et les objets donnés.
	 */
	public static PreparedStatement initialisationRequetePreparee( Connection connexion, String sql, boolean returnGeneratedKeys, Object... objets ) throws SQLException {
	    PreparedStatement preparedStatement = connexion.prepareStatement( sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS );
	    for ( int i = 0; i < objets.length; i++ ) {
	        preparedStatement.setObject( i + 1, objets[i] );
	    }
	    return preparedStatement;
	}

	
	/* Fermeture silencieuse du resultset */
	public static void fermetureSilencieuse( ResultSet resultSet ) {
	    if ( resultSet != null ) {
	        try {
	            resultSet.close();
	        } catch ( SQLException e ) {
	            System.out.println( "Échec de la fermeture du ResultSet : " + e.getMessage() );
	        }
	    }
	}

	/* Fermeture silencieuse du statement */
	public static void fermetureSilencieuse( Statement statement ) {
	    if ( statement != null ) {
	        try {
	            statement.close();
	        } catch ( SQLException e ) {
	            System.out.println( "Échec de la fermeture du Statement : " + e.getMessage() );
	        }
	    }
	}

	/* Fermeture silencieuse de la connexion */
	public static void fermetureSilencieuse( Connection connexion ) {
	    if ( connexion != null ) {
	        try {
	            connexion.close();
	        } catch ( SQLException e ) {
	            System.out.println( "Échec de la fermeture de la connexion : " + e.getMessage() );
	        }
	    }
	}

	/* Fermetures silencieuses du statement et de la connexion */
	public static void fermeturesSilencieuses( Statement statement, Connection connexion ) {
	    fermetureSilencieuse( statement );
	    fermetureSilencieuse( connexion );
	}

	/* Fermetures silencieuses du resultset, du statement et de la connexion */
	public static void fermeturesSilencieuses( ResultSet resultSet, Statement statement, Connection connexion ) {
	    fermetureSilencieuse( resultSet );
	    fermetureSilencieuse( statement );
	    fermetureSilencieuse( connexion );
	}
	
	// ########################################################################################################
	// #                            Methodes pour la création des requêtes préparées                          #
	// ########################################################################################################

	/**
	 * Initialise une requête préparée.
	 * 
	 * @param connection la connexion à la BDD.
	 * @param sql la requête SQL.
	 * @param returnGeneratedKeys le boolean qui permet de générer des ID ou pas.
	 * @param objets la liste d'objets à insérer dans la requête.
	 * @return preparedStatement la requête préparée initialisée.
	 * @throws SQLException
	 */
	protected static String initialisationRequetePreparee(String sql, Object... objets) {
		String[] listeSQL = (sql+" ").split("\\?");
		StringBuilder newSQL = new StringBuilder(listeSQL[0]);
		for(int i = 0; i<objets.length; i++) {
			newSQL.append("\"" + objets[i] + "\"" + listeSQL[i+1]);
		}
		return newSQL.toString().replaceAll("\"null\"", "null");
	}	
	
	/**
	 * Initialise une requête préparée.
	 * 
	 * @param sql la requête SQL.
	 * @param la liste d'objets à insérer dans la requête.
	 * @return La requête SQL complété des paramètres
	 * @throws SQLException
	 */
	protected static String initialisationRequetePreparee(String sql, String[] parametres) {
		String[] listeSQL = (sql+" ").split("\\?");
		StringBuilder newSQL = new StringBuilder(listeSQL[0]);
		for(int i = 0; i<parametres.length; i++) {
			newSQL.append("\"" + parametres[i] + "\"" + listeSQL[i+1]);
		}
		return newSQL.toString().replaceAll("\"null\"", "null");
	}

	
	// ########################################################################################################
	// #                            Methodes pour la fermeture des ressources                                 #
	// ########################################################################################################
	
	/**
	 * Ferme le resultset.
	 * 
	 * @param resultSet le resultSet à fermer.
	 */
	public static void fermeture(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				logger.log(Level.WARN, "Echec de la fermeture du ResultSet : " + e.getMessage(), e);
			}
		}
	}

	/**
	 * Ferme le statement.
	 * 
	 * @param statement le statement à fermer.
	 */
	public static void fermeture(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				logger.log(Level.WARN, "Echec de la fermeture du Statement : " + e.getMessage(), e);
			}
		}
	}

	/**
	 * Ferme la connection.
	 * 
	 * @param connection la connection à fermer.
	 */
	public static void fermeture(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.log(Level.WARN, "Echec de la fermeture de la connexion : " + e.getMessage(), e);
			}
		}
	}
	
	/**
	 * Ferme le statement et la connection.
	 * 
	 * @param statement le statement à fermer.
	 * @param connection la connection à fermer.
	 */
	public static void fermetures(Statement statement, Connection connection) {
		fermeture(statement);
		fermeture(connection);
	}

	/**
	 * Ferme le resultSet, le statement et la connection.
	 * 
	 * @param resultSet le resultSet à fermer.
	 * @param statement le statement à fermer.
	 * @param connection la connection à fermer.
	 */
	public static void fermetures(ResultSet resultSet, Statement statement, Connection connection) {
		fermeture(resultSet);
		fermeture(statement);
		fermeture(connection);
	}
	
}
