package Controleur;

public class Game {
	
	private Controleur controleur;
	
	//classe pas trop utile mais bon ...
	public Game(){
		controleur = new Controleur();
	}
	
	//lance le jeu
	public void start(){
		controleur.init();
	}
}
