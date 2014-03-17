package Modeles.entities2D;

/**
 * Classe gérant le HUD et interagissant avec les entities2D qui iront dessus
 *
 *
 */

public class HUDManager {
	private Cursor2D curseur;
	private Inventaire2D inventaire;
	
	/*
	 * Constructeur
	 */
	public HUDManager(){
		curseur = new Cursor2D();
		inventaire = new Inventaire2D();
	}
	
	/*
	 * Getters
	 */
	public Cursor2D getCurseur(){
		return curseur;
	}
	public Inventaire2D getInventaire(){
		return inventaire;
	}

}
