package controleur;

/**
 * Classe Peut utile qui se charge de lancer le jeu par le biais du controleur
 * @author joachim
 *
 */
public class Game {
	
	private Controleur controleur;
	
	public Game(){
		controleur = new Controleur();
	}
	
	//lance le jeu
	public void start(){
		controleur.init();
	}
}
