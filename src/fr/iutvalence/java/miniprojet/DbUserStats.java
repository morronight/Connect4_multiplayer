package fr.iutvalence.java.miniprojet;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe definissant l'utilisation d'une base de donnes pour l'application
 * @author Réveillard Théo
 */
public class DbUserStats implements UserStats {
	
	private String dbUrl;
	private String user;
	private String password;
	
	/**
	 * Creation d'un objet utilisateur de la base de donnees
	 * @param driverClassName
	 * 			: le driver utilise par le SGBDR
	 * @param dbUrl
	 * 			: l'url de la base de donnees
	 * @param user
	 * 			: le login
	 * @param password
	 * 			: le mot de passe
	 * @throws ClassNotFoundException
	 */
	public DbUserStats(String driverClassName, String dbUrl, String user, String password) throws ClassNotFoundException
	{
		Class.forName(driverClassName);
		this.dbUrl = dbUrl;
		this.user = user;
		this.password = password;
	}
	
	/**
	 * Mehtode de connexion a la base de donnees
	 * @return l'objet issu de la connexion
	 * @throws SQLException
	 */
	public Statement connexion() throws SQLException{
	 return DriverManager.getConnection(this.dbUrl, this.user, this.password).createStatement(); 
	}
	
	/**
	 * methode de deconnexion de la connexion passee en parametre
	 * @param s
	 * 			: la connexion a fermer
	 * @throws SQLException
	 */
	public void deconnexion(Statement s) throws SQLException{
		s.getConnection().close();
	}
	
	@Override
	public boolean rechJoueur(String pseudo) throws UserStatsAccessException{
		Statement stat;
		try
		{
			stat = connexion();
		}
		catch (SQLException e1)
		{
			throw new UserStatsAccessException();
		}
		ResultSet r;
		String req = "SELECT pseudo FROM joueurs WHERE pseudo='"+pseudo+"'";
		try
		{
			r=stat.executeQuery(req);
		}
		catch (SQLException e)
		{
			throw new UserStatsAccessException();
		}
		boolean b=false;
		try
		{
			b=r.next();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			deconnexion(stat);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return b;
		
	}

	
	public void ajoutJoueur(String pseudo, String password)
			throws UserStatsAccessException
	{
		Statement stat;
		try
		{
			stat = connexion();
		}
		catch (SQLException e1)
		{
			throw new UserStatsAccessException();
		}
		String req = "INSERT INTO joueurs VALUES ('"+pseudo+"','"+password+"',0,0,0)";
		try
		{
		stat.executeUpdate(req);
		}
		catch (SQLException e) {
		throw new UserStatsAccessException();
		}
		try
		{
			deconnexion(stat);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
		

	@Override
	public void ajoutPartieGagnee(String pseudo)
			throws UserStatsAccessException
	{
		Statement stat;
		try
		{
			stat = connexion();
		}
		catch (SQLException e1)
		{
			throw new UserStatsAccessException();
		}
		String req = "UPDATE joueurs SET parties_gagnees=parties_gagnees+1 WHERE pseudo='"+pseudo+"'";
		try
		{
		stat.executeUpdate(req);
		}
		catch (SQLException e){
			throw new UserStatsAccessException();
		}
		try
		{
			deconnexion(stat);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void ajoutPartieNulle(String pseudo) throws UserStatsAccessException
	{
		Statement stat;
		try
		{
			stat = connexion();
		}
		catch (SQLException e1)
		{
			throw new UserStatsAccessException();
		}
		String req = "UPDATE joueurs SET parties_nulles=parties_nulles+1 WHERE pseudo='"+pseudo+"'";
		try
		{
		stat.executeUpdate(req);
		}
		catch (SQLException e) {
			throw new UserStatsAccessException();
		}
		try
		{
			deconnexion(stat);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void ajoutPartiePerdue(String pseudo) throws UserStatsAccessException
	{
		Statement stat;
		try
		{
			stat = connexion();
		}
		catch (SQLException e1)
		{
			throw new UserStatsAccessException();
		}
		String req = "UPDATE joueurs SET parties_perdues=parties_perdues+1 WHERE pseudo='"+pseudo+"'";
		try
		{
		stat.executeUpdate(req);
		}
		catch (SQLException e) {
			throw new UserStatsAccessException();
		}
		try
		{
			deconnexion(stat);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public ScoreJ scoreJoueur(String pseudo) throws UserStatsAccessException
	{
		Statement stat;
		ScoreJ score=null;
		try
		{

			stat = connexion();
		}
		catch (SQLException e1)
		{
			throw new UserStatsAccessException();
		}
		ResultSet r;
		
		String req = "SELECT parties_gagnees, parties_perdues, parties_nulles FROM joueurs WHERE pseudo='"+pseudo+"'";		
		try
		{
			r=stat.executeQuery(req);
		}
		catch (SQLException e)
		{
			throw new UserStatsAccessException();
		}
		try
		{
			r.next();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			score=new ScoreJ(r.getInt("parties_gagnees"), r.getInt("parties_perdues"), r.getInt("parties_nulles"));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			deconnexion(stat);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return score;
	}

	@Override
	public boolean verifpassw(String pseudo, String password)
			throws UserStatsAccessException
	{
		Statement stat;
		try
		{		

			stat = connexion();
		}
		catch (SQLException e1)
		{
			throw new UserStatsAccessException();
		}
		ResultSet r;
		String req = "SELECT password FROM joueurs WHERE pseudo='"+pseudo+"'";
		try
		{
			r=stat.executeQuery(req);
		}
		catch (SQLException e)
		{
			throw new UserStatsAccessException();
		}
		try
		{
			r.next();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		boolean b=false;
		try
		{
			b=r.getString("password").equals(password);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			deconnexion(stat);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return b;
	}
}
