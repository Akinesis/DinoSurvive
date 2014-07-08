package modeles.entities2D;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import controleur.Controleur;

/**
 * Classe pour gérer l'affichage de "debug" qu'on peut vouloir, pour le moment en 
 * mode utilisation de méthodes dépreciées
 * @author Freyja
 *
 */
public class DebugText extends AbstractEntity2D {

	private boolean modeDebug = false;
	private TrueTypeFont font;
	private Controleur clone;
	
	public DebugText(Controleur clone){
		
		this.clone = clone;
		Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
		font = new TrueTypeFont(awtFont, true);
		
	}
	
	public boolean getModeDebug(){
		return modeDebug;
	}
	
	public void changeDebug(){
		this.modeDebug = !(this.modeDebug);
	}


	@Override
	public void draw() {
		// TODO Auto-generated method stub
		//affiche la position de la caméra et le chunk actuel (ou -1,-1,-1 si pas de chunk)
		float[] temp = clone.getChunkManager().getChunkAt(clone.getCamera().getPos().x, clone.getCamera().getPos().y, clone.getCamera().getPos().z);
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
