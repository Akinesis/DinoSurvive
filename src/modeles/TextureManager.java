package modeles;

import java.io.IOException;
import java.nio.FloatBuffer;



import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

import org.lwjgl.BufferUtils;
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
			texture = TextureLoader.getTexture("PNG",
					ResourceLoader.getResourceAsStream("res/text.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		textureData = BufferUtils.createFloatBuffer(36 * 2);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	}

	private void genGrassTexture(float indiceTextX, float indiceTextY){	
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
		
		textureData.put(new float[]{
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

		});
		textureData.flip();
	}

	private void genDirtTexture(float indiceTextX, float indiceTextY){
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;
		
		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		textureData.put(new float[]{
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
		});
		textureData.flip();
	}

	private void genStoneTexture(float indiceTextX, float indiceTextY){
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;
		
		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		textureData.put(new float[]{
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
		});
		textureData.flip();
	}
	
	private void genCoalTexture(float indiceTextX, float indiceTextY){
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;
		
		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		textureData.put(new float[]{
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
		});
		textureData.flip();
	}

	private void genGoldTexture(float indiceTextX, float indiceTextY){
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;
		
		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		textureData.put(new float[]{
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
		});
		textureData.flip();
	}

	private void genSilverTexture(float indiceTextX, float indiceTextY){
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;
		
		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		textureData.put(new float[]{
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
		});
		textureData.flip();
	}

	private void genIronTexture(float indiceTextX, float indiceTextY){
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;
		
		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		textureData.put(new float[]{
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
		});
		textureData.flip();
	}

	private void genCopperTexture(float indiceTextX, float indiceTextY){
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;
		
		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		textureData.put(new float[]{
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
		});
		textureData.flip();
	}

	private void genTitaneTexture(float indiceTextX, float indiceTextY){
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;
		
		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		textureData.put(new float[]{
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
		});
		textureData.flip();
	}

	private void genUraniumTexture(float indiceTextX, float indiceTextY){
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;
		
		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		textureData.put(new float[]{
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
		});
		textureData.flip();
	}

	private void genWoodTexture(float indiceTextX, float indiceTextY){
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;
		
		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		textureData.put(new float[]{
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
		});
		textureData.flip();
	}

	private void genLeafTexture(float indiceTextX, float indiceTextY){
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;
		
		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		textureData.put(new float[]{
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
		});
		textureData.flip();
	}

	private void genWaterTexture(float indiceTextX, float indiceTextY){
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;
		
		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		textureData.put(new float[]{
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
		});
		textureData.flip();
	}

	private void genLavaTexture(float indiceTextX, float indiceTextY){
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;
		
		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		textureData.put(new float[]{
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
		});
		textureData.flip();
	}
	
	private void genPlankTexture(){
		textureData.put(new float[]{
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
		});
		textureData.flip();
	}
	
	private void genWorkbenchTexture(){
		textureData.put(new float[]{
				//south
				0.125f, 0.125f,
				0.125f, 0.09375f,
				0.09375f, 0.125f,
				0.09375f, 0.125f,
				0.125f, 0.09375f,
				0.09375f, 0.09375f,

				0.125f, 0.09375f,
				0.09375f, 0.09375f,
				0.09375f, 0.125f,
				0.125f, 0.09375f,
				0.09375f, 0.125f,
				0.125f, 0.125f,

				//top
				0.15625f, 0.09375f,
				0.15625f, 0.0625f,
				0.125f, 0.09375f,
				0.125f, 0.09375f,
				0.15625f, 0.0625f,
				0.125f, 0.0625f,

				//bottom
				0.125f, 0.0625f,
				0.125f, 0.09375f,
				0.15625f, 0.0625f,
				0.15625f, 0.0625f,
				0.125f, 0.09375f,
				0.15625f, 0.09375f,

				0.15625f, 0.125f,
				0.15625f, 0.09375f,
				0.125f, 0.125f,
				0.125f, 0.125f,
				0.15625f, 0.09375f,
				0.125f, 0.09375f,

				0.125f, 0.125f,
				0.15625f, 0.09375f,
				0.125f, 0.09375f,
				0.15625f, 0.125f,
				0.15625f, 0.09375f,
				0.125f, 0.125f
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

	public void bindDrawTexture(){
		glBindBuffer(GL_ARRAY_BUFFER, vboTexHandle);
		glTexCoordPointer(2, GL_FLOAT, 0, 0l);
	}

	public void enableTexture(){
		glEnableClientState(GL_TEXTURE_COORD_ARRAY);
	}

	public void disableTexture(){
		glDisableClientState(GL_TEXTURE_COORD_ARRAY);
	}

	public void genText(int type, float x, float y){
		textureData.clear();
		switch (type) {
		case 1:
			genGrassTexture(x, y);
			break;
		case 2:
			genDirtTexture(x,y);
			break;
		case 3:
			genStoneTexture(x, y);
			break;
		case 4:
			genCoalTexture(x, y);
			break;
		case 5:
			genGoldTexture(x, y);
			break;
		case 6:
			genSilverTexture(x, y);
			break;
		case 7:
			genIronTexture(x, y);
			break;
		case 8:
			genCopperTexture(x, y);
			break;
		case 9:
			genTitaneTexture(x, y);
			break;
		case 10:
			genUraniumTexture(x, y);
			break;
		case 11:
			genWoodTexture(x, y);
			break;
		case 12:
			genLeafTexture(x, y);
			break;
		case 13:
			genWaterTexture(x, y);
			break;
		case 14:
			genLavaTexture(x, y);
			break;
		case 15:
			genPlankTexture();
			break;
		case 16:
			genWorkbenchTexture();
			break;
		default:
			break;
		}
	}

}
