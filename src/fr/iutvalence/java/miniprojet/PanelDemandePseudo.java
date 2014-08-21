package fr.iutvalence.java.miniprojet;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Partie de la fenetre d'accueil qui demande a l'utilisateur de s'identifier
 * @author Théo Réveillard
 */
@SuppressWarnings("serial")
public class PanelDemandePseudo extends JPanel implements ActionListener{
	private JTextField jtf1;
	private JPasswordField jp2;
	private JButton Jb1;
	private JButton Jb2;
	private JLabel labelpseudo;
	private JLabel labelpassw;
	private boolean seco;
	private boolean updated;
	private String pseudo;
	private String passw;
	
	/**
	 * Permet de retourner le pseudo
	 * @return le pseudo entre par le joueur
	 */
	public String getPseudo() {
		return this.pseudo;
	}

	/**
	 * Permet de retourner le mdp
	 * @return le mdp entre par le joueur
	 */
	public String getPassw() {
		return this.passw;
	}

	/**
	 * Definit la partie de la fenetre dans laquelle il est appelle
	 */
	public PanelDemandePseudo(){
		super();
		this.setPreferredSize(new Dimension(400,70));
		this.updated=false;
        this.setLayout(new GridLayout(3, 2));
        this.jtf1= new JTextField();
        this.jp2= new JPasswordField();
        this.labelpseudo = new JLabel("Entrer votre pseudo :");
        this.labelpassw = new JLabel("Entrer votre password :");
        	this.Jb1 = new JButton("Se connecter");
        	this.Jb2 = new JButton("Creer un compte");
        	this.add(this.labelpseudo);
        	this.add(this.jtf1);
        	this.add(this.labelpassw);
        	this.add(this.jp2);
        	this.add(this.Jb1);
        	this.add(this.Jb2);
        	this.Jb1.addActionListener(this);
        	this.Jb2.addActionListener(this);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==this.Jb1) this.seco=true;
		else this.seco=false;
		this.pseudo=this.jtf1.getText();
		this.passw=this.jp2.getText();
		this.jtf1.setText("");
		this.jp2.setText("");
		this.updated=true;
		
	}
	/**
	 * Permet de savoir si l'utilisateur a cliquer sur le bouton "se connecter" ou
	 * "creer un compte"
	 * @return vrai ou faux (vrai si clic sur "se connecter", faux si clic sur "creer un compte")
	 */
	public boolean isSeco() {
		return this.seco;
	}

	/**
	 * Permet de connaitre l'etat de Updated, a vrai ou faux
	 * @return l'etat de Updated
	 */
	public boolean isUpdated() {
		return this.updated;
	}

	/**
	 * modifie la valeur de l'attribut updated
	 * @param updated
	 * 				: vrai ou faux
	 */
	public void setUpdated(boolean updated)
	{
		this.updated = updated;
	}


}
