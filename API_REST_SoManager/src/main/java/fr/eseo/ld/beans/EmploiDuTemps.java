package fr.eseo.ld.beans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmploiDuTemps {

	private Professeur professeur;
	private File planningEdt;


	public EmploiDuTemps(Professeur professeur, String urlPlanning) {
		this.professeur = professeur;
		this.planningEdt = new File(urlPlanning);
	}

	public Professeur getProfesseur() {
		return professeur;
	}

	public void setProfesseur(Professeur professeur) {
		this.professeur = professeur;
	}

	public File getUrlPlanning() {
		return planningEdt;
	}

	public void setUrlPlanning(File urlPlanning) {
		this.planningEdt = urlPlanning;
	}

	public List<PlageHoraire> ParcoursICS(File planningFile, String dateLimiteDebut, String dateLimiteFin) {
		String ligne;
		ArrayList<PlageHoraire> plageHoraire = new ArrayList<>();

		BufferedReader planning = null;

		try {
			planning = new BufferedReader(new FileReader(planningFile));
			ligne = planning.readLine();
			while (!ligne.equalsIgnoreCase("END:VCALENDAR") && ligne != null) {
				if (ligne.equalsIgnoreCase("BEGIN:VEVENT")) {
					ligne = planning.readLine();
					String dateDebutEvent = null;
					String dateFinEvent = null;
					int jourDebutEvent = 0;
					int heureDebutEvent = 0;
					int jourFinEvent = 0;
					int heureFinEvent = 0;

					while (!ligne.equalsIgnoreCase("END:VEVENT")) {
						if (ligne.substring(0, 7).equalsIgnoreCase("DTSTART")) {
							dateDebutEvent = ligne.substring(26);
							jourDebutEvent = Integer.parseInt(dateDebutEvent.substring(0, 8));
							heureDebutEvent = Integer.parseInt(dateDebutEvent.substring(9));
						} else if (ligne.substring(0, 5).equalsIgnoreCase("DTEND")) {
							dateFinEvent = ligne.substring(24);
							jourFinEvent = Integer.parseInt(dateFinEvent.substring(0, 8));
							heureFinEvent = Integer.parseInt(dateFinEvent.substring(9));
						}
						try {
							ligne = planning.readLine();
						} catch (IOException e) {
						}
					}
					int jourLimiteDebut = Integer.parseInt(dateLimiteDebut.substring(0, 8));
					int jourLimiteFin = Integer.parseInt(dateLimiteFin.substring(0, 8));
					int heureLimiteDebut = Integer.parseInt(dateLimiteDebut.substring(9));
					int heureLimiteFin = Integer.parseInt(dateLimiteFin.substring(9));

					if (jourLimiteDebut <= jourDebutEvent && jourLimiteFin >= jourFinEvent
							&& heureLimiteDebut <= heureDebutEvent && heureLimiteFin >= heureFinEvent) {
						plageHoraire.add(new PlageHoraire(this.getProfesseur(), dateDebutEvent, dateFinEvent));
					}
				}
				ligne = planning.readLine();
			}
			planning.close();
		} catch (FileNotFoundException e) {
			System.out.println("ici");
		} catch (IOException e) {
			System.out.println("IOException");
		} 

		return plageHoraire;
	}



	void ordonnerEdt(List<PlageHoraire> cours) {
		ArrayList<String> datesDebut = new ArrayList<>();
		for (int i = 0; i < cours.size(); i++) {
			datesDebut.add(cours.get(i).getDateDebut());
		}
		Collections.sort(datesDebut);
		for (int i = 0; i < cours.size(); i++) {
			for (int j = 0; j < cours.size(); j++) {
				if (cours.get(i).getDateDebut() == datesDebut.get(j)) {
					Collections.swap(cours, i, j);
				}
			}
		}
	}

	public ArrayList<PlageHoraire> trouverDispo(File planningFile, String dateLimiteDebut, String dateLimiteFin) {
		List<PlageHoraire> cours = this.ParcoursICS(planningFile, dateLimiteDebut, dateLimiteFin);
		this.ordonnerEdt(cours);

		ArrayList<PlageHoraire> dispos = new ArrayList<>();

		for (int i = 0; i < cours.size(); i++) {
			PlageHoraire coursCourant = cours.get(i);
			dispoMoins4h(coursCourant, dispos);
		}

		int jourLimiteDebut = Integer.parseInt(dateLimiteDebut.substring(0, 8));
		int jourLimiteFin = Integer.parseInt(dateLimiteFin.substring(0, 8));
		int nbJours = jourLimiteFin - jourLimiteDebut;
		int compteur = 0;
		boolean matinLibre;
		boolean apremLibre;
		while (compteur != nbJours + 1) {
			matinLibre = true;
			apremLibre = true;
			dispo4h(matinLibre, apremLibre, cours, jourLimiteDebut, compteur, dispos);
			++compteur;
		}

		this.ordonnerEdt(dispos);

		for (int i = 0; i < dispos.size(); i++) {
			dispos.get(i).setDateDebut(this.conversionStringICSToSQL(dispos.get(i).getDateDebut()));
			dispos.get(i).setDateFin(this.conversionStringICSToSQL(dispos.get(i).getDateFin()));
		}
		return dispos;

	}

	private void dispoMoins4h(PlageHoraire coursCourant, ArrayList<PlageHoraire> dispos) {

		String jourDebut = coursCourant.getDateDebut().substring(0, 8);
		int heureDebut = Integer.parseInt(coursCourant.getDateDebut().substring(9));
		int heureFin = Integer.parseInt(coursCourant.getDateFin().substring(9));

		if (heureDebut - 80000 > 10000 && 80000 < heureDebut && heureDebut < 120000) { // 10000 correspond à 1H
			String heureDebutDispo = "0" + Integer.toString(80000);
			String dateDebutDispo = jourDebut.substring(0, 8) + "T" + heureDebutDispo;
			String dateFinDispo = jourDebut.substring(0, 8) + "T" + coursCourant.getDateDebut().substring(9);

			dispos.add(new PlageHoraire(this.getProfesseur(), dateDebutDispo, dateFinDispo));
		} else if (120000 - heureFin > 10000 && 80000 < heureFin && heureFin < 120000) {
			String heureFinDispo = Integer.toString(120000);
			String dateDebutDispo = jourDebut.substring(0, 8) + "T" + coursCourant.getDateFin().substring(9);
			String dateFinDispo = jourDebut.substring(0, 8) + "T" + heureFinDispo;

			dispos.add(new PlageHoraire(this.getProfesseur(), dateDebutDispo, dateFinDispo));
		}
		if (heureDebut - 133000 > 10000 && 133000 < heureDebut && heureDebut < 173000) { // 10000 correspond à 1H
			String heureDebutDispo = Integer.toString(133000);
			String dateDebutDispo = jourDebut.substring(0, 8) + "T" + heureDebutDispo;
			String dateFinDispo = jourDebut.substring(0, 8) + "T" + coursCourant.getDateDebut().substring(9);

			dispos.add(new PlageHoraire(this.getProfesseur(), dateDebutDispo, dateFinDispo));
		} else if (173000 - heureFin > 10000 && 133000 < heureFin && heureFin < 173000) {
			String heureFinDispo = Integer.toString(173000);
			String dateDebutDispo = jourDebut.substring(0, 8) + "T" + coursCourant.getDateFin().substring(9);
			String dateFinDispo = jourDebut.substring(0, 8) + "T" + heureFinDispo;

			dispos.add(new PlageHoraire(this.getProfesseur(), dateDebutDispo, dateFinDispo));
		}
	}

	private void dispo4h(boolean matinLibre, boolean apremLibre, List<PlageHoraire> cours, int jourLimiteDebut,
			int compteur, ArrayList<PlageHoraire> dispos) {
		for (int i = 0; i < cours.size(); i++) {
			PlageHoraire coursCourant = cours.get(i);
			int jour = Integer.parseInt(coursCourant.getDateDebut().substring(0, 8));
			int heureDebut = Integer.parseInt(coursCourant.getDateDebut().substring(9));
			if (jour == jourLimiteDebut + compteur) {
				if (80000 <= heureDebut && heureDebut <= 120000) {
					matinLibre = false;
				}
				if (133000 <= heureDebut && heureDebut <= 173000) {
					apremLibre = false;
				}
			}
		}
		if (matinLibre) {
			String dateDebutDispo = Integer.toString(jourLimiteDebut + compteur) + "T080000";
			String dateFinDispo = Integer.toString(jourLimiteDebut + compteur) + "T120000";
			dispos.add(new PlageHoraire(this.getProfesseur(), dateDebutDispo, dateFinDispo));
		} else if (apremLibre) {
			String dateDebutDispo = Integer.toString(jourLimiteDebut + compteur) + "T133000";
			String dateFinDispo = Integer.toString(jourLimiteDebut + compteur) + "T173000";
			dispos.add(new PlageHoraire(this.getProfesseur(), dateDebutDispo, dateFinDispo));
		}
	}

	private String conversionStringICSToSQL(String dateICS) {
		return dateICS.substring(0, 4) + "-" + dateICS.substring(4, 6) + "-" + dateICS.substring(6, 8) + " "
				+ dateICS.substring(9, 11) + ":" + dateICS.substring(11, 13) + ":" + dateICS.substring(13);
	}

}
