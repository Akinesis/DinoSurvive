package modeles.entities2D;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_COORD_ARRAY;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.glDisableClientState;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glTexParameteri;

import java.io.IOException;

import org.lwjgl.opengl.Display;
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
	public float[] genText(int type){
		switch (type) {
		case 1:
			return genHotBarText();
			//return genHotBarText();
		case 2:
			return genBarreEtat();
		case 3:
			return genBarreSac();
		case 4:
			return genHotBarText();
			//return genInventaire2D();
		case 5:
			return genHotBarText();
			//return genMenuJeu();
		case 6:
			return genCurseur2D();
		case 7: 
			return genPortrait();
		default:
			return genHotBarText();
		}
	}

	private float[] genMenuJeu() {
		return new float[]{};
	}
	
	private float[] genCurseur2D(){
		return new float[]{
				0.427734375f, 0.041015625f,
				0.427734375f, 0.00390625f,
				0.390625f, 0.00390625f,
				
				0.427734375f, 0.041015625f,
				0.390625f, 0.00390625f,
				0.390625f, 0.041015625f};
	}

	private float[] genInventaire2D() {
		return new float[]{};
	}

	private float[] genBarreSac() {
		float lar = Display.getWidth();
		float hau = Display.getHeight();
		return new float[]{	
				//triangle qui dépasse
				(32+1)/512f, (428+1)/512f,
				(32+1)/512f, (396+1)/512f,
				(0+1)/512f, (428+1)/512f,				
				//triangle sup  de la barre
				(197+1)/512f, (396+1)/512f,
				(32+1)/512f, (396+1)/512f,
				(32+1)/512f, (428+1)/512f,
				//triangle inf de la barre
				(197+1)/512f, (396+1)/512f,
				(32+1)/512f, (428+1)/512f,
				(197+1)/512f, (428+1)/512f,
				};
	}
	
	private float[] genPortrait(){
		return new float[]{
				//triangle du haut
				(85+1)/512f, (73+1)/512f,
				(36+1)/512f, (73+1)/512f,
				(36+1)/512f, (122+1)/512f,
				
				//triangle sup du grand rectangle
				(36+1)/512f, (122+1)/512f,
				(85+1)/512f, (122+1)/512f,
				(85+1)/512f, (73+1)/512f,
		};
	}

	private float[] genBarreEtat() {
		float lar = Display.getWidth();
		float hau = Display.getHeight();
		return new float[]{
				//barre supérieure (santé)
				//triangle sup
				(141+1)/512f, (51+1)/512f,
				(141+1)/512f, (36+1)/512f,
				(36+1)/512f, (36+1)/512f,			
				//triangle inf
				(141+1)/512f, (51+1)/512f,
				(36+1)/512f, (36+1)/512f,
				(36+1)/512f, (51+1)/512f,
				//barre middle (anti matière)
				//triangle sup
				(141+1)/512f, (69+1)/512f,
				(141+1)/512f, (54+1)/512f,
				(36+1)/512f, (54+1)/512f,			
				//triangle inf
				(141+1)/512f, (69+1)/512f,
				(36+1)/512f, (54+1)/512f,			
				(36+1)/512f, (69+1)/512f,
				//barre inf (endurance)
				//triangle sup
				(141+1)/512f, (69+1)/512f,
				(141+1)/512f, (54+1)/512f,
				(36+1)/512f, (54+1)/512f,				
				//triangle inf
				(141+1)/512f, (69+1)/512f,
				(36+1)/512f, (54+1)/512f,			
				(36+1)/512f, (69+1)/512f,
		};
	}

	private float[] genHotBarText() {
		return new float[]{
				//pointe vers le  haut
				0f, 0f,
				0f, 0.0625f,
				0.0625f, 0.0625f,
				
				//triangle sup du grand rectangle
				0.064453125f, 0.708984375f,
				0.064453125f, 0.064453125f,
				0f, 0.064453125f,
				
				//triangle inf du grand rectangle
				0.0625f, 0.708984375f,
				0f, 0.064453125f, 
				0f, 0.708984375f,
				
				//pointe vers le bas
				0.064453125f, 0.708984375f,
				0f, 0.708984375f,
				0f, 0.771484375f,
				
				
		};
	}
}
