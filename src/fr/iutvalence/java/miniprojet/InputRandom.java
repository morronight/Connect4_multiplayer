package fr.iutvalence.java.miniprojet;
/**
 * 
 * @author REIFA Th�o R�veillard - WEHRLIN Th�o R�veillard
 *
 */
public class InputRandom
{

	/**
	 * Choix aléatoire d'une colonne
	 * @return un numéro de colonne entre 0 et 8 (8 non compris)
	 */
	public int choixJoueur()
	{
		double rand8 = Math.random()*8;
		double rand7 = Math.floor(rand8); 
		return (int) (Math.round(rand7));
		
	}

}