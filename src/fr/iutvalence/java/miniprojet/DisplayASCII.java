package fr.iutvalence.java.miniprojet;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Classe qui g√®re l'affichage de la grille de jeu
 * sous forme ASCII
 * @author ThÈo RÈveillard
 *
 */
public class DisplayASCII implements Display
{
	
	private PrintStream out;
	
	/**
	 * Affichage en ASCII
	 * @param o
	 */
	public DisplayASCII(OutputStream o)
	{
		try
		{
			this.out= new PrintStream(o, true, "US-ASCII");
		}
		catch (UnsupportedEncodingException e)
		{
			// on ignore cette exception qui ne devrait pas arriver 
			// (toutes les plateformes g√®rent ASCII)
		}		
	}
	
	public void afficheGrille(Map<Position, Integer> grille)
	{
		String c="";
		for (int i=7;i>=0;i--)
		{
			for(int j=0;j<=7;j++)
			{
				Position p=new Position(i,j);
				if (!grille.containsKey(p)) c=c+"0"+" ";
				else c=c+grille.get(p)+" ";
			}
			c=c+"\n";
		}
		this.out.println(c);
	}

	@Override
	public void afficheMessage(String m)
	{
		this.out.println("<Puissance4 message> "+m);
		
	}
}