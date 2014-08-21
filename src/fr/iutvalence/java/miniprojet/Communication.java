package fr.iutvalence.java.miniprojet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Map;


/**
 * Definition du thread de communication entre le serveur et les clients
 * @author Théo Réveillard
 */
public class Communication extends Thread implements Display, Input 
{
	private DatagramSocket sock;
	private DatagramPacket pack;
	
	
	// Adresse du client :
	private InetAddress adrClient;
	// Port du client :
	private int portClient;

	
	
	/**
	 * Le constructeur du thread, attachant un packet,
	 * une adresse client, un port et un socket
	 * @param pack
	 * 				: le packet attribue au thread
	 * @param num_port
	 * 				: le port attribue au thread
	 */
	public Communication(DatagramPacket pack, int num_port)
	{
		super();
		this.pack = pack;
		this.adrClient = pack.getAddress();
		this.portClient = pack.getPort();
		try 
		{
			this.sock = new DatagramSocket(num_port);
			System.out.println("<Com> Dï¿½marrage rï¿½ussit");
		}
		catch (SocketException ex)
		{
			System.err.println("<Com> Dï¿½marrage impossible : Port dï¿½jï¿½ occupï¿½");	
		}
	}
	
	public void run()
	{
		;
	}


	@Override
	public int choixJoueur() 
	{
		String s="C";
		envoiReponse(s);
		// ATTENTE DE LA REPONSE DU JOUEUR
		String rep=attenteReponse();
		System.out.println("LE CHOIX RECU EST : "+rep);
		return Integer.parseInt(rep);
	}
	
	/**
	 * Methode blocante attendant de recevoir un "message" par datagramme
	 * @return le "message" recu par le thread
	 */
	public String attenteReponse()
	{
		this.pack = new DatagramPacket(new byte[512], 512);
		try 
		{
			this.sock.receive(this.pack);
		} 
		catch (IOException e) 
		{
			System.err.println("<Communication> Impossible de recevoir le message");
			e.printStackTrace();
		}
		System.out.println("<Communication> Traitement message reçu ");
		String reponse = null;
		try 
		{
			reponse = new String(this.pack.getData(),"ascii").trim();

		} 
		catch (UnsupportedEncodingException e) 
		{
			System.err.println("<Communication> Impossible de lire le message");
			e.printStackTrace();
		}
		return reponse;
	}
	
	/**
	 * Methode decrivant la maniere d'envoyer un "message" au serveur par le reseau
	 * @param s
	 * 			: le "message" a envoyer
	 */
	public void envoiReponse(String s){
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
		this.pack = new DatagramPacket(buffer,buffer.length,this.adrClient,this.portClient);
	
		// Envoi de la rï¿½ponse :
		try 
		{
			this.sock.send(this.pack);
			System.out.println("<Serveur> Envoi de la rï¿½ponse au client : "+s.trim());
		} 
		catch (IOException e) 
		{
			System.err.println("<Serveur> Impossible d'envoyer le message");
			e.printStackTrace();
		}	
	}


	@Override
	public void afficheGrille(Map<Position, Integer> grille) {
		// pacourir la grille
		// la coder sous une forme prï¿½cis 012001 ....
		String s = "G";
		for (int i=7;i>=0;i--)
		{
			for(int j=0;j<=7;j++)
			{
				Position p=new Position(i,j);
				if (!grille.containsKey(p))
					s=s+"0";
				else
					s=s+grille.get(p);
			}
		}
		
		// Envoi au client en UDP
		// Envoi de la rï¿½ponse au client :
		envoiReponse(s);
		
	}


	@Override
	public void afficheMessage(String m) {
		String s="M"+m;
		// Envoi au client en UDP
		// Envoi de la rï¿½ponse au client :
		envoiReponse(s);
		
	}


	/**
	 * Definit le message a envoyer pour annoncer le debut de la partie
	 */
	public void demarragePartie()
	{
		String s="D";		
		// Envoi au client en UDP
		// Envoi de la rï¿½ponse au client :
		envoiReponse(s);
		
	}
}
