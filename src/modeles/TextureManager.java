package modeles;

import java.io.IOException;
import java.nio.FloatBuffer;





import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.sun.org.apache.bcel.internal.generic.IDIV;

public class TextureManager {

	private Texture texture;
	private FloatBuffer textureData;
	private int vboTexHandle;

	public TextureManager() {
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/text.png_version/text.joa.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		textureData = BufferUtils.createFloatBuffer(36 * 2);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	}
	
	public void deleteText(){
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL11.glDeleteTextures(texture.getTextureID());
	}

	private float[] genGrassTexture(float indiceTextX, float indiceTextY){	
		float xGrass = indiceTextX*0.03125f;
		float yGrass = indiceTextY*0.03125f;
		
		xGrass = xGrass-(xGrass%0.001953125f);
		yGrass = yGrass-(yGrass%0.001953125f);
		
		float xDirt = indiceTextX*0.03125f;
		float yDirt = indiceTextY*0.03125f;
		
		xDirt = xDirt-(xDirt%0.001953125f);
		yDirt = yDirt-(yDirt%0.001953125f);
		
		indiceTextX = (int)(indiceTextX * ((1 - 0) + 1))*0.03125f;
		indiceTextY = (int)(indiceTextY * ((1 - 0) + 1))*0.03125f;
		
		return new float[]{
				//south
				0.03125f+indiceTextX, 0.03125f+indiceTextY,
				0.03125f+indiceTextX, 0+indiceTextY,
				0+indiceTextX, 0.03125f+indiceTextY,
				0+indiceTextX, 0.03125f+indiceTextY,
				0.03125f+indiceTextX, 0+indiceTextY,
				0+indiceTextX, 0+indiceTextY,

				0.03125f+indiceTextX, 0+indiceTextY,
				0+indiceTextX, 0+indiceTextY,
				0+indiceTextX, 0.03125f+indiceTextY,
				0.03125f+indiceTextX, 0+indiceTextY,
				0+indiceTextX, 0.03125f+indiceTextY,
				0.03125f+indiceTextX, 0.03125f+indiceTextY,

				//top
				0.03125f+xGrass, 0.09375f+yDirt,
				0.03125f+xGrass, 0.0625f+yDirt,
				0.0f+xGrass, 0.09375f+yDirt,
				0.0f+xGrass, 0.09375f+yDirt,
				0.03125f+xGrass, 0.0625f+yDirt,
				0.0f+xGrass, 0.0625f+yDirt,

				//bottom
				0.0625f+xDirt, 0.0f+indiceTextY,
				0.0625f+xDirt, 0.03125f+indiceTextY,
				0.09375f+xDirt, 0.0f+indiceTextY,
				0.09375f+xDirt, 0.0f+indiceTextY,
				0.0625f+xDirt, 0.03125f+indiceTextY,
				0.09375f+xDirt, 0.03125f+indiceTextY,

				0.03125f+indiceTextX, 0.03125f+indiceTextY,
				0.03125f+indiceTextX, 0+indiceTextY,
				0+indiceTextX, 0.03125f+indiceTextY,
				0+indiceTextX, 0.03125f+indiceTextY,
				0.03125f+indiceTextX, 0+indiceTextY,
				0+indiceTextX, 0+indiceTextY,

				0+indiceTextX, 0.03125f+indiceTextY,
				0.03125f+indiceTextX, 0+indiceTextY,
				0+indiceTextX, 0+indiceTextY,
				0.03125f+indiceTextX,0.03125f+indiceTextY,
				0.03125f+indiceTextX, 0+indiceTextY,
				0+indiceTextX, 0.03125f+indiceTextY

		};
		
	}

	private float[] genDirtTexture(float indiceTextX, float indiceTextY){
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;
		
		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		return new float[]{
				//south
				0.09375f+indiceTextX, 0.03125f+indiceTextY,
				0.09375f+indiceTextX, 0.0f+indiceTextY,
				0.0625f+indiceTextX, 0.03125f+indiceTextY,
				0.0625f+indiceTextX, 0.03125f+indiceTextY,
				0.09375f+indiceTextX, 0.0f+indiceTextY,
				0.0625f+indiceTextX, 0.0f+indiceTextY,

				0.09375f+indiceTextX, 0.0f+indiceTextY,
				0.0625f+indiceTextX, 0.0f+indiceTextY,
				0.0625f+indiceTextX, 0.03125f+indiceTextY,
				0.09375f+indiceTextX, 0.0f+indiceTextY,
				0.0625f+indiceTextX, 0.03125f+indiceTextY,
				0.09375f+indiceTextX, 0.03125f+indiceTextY,

				//top
				0.09375f+indiceTextX, 0.03125f+indiceTextY,
				0.09375f+indiceTextX, 0.0f+indiceTextY,
				0.0625f+indiceTextX, 0.03125f+indiceTextY,
				0.0625f+indiceTextX, 0.03125f+indiceTextY,
				0.09375f+indiceTextX, 0.0f+indiceTextY,
				0.0625f+indiceTextX, 0.0f+indiceTextY,

				//bottom
				0.0625f+indiceTextX, 0.0f+indiceTextY,
				0.0625f+indiceTextX, 0.03125f+indiceTextY,
				0.09375f+indiceTextX, 0.0f+indiceTextY,
				0.09375f+indiceTextX, 0.0f+indiceTextY,
				0.0625f+indiceTextX, 0.03125f+indiceTextY,
				0.09375f+indiceTextX, 0.03125f+indiceTextY,

				0.09375f+indiceTextX, 0.03125f+indiceTextY,
				0.09375f+indiceTextX, 0.0f+indiceTextY,
				0.0625f+indiceTextX, 0.03125f+indiceTextY,
				0.0625f+indiceTextX, 0.03125f+indiceTextY,
				0.09375f+indiceTextX, 0.0f+indiceTextY,
				0.0625f+indiceTextX, 0.0f+indiceTextY,

				0.0625f+indiceTextX, 0.03125f+indiceTextY,
				0.09375f+indiceTextX, 0.0f+indiceTextY,
				0.0625f+indiceTextX, 0.0f+indiceTextY,
				0.09375f+indiceTextX, 0.03125f+indiceTextY,
				0.09375f+indiceTextX, 0.0f+indiceTextY,
				0.0625f+indiceTextX, 0.03125f+indiceTextY
		};
		
	}

