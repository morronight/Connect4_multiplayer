package fr.iutvalence.java.miniprojet;

/**
 * Classe de test de la connexion a une BDD
 * @author Théo Réveillard
 */
public class testBd
{

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws UserStatsAccessException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, UserStatsAccessException
	{
		UserStats base= new DbUserStats("com.mysql.jdbc.Driver","jdbc:mysql://gigondas/awehrlin","awehrlin","awehrlin");
	boolean b=base.rechJoueur("ToNiO");
	System.out.println(b);
		
		

	}

}
