package fr.iutvalence.java.miniprojet;

/**
 * Classe "fictive" servant à lier l'identification
 * des joueurs a l'application
 * @author Th�o R�veillard
 *
 */
public class classeAcc {
	
	private accueil A;
	
	/**
	 * Le constructeur de la classe
	 * @param A
	 * 			: l'interface d'accueil liee a l'application
	 */
	public classeAcc(accueil A)
	{
		this.A=A;
	}

	/**
	 * Retourne la reference de l'interface d'accueil liee a l'application
	 * @return la reference de l'interface d'accueil
	 */
	public accueil getA() 
	{
		return this.A;
	}

}
