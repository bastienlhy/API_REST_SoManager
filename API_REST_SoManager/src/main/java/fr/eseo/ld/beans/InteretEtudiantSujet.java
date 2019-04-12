package fr.eseo.ld.beans;

/**
 * Classe du bean InteressementSujet.
 * 
 * <p>Définition des attributs de l'objet InteressementSujet ainsi que des accesseurs et des mutateurs.</p>
 * 
 * @author Lilian BRAUD
 */
public class InteretEtudiantSujet {
	
	private Long idEtudiant;
	private Long idSujet;
	
	/**
	 * Constructeur par défaut.
	 */
	public InteretEtudiantSujet() {
		super();
	}
	
	/**
	 * Accessseur pour récupérer l'ID de l'étudiant
	 * @return idEtudiant
	 */
	public Long getIdEtudiant() {
		return idEtudiant;
	}

	/**
	 * Mutateur pour modifier l'ID de l'étudiant
	 * @param idEtudiant
	 */
	public void setIdEtudiant(Long idEtudiant) {
		this.idEtudiant = idEtudiant;
	}

	/**
	 * Accessseur pour récupérer l'ID du sujet qui intéresse l'étudiant
	 * @return idSujet
	 */
	public Long getIdSujet() {
		return idSujet;
	}

	/**
	 * Mutateur pour modifier l'ID du sujet qui intéresse l'étudiant
	 * @param idSujet
	 */
	public void setIdSujet(Long idSujet) {
		this.idSujet = idSujet;
	}

	
	/**
	 * Redéfinition de la méthode toString().
	 */
	@Override
	public String toString() {
		return "InteressementSujet [idEtudiant=" + this.idEtudiant + ", idSujet=" + this.idSujet;
	}
	
}