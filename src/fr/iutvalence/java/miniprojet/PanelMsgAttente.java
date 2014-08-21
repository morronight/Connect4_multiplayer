package fr.iutvalence.java.miniprojet;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Definition du contenu de la fenetre d'attente d'un second joueur
 * @author Th�o R�veillard
 */
@SuppressWarnings("serial")
public class PanelMsgAttente extends JPanel{

	private JLabel msg;

	/**
	 * Mise en forme du contenu
	 */
	public PanelMsgAttente(){
		super();
		this.setPreferredSize(new Dimension(400,70));
        this.setLayout(new FlowLayout());
        this.msg = new JLabel("Attente d'un deuxi�me joueur ...");

        	this.add(this.msg);

	}
}
