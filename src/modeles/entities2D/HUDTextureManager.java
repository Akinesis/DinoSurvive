package modeles.entities2D;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_COORD_ARRAY;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.glDisableClientState;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glTexParameteri;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class HUDTextureManager {
	
	private Texture texture;

	public HUDTextureManager() {
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/hud-text.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	}
	
	public HUDTextureManager(String adresseTexture) {
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(adresseTexture));
		} catch (IOException e) {
			e.printStackTrace();
		}

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	}
	
	public void deleteText(){
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL11.glDeleteTextures(texture.getTextureID());
	}
	public int getID(){
		return texture.getTextureID();
	}

	public void enableTexture(){
		glEnableClientState(GL_TEXTURE_COORD_ARRAY);
	}

	public void disableTexture(){
		glDisableClientState(GL_TEXTURE_COORD_ARRAY);
	}
	
	//arbitrairement hotbar = 1, barreEtat = 2, barreSac = 3, Inventaire2D = 4, MenuJeu = 5, au cas où cursor2D = 6, le reste 0
	public float[] genText(int type, float x, float y){
		switch (type) {
		case 1:
			return genHotBarText(x,y);
		case 2:
			return genBarreEtat(x,y);
		case 3:
			return genBarreSac(x,y);
		case 4:
			return genInventaire2D(x,y);
		case 5:
			return genMenuJeu(x,y);
		//case 6:
		//	return genCurseur2D(x,y);
		default:
			return genHotBarText(x, y);
		}
	}

	private float[] genMenuJeu(float x, float y) {
		return new float[]{};
	}

	private float[] genInventaire2D(float x, float y) {
		return new float[]{};
	}

	private float[] genBarreSac(float x, float y) {
		return new float[]{};
	}

	private float[] genBarreEtat(float x, float y) {
		return new float[]{};
	}

	private float[] genHotBarText(float x, float y) {
		return new float[]{};
	}
}
