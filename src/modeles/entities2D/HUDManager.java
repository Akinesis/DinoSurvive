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
	private TrueTypeFont font;
	private Controleur clone;
	
	/*
	 * Constructeur
	 */
	public HUDManager(Controleur contr){
		curseur = new Cursor2D();
		inventaire = new Inventaire2D();
		curseur.genCurseur();
		inventaire.genInventaire();
		clone = contr;
		Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
		font = new TrueTypeFont(awtFont, true);
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
	
	/*
	 * Méthodes
	 */	
	@Override
	public void draw() {
		float[] temp = clone.getChunkManager().getChunkAt(clone.getCamera().getPos().x, clone.getCamera().getPos().y, clone.getCamera().getPos().z);
		if (inventaire.getaffichInventaire()){
			inventaire.bindBuffer();
			inventaire.bindDrawInventory();
			inventaire.enableInventory();
			inventaire.draw();
			inventaire.disableInventory();
		}
		curseur.bindBuffer();	
		curseur.bindDrawCursor();
		curseur.enableCursor();
		curseur.draw();
		curseur.disableCursor();
		
		//affiche la position de la caméra et le chunk actuel (ou -1,-1,-1 si pas de chunk)
		font.drawString(10, 10, clone.getCamera().getPos().toString() , Color.white);
		font.drawString(10, 30, "Chunk : "+temp[0]+", "+temp[1]+", "+temp[2]  , Color.white);
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
