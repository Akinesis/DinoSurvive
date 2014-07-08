package modeles.entities2D;

import java.awt.Font;

import modeles.entities.Cube3dVbo;

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
	
	// affichage désactivé de base (vu que ça fait bugger le reste de la 2D)
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
		
		Cube3dVbo cubeTemp = clone.getChunkManager().getCubeAt(clone.getCamera().getPos().x, clone.getCamera().getPos().y, clone.getCamera().getPos().z);
		int[] type = {(cubeTemp!=null)?cubeTemp.getType():-1,-1,-1};
		cubeTemp = clone.getChunkManager().getCubeAt(clone.getCamera().getPos().x, clone.getCamera().getPos().y+1, clone.getCamera().getPos().z);
		type[1] = (cubeTemp!=null)?cubeTemp.getType():-1;
		cubeTemp = clone.getChunkManager().getCubeAt(clone.getCamera().getPos().x, clone.getCamera().getPos().y+2, clone.getCamera().getPos().z);
		type[2] = (cubeTemp!=null)?cubeTemp.getType():-1;
		
		font.drawString(10, 10, clone.getCamera().getPos().toString() , Color.white);
		font.drawString(10, 30, "Chunk : "+temp[0]+", "+temp[1]+", "+temp[2]  , Color.white);
		font.drawString(10, 50, "Block : "+type[0]+", "+type[1]+", "+type[2] , Color.white);
		
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void disable() {
		// TODO Auto-generated method stub
		
	}

}
