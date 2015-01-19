package modeles;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.glTexParameteri;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class DropTextureManager {
	
	private Texture texture;
	
	public DropTextureManager(){
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res//item.png"));
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
	
	public float[] genText(float x, float y){
		
		float fact = 1/2048f;
		
		return new float[]{
				fact*x, fact*y,
				fact*x, fact*(y+15),
				fact*(x+15), fact*y,
				fact*(x+15), fact*y,
				fact*x, fact*(y+15),
				fact*(x+15), fact*(y+15),
				
				fact*(x+15), fact*y,
				fact*x, fact*y,
				fact*(x+15), fact*(y+15),
				fact*x, fact*y,
				fact*x, fact*(y+15),
				fact*(x+15), fact*(y+15),
		};
	}
	

}
