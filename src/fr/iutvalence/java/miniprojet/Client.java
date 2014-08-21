package fr.iutvalence.java.miniprojet;

import java.net.*;
import java.util.Hashtable;
import java.util.Map;
import java.io.*;


/**
 * La partie client de la mise en reseau de l'application
 * @author Théo Réveillard
 */
public class Client {
	
	private DatagramSocket sock;
	private InetSocketAddress Adr;
	
	/**
	 * Le constructeur du client, qui lui attribue un socket UDP.
	 */
	public Client()
	{
		try {
			this.sock = new DatagramSocket();
		}
		catch (SocketException ex){
			System.err.println("Port dï¿½jï¿½ occupï¿½e");	
		}
		
	}
	
	/**
	 * Methode definissant le comportement du client quand celui-ci est connecte 
	 * au serveur.
	 * @throws ClassNotFoundException
	 * @throws UserStatsAccessException
	 */
	//procedure associï¿½e au fonctionnement du serveur :
	public void activiteClient() throws ClassNotFoundException, UserStatsAccessException
	{
		String pseudo;
		FenetreJeu fen=null;
		UserStats base= new DbUserStats("com.mysql.jdbc.Driver","jdbc:mysql://gigondas/awehrlin","awehrlin","awehrlin");
		FenetreAccueil fena1 = new FenetreAccueil(base);
		classeAcc f = new classeAcc(fena1);
		pseudo=f.getA().getpseudo();
		fena1.setVisible(false);
		FenetreAttente fenattente= new FenetreAttente();
		this.Adr = null;
		try {
			InetAddress In = InetAddress.getByName("172.26.122.5");
	// on envoit d'abord le pseudo au puissance4Serveur, ensuite on s'occupera de changer le port de transmission/reception
			this.Adr = new InetSocketAddress(In,12345);
		} 
		catch (UnknownHostException e) {
			System.err.println("Impossible de crï¿½er l'adresse IP");
			e.printStackTrace();
		}
		
		envoiReponse(pseudo);
		String reponse;
		
		// Ecoute de la rï¿½ponse du serveur :
		// Dans cette partie du code, nous testons differentes lettre au debut 
		// datagramme "reponse". Ces lettres sont introduites pour savoir 
		// quel type de transmission est recu.
		while(true)
		{
			reponse=attenteReponse();
			// D correspond au code "debut de connexion"
			if (reponse.charAt(0)=='D')
			{
				fenattente.setVisible(false);
				fen= new FenetreJeu(base,pseudo);
			}
			// M correspond au code de "message"
			else if (reponse.charAt(0)=='M')
			{
				String msg="";
				int i=1;
				Character c;
				while (i<reponse.length())
				{
					c = reponse.charAt(i);
					msg+=c.toString();
					i++;
				}
				fen.afficheMessage(msg);
			}
			// G correspond au code de "grille"
			else if(reponse.charAt(0)=='G')
			{
				Map<Position,Integer> grille=new Hashtable<Position,Integer>();
				int ligne=7;
				int colonne=0;
				for (int j=1; j<reponse.length(); j++)
				{
					if (reponse.charAt(j)!=0)
					{
						Position p=new Position(ligne,colonne);
						Character c = reponse.charAt(j);
						String aff=c.toString();
						grille.put(p, Integer.parseInt(aff));
					}
					if (colonne==7){ colonne=0; ligne--;}
					else colonne++;
				}
				fen.afficheGrille(grille);
				
			}
			// E correspond au code de "end"
			else if (reponse.charAt(0)=='E')break;
			// C correspond au code de "choix"
			else if (reponse.charAt(0)=='C')
			{
				Input in = fen;
				int choix = in.choixJoueur();
				String s = Integer.toString(choix);
				envoiReponse(s);	
			}
		}
		fen.setVisible(false);
		this.sock.close();	
	}
	
	/**
	 * Methode qui permet d'envoyer les "messages" au serveur
	 * (generalement, le choix de colonne du joueur)
	 * @param s
	 * 			: le "message"
	 */
	public void envoiReponse(String s)
	{
		byte[] buffer = new byte[512];
		try 
		{
			buffer = s.trim().getBytes("ascii");
		} 
		catch (UnsupportedEncodingException e) 
		{
			System.err.println("Impossible d'encoder le message");
			e.printStackTrace();
		}
	
		// Datagramme UDP d'envoi :
		DatagramPacket pack=null;
		try 
		{
			pack = new DatagramPacket(buffer,buffer.length,this.Adr);
		} 
		catch (SocketException e1) 
		{
			e1.printStackTrace();
		}
	
		// Envoi de la rï¿½ponse :
		try 
		{
			this.sock.send(pack);
			System.out.println("<Client> Envoi de la rï¿½ponse au client : "+s.trim());
		} 
		catch (IOException e) 
		{
			System.err.println("<Client> Impossible d'envoyer le message");
			e.printStackTrace();
		}	
	}
	
	/**
	 * Methode (blocante) qui attend de recevoir une reponse du serveur
	 * @return sous forme de chaine de caractere, le "message" recu
	 */
	public String attenteReponse(){
		DatagramPacket p = new DatagramPacket(new byte[512], 512);
		try 
		{
			this.sock.receive(p);
		} 
		catch (IOException e) 
		{
			System.err.println("<Client> Impossible de recevoir le message");
			e.printStackTrace();
		}
		System.out.println("<Client> Traitement message reï¿½u ");
		String reponse = null;
		try 
		{
			reponse = new String(p.getData(),"ascii").trim();

		} 
		catch (UnsupportedEncodingException e) 
		{
			System.err.println("<Client> Impossible de lire le message");
			e.printStackTrace();
		}
		Adr = new InetSocketAddress(p.getAddress(),p.getPort());
		return reponse;
	}




	
	
	/**
	 * Demarrage du client
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws UserStatsAccessException
	 */
	public static void main (String[] args) throws ClassNotFoundException, UserStatsAccessException
	{
		Client serv = new Client();
		
		serv.activiteClient();
		
	}
}

