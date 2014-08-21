package fr.iutvalence.java.miniprojet;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * La partie de la fenetre qui gere l'interraction avec le joueur
 * @author Théo Réveillard
 */
@SuppressWarnings("serial")
public class PanelAction extends JPanel implements ActionListener {
	
	private JButton[] tab_button = new JButton[8];
	private boolean updated;
	private int choix;
	
	/**
	 * Permet de savoir si un bouton a ete clique ou non
	 * @return l'etat de l'action
	 */
	public boolean isUpdated()
	{
		return this.updated;
	}

	/**
	 * Permet de modifier la valeur du booleen updated avec le booleen passe en parametre
	 * @param updated
	 * 				: le booleen a affecter
	 */
	public void setUpdated(boolean updated)
	{
		this.updated = updated;
	}

	/**
	 * Permet de savoir lequel des boutons a ete clique
	 * @return l'indice du bouton choisi
	 * @see PanelAction#actionPerformed(ActionEvent)
	 */
	public int getChoix()
	{
		return this.choix;
	}

	/**
	 * DÃ©finition de la partie de la fenetre qui contient les boutons
	 */
	public PanelAction(){
		super();
		this.setPreferredSize(new Dimension(560, 70));
        this.setLayout(new GridLayout(1, 8));
        for (int i=0;i<=7;i++){
        	this.tab_button[i] = new JButton(new ImageIcon("src/fr/iutvalence/java/miniprojet/fleche.png"));
        	this.add(this.tab_button[i]);
        	this.tab_button[i].addActionListener(this);
        }
        this.updated=false;
	}

	public void actionPerformed(ActionEvent arg0) {
		 for (int i=0;i<=7;i++){
			if (arg0.getSource() == this.tab_button[i]){
				this.choix=i;
				this.updated=true;
			}
		 }
	}
		 

		
}