	private float[] genStoneTexture(float indiceTextX, float indiceTextY){
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;
		
		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		return new float[]{
				//south
				0.09375f+indiceTextX, 0.09375f+indiceTextY,
				0.09375f+indiceTextX, 0.0625f+indiceTextY,
				0.0625f+indiceTextX, 0.09375f+indiceTextY,
				0.0625f+indiceTextX, 0.09375f+indiceTextY,
				0.09375f+indiceTextX, 0.0625f+indiceTextY,
				0.0625f+indiceTextX, 0.0625f+indiceTextY,

				0.09375f+indiceTextX, 0.0625f+indiceTextY,
				0.0625f+indiceTextX, 0.0625f+indiceTextY,
				0.0625f+indiceTextX, 0.09375f+indiceTextY,
				0.09375f+indiceTextX, 0.0625f+indiceTextY,
				0.0625f+indiceTextX, 0.09375f+indiceTextY,
				0.09375f+indiceTextX, 0.09375f+indiceTextY,

				//top
				0.09375f+indiceTextX, 0.09375f+indiceTextY,
				0.09375f+indiceTextX, 0.0625f+indiceTextY,
				0.0625f+indiceTextX, 0.09375f+indiceTextY,
				0.0625f+indiceTextX, 0.09375f+indiceTextY,
				0.09375f+indiceTextX, 0.0625f+indiceTextY,
				0.0625f+indiceTextX, 0.0625f+indiceTextY,

				//bottom
				0.0625f+indiceTextX, 0.0625f+indiceTextY,
				0.0625f+indiceTextX, 0.09375f+indiceTextY,
				0.09375f+indiceTextX, 0.0625f+indiceTextY,
				0.09375f+indiceTextX, 0.0625f+indiceTextY,
				0.0625f+indiceTextX, 0.09375f+indiceTextY,
				0.09375f+indiceTextX, 0.09375f+indiceTextY,

				0.09375f+indiceTextX, 0.09375f+indiceTextY,
				0.09375f+indiceTextX, 0.0625f+indiceTextY,
				0.0625f+indiceTextX, 0.09375f+indiceTextY,
				0.0625f+indiceTextX, 0.09375f+indiceTextY,
				0.09375f+indiceTextX, 0.0625f+indiceTextY,
				0.0625f+indiceTextX, 0.0625f+indiceTextY,

				0.0625f+indiceTextX, 0.09375f+indiceTextY,
				0.09375f+indiceTextX, 0.0625f+indiceTextY,
				0.0625f+indiceTextX, 0.0625f+indiceTextY,
				0.09375f+indiceTextX, 0.09375f+indiceTextY,
				0.09375f+indiceTextX, 0.0625f+indiceTextY,
				0.0625f+indiceTextX, 0.09375f+indiceTextY
		};
		
	}
	
	private float[] genCoalTexture(float indiceTextX, float indiceTextY){
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;
		
		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		return new float[]{
				//south
				0.15625f+indiceTextX, 0.03125f+indiceTextY,
				0.15625f+indiceTextX, 0.0f+indiceTextY,
				0.125f+indiceTextX, 0.03125f+indiceTextY,
				0.125f+indiceTextX, 0.03125f+indiceTextY,
				0.15625f+indiceTextX, 0.0f+indiceTextY,
				0.125f+indiceTextX, 0.0f+indiceTextY,

				0.15625f+indiceTextX, 0.0f+indiceTextY,
				0.125f+indiceTextX, 0.0f+indiceTextY,
				0.125f+indiceTextX, 0.03125f+indiceTextY,
				0.15625f+indiceTextX, 0.0f+indiceTextY,
				0.125f+indiceTextX, 0.03125f+indiceTextY,
				0.15625f+indiceTextX, 0.03125f+indiceTextY,

				//top
				0.15625f+indiceTextX, 0.03125f+indiceTextY,
				0.15625f+indiceTextX, 0.0f+indiceTextY,
				0.125f+indiceTextX, 0.03125f+indiceTextY,
				0.125f+indiceTextX, 0.03125f+indiceTextY,
				0.15625f+indiceTextX, 0.0f+indiceTextY,
				0.125f+indiceTextX, 0.0f+indiceTextY,

				//bottom
				0.125f+indiceTextX, 0.0f+indiceTextY,
				0.125f+indiceTextX, 0.03125f+indiceTextY,
				0.15625f+indiceTextX, 0.0f+indiceTextY,
				0.15625f+indiceTextX, 0.0f+indiceTextY,
				0.125f+indiceTextX, 0.03125f+indiceTextY,
				0.15625f+indiceTextX, 0.03125f+indiceTextY,

				0.15625f+indiceTextX, 0.03125f+indiceTextY,
				0.15625f+indiceTextX, 0.0f+indiceTextY,
				0.125f+indiceTextX, 0.03125f+indiceTextY,
				0.125f+indiceTextX, 0.03125f+indiceTextY,
				0.15625f+indiceTextX, 0.0f+indiceTextY,
				0.125f+indiceTextX, 0.0f+indiceTextY,

				0.125f+indiceTextX, 0.03125f+indiceTextY,
				0.15625f+indiceTextX, 0.0f+indiceTextY,
				0.125f+indiceTextX, 0.0f+indiceTextY,
				0.15625f+indiceTextX, 0.03125f+indiceTextY,
				0.15625f+indiceTextX, 0.0f+indiceTextY,
				0.125f+indiceTextX, 0.03125f+indiceTextY,
		};
		
	}

