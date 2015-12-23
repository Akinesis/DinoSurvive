package modeles.entities2D;

import java.awt.Font;

import modeles.entities.Cube3dVbo;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import controleur.Controleur;

/**
 * Classe pour gérer l'affichage de "debug" qu'on peut vouloir, pour le moment en 
 * mode utilisation de méthodes dépreciées
 * ne fonctionne pas pour le moment (affiche juste des rectangles blancs)
 * TODO: un affichage de débug qui marche
 * @author Freyja
 *
 */
public class DebugText extends AbstractEntity2D{
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
		modeDebug = !(modeDebug);
	}


	public void draw() {
		//affiche la position de la caméra et le chunk actuel (ou -1,-1,-1 si pas de chunk)
		int[] temp = clone.getChunkManager().getChunkAt(clone.getCamera().getPos().getX(), clone.getCamera().getPos().getY(), clone.getCamera().getPos().getZ());
		
		//récupère les info des cubes de tête, torse et pied.
		Cube3dVbo cubeTemp = clone.getChunkManager().getCubeAt(clone.getCamera().getPos().getX(), clone.getCamera().getPos().getY(), clone.getCamera().getPos().getZ());
		int[] type = {(cubeTemp!=null)?cubeTemp.getType():-1,-1,-1};
		cubeTemp = clone.getChunkManager().getCubeAt(clone.getCamera().getPos().getX(), clone.getCamera().getPos().getY()+1, clone.getCamera().getPos().getZ());
		type[1] = (cubeTemp!=null)?cubeTemp.getType():-1;
		cubeTemp = clone.getChunkManager().getCubeAt(clone.getCamera().getPos().getX(), clone.getCamera().getPos().getY()+2, clone.getCamera().getPos().getZ());
		type[2] = (cubeTemp!=null)?cubeTemp.getType():-1;
		
		font.drawString(10, 10, clone.getCamera().getPos().toString() , Color.white);
		System.out.println(clone.getCamera().getPos().toString());
		System.out.println(type[0]);
		font.drawString(10, 30, "Chunk : "+temp[0]+", "+temp[1]+", "+temp[2]  , Color.white);
		font.drawString(10, 50, "Block : "+type[0]+", "+type[1]+", "+type[2] , Color.white);
		
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}

	@Override
	public void setUp() {
	}

	@Override
	public void destroy() {
		
	}

	public void disable() {
		
	}

	@Override
	public float[] getCoord() {
		return null;
	}

	@Override
	public int getType() {
		return 4;
	}

}
