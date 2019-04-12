package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
