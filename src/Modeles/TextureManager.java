package Modeles;
import java.io.IOException;
import java.nio.FloatBuffer;


import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import org.lwjgl.BufferUtils;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class TextureManager {

	private Texture texture;
	private FloatBuffer textureData;
	private int vboTexHandle;

	public TextureManager() {
		try {
			texture = TextureLoader.getTexture("PNG",
					ResourceLoader.getResourceAsStream("res/text.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		textureData = BufferUtils.createFloatBuffer(6 * 2);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	}

	public void genTexture(){
		textureData.put(new float[]{
				//south
				0.25f, 0.25f,
				0.25f, 0,
				0, 0.25f,
				0, 0.25f,
				0.25f, 0,
				0, 0

		});
		textureData.flip();
	}

	public void bindText(){
		glBindTexture(GL_TEXTURE_2D, texture.getTextureID());
		texture.bind();
	}
	
	public void bindBuffer(){
		vboTexHandle = texture.getTextureID();
	    glBindBuffer(GL_ARRAY_BUFFER, vboTexHandle);
	    glBufferData(GL_ARRAY_BUFFER, textureData, GL_STATIC_DRAW);
	    glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	public void drawTexture(){
		glBindBuffer(GL_ARRAY_BUFFER, vboTexHandle);
		glTexCoordPointer(2, GL_FLOAT, 0, 0l);
		glEnableClientState(GL_TEXTURE_COORD_ARRAY);
	}

}
