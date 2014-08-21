package fr.iutvalence.java.miniprojet;

import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Definition de la fenetre qui apparait au lancement de l'application
 * @author Théo Réveillard
 */
@SuppressWarnings("serial")
public class FenetreAccueil  extends JFrame implements accueil 
{
	
	
	private JPanel container = new JPanel();
	private PanelDemandePseudo panpseudo;
	private UserStats base;
	/**
	 * Message d'erreur envoye en cas de probleme d'identifiant
	 */
	public static String msg1="Le pseudo ou le mot de passe est incorrect !";
	/**
	 * Message d'erreur envoye si le pseudo specifie est deja utilise
	 * dans le cas d'un nouveau compte
	 */
	public static String msg2="Le pseudo est deja  utilise !";
	/**
	 * Message renvoye en cas de succes de creation d'un compte joueur
	 */
	public static String msg3="Creation du compte avec succes !";
	/**
	 * Message renvoye en cas de succes de connexion avec des identifiants contenus
	 * dans la base de donnees
	 */
	public static String msg4="Connexion reussie !";
	
	/**
	 * Creation de la fenetre et mise en forme
	 * @param base
	 * 				: la base de donnees associee a la fenetre
	 */
	public FenetreAccueil(UserStats base){
		this.base=base;
		this.panpseudo = new PanelDemandePseudo();
		//Titre de la fenetre :
        this.setTitle("Fenetre d'accueil");
        //Taille : (Largeur, Hauteur)
        this.setSize(500, 120);
        //Positionnement au centre :
        this.setLocationRelativeTo(null);
        // Action fermeture :
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ajout des composants au container
        this.container.add(this.panpseudo,BorderLayout.NORTH);
        this.setContentPane(this.container);
		this.setVisible(true);
		}
	
	public String getpseudo() throws UserStatsAccessException
	{
		while (true)
		{
			this.panpseudo.setUpdated(false);
			while (!this.panpseudo.isUpdated()){try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
}
			this.panpseudo.setUpdated(false);
			if (this.panpseudo.isSeco()) 
			{
				if (this.base.verifpassw(this.panpseudo.getPseudo(),this.panpseudo.getPassw())) 
				{
					JOptionPane.showMessageDialog(new Frame(),msg4);
					return this.panpseudo.getPseudo(); 
				}
				else {JOptionPane.showMessageDialog(new Frame(),msg1);}
			}
			else 
			{
				if (this.base.rechJoueur(this.panpseudo.getPseudo())) 
					{JOptionPane.showMessageDialog(new Frame(),msg2);}
				else
				{ 
					this.base.ajoutJoueur(this.panpseudo.getPseudo(), this.panpseudo.getPassw()); 
					JOptionPane.showMessageDialog(new Frame(),msg3);
					return this.panpseudo.getPseudo();
				}
			}
		}
	}
}

