package modeles.entities2D;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import controleur.Controleur;

/**
 * Classe gérant le HUD et interagissant avec les entities2D qui iront dessus
 *
 *
 */

public class HUDManager extends AbstractEntity2D{
	private Cursor2D curseur;
	private Inventaire2D inventaire;
	private Hotbar hotbar;
	private DebugText debug;
	
	/*
	 * Constructeur
	 */
	public HUDManager(Controleur contr){
		curseur = new Cursor2D();
		inventaire = new Inventaire2D();
		hotbar = new Hotbar();
		curseur.genCurseur();
		inventaire.genInventaire();
		hotbar.genHotbar();
		debug = new DebugText(contr);
		

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
	public Hotbar getHotbar(){
		return hotbar;
	}
	/*
	 * Méthodes
	 */	
	@Override
	public void draw() {
		//affichage de l'inventaire
		if(debug.getModeDebug()){
			debug.setUp();
			debug.draw();
			debug.disable();
		}
		
		if (inventaire.getaffichInventaire()){
			inventaire.bindBuffer();
			inventaire.bindDrawInventory();
			inventaire.enableInventory();
			inventaire.draw();
			inventaire.disableInventory();
		}
		//affichage des données en plus (pour test et compagnie)

		curseur.bindBuffer();	
		curseur.bindDrawCursor();
		curseur.enableCursor();
		curseur.draw();
		curseur.disableCursor();
		
		
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public DebugText getModeDebug() {
		return this.debug;
	}

}
