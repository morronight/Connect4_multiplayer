package fr.iutvalence.java.miniprojet;

/**
 * Classe dÃ©finissant un joueur, lui attribuant un pseudo, un display et un input
 * @author Théo Réveillard
 *
 */
public class Joueur
{
	private final String pseudo;
	
	private final Display display;
	private final Input input;
	
	/**
	 * Cree le joueur avec les parametres specifies
	 * @param pseudo
	 * 				: le pseudo choisi
	 * @param display
	 * 				: l'affichage personnel du joueur
	 * @param input
	 * 				: la moyen d'interraction du joueur avec l'interface
	 */
	public Joueur(String pseudo, Display display, Input input)
	{
		super();
		this.pseudo = pseudo;
		this.display = display;
		this.input = input;
	}

	/**
	 * Permet de connaitre le pseudo du joueur courant
	 * @return le pseudo du joueur
	 */
	public String getPseudo()
	{
		return this.pseudo;
	}

	/**
	 * Permet de connaitre le diplay du joueur courant
	 * @return le display du joueur
	 */
	public Display getDisplay()
	{
		return this.display;
	}

	/**
	 * Permet de connaitre l'input du joueur courant
	 * @return l'input du joueur
	 */
	public Input getInput()
	{
		return this.input;
	}
	
	
}
