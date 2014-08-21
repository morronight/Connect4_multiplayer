package fr.iutvalence.java.miniprojet;

/**
 * Programme de test du Puissance4
 * 
 * @version 3.0
 * @author Théo Réveillard REIFA - Théo Réveillard WEHRLIN
 *
 */
public class TestPuissance4
{
	/**
	 * Main du test
	 * @param args
	 * @throws TropDeJoueursException
	 * @throws PartieIncompleteException
	 * @throws UserStatsAccessException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws TropDeJoueursException, PartieIncompleteException, UserStatsAccessException, ClassNotFoundException
	{
		@SuppressWarnings("unused")
		String pseudo1=null;;
		@SuppressWarnings("unused")
		String pseudo2=null;
		UserStats base= new DbUserStats("com.mysql.jdbc.Driver","jdbc:mysql://gigondas/awehrlin","awehrlin","awehrlin");
		FenetreAccueil fena1 = new FenetreAccueil(base);
		classeAcc f1 = new classeAcc(fena1);
		pseudo1=f1.getA().getpseudo();
		pseudo2=f1.getA().getpseudo();
		fena1.setVisible(false);
		Puissance4 p= new Puissance4(base);
		FenetreJeu fen1= new FenetreJeu(base,"ToNiO");
		Joueur j1 = new Joueur("ToNiO", fen1, fen1);
		Joueur j2 = new Joueur("Maxime", fen1, fen1);
		p.addJoueur(j1);
		p.addJoueur(j2);
		p.start();
	}

}