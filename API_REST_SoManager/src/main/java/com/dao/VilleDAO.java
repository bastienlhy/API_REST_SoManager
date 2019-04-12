package com.dao;

import java.util.List;

import com.models.Ville;

public interface VilleDAO {

	String ajouter( Ville ville );

	String supprimer ( Ville ville );
	
	List<Ville> lister ();
	
	List<Ville> filtrer ( Ville ville );

}
