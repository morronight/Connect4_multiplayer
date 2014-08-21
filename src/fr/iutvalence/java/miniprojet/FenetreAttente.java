package fr.iutvalence.java.miniprojet;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Definition d'une fenetre intermediaire quand un joueur attend qu'un
 * second le rejoigne
 * @author Théo Réveillard
 */
@SuppressWarnings("serial")
public class FenetreAttente  extends JFrame{
	
	
	private JPanel container = new JPanel();
	private PanelMsgAttente panattente;
	
	/**
	 * Creation et mise en forme de la fenetre d'attente
	 */
	public FenetreAttente(){

		this.panattente = new PanelMsgAttente();
		//Titre de la fenetre :
        this.setTitle("Fenetre d'accueil");
        //Taille : (Largeur, Hauteur)
        this.setSize(500, 100);
        //Positionnement au centre :
        this.setLocationRelativeTo(null);
        // Action fermeture :
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.container.add(this.panattente,BorderLayout.NORTH);
        this.setContentPane(this.container);
		this.setVisible(true);
		}
	
	
	}

