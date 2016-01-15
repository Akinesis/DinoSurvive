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
/**
 * Classe qui gère les associations de textures pour le HUD (menu de jeu, inventaires, portrait, barres d'états etc)
 * 
 */
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
		case 2:
			return genBarreEtat();
		case 3:
			return genBarreSac();
		case 4:
			return genInventaire2D();
		case 5:		
			return genMenuJeu();
		case 6:
			return genCurseur2D();
		case 7: 
			return genPortrait();
		default:
			return genHotBarText();
		}
	}

	private float[] genMenuJeu() {
		return new float[]{
				//bouton Menu
				//triangle sup
				159/512f, 362/512f,
				37/512f, 362/512f,
				37/512f, 392/512f,
				//triangle inf
				159/512f, 362/512f,
				37/512f, 392/512f,
				159/512f, 392/512f,
				
				//bouton Options
				//triangle sup
				285/512f, 329/512f,
				159/512f, 329/512f,
				159/512f, 358/512f,
				//triangle inf
				285/512f, 329/512f,
				159/512f, 358/512f,
				285/512f, 358/512f,
			
				//bouton Réglages
				//triangle sup
				285/512f, 363/512f,
				163/512f, 363/512f,
				163/512f, 392/512f,
				//triangle inf
				285/512f, 363/512f,
				163/512f, 392/512f,
				285/512f, 392/512f,
				
				//texture du bouton Quitter
				//triangle sup
				159/512f, 329/512f,
				37/512f, 329/512f,
				37/512f, 359/512f,
				//triangle inf
				159/512f, 329/512f,
				37/512f, 359/512f,
				159/512f, 359/512f,
	
				//texture du fond
				//triangle sup
				235/512f, 126/512f,
				36/512f, 126/512f,
				36/512f, 325/512f,
				//triangle inf
				36/512f, 325/512f,
				235/512f, 325/512f,
				235/512f, 126/512f,

		};
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
		return new float[]{
				1f, 492/512f,
				1f, 0f,
			    291/512f, 0f,
				
				291/512f, 0f,
				291/512f, 492/512f,
				1f, 492/512f,
		};
	}

	private float[] genBarreSac() {
		float lar = Display.getWidth();
		float hau = Display.getHeight();
		return new float[]{	
				//triangle qui dépasse
				33/512f, 429/512f,
				33/512f, 397/512f,
				1/512f, 429/512f,				
				//triangle sup  de la barre
				231/512f, 397/512f,
				33/512f, 397/512f,
				33/512f, 429/512f,
				//triangle inf de la barre
				231/512f, 397/512f,
				33/512f, 429/512f,
				231/512f, 429/512f,
				};
	}
	
	private float[] genPortrait(){
		return new float[]{
				//triangle du haut
				86/512f, 74/512f,
				37/512f, 74/512f,
				37/512f, 123/512f,
				
				//triangle inférieur
				37/512f, 123/512f,
				86/512f, 123/512f,
				86/512f, 74/512f,
		};
	}

	private float[] genBarreEtat() {
		float lar = Display.getWidth();
		float hau = Display.getHeight();
		return new float[]{
				//barre supérieure (santé)
				//triangle sup
				142/512f, 52/512f,
				142/512f, 37/512f,
				37/512f, 37/512f,			
				//triangle inf
				142/512f, 52/512f,
				37/512f, 37/512f,
				37/512f, 52/512f,
				//barre middle (anti matière)
				//triangle sup
				142/512f, 70/512f,
				142/512f, 55/512f,
				37/512f, 55/512f,			
				//triangle inf
				142/512f, 70/512f,
				37/512f, 55/512f,			
				37/512f, 70/512f,
				//barre inf (endurance)
				//triangle sup
				142/512f, 70/512f,
				142/512f, 55/512f,
				37/512f, 55/512f,				
				//triangle inf
				142/512f, 70/512f,
				37/512f, 55/512f,			
				37/512f, 70/512f,
		};
	}

	private float[] genHotBarText() {
		return new float[]{
				//pointe vers le  haut
				0f, 0.0625f,
				0f, 0f,
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
