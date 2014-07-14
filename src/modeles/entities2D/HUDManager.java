package modeles.entities2D;

import java.awt.Font;

import org.lwjgl.opengl.GL11;
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
	private Menu menu;
	
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
		this.menu = new Menu();
		this.menu.generationMenu();

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
		//hotfix du conflit debug/2d
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		
		if (inventaire.getaffichInventaire()){
			inventaire.bindBuffer();
			inventaire.bindDrawInventory();
			inventaire.enableInventory();
			inventaire.draw();
			inventaire.disableInventory();
		}
		if (this.menu.getEstAfficher()){
			this.menu.bindBufferMenu();;
			this.menu.bindDrawMenu();
			this.menu.enableMenu();
			this.menu.draw();
			this.menu.disableMenu();
		}else{
			//inhibe le curseur tantque e menu principale est present
			//affichage des données en plus (pour test et compagnie)
			curseur.bindBuffer();	
			curseur.bindDrawCursor();
			curseur.enableCursor();
			curseur.draw();
			curseur.disableCursor();
		}
		
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


	public Menu getMenu() {
		return this.menu;
	}
}
