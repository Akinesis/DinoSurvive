package modeles;

import java.sql.*;

public class BDDConector {
	private String databse ="jdbc:derby:database";
	private String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	private String user ="root";
	private String password ="root";
	Connection connexion;
	
	public BDDConector(){
		connexion = null;
	}
	
	public void connexionBDD() throws IllegalAccessException, InstantiationException{
		try{
			Class.forName(driver).newInstance();
			System.out.println("Chargement du driver : ok");
		}catch(ClassNotFoundException e){
			System.out.println("Impossible de charger le driver");
			e.printStackTrace();
		}
		
		try{
			connexion = DriverManager.getConnection(databse+";create=true", user, password);
			System.out.println("Connexion : ok");
		}catch(SQLException e){
			System.out.println("Impossible de ce connecter");
			e.printStackTrace();
		}
	}
	
	public void arretBDD(){
		try {
			DriverManager.getConnection("jdbc:derby:database;shutdown=true");
			connexion.close();
			System.out.println("Connexion : fermée");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}

}
