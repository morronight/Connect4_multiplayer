package fr.iutvalence.java.miniprojet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;

/**
 * Definition du fonctionnement du serveur
 * @author Théo Réveillard
 */
public class Puissance4Serveur
{
	
	private DatagramSocket sock;
	
	int num_port;
	
	/**
	 * Definit le serveur en lui attribuant un socket sur un port
	 */
	public Puissance4Serveur()
	{
		try 
		{
			this.sock = new DatagramSocket(12345);
			System.out.println("<Serveur> Démarrage réussit");
		}
		catch (SocketException ex)
		{
			System.err.println("<Serveur> Démarrage impossible : Port déjà occupé ");	
		}
	}

	/**
	 * Definit le comportement du serveur au demarrage
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void start() throws IOException, ClassNotFoundException
	{
		// JOUEUR 1 :
		
		// Buffer :
		byte[] buffer = new byte[512];
		
		// Datagramme UDP : 
		DatagramPacket pack;
		
		// Attente Joueur 1
		pack = new DatagramPacket(buffer,512);
		
		// Attente et reception du premier datagramme du client :
		System.out.println("<Serveur> Attente d'un packet ...");		
		try 
		{
			this.sock.receive(pack);
			System.out.println("<Serveur> Premier joueur connecté");
		} 
		catch (IOException e) 
		{
			System.err.println("<Serveur> Impossible de recevoir le message");
			e.printStackTrace();
		}
		
		Communication comJoueur1 = new Communication(pack,12346);	
		comJoueur1.start();
		
		// decodage pseudo :
		String s = null;
		try 
		{
			s = new String(pack.getData(),"ascii");
			s = s.trim();

		} 
		catch (UnsupportedEncodingException e) 
		{
			System.err.println("<Communication> Impossible de lire le message");
			e.printStackTrace();
		}
		System.out.println("<Serveur> Pseudo : "+s);
		Joueur joueur1 = new Joueur(s,comJoueur1,comJoueur1);


		
		// JOUEUR 2
				
		// Attente et reception du premier datagramme du client :
		System.out.println("<Serveur> Attente d'un packet ...");		
		try 
		{
			this.sock.receive(pack);
			System.out.println("<Serveur> Deuxième joueur connecté");
		} 
		catch (IOException e) 
		{
			System.err.println("<Serveur> Impossible de recevoir le message");
			e.printStackTrace();
		}
		
		Communication comJoueur2 = new Communication(pack,12347);	
		comJoueur2.start();
		// decodage pseudo :
	s = null;
		try 
		{
			s = new String(pack.getData(),"ascii");
			s = s.trim();

		} 
		catch (UnsupportedEncodingException e) 
		{
			System.err.println("<Communication> Impossible de lire le message");
			e.printStackTrace();
		}
		System.out.println("<Serveur> Pseudo : "+s);
		Joueur joueur2 = new Joueur(s,comJoueur2,comJoueur2);


		// CREATION PARTIE :
		UserStats base= new DbUserStats("com.mysql.jdbc.Driver","jdbc:mysql://gigondas/awehrlin","awehrlin","awehrlin");
		Puissance4 p4 = new Puissance4(base);
		
		// ENVOI AUX 2 JOUEURS D'UN DATAGRAMME PREVENANT
		// LE DEBUT DE LA PARTIE
		
		comJoueur1.demarragePartie();
		comJoueur2.demarragePartie();
		
		try {
			p4.addJoueur(joueur1);
			p4.addJoueur(joueur2);
			p4.start();
		} catch (TropDeJoueursException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (PartieIncompleteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserStatsAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Demarrage du serveur
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws UserStatsAccessException
	 * @throws IOException
	 */
	public static void main (String[] args) throws ClassNotFoundException, UserStatsAccessException, IOException
	{
		Puissance4Serveur serv = new Puissance4Serveur();
		serv.start();
		
	}
}