	private float[] genGoldTexture(float indiceTextX, float indiceTextY){
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;
		
		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		return new float[]{
				//south
				0.21875f+indiceTextX, 0.03125f+indiceTextY,
				0.21875f+indiceTextX, 0.0f+indiceTextY,
				0.1875f+indiceTextX, 0.03125f+indiceTextY,
				0.1875f+indiceTextX, 0.03125f+indiceTextY,
				0.21875f+indiceTextX, 0.0f+indiceTextY,
				0.1875f+indiceTextX, 0.0f+indiceTextY,

				0.21875f+indiceTextX, 0.0f+indiceTextY,
				0.1875f+indiceTextX, 0.0f+indiceTextY,
				0.1875f+indiceTextX, 0.03125f+indiceTextY,
				0.21875f+indiceTextX, 0.0f+indiceTextY,
				0.1875f+indiceTextX, 0.03125f+indiceTextY,
				0.21875f+indiceTextX, 0.03125f+indiceTextY,

				//top
				0.21875f+indiceTextX, 0.03125f+indiceTextY,
				0.21875f+indiceTextX, 0.0f+indiceTextY,
				0.1875f+indiceTextX, 0.03125f+indiceTextY,
				0.1875f+indiceTextX, 0.03125f+indiceTextY,
				0.21875f+indiceTextX, 0.0f+indiceTextY,
				0.1875f+indiceTextX, 0.0f+indiceTextY,

				//bottom
				0.1875f+indiceTextX, 0.0f+indiceTextY,
				0.1875f+indiceTextX, 0.03125f+indiceTextY,
				0.21875f+indiceTextX, 0.0f+indiceTextY,
				0.21875f+indiceTextX, 0.0f+indiceTextY,
				0.1875f+indiceTextX, 0.03125f+indiceTextY,
				0.21875f+indiceTextX, 0.03125f+indiceTextY,

				0.21875f+indiceTextX, 0.03125f+indiceTextY,
				0.21875f+indiceTextX, 0.0f+indiceTextY,
				0.1875f+indiceTextX, 0.03125f+indiceTextY,
				0.1875f+indiceTextX, 0.03125f+indiceTextY,
				0.21875f+indiceTextX, 0.0f+indiceTextY,
				0.1875f+indiceTextX, 0.0f+indiceTextY,

				0.1875f+indiceTextX, 0.03125f+indiceTextY,
				0.21875f+indiceTextX, 0.0f+indiceTextY,
				0.1875f+indiceTextX, 0.0f+indiceTextY,
				0.21875f+indiceTextX, 0.03125f+indiceTextY,
				0.21875f+indiceTextX, 0.0f+indiceTextY,
				0.1875f+indiceTextX, 0.03125f+indiceTextY
		};
		
	}

	private float[] genSilverTexture(float indiceTextX, float indiceTextY){
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;
		
		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		return new float[]{
				//south
				0.03125f+indiceTextX, 0.15625f+indiceTextY,
				0.03125f+indiceTextX, 0.125f+indiceTextY,
				0.0f+indiceTextX, 0.15625f+indiceTextY,
				0.0f+indiceTextX, 0.15625f+indiceTextY,
				0.03125f+indiceTextX, 0.125f+indiceTextY,
				0.0f+indiceTextX, 0.125f+indiceTextY,

				0.03125f+indiceTextX, 0.125f+indiceTextY,
				0.0f+indiceTextX, 0.125f+indiceTextY,
				0.0f+indiceTextX, 0.15625f+indiceTextY,
				0.03125f+indiceTextX, 0.125f+indiceTextY,
				0.0f+indiceTextX, 0.15625f+indiceTextY,
				0.03125f+indiceTextX, 0.15625f+indiceTextY,

				//top
				0.03125f+indiceTextX, 0.15625f+indiceTextY,
				0.03125f+indiceTextX, 0.125f+indiceTextY,
				0.0f+indiceTextX, 0.15625f+indiceTextY,
				0.0f+indiceTextX, 0.15625f+indiceTextY,
				0.03125f+indiceTextX, 0.125f+indiceTextY,
				0.0f+indiceTextX, 0.125f+indiceTextY,

				//bottom
				0.0f+indiceTextX, 0.125f+indiceTextY,
				0.0f+indiceTextX, 0.15625f+indiceTextY,
				0.03125f+indiceTextX, 0.125f+indiceTextY,
				0.03125f+indiceTextX, 0.125f+indiceTextY,
				0.0f+indiceTextX, 0.15625f+indiceTextY,
				0.03125f+indiceTextX, 0.15625f+indiceTextY,

				0.03125f+indiceTextX, 0.15625f+indiceTextY,
				0.03125f+indiceTextX, 0.125f+indiceTextY,
				0.0f+indiceTextX, 0.15625f+indiceTextY,
				0.0f+indiceTextX, 0.15625f+indiceTextY,
				0.03125f+indiceTextX, 0.125f+indiceTextY,
				0.0f+indiceTextX, 0.125f+indiceTextY,

				0.0f+indiceTextX, 0.15625f+indiceTextY,
				0.03125f+indiceTextX, 0.125f+indiceTextY,
				0.0f+indiceTextX, 0.125f+indiceTextY,
				0.03125f+indiceTextX, 0.15625f+indiceTextY,
				0.03125f+indiceTextX, 0.125f+indiceTextY,
				0.0f+indiceTextX, 0.15625f+indiceTextY
		};
		
	}

