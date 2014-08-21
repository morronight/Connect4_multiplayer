package fr.iutvalence.java.miniprojet;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Definit le contenu de la partie de la fenetre qui rappelle les stats du joueur
 * @author Théo Réveillard
 */
@SuppressWarnings("serial")
public class PanelScore extends JPanel{
	private JLabel labellpseudo;
	private String pseudo;
	
	/**
	 * Mise en forme du contenu
	 * @param base
	 * 				: la base de donnees associee a la partie
	 * @param pseudo
	 * 				: le pseudo du joueur utilisant la fenetre sur lequel se trouve ce contenu
	 * @throws UserStatsAccessException
	 */
	public PanelScore(UserStats base, String pseudo) throws UserStatsAccessException{
		this.pseudo=pseudo;
		this.setPreferredSize(new Dimension(800,100));
		this.setLayout(new FlowLayout());
		ScoreJ score = base.scoreJoueur(this.pseudo);
		this.labellpseudo = new JLabel("Votre pseudo : "+this.pseudo+"  | Parties gagnees : "+String.valueOf(score.getParties_gagnees())+"  | Parties nulles : "+String.valueOf(score.getParties_nulles())+"  | Parties perdues : "+String.valueOf(score.getParties_perdues()));
		this.add(this.labellpseudo);
        
	}

}
