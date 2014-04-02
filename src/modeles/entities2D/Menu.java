package modeles.entities2D;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import org.lwjgl.util.glu.GLU;
import static org.lwjgl.opengl.GL11.*;

import parametres.Parametres;

public class Menu implements Parametres{
	private Texture texture;

	//on doit peut etre converser le nom du fichier.
	public Menu(){
		try {
			this.texture = TextureLoader.getTexture("JPEG", new FileInputStream(new File("res/Menu.jpg")));
			//this.texture = TextureLoader.getTexture("JPEG",ResourceLoader.getResourceAsStream("res/Menu.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Texture getTexture() {
		return texture;
	}
	
	public void use(){
		while(!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			// Affichage du menu
			this.Dessiner();
		}
		this.texture.release();
	}
	
	public void Dessiner(){
		glLoadIdentity();
		glMatrixMode(GL_PROJECTION);
		// Definition de la fenetre
		GLU.gluOrtho2D(0.0f, Parametres.largeur, 0.0f, Parametres.hauteur);
	    // Desactivation du test de prophondeur
		glDisable(GL_DEPTH_TEST);
	    // Vidage de l'image
		glClear(GL_COLOR_BUFFER_BIT);
		this.DessinerFond();
		//this.DessinerBoutons();
		glFlush();
		try {
			Display.swapBuffers();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	public void DessinerFond(){
		glBindTexture(GL_TEXTURE_2D, this.texture.getTextureID());
		this.texture.bind();
		
		glBegin(GL_TRIANGLES); 
		glTexCoord2f(1, 0); glVertex2i(450, 10);
		glTexCoord2f(0, 0); glVertex2i(10, 10);
		glTexCoord2f(0, 1); glVertex2i(10, 450);
		 
		glTexCoord2f(0, 1); glVertex2i(10, 450);
		glTexCoord2f(1, 1); glVertex2i(450, 450);
		glTexCoord2f(1, 0); glVertex2i(450, 10);
		glEnd();
	}
	
	public void DessinerBoutons(){
		
	}
	
	
	
}