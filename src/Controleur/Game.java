package Controleur;

public class Game {
	
	private Controleur controleur;
	
	public Game(){
		controleur = new Controleur();
	}
	
	public void start(){
		controleur.init();
	}
}
