package fr.eseo.ld.beans;

public class PlageHoraire {
	
	Professeur professeur;
	String dateDebut;
	String dateFin;
	
	public PlageHoraire(Professeur professeur, String dateDebut, String dateFin) {
		this.professeur = professeur;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}
	
	public String getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}
	public String getDateFin() {
		return dateFin;
	}
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public Professeur getProfesseur() {
		return professeur;
	}

	public void setProfesseur(Professeur professeur) {
		this.professeur = professeur;
	}

}