	private float[] genIronTexture(float indiceTextX, float indiceTextY){
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;
		
		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		return new float[]{
				//south
				0.15625f+indiceTextX, 0.09375f+indiceTextY,
				0.15625f+indiceTextX, 0.0625f+indiceTextY,
				0.125f+indiceTextX, 0.09375f+indiceTextY,
				0.125f+indiceTextX, 0.09375f+indiceTextY,
				0.15625f+indiceTextX, 0.0625f+indiceTextY,
				0.125f+indiceTextX, 0.0625f+indiceTextY,

				0.15625f+indiceTextX, 0.0625f+indiceTextY,
				0.125f+indiceTextX, 0.0625f+indiceTextY,
				0.125f+indiceTextX, 0.09375f+indiceTextY,
				0.15625f+indiceTextX, 0.0625f+indiceTextY,
				0.125f+indiceTextX, 0.09375f+indiceTextY,
				0.15625f+indiceTextX, 0.09375f+indiceTextY,

				//top
				0.15625f+indiceTextX, 0.09375f+indiceTextY,
				0.15625f+indiceTextX, 0.0625f+indiceTextY,
				0.125f+indiceTextX, 0.09375f+indiceTextY,
				0.125f+indiceTextX, 0.09375f+indiceTextY,
				0.15625f+indiceTextX, 0.0625f+indiceTextY,
				0.125f+indiceTextX, 0.0625f+indiceTextY,

				//bottom
				0.125f+indiceTextX, 0.0625f+indiceTextY,
				0.125f+indiceTextX, 0.09375f+indiceTextY,
				0.15625f+indiceTextX, 0.0625f+indiceTextY,
				0.15625f+indiceTextX, 0.0625f+indiceTextY,
				0.125f+indiceTextX, 0.09375f+indiceTextY,
				0.15625f+indiceTextX, 0.09375f+indiceTextY,

				0.15625f+indiceTextX, 0.09375f+indiceTextY,
				0.15625f+indiceTextX, 0.0625f+indiceTextY,
				0.125f+indiceTextX, 0.09375f+indiceTextY,
				0.125f+indiceTextX, 0.09375f+indiceTextY,
				0.15625f+indiceTextX, 0.0625f+indiceTextY,
				0.125f+indiceTextX, 0.0625f+indiceTextY,

				0.125f+indiceTextX, 0.09375f+indiceTextY,
				0.15625f+indiceTextX, 0.0625f+indiceTextY,
				0.125f+indiceTextX, 0.0625f+indiceTextY,
				0.15625f+indiceTextX, 0.09375f+indiceTextY,
				0.15625f+indiceTextX, 0.0625f+indiceTextY,
				0.125f+indiceTextX, 0.09375f+indiceTextY,
		};
		
	}

	private float[] genCopperTexture(float indiceTextX, float indiceTextY){
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;
		
		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		return new float[]{
				//south
				0.09375f+indiceTextX, 0.15625f+indiceTextY,
				0.09375f+indiceTextX, 0.125f+indiceTextY,
				0.0625f+indiceTextX, 0.15625f+indiceTextY,
				0.0625f+indiceTextX, 0.15625f+indiceTextY,
				0.09375f+indiceTextX, 0.125f+indiceTextY,
				0.0625f+indiceTextX, 0.125f+indiceTextY,

				0.09375f+indiceTextX, 0.125f+indiceTextY,
				0.0625f+indiceTextX, 0.125f+indiceTextY,
				0.0625f+indiceTextX, 0.15625f+indiceTextY,
				0.09375f+indiceTextX, 0.125f+indiceTextY,
				0.0625f+indiceTextX, 0.15625f+indiceTextY,
				0.09375f+indiceTextX, 0.15625f+indiceTextY,

				//top
				0.09375f+indiceTextX, 0.15625f+indiceTextY,
				0.09375f+indiceTextX, 0.125f+indiceTextY,
				0.0625f+indiceTextX, 0.15625f+indiceTextY,
				0.0625f+indiceTextX, 0.15625f+indiceTextY,
				0.09375f+indiceTextX, 0.125f+indiceTextY,
				0.0625f+indiceTextX, 0.125f+indiceTextY,

				//bottom
				0.0625f+indiceTextX, 0.125f+indiceTextY,
				0.0625f+indiceTextX, 0.15625f+indiceTextY,
				0.09375f+indiceTextX, 0.125f+indiceTextY,
				0.09375f+indiceTextX, 0.125f+indiceTextY,
				0.0625f+indiceTextX, 0.15625f+indiceTextY,
				0.09375f+indiceTextX, 0.15625f+indiceTextY,

				0.09375f+indiceTextX, 0.15625f+indiceTextY,
				0.09375f+indiceTextX, 0.125f+indiceTextY,
				0.0625f+indiceTextX, 0.15625f+indiceTextY,
				0.0625f+indiceTextX, 0.15625f+indiceTextY,
				0.09375f+indiceTextX, 0.125f+indiceTextY,
				0.0625f+indiceTextX, 0.125f+indiceTextY,

				0.0625f+indiceTextX, 0.15625f+indiceTextY,
				0.09375f+indiceTextX, 0.125f+indiceTextY,
				0.0625f+indiceTextX, 0.125f+indiceTextY,
				0.09375f+indiceTextX, 0.15625f+indiceTextY,
				0.09375f+indiceTextX, 0.125f+indiceTextY,
				0.0625f+indiceTextX, 0.15625f+indiceTextY,
		};
		
	}

