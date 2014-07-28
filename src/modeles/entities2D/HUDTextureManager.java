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
	public float[] genText(int type){
		switch (type) {
		case 1:
			return genHotBarText();
			//return genHotBarText();
		case 2:
			return genBarreEtat();
		case 3:
			return genHotBarText();
			//return genBarreSac();
		case 4:
			return genHotBarText();
			//return genInventaire2D();
		case 5:
			return genHotBarText();
			//return genMenuJeu();
		//case 6:
		//	return genCurseur2D();
		case 7: 
			return genPortrait();
		default:
			return genHotBarText();
		}
	}

	private float[] genMenuJeu() {
		return new float[]{};
	}

	private float[] genInventaire2D() {
		return new float[]{};
	}

	private float[] genBarreSac() {
		return new float[]{};
	}
	
	private float[] genPortrait(){
		return new float[]{
				//pointe vers le  haut
				0f, 0f,
				0f, 0.0266f,
				0.0266f, 0.0266f,
				
				//triangle sup du grand rectangle
				0.0275f, 0.301f,
				0.0275f, 0.0275f,
				0f, 0.0275f,
				
				//triangle inf du grand rectangle
				0.017f, 0.025f,
				0f, 0.27f, 
				0.017f, 0.27f,
				
				//pointe vers le bas
				0.017f, 0.27f,
				0f, 0.27f,
				0f, 0.29f,
				
				
		};
	}

	private float[] genBarreEtat() {
		
		return new float[]{		
				//pointe vers le  haut
				0f, 0f,
				0f, 0.024f,
				0.017f, 0.024f,
				
				//triangle sup du grand rectangle
				0.017f, 0.025f,
				0f, 0.025f,
				0f, 0.27f,
				
				//triangle inf du grand rectangle
				0.017f, 0.025f,
				0f, 0.27f, 
				0.017f, 0.27f,
				
				//pointe vers le bas
				0.017f, 0.27f,
				0f, 0.27f,
				0f, 0.29f,
				//triangle inf du grand rectangle
				0.017f, 0.025f,
				0f, 0.27f, 
				0.017f, 0.27f,
				
				//pointe vers le bas
				0.017f, 0.27f,
				0f, 0.27f,
				0f, 0.29f,				//triangle inf du grand rectangle
				0.017f, 0.025f,
				0f, 0.27f, 
				0.017f, 0.27f,
				
				//pointe vers le bas
				0.017f, 0.27f,
				0f, 0.27f,
				0f, 0.29f,
				//triangle inf du grand rectangle
				0.017f, 0.025f,
				0f, 0.27f, 
				0.017f, 0.27f,
				
				//pointe vers le bas
				0.017f, 0.27f,
				0f, 0.27f,
				0f, 0.29f,
				//triangle inf du grand rectangle
				0.017f, 0.025f,
				0f, 0.27f, 
				0.017f, 0.27f,
				
				//pointe vers le bas
				0.017f, 0.27f,
				0f, 0.27f,
				0f, 0.29f,
				};
	}

	private float[] genHotBarText() {
		return new float[]{
				//pointe vers le  haut
				0f, 0f,
				0f, 0.0266f,
				0.0266f, 0.0266f,
				
				//triangle sup du grand rectangle
				0.0275f, 0.301f,
				0.0275f, 0.0275f,
				0f, 0.0275f,
				
				//triangle inf du grand rectangle
				0.017f, 0.025f,
				0f, 0.27f, 
				0.017f, 0.27f,
				
				//pointe vers le bas
				0.017f, 0.27f,
				0f, 0.27f,
				0f, 0.29f,
				
				
		};
	}
}
