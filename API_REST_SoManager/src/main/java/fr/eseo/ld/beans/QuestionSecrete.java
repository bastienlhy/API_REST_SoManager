package fr.eseo.ld.beans;

public class QuestionSecrete {

	/* Attributs */ 
	private Long idUtilisateur;
	private Long idQuestion;
	private String intitule;
	private String reponse;
	
	/**
	 * Constructeur par défaut.
	 */
	public QuestionSecrete() {
		super();
	}

	/* Accesseurs et Mutateurs */
	/**
	 * Mutateur pour modifier l'intitule de la question
	 * @param reponse
	 */
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	

	/**
	 * Accessseur pour récupérer l'intitulé de la question
	 * @return reponse
	 */
	public String getIntitule() {
		return this.intitule;
	}
	
	/**
	 * Accessseur pour récupérer l'ID de l'option
	 * @return idUtilisateur
	 */
	public Long getIdUtilisateur() {
		return this.idUtilisateur;
	}

	/**
	 * Mutateur pour modifier l'ID de l'option
	 * @param idUtilisateur
	 */
	public void setIdUtilisateur(Long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	/**
	 * Accessseur pour récupérer l'ID de la question
	 * @return idQuestion
	 */
	public Long getIdQuestion() {
		return this.idQuestion;
	}

	/**
	 * Mutateur pour modifier l'ID de la question
	 * @param idQuestion
	 */
	public void setIdQuestion(Long idQuestion) {
		this.idQuestion = idQuestion;
	}


	/**
	 * Mutateur pour modifier la réponse à la question secrète
	 * @param reponse
	 */
	public void setReponse(String reponse) {
		this.reponse = reponse;
	}
	

	/**
	 * Accessseur pour récupérer la réponse à la question secrète
	 * @return reponse
	 */
	public String getReponse() {
		return this.reponse;
	}
	
}