	private float[] genTitaneTexture(float indiceTextX, float indiceTextY){
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;
		
		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		return new float[]{
				//south
				0.15625f+indiceTextX, 0.15625f+indiceTextY,
				0.15625f+indiceTextX, 0.125f+indiceTextY,
				0.125f+indiceTextX, 0.15625f+indiceTextY,
				0.125f+indiceTextX, 0.15625f+indiceTextY,
				0.15625f+indiceTextX, 0.125f+indiceTextY,
				0.125f+indiceTextX, 0.125f+indiceTextY,

				0.15625f+indiceTextX, 0.125f+indiceTextY,
				0.125f+indiceTextX, 0.125f+indiceTextY,
				0.125f+indiceTextX, 0.15625f+indiceTextY,
				0.15625f+indiceTextX, 0.125f+indiceTextY,
				0.125f+indiceTextX, 0.15625f+indiceTextY,
				0.15625f+indiceTextX, 0.15625f+indiceTextY,

				//top
				0.15625f+indiceTextX, 0.15625f+indiceTextY,
				0.15625f+indiceTextX, 0.125f+indiceTextY,
				0.125f+indiceTextX, 0.15625f+indiceTextY,
				0.125f+indiceTextX, 0.15625f+indiceTextY,
				0.15625f+indiceTextX, 0.125f+indiceTextY,
				0.125f+indiceTextX, 0.125f+indiceTextY,

				//bottom
				0.125f+indiceTextX, 0.125f+indiceTextY,
				0.125f+indiceTextX, 0.15625f+indiceTextY,
				0.15625f+indiceTextX, 0.125f+indiceTextY,
				0.15625f+indiceTextX, 0.125f+indiceTextY,
				0.125f+indiceTextX, 0.15625f+indiceTextY,
				0.15625f+indiceTextX, 0.15625f+indiceTextY,

				0.15625f+indiceTextX, 0.15625f+indiceTextY,
				0.15625f+indiceTextX, 0.125f+indiceTextY,
				0.125f+indiceTextX, 0.15625f+indiceTextY,
				0.125f+indiceTextX, 0.15625f+indiceTextY,
				0.15625f+indiceTextX, 0.125f+indiceTextY,
				0.125f+indiceTextX, 0.125f+indiceTextY,

				0.125f+indiceTextX, 0.15625f+indiceTextY,
				0.15625f+indiceTextX, 0.125f+indiceTextY,
				0.125f+indiceTextX, 0.125f+indiceTextY,
				0.15625f+indiceTextX, 0.15625f+indiceTextY,
				0.15625f+indiceTextX, 0.125f+indiceTextY,
				0.125f+indiceTextX, 0.15625f+indiceTextY,
		};
		
	}

	private float[] genUraniumTexture(float indiceTextX, float indiceTextY){
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;
		
		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		return new float[]{
				//south
				0.21875f+indiceTextX, 0.09375f+indiceTextY,
				0.21875f+indiceTextX, 0.0625f+indiceTextY,
				0.1875f+indiceTextX, 0.09375f+indiceTextY,
				0.1875f+indiceTextX, 0.09375f+indiceTextY,
				0.21875f+indiceTextX, 0.0625f+indiceTextY,
				0.1875f+indiceTextX, 0.0625f+indiceTextY,

				0.21875f+indiceTextX, 0.0625f+indiceTextY,
				0.1875f+indiceTextX, 0.0625f+indiceTextY,
				0.1875f+indiceTextX, 0.09375f+indiceTextY,
				0.21875f+indiceTextX, 0.0625f+indiceTextY,
				0.1875f+indiceTextX, 0.09375f+indiceTextY,
				0.21875f+indiceTextX, 0.09375f+indiceTextY,

				//top
				0.21875f+indiceTextX, 0.09375f+indiceTextY,
				0.21875f+indiceTextX, 0.0625f+indiceTextY,
				0.1875f+indiceTextX, 0.09375f+indiceTextY,
				0.1875f+indiceTextX, 0.09375f+indiceTextY,
				0.21875f+indiceTextX, 0.0625f+indiceTextY,
				0.1875f+indiceTextX, 0.0625f+indiceTextY,

				//bottom
				0.1875f+indiceTextX, 0.0625f+indiceTextY,
				0.1875f+indiceTextX, 0.09375f+indiceTextY,
				0.21875f+indiceTextX, 0.0625f+indiceTextY,
				0.21875f+indiceTextX, 0.0625f+indiceTextY,
				0.1875f+indiceTextX, 0.09375f+indiceTextY,
				0.21875f+indiceTextX, 0.09375f+indiceTextY,

				0.21875f+indiceTextX, 0.09375f+indiceTextY,
				0.21875f+indiceTextX, 0.0625f+indiceTextY,
				0.1875f+indiceTextX, 0.09375f+indiceTextY,
				0.1875f+indiceTextX, 0.09375f+indiceTextY,
				0.21875f+indiceTextX, 0.0625f+indiceTextY,
				0.1875f+indiceTextX, 0.0625f+indiceTextY,

				0.1875f+indiceTextX, 0.09375f+indiceTextY,
				0.21875f+indiceTextX, 0.0625f+indiceTextY,
				0.1875f+indiceTextX, 0.0625f+indiceTextY,
				0.21875f+indiceTextX, 0.09375f+indiceTextY,
				0.21875f+indiceTextX, 0.0625f+indiceTextY,
				0.1875f+indiceTextX, 0.09375f+indiceTextY
		};
		
	}

