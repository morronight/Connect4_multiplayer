package fr.iutvalence.java.miniprojet;

/**
 * Classe pour reprÃ©senter une position dans la grille
 * @author Théo Réveillard
 *
 */
public class Position
{

	private int colonne;
	private int ligne;
	
	/**
	 * Le constructeur.
	 * Cree un objet position englobant une ligne et une colonne
	 * @param x
	 * 			la ligne
	 * @param y
	 * 			la colonne
	 */
	public Position(int x, int y){
		this.ligne=x;
		this.colonne=y;		
	}
	
	/**
	 * L'accesseur de colonne
	 * @return le numero de la colonne
	 */
	public int getColonne()
	{
		return this.colonne;
	}
	/**
	 * L'accesseur de ligne
	 * @return le numero de la ligne
	 */
	public int getLigne()
	{
		return this.ligne;
	}

	@SuppressWarnings("unused")
	public boolean equals(Object obj)
	{
		if (!(obj instanceof Position)) return false;
		if (obj == null) return false;
		
		return ((this.colonne == ((Position) obj).colonne) && (this.ligne == ((Position) obj).ligne));
	}
	
	public int hashCode()
	{
		return (this.ligne/2+this.colonne/2);
	}
	
	
	
	
	
	
}
