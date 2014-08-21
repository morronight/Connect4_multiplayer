package fr.iutvalence.java.miniprojet;


/**
 * Classe permettant de connaitre l'etat d'une partie en reseau
 * (aurait servie si nous avions pu traiter de multiples parties
 * en paralleles)
 * @deprecated
 * @author Th�o R�veillard
 */
public class Statut
{
	/**
	 * la partie associee a ce statut
	 */
	public Puissance4 p;
	
	/**
	 * Les différents etats que peut prendre une partie
	 */
	public enum Resultat { 
		/**
		 * Le code renvoye dans le cas d'une partie en attente 
		 */
		EN_ATTENTE,
		/**
		 * Le code renvoye dans le cas d'une partie en cours
		 */
		EN_COURS,
		};
}
