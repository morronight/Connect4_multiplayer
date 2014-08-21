package fr.iutvalence.java.miniprojet;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JPanel;

/**
 * Classe qui permet de savoir, quand elle est appellee pour l'affichage de la grille,
 * laquelle des trois images (case vide, case jaune, case rouge), doit etre inscrite.
 * @author Théo Réveillard
 */
@SuppressWarnings("serial")
public class GraphicCase extends JPanel {
	
	private int couleurCase;

	/**
	 * Permet d'attribuer a la GraphicCase une couleur
	 * @param couleur
	 * 			: la couleur du jeton du joueur
	 */
	public GraphicCase(int couleur) {
		this.couleurCase = couleur;
	}
	
    @Override
	public void paintComponent(Graphics g){
		
		String path = "case.png";
		if (this.couleurCase == 1){
			path = "casejaune.png";
		}
		else if (this.couleurCase == 2){
			path = "caserouge.png";
		}
	
		URL url = getClass().getResource(path);
        Image img = Toolkit.getDefaultToolkit().getImage(url);
        g.drawImage(img, 0, 0, this); 
		
	}
	
}