	private float[] genWoodTexture(float indiceTextX, float indiceTextY){
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;
		
		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		return new float[]{
				//south
				0.03125f+indiceTextX, 0.21875f+indiceTextY,
				0.03125f+indiceTextX, 0.1875f+indiceTextY,
				0.0f+indiceTextX, 0.21875f+indiceTextY,
				0.0f+indiceTextX, 0.21875f+indiceTextY,
				0.03125f+indiceTextX, 0.1875f+indiceTextY,
				0.0f+indiceTextX, 0.1875f+indiceTextY,

				0.03125f+indiceTextX, 0.1875f+indiceTextY,
				0.0f+indiceTextX, 0.1875f+indiceTextY,
				0.0f+indiceTextX, 0.21875f+indiceTextY,
				0.03125f+indiceTextX, 0.1875f+indiceTextY,
				0.0f+indiceTextX, 0.21875f+indiceTextY,
				0.03125f+indiceTextX, 0.21875f+indiceTextY,

				//top
				0.28125f, 0.15625f,
				0.28125f, 0.125f,
				0.25f, 0.15625f,
				0.25f, 0.15625f,
				0.28125f, 0.125f,
				0.25f, 0.125f,

				//bottom
				0.25f, 0.125f,
				0.25f, 0.15625f,
				0.28125f, 0.125f,
				0.28125f, 0.125f,
				0.25f, 0.15625f,
				0.28125f, 0.15625f,

				0.03125f+indiceTextX, 0.21875f+indiceTextY,
				0.03125f+indiceTextX, 0.1875f+indiceTextY,
				0.0f+indiceTextX, 0.21875f+indiceTextY,
				0.0f+indiceTextX, 0.21875f+indiceTextY,
				0.03125f+indiceTextX, 0.1875f+indiceTextY,
				0.0f+indiceTextX, 0.1875f+indiceTextY,

				0.0f+indiceTextX, 0.21875f+indiceTextY,
				0.03125f+indiceTextX, 0.1875f+indiceTextY,
				0.0f+indiceTextX, 0.1875f+indiceTextY,
				0.03125f+indiceTextX, 0.21875f+indiceTextY,
				0.03125f+indiceTextX, 0.1875f+indiceTextY,
				0.0f+indiceTextX, 0.21875f+indiceTextY,
		};
		
	}

	private float[] genLeafTexture(float indiceTextX, float indiceTextY){
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;
		
		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		return new float[]{
				//south
				0.09375f+indiceTextX, 0.21875f+indiceTextY,
				0.09375f+indiceTextX, 0.1875f+indiceTextY,
				0.0625f+indiceTextX, 0.21875f+indiceTextY,
				0.0625f+indiceTextX, 0.21875f+indiceTextY,
				0.09375f+indiceTextX, 0.1875f+indiceTextY,
				0.0625f+indiceTextX, 0.1875f+indiceTextY,

				0.09375f+indiceTextX, 0.1875f+indiceTextY,
				0.0625f+indiceTextX, 0.1875f+indiceTextY,
				0.0625f+indiceTextX, 0.21875f+indiceTextY,
				0.09375f+indiceTextX, 0.1875f+indiceTextY,
				0.0625f+indiceTextX, 0.21875f+indiceTextY,
				0.09375f+indiceTextX, 0.21875f+indiceTextY,

				//top
				0.09375f+indiceTextX, 0.21875f+indiceTextY,
				0.09375f+indiceTextX, 0.1875f+indiceTextY,
				0.0625f+indiceTextX, 0.21875f+indiceTextY,
				0.0625f+indiceTextX, 0.21875f+indiceTextY,
				0.09375f+indiceTextX, 0.1875f+indiceTextY,
				0.0625f+indiceTextX, 0.1875f+indiceTextY,

				//bottom
				0.0625f+indiceTextX, 0.1875f+indiceTextY,
				0.0625f+indiceTextX, 0.21875f+indiceTextY,
				0.09375f+indiceTextX, 0.1875f+indiceTextY,
				0.09375f+indiceTextX, 0.1875f+indiceTextY,
				0.0625f+indiceTextX, 0.21875f+indiceTextY,
				0.09375f+indiceTextX, 0.21875f+indiceTextY,

				0.09375f+indiceTextX, 0.21875f+indiceTextY,
				0.09375f+indiceTextX, 0.1875f+indiceTextY,
				0.0625f+indiceTextX, 0.21875f+indiceTextY,
				0.0625f+indiceTextX, 0.21875f+indiceTextY,
				0.09375f+indiceTextX, 0.1875f+indiceTextY,
				0.0625f+indiceTextX, 0.1875f+indiceTextY,

				0.0625f+indiceTextX, 0.21875f+indiceTextY,
				0.09375f+indiceTextX, 0.1875f+indiceTextY,
				0.0625f+indiceTextX, 0.1875f+indiceTextY,
				0.09375f+indiceTextX, 0.21875f+indiceTextY,
				0.09375f+indiceTextX, 0.1875f+indiceTextY,
				0.0625f+indiceTextX, 0.21875f+indiceTextY
		};
		
	}

