package fr.iutvalence.java.miniprojet;

import java.awt.BorderLayout;

import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Definition de la fenetre de jeu, une fois la partie lancee
 * @author Théo Réveillard
 *
 */
@SuppressWarnings("serial")
public class FenetreJeu  extends JFrame implements Display, Input {
	
	
	private JPanel container = new JPanel();
	private PanelJeu panjeu;
	private PanelAction panact;
    private PanelInformation paninfo;
    private PanelScore panscore;
    private String pseudo;
    private UserStats base;
	
	/**
	 * Creation et mise en forme de la fenetre de jeu
	 * @param base
	 * 				: la base de donnees reliee au jeu
	 * @param pseudo
	 * 				: le pseudo du joueur utilisant la fenetre
	 * @throws UserStatsAccessException
	 */
	public FenetreJeu(UserStats base, String pseudo) throws UserStatsAccessException{
		this.pseudo=pseudo;
		this.base=base;
		this.panjeu = new PanelJeu();
		this.panact = new PanelAction();
		this.paninfo = new PanelInformation();
		this.panscore= new PanelScore(this.base, this.pseudo);
		//Titre de la fenetre :
        this.setTitle("Puissance 4");
        //Taille : (Largeur, Hauteur)
        this.setSize(700, 800);
        //Positionnement au centre :
        this.setLocationRelativeTo(null);
        // Action fermeture :
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.container.add(this.panact,BorderLayout.NORTH);
        this.container.add(this.panjeu,BorderLayout.CENTER);
        this.container.add(this.paninfo, BorderLayout.SOUTH);
        this.container.add(this.panscore,BorderLayout.SOUTH);
        this.setContentPane(this.container);
		this.setVisible(true);
		}

	
	public void afficheGrille (Map<Position, Integer> grille)
	{
	this.panjeu.afficheGrille(grille);
	}
	
	public void afficheMessage(String m)
	{
	this.paninfo.actualiser(m);
	}

	public int choixJoueur()
	{
	this.panact.setUpdated(false);
	while(!this.panact.isUpdated()){try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	return this.panact.getChoix();
	}


}
