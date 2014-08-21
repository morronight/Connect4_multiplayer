package fr.iutvalence.java.miniprojet;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Classe qui sert au choix des joueurs
 * 
 * @author Théo Réveillard
 *
 */
public class InputASCII
{
	private InputStream in;
	
	/**
	 * Cree un Input
	 * @param in
	 * 			: methode d'interraction (console, fenetre ...)
	 */
	public InputASCII(InputStream in)
	{
		super();
		this.in = in;
	}

	/**
	 * saisie d'une colonne par un joueur, au clavier
	 * @param grille 
	 * @return le choix de colonne
	 */	
	public int choixJoueur()
	{
		while (true)
		{
			Scanner sc = new Scanner(this.in);
			int choix = sc.nextInt();
			if ( choix >= 0 && choix < 8) return choix;
		}
	}
}
