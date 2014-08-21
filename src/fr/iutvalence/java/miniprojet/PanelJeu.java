package fr.iutvalence.java.miniprojet;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Map;

import javax.swing.JPanel;

/**
 * Partie de la fenetre de jeu contenant la grille
 * @author Théo Réveillard
 */
@SuppressWarnings("serial")
public class PanelJeu extends JPanel {

	
	
	/**
	 * Met en forme la partie de la fenetre qui contient la grille
	 */
	public PanelJeu(){
		super();
		this.setPreferredSize(new Dimension(560, 560));
		// Layout ï¿½ utiliser sur le contentPlace, le "jeu", 8 lignes et 8 colonnes
	    this.setLayout(new GridLayout(8, 8));
	}
	
	
	/**
	 * Methode qui dÃ©finit l'affichage de la grille passe en parametre
	 * @param grille
	 */
	public void afficheGrille(Map<Position, Integer> grille){
		this.removeAll();
		for (int i=7;i>=0;i--)
		{
			for(int j=0;j<=7;j++)
			{
				Position p=new Position(i,j);
				if (!grille.containsKey(p))
					this.add(new GraphicCase(0));
				else
					this.add(new GraphicCase(grille.get(p)));
			}
		}
		this.revalidate();
	}
	
}
