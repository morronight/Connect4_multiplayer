package fr.iutvalence.java.miniprojet;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Definition de la partie de la fenetre qui gere les messages ecrit pour les joueurs
 * @author Théo Réveillard
 */
@SuppressWarnings("serial")
public class PanelInformation extends JPanel
{	
	/**
	 * Creation et mise en forme de cette partie de fenetre
	 */
	public PanelInformation () {
	super();
	this.setPreferredSize(new Dimension(560, 50));
	this.setLayout(new FlowLayout());
	}
	
/**
 * Methode de mise a jour du message
 * @param m
 * 			: le message a afficher sur la fenetre
 */
public void actualiser(String m) {
	this.removeAll();
	JLabel label = new JLabel(m);
	this.add(label);
	this.revalidate();
}
}
