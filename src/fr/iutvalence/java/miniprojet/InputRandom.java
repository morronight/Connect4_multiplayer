package fr.iutvalence.java.miniprojet;
/**
 * 
 * @author REIFA Théo Réveillard - WEHRLIN Théo Réveillard
 *
 */
public class InputRandom
{

	/**
	 * Choix alÃ©atoire d'une colonne
	 * @return un numÃ©ro de colonne entre 0 et 8 (8 non compris)
	 */
	public int choixJoueur()
	{
		double rand8 = Math.random()*8;
		double rand7 = Math.floor(rand8); 
		return (int) (Math.round(rand7));
		
	}

}