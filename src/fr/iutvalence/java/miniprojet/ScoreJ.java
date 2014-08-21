package fr.iutvalence.java.miniprojet;

/**
 * Structure synthetisant les differentes statistiques de jeu
 * @author Théo Réveillard
 *
 */
public class ScoreJ {

	private int parties_gagnees;
	private int parties_perdues;
	private int parties_nulles;
	
	/**
	 * Permet de connaitre le nombre de parties gagnees
	 * @return le nombre de parties gagnees
	 */
	public int getParties_gagnees() {
		return this.parties_gagnees;
	}

	/**
	 * Permet de connaitre le nombre de parties perdues
	 * @return le nombre de parties perdues
	 */
	public int getParties_perdues() {
		return this.parties_perdues;
	}

	/**
	 * Permet de connaitre le nombre de parties nulles
	 * @return le nombre de parties nulles
	 */
	public int getParties_nulles() {
		return this.parties_nulles;
	}

	/**
	 * Initialise la structure avec les stats passees en parametre
	 * @param partiesGagnees
	 * 						: le nombre de parties gagnees
	 * @param partiesPerdues
	 * 						: le nombre de parties perdues
	 * @param partiesNulles
	 * 						: le nombre de parties nulles
	 */
	public ScoreJ(int partiesGagnees, int partiesPerdues, int partiesNulles)
	{
		this.parties_gagnees=partiesGagnees;
		this.parties_perdues=partiesPerdues;
		this.parties_nulles=partiesNulles;
	}

}
