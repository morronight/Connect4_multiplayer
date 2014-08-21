package fr.iutvalence.java.miniprojet;

import java.util.Map;

/**
 * Interface de gestion des affichages (g√©n√©rique)
 * @author ThÈo RÈveillard
 */
public interface Display
{
	/**
	 * Methode permettant d'afficher la grille sur l'ecran du joueur
	 * a qui elle est envoyee
	 * @param grille
	 *				: la grille du jeu au moment de l'envoi
	 */
	public void afficheGrille(Map<Position, Integer> grille);
	
	/**
	 * Methode permettant d'afficher un message sur l'ecran du joueur
	 * a qui il est envoye
	 * @param m
	 * 			: le message a afficher
	 */
	public void afficheMessage(String m);
	
}