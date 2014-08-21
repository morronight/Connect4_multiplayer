package fr.iutvalence.java.miniprojet;

/**
 * Interface servant a la toute premiere fenetre de l'application,
 * chargee de recuperer les identifications des joueurs (pseudo et mdp)
 * @author Réveillard Théo
 */
public interface accueil {

	/**
	 * Recupere le pseudo entre par le joueur
	 * @return le pseudo du joueur
	 * @throws UserStatsAccessException
	 */
	public String getpseudo() throws UserStatsAccessException;
	
}
