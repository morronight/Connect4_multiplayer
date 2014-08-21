package fr.iutvalence.java.miniprojet;


import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/**
 * Puissance4 est la classe representant une partie de puissance 4.
 * 
 * @author REIFA Théo Réveillard - WEHRLIN Théo Réveillard
 * @version 3.0
 */

public class Puissance4
{

	private UserStats base;
	
	/**
	 * Represente une case vide
	 */
	public final static int JETON_NUL = 0;
	/**
	 * Represente les jetons du joueur 1
	 */
	public final static int JETON_JOUEUR1 = 1;
	/**
	 * Represente les jetons du joueur 2
	 */
	public final static int JETON_JOUEUR2 = 2;

	/**
	 * Enumeration de differents resultats possibles
	 */
	public enum Resultat { 
		/**
		 * Le code renvoye dans le cas d'un match nul 
		 */
		MATCH_NUL,
		/**
		 * Le code renvoye dans le cas de la victoire du joueur 1
		 */
		J1_GAGNE,
		/**
		 * Le code renvoye dans le cas de la victoire du joueur 2
		 */
		J2_GAGNE,
		/**
		 * Le code renvoye si la partie n'est pas terminee
		 */
		CONTINUER;
		};


	private List<Joueur> joueurs;
	


	private Map<Position,Integer> grille;

	
	/**
	 * Construit la partie, en attachant une base de donnees 
	 * et en creant un map representant la grille de jeu et une liste
	 * de joueurs
	 * @param base
	 * 				: la base de donnees attachee a la partie
	 */
	public Puissance4(UserStats base)
	{
		this.base=base;
		this.grille= new Hashtable<Position,Integer>();
		this.joueurs = new LinkedList<Joueur>();
	}
	
	/**
	 * Si la cle issue des parametre existe, on renvoie la valeur associee. Sinon
	 * on renvoie 0.
	 * @param ligne
	 * @param colonne
	 * @return la valeur associee aux parametres ou 0 si la cle n'existe pas
	 */
	public int getValeurPosition(int ligne, int colonne)
	{
		Position p= new Position(ligne,colonne);
		if (this.grille.containsKey(p)) return this.grille.get(p);
		else return 0;
	}

	
	/**
	 * Ajoute comme cle la position issue de ligne et colonne, et
	 * comme valeur le jeton 
	 * @param ligne
	 * @param colonne
	 * @param jeton
	 */
	public void ajoutValeur(int ligne, int colonne, int jeton)
	{
		Position p= new Position(ligne,colonne);
		this.grille.put(p,jeton);
	}
	/**
	 * Ajout d'un joueur Ã  la partie
	 * @param j le joueur Ã  ajouter
	 * @throws TropDeJoueursException s'il y a dÃ©jÃ  2 joueurs
	 */
	public void addJoueur(Joueur j) throws TropDeJoueursException
	{
		if (this.joueurs.size() == 2) throw new TropDeJoueursException();
		
		this.joueurs.add(j);
	}

	/**
	 * Sert a connaitre la taille de la colonne passee en parametre,
	 * afin de determiner a quelle hauteur poser le jeton.
	 * 
	 * @param j 
	 * 			Le numero de la colonne.
	 * 
	 * @return la ligne la plus haute de la colonne
	 */
	private int getTailleColonne(int j)
	{
		int i =0;
		Position p = null;
		while (i<8)
		{
			p=new Position(i,j);
			if (!this.grille.containsKey(p)) break;
			i++;
		}
		return i;
	}

	/**
	 * Joue le tour.
	 * Le joueur a jouer choisit une colonne, un jeton est ajoute a la grille
	 * le joueur a jouer est change et l'algorithme d'etat de la grille est lance,
	 * et renvoie la valeur associee a l'etat de la grille, que cette methode renvoie a son tour.
	 * @return L'etat de la grille
	 */
	public Puissance4.Resultat tourSuivant() 
	{
		int j =0;
		int i =0;
		while (true)
		{
			j=this.joueurs.get(this.grille.size()%2).getInput().choixJoueur();
			i = this.getTailleColonne(j);
			if (i <8) break;
		}
		
		if (this.grille.size()%2==0)
		{	
			this.ajoutValeur(i, j, JETON_JOUEUR1);
		}
		else
		{
			this.ajoutValeur(i, j, JETON_JOUEUR2);
		}
		
		return this.etatGrille();
	}	


	/**
	 * Determine aleatoirement le numero d'une colonne de 0 a 8 (8 non compris)
	 * @deprecated
	 * @return La colonne choisie par le joueur (ordinateur)
	 */
	public int choixJoueur()
	{
		double rand8 = Math.random()*8;
		double rand7 = Math.floor(rand8); 
		return (int) (Math.round(rand7));
	
	}

