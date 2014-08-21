package fr.iutvalence.java.miniprojet;

/**
 * Interface de gestion des interractions entre les actions des utilisateurs
 * et la base de donnees
 * @author Théo Réveillard
 */
public interface UserStats {

	/**
	 * Recherche la presence du pseudo passe en parametre dans la BDD
	 * @param pseudo
	 * @return vrai ou faux, selon la presence ou non du pseudo dans la BDD
	 * @throws UserStatsAccessException
	 */
	public boolean rechJoueur(String pseudo) throws UserStatsAccessException;
	
	/**
	 * Ajoute le joueur dans la BDD avec les identifiants passes en parametre
	 * @param pseudo
	 * @param password
	 * @throws UserStatsAccessException
	 */
	public void ajoutJoueur(String pseudo, String password) throws UserStatsAccessException;
	
	/**
	 * Verifie si le mdp entre est correct
	 * @param pseudo
	 * @param password
	 * @return vrai si identification ok, faux sinon
	 * @throws UserStatsAccessException
	 */
	public boolean verifpassw(String pseudo, String password) throws UserStatsAccessException;
	
	/**
	 * Recherche les stats du joueur
	 * @param pseudo
	 * @return les statistiques du joueur passe en parametre
	 * @throws UserStatsAccessException
	 */
	public ScoreJ scoreJoueur(String pseudo) throws UserStatsAccessException;
	
	/**
	 * Ajoute une partie gagnee supplementaire aux stats du joueur
	 * @param pseudo
	 * @throws UserStatsAccessException
	 */
	public void ajoutPartieGagnee(String pseudo) throws UserStatsAccessException;
	
	/**
	 * Ajoute une partie perdue supplementaire aux stats du joueur
	 * @param pseudo
	 * @throws UserStatsAccessException
	 */
	public void ajoutPartiePerdue(String pseudo) throws UserStatsAccessException;
	
	/**
	 * Ajoute une partie nulle supplementaire aux stats du joueur
	 * @param pseudo
	 * @throws UserStatsAccessException
	 */
	public void ajoutPartieNulle(String pseudo) throws UserStatsAccessException;
}