	private float[] genWaterTexture(float indiceTextX, float indiceTextY){
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;
		
		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		return new float[]{
				//south
				0.28125f+indiceTextX, 0.03125f+indiceTextY,
				0.28125f+indiceTextX, 0.0f+indiceTextY,
				0.25f+indiceTextX, 0.03125f+indiceTextY,
				0.25f+indiceTextX, 0.03125f+indiceTextY,
				0.28125f+indiceTextX, 0.0f+indiceTextY,
				0.25f+indiceTextX, 0.0f+indiceTextY,

				0.28125f+indiceTextX, 0.0f+indiceTextY,
				0.25f+indiceTextX, 0.0f+indiceTextY,
				0.25f+indiceTextX, 0.03125f+indiceTextY,
				0.28125f+indiceTextX, 0.0f+indiceTextY,
				0.25f+indiceTextX, 0.03125f+indiceTextY,
				0.28125f+indiceTextX, 0.03125f+indiceTextY,

				//top
				0.28125f+indiceTextX, 0.03125f+indiceTextY,
				0.28125f+indiceTextX, 0.0f+indiceTextY,
				0.25f+indiceTextX, 0.03125f+indiceTextY,
				0.25f+indiceTextX, 0.03125f+indiceTextY,
				0.28125f+indiceTextX, 0.0f+indiceTextY,
				0.25f+indiceTextX, 0.0f+indiceTextY,

				//bottom
				0.25f+indiceTextX, 0.0f+indiceTextY,
				0.25f+indiceTextX, 0.03125f+indiceTextY,
				0.28125f+indiceTextX, 0.0f+indiceTextY,
				0.28125f+indiceTextX, 0.0f+indiceTextY,
				0.25f+indiceTextX, 0.03125f+indiceTextY,
				0.28125f+indiceTextX, 0.03125f+indiceTextY,

				0.28125f+indiceTextX, 0.03125f+indiceTextY,
				0.28125f+indiceTextX, 0.0f+indiceTextY,
				0.25f+indiceTextX, 0.03125f+indiceTextY,
				0.25f+indiceTextX, 0.03125f+indiceTextY,
				0.28125f+indiceTextX, 0.0f+indiceTextY,
				0.25f+indiceTextX, 0.0f+indiceTextY,

				0.25f+indiceTextX, 0.03125f+indiceTextY,
				0.28125f+indiceTextX, 0.0f+indiceTextY,
				0.25f+indiceTextX, 0.0f+indiceTextY,
				0.28125f+indiceTextX, 0.03125f+indiceTextY,
				0.28125f+indiceTextX, 0.0f+indiceTextY,
				0.25f+indiceTextX, 0.03125f+indiceTextY
		};
		
	}

	private float[] genLavaTexture(float indiceTextX, float indiceTextY){
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;
		
		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		return new float[]{
				//south
				0.28125f+indiceTextX, 0.09375f+indiceTextY,
				0.28125f+indiceTextX, 0.0625f+indiceTextY,
				0.25f+indiceTextX, 0.09375f+indiceTextY,
				0.25f+indiceTextX, 0.09375f+indiceTextY,
				0.28125f+indiceTextX, 0.0625f+indiceTextY,
				0.25f+indiceTextX, 0.0625f+indiceTextY,

				0.28125f+indiceTextX, 0.0625f+indiceTextY,
				0.25f+indiceTextX, 0.0625f+indiceTextY,
				0.25f+indiceTextX, 0.09375f+indiceTextY,
				0.28125f+indiceTextX, 0.0625f+indiceTextY,
				0.25f+indiceTextX, 0.09375f+indiceTextY,
				0.28125f+indiceTextX, 0.09375f+indiceTextY,

				//top
				0.28125f+indiceTextX, 0.09375f+indiceTextY,
				0.28125f+indiceTextX, 0.0625f+indiceTextY,
				0.25f+indiceTextX, 0.09375f+indiceTextY,
				0.25f+indiceTextX, 0.09375f+indiceTextY,
				0.28125f+indiceTextX, 0.0625f+indiceTextY,
				0.25f+indiceTextX, 0.0625f+indiceTextY,

				//bottom
				0.25f+indiceTextX, 0.0625f+indiceTextY,
				0.25f+indiceTextX, 0.09375f+indiceTextY,
				0.28125f+indiceTextX, 0.0625f+indiceTextY,
				0.28125f+indiceTextX, 0.0625f+indiceTextY,
				0.25f+indiceTextX, 0.09375f+indiceTextY,
				0.28125f+indiceTextX, 0.09375f+indiceTextY,

				0.28125f+indiceTextX, 0.09375f+indiceTextY,
				0.28125f+indiceTextX, 0.0625f+indiceTextY,
				0.25f+indiceTextX, 0.09375f+indiceTextY,
				0.25f+indiceTextX, 0.09375f+indiceTextY,
				0.28125f+indiceTextX, 0.0625f+indiceTextY,
				0.25f+indiceTextX, 0.0625f+indiceTextY,

				0.25f+indiceTextX, 0.09375f+indiceTextY,
				0.28125f+indiceTextX, 0.0625f+indiceTextY,
				0.25f+indiceTextX, 0.0625f+indiceTextY,
				0.28125f+indiceTextX, 0.09375f+indiceTextY,
				0.28125f+indiceTextX, 0.0625f+indiceTextY,
				0.25f+indiceTextX, 0.09375f+indiceTextY
		};
		
	}
	