	/**
	 * Algorithme qui determine l'etat de la grille
	 * @return la code d'etat de la grille (joueur1,joueur2,matchnul ou continuer)
	 */
	private Puissance4.Resultat etatGrille()
	{
		int verif;
		int i;
		int j;
		for (i=0; i<8; i++)
		{
			for (j=0; j<8; j++)
			{
				if (j<=4)
				{
					verif = this.getValeurPosition(i,j) * this.getValeurPosition(i,j+1) * this.getValeurPosition(i,j+2) * this.getValeurPosition(i,j+3);
					if (verif==1) return Puissance4.Resultat.J1_GAGNE;
					if (verif==16) return Puissance4.Resultat.J2_GAGNE;
					
				}
				if (j <= 4 && i <= 4)
				{
					verif = this.getValeurPosition(i,j) * this.getValeurPosition(i+1,j+1) * this.getValeurPosition(i+2,j+2) * this.getValeurPosition(i+3,j+3);
					if (verif==1) return Puissance4.Resultat.J1_GAGNE;
					if (verif==16) return Puissance4.Resultat.J2_GAGNE;
				}
				if (i <= 4)
				{
					verif = this.getValeurPosition(i,j) * this.getValeurPosition(i+1,j) * this.getValeurPosition(i+2,j) * this.getValeurPosition(i+3,j);
					if (verif==1) return Puissance4.Resultat.J1_GAGNE;
					if (verif==16) return Puissance4.Resultat.J2_GAGNE;
				}
				if (j >= 3 && i <= 4)
				{
					verif = this.getValeurPosition(i,j) * this.getValeurPosition(i+1,j-1) * this.getValeurPosition(i+2,j-2) * this.getValeurPosition(i+3,j-3);
					if (verif==1) return Puissance4.Resultat.J1_GAGNE;
					if (verif==16) return Puissance4.Resultat.J2_GAGNE;
				}
			}
		}
		if (this.grille.size()==64) return Puissance4.Resultat.MATCH_NUL;
		return Puissance4.Resultat.CONTINUER;
	}

	/**
	 * Deroulement de la partie (s'arrete quand le code renvoye par
	 * la fonction tourSuivant() est different de continuer).
	 * Affiche la grille.
	 * @return l'etat de la partie terminee
	 * @throws PartieIncompleteException s'il manque un display ou un joueur
	 * @throws UserStatsAccessException 
	 */
	public Puissance4.Resultat start() throws PartieIncompleteException, UserStatsAccessException
	{
		if (this.joueurs.get(1)!=null)
		{
			Puissance4.Resultat a = Puissance4.Resultat.CONTINUER;
			this.joueurs.get(0).getDisplay().afficheGrille(this.grille);
			this.joueurs.get(1).getDisplay().afficheGrille(this.grille);
			while (true)
			{
				this.joueurs.get(this.grille.size()%2).getDisplay().afficheMessage(this.joueurs.get(this.grille.size()%2).getPseudo()+" a vous de jouer!");
				this.joueurs.get((this.grille.size()+1)%2).getDisplay().afficheMessage("C'est a "+this.joueurs.get(this.grille.size()%2).getPseudo()+" de jouer!");
				a=tourSuivant();
				this.joueurs.get(0).getDisplay().afficheGrille(this.grille);
				this.joueurs.get(1).getDisplay().afficheGrille(this.grille);
				a=etatGrille();
				if (a !=  Puissance4.Resultat.CONTINUER)
					{
					if (a== Puissance4.Resultat.MATCH_NUL)
					{
						this.joueurs.get(0).getDisplay().afficheMessage("La partie se finit sur un match nul !");
						this.joueurs.get(1).getDisplay().afficheMessage("La partie se finit sur un match nul !");
						this.base.ajoutPartieNulle(this.joueurs.get(0).getPseudo());
						this.base.ajoutPartieNulle(this.joueurs.get(1).getPseudo());
					}
					else
					{
						this.joueurs.get((this.grille.size()+1)%2).getDisplay().afficheMessage("Vous avez gagnee !");
						this.base.ajoutPartieGagnee(this.joueurs.get((this.grille.size()+1)%2).getPseudo());
						this.joueurs.get(this.grille.size()%2).getDisplay().afficheMessage("Vous avez perdu, le gagnant est "+this.joueurs.get((this.grille.size()+1)%2).getPseudo()+" !");
						this.base.ajoutPartiePerdue(this.joueurs.get(this.grille.size()%2).getPseudo());
					}
					return a;
					}
			}
		}
		throw new PartieIncompleteException();
	}

	/**
	 * L'accesseur de la grille
	 * @return la grille, sous forme de map
	 */
	public Map<Position, Integer> getGrille()
	{
		return this.grille;
	}
	
	
	
	/**
	 * Permet de connaitre la liste des joueurs
	 * @return les deux joueurs
	 */
	public List<Joueur> getJoueurs()
	{
		return this.joueurs;
	}
}