	private float[] genPlankTexture(){
		return new float[]{
				//south
				0.21875f, 0.15625f,
				0.21875f, 0.125f,
				0.1875f, 0.15625f,
				0.1875f, 0.15625f,
				0.21875f, 0.125f,
				0.1875f, 0.125f,

				0.21875f, 0.125f,
				0.1875f, 0.125f,
				0.1875f, 0.15625f,
				0.21875f, 0.125f,
				0.1875f, 0.15625f,
				0.21875f, 0.15625f,

				//top
				0.21875f, 0.15625f,
				0.21875f, 0.125f,
				0.1875f, 0.15625f,
				0.1875f, 0.15625f,
				0.21875f, 0.125f,
				0.1875f, 0.125f,

				//bottom
				0.1875f, 0.125f,
				0.1875f, 0.15625f,
				0.21875f, 0.125f,
				0.21875f, 0.125f,
				0.1875f, 0.15625f,
				0.21875f, 0.15625f,

				0.21875f, 0.15625f,
				0.21875f, 0.125f,
				0.1875f, 0.15625f,
				0.1875f, 0.15625f,
				0.21875f, 0.125f,
				0.1875f, 0.125f,

				0.1875f, 0.15625f,
				0.21875f, 0.125f,
				0.1875f, 0.125f,
				0.21875f, 0.15625f,
				0.21875f, 0.125f,
				0.1875f, 0.15625f
		};
		
	}
	
	private float[] genWorkbenchTexture(){
		return new float[]{
				//south
				0.21875f, 0.1875f,
				0.21875f, 0.15625f,
				0.1875f, 0.1875f,
				0.1875f, 0.1875f,
				0.21875f, 0.15625f,
				0.1875f, 0.15625f,

				0.21875f, 0.15625f,
				0.1875f, 0.15625f,
				0.1875f, 0.1875f,
				0.21875f, 0.15625f,
				0.1875f, 0.1875f,
				0.21875f, 0.1875f,

				//top
				0.25f, 0.15625f,
				0.25f, 0.125f,
				0.21875f, 0.15625f,
				0.21875f, 0.15625f,
				0.25f, 0.125f,
				0.21875f, 0.125f,

				//bottom
				0.21875f, 0.125f,
				0.21875f, 0.15625f,
				0.25f, 0.125f,
				0.25f, 0.125f,
				0.21875f, 0.15625f,
				0.25f, 0.15625f,

				0.25f, 0.1875f,
				0.25f, 0.15625f,
				0.21875f, 0.1875f,
				0.21875f, 0.1875f,
				0.25f, 0.15625f,
				0.21875f, 0.15625f,

				0.21875f, 0.1875f,
				0.25f, 0.15625f,
				0.21875f, 0.15625f,
				0.25f, 0.1875f,
				0.25f, 0.15625f,
				0.21875f, 0.1875f,
		};
	}
	
	public void bindText(){
		glBindTexture(GL_TEXTURE_2D, texture.getTextureID());
		texture.bind();
	}

	public void bindDrawTexture(){
		glBindBuffer(GL_ARRAY_BUFFER, vboTexHandle);
		glTexCoordPointer(2, GL_FLOAT, 0, 0l);
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
	
	public void undindTexture(){
		glBindTexture(GL_TEXTURE_2D, 0);
		texture.release();
	}

	public float[] genText(int type, float x, float y){
		textureData.clear();
		switch (type) {
		case 1:
			return genGrassTexture(x, y);
		case 2:
			return genDirtTexture(x,y);
		case 3:
			return genStoneTexture(x, y);
		case 4:
			return genCoalTexture(x, y);
		case 5:
			return genGoldTexture(x, y);
		case 6:
			return genSilverTexture(x, y);
		case 7:
			return genIronTexture(x, y);
		case 8:
			return genCopperTexture(x, y);
		case 9:
			return genTitaneTexture(x, y);
		case 10:
			return genUraniumTexture(x, y);
		case 11:
			return genWoodTexture(x, y);
		case 12:
			return genLeafTexture(x, y);
		case 13:
			return genWaterTexture(x, y);
		case 14:
			return genLavaTexture(x, y);
		case 15:
			return genPlankTexture();
		case 16:
			return genWorkbenchTexture();
		default:
			return genGrassTexture(x, y);
		}
	}

}
