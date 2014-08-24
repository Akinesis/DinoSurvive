package modeles;

import java.io.IOException;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class TextureManager {

	private Texture texture;

	public TextureManager() {
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/text.png_version/text.joa.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	}

	public TextureManager(String adresseTexture) {
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

	private float[] genGrassTexture(float indiceTextX, float indiceTextY){	
		float xGrass = indiceTextX*0.03125f; //dessus
		float yGrass = indiceTextY*0.03125f;

		xGrass = xGrass-(xGrass%0.001953125f);
		yGrass = yGrass-(yGrass%0.001953125f);

		float xDirt = indiceTextX*0.03125f; //dessous
		float yDirt = indiceTextY*0.03125f;

		xDirt = xDirt-(xDirt%0.001953125f);
		yDirt = yDirt-(yDirt%0.001953125f);

		//indiceTextX = (int)(indiceTextX * ((1 - 0) + 1))*0.03125f;
		indiceTextX *= 0.03125f; //cotés
		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
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

	private float[] genNailedPlankTexture(float indiceTextX, float indiceTextY){	

		indiceTextX = (int)(indiceTextX * ((1 - 0) + 1))*0.03125f;
		indiceTextY = (int)(indiceTextY * ((1 - 0) + 1))*0.03125f;

		return new float[]{
				//south
				0.15625f+indiceTextX, 0.21875f+indiceTextY,
				0.15625f+indiceTextX, 0.1875f+indiceTextY,
				0.125f+indiceTextX, 0.21875f+indiceTextY,
				0.125f+indiceTextX, 0.21875f+indiceTextY,
				0.15625f+indiceTextX, 0.1875f+indiceTextY,
				0.125f+indiceTextX, 0.1875f+indiceTextY,

				0.15625f+indiceTextX, 0.1875f+indiceTextY,
				0.125f+indiceTextX, 0.1875f+indiceTextY,
				0.125f+indiceTextX, 0.21875f+indiceTextY,
				0.15625f+indiceTextX, 0.1875f+indiceTextY,
				0.125f+indiceTextX, 0.21875f+indiceTextY,
				0.15625f+indiceTextX, 0.21875f+indiceTextY,

				//top
				0.15625f+indiceTextX, 0.21875f+indiceTextY,
				0.15625f+indiceTextX, 0.1875f+indiceTextY,
				0.125f+indiceTextX, 0.21875f+indiceTextY,
				0.125f+indiceTextX, 0.21875f+indiceTextY,
				0.15625f+indiceTextX, 0.1875f+indiceTextY,
				0.125f+indiceTextX, 0.1875f+indiceTextY,

				//bottom
				0.125f+indiceTextX, 0.1875f+indiceTextY,
				0.125f+indiceTextX, 0.21875f+indiceTextY,
				0.15625f+indiceTextX, 0.1875f+indiceTextY,
				0.15625f+indiceTextX, 0.1875f+indiceTextY,
				0.125f+indiceTextX, 0.21875f+indiceTextY,
				0.15625f+indiceTextX, 0.21875f+indiceTextY,

				0.15625f+indiceTextX, 0.21875f+indiceTextY,
				0.15625f+indiceTextX, 0.1875f+indiceTextY,
				0.125f+indiceTextX, 0.21875f+indiceTextY,
				0.125f+indiceTextX, 0.21875f+indiceTextY,
				0.15625f+indiceTextX, 0.1875f+indiceTextY,
				0.125f+indiceTextX, 0.1875f+indiceTextY,

				0.125f+indiceTextX, 0.21875f+indiceTextY,
				0.15625f+indiceTextX, 0.1875f+indiceTextY,
				0.125f+indiceTextX, 0.1875f+indiceTextY,
				0.15625f+indiceTextX, 0.21875f+indiceTextY,
				0.15625f+indiceTextX, 0.1875f+indiceTextY,
				0.125f+indiceTextX, 0.21875f+indiceTextY,

		};

	}

	private float[] genClayTexture(float indiceTextX, float indiceTextY){
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;

		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		return new float[]{
				//south
				0.21875f+indiceTextX, 0.21875f+indiceTextY,
				0.21875f+indiceTextX, 0.1875f+indiceTextY,
				0.1875f+indiceTextX, 0.21875f+indiceTextY,
				0.1875f+indiceTextX, 0.21875f+indiceTextY,
				0.21875f+indiceTextX, 0.1875f+indiceTextY,
				0.1875f+indiceTextX, 0.1875f+indiceTextY,

				0.21875f+indiceTextX, 0.1875f+indiceTextY,
				0.1875f+indiceTextX, 0.1875f+indiceTextY,
				0.1875f+indiceTextX, 0.21875f+indiceTextY,
				0.21875f+indiceTextX, 0.1875f+indiceTextY,
				0.1875f+indiceTextX, 0.21875f+indiceTextY,
				0.21875f+indiceTextX, 0.21875f+indiceTextY,

				//top
				0.21875f+indiceTextX, 0.21875f+indiceTextY,
				0.21875f+indiceTextX, 0.1875f+indiceTextY,
				0.1875f+indiceTextX, 0.21875f+indiceTextY,
				0.1875f+indiceTextX, 0.21875f+indiceTextY,
				0.21875f+indiceTextX, 0.1875f+indiceTextY,
				0.1875f+indiceTextX, 0.1875f+indiceTextY,

				//bottom
				0.1875f+indiceTextX, 0.1875f+indiceTextY,
				0.1875f+indiceTextX, 0.21875f+indiceTextY,
				0.21875f+indiceTextX, 0.1875f+indiceTextY,
				0.21875f+indiceTextX, 0.1875f+indiceTextY,
				0.1875f+indiceTextX, 0.21875f+indiceTextY,
				0.21875f+indiceTextX, 0.21875f+indiceTextY,

				0.21875f+indiceTextX, 0.21875f+indiceTextY,
				0.21875f+indiceTextX, 0.1875f+indiceTextY,
				0.1875f+indiceTextX, 0.21875f+indiceTextY,
				0.1875f+indiceTextX, 0.21875f+indiceTextY,
				0.21875f+indiceTextX, 0.1875f+indiceTextY,
				0.1875f+indiceTextX, 0.1875f+indiceTextY,

				0.1875f+indiceTextX, 0.21875f+indiceTextY,
				0.21875f+indiceTextX, 0.1875f+indiceTextY,
				0.1875f+indiceTextX, 0.1875f+indiceTextY,
				0.21875f+indiceTextX, 0.21875f+indiceTextY,
				0.21875f+indiceTextX, 0.1875f+indiceTextY,
				0.1875f+indiceTextX, 0.21875f+indiceTextY
		};

	}

	private float[] genBricksTexture(float indiceTextX, float indiceTextY){	

		indiceTextX = (int)(indiceTextX * ((1 - 0) + 1))*0.03125f;
		indiceTextY = (int)(indiceTextY * ((1 - 0) + 1))*0.03125f;

		return new float[]{
				//south
				0.09375f+indiceTextX, 0.28125f+indiceTextY,
				0.09375f+indiceTextX, 0.25f+indiceTextY,
				0.0625f+indiceTextX, 0.28125f+indiceTextY,
				0.0625f+indiceTextX, 0.28125f+indiceTextY,
				0.09375f+indiceTextX, 0.25f+indiceTextY,
				0.0625f+indiceTextX, 0.25f+indiceTextY,

				0.09375f+indiceTextX, 0.25f+indiceTextY,
				0.0625f+indiceTextX, 0.25f+indiceTextY,
				0.0625f+indiceTextX, 0.28125f+indiceTextY,
				0.09375f+indiceTextX, 0.25f+indiceTextY,
				0.0625f+indiceTextX, 0.28125f+indiceTextY,
				0.09375f+indiceTextX, 0.28125f+indiceTextY,

				//top
				0.09375f+indiceTextX, 0.28125f+indiceTextY,
				0.09375f+indiceTextX, 0.25f+indiceTextY,
				0.0625f+indiceTextX, 0.28125f+indiceTextY,
				0.0625f+indiceTextX, 0.28125f+indiceTextY,
				0.09375f+indiceTextX, 0.25f+indiceTextY,
				0.0625f+indiceTextX, 0.25f+indiceTextY,

				//bottom
				0.0625f+indiceTextX, 0.25f+indiceTextY,
				0.0625f+indiceTextX, 0.28125f+indiceTextY,
				0.09375f+indiceTextX, 0.25f+indiceTextY,
				0.09375f+indiceTextX, 0.25f+indiceTextY,
				0.0625f+indiceTextX, 0.28125f+indiceTextY,
				0.09375f+indiceTextX, 0.28125f+indiceTextY,

				0.09375f+indiceTextX, 0.28125f+indiceTextY,
				0.09375f+indiceTextX, 0.25f+indiceTextY,
				0.0625f+indiceTextX, 0.28125f+indiceTextY,
				0.0625f+indiceTextX, 0.28125f+indiceTextY,
				0.09375f+indiceTextX, 0.25f+indiceTextY,
				0.0625f+indiceTextX, 0.25f+indiceTextY,

				0.0625f+indiceTextX, 0.28125f+indiceTextY,
				0.09375f+indiceTextX, 0.25f+indiceTextY,
				0.0625f+indiceTextX, 0.25f+indiceTextY,
				0.09375f+indiceTextX, 0.28125f+indiceTextY,
				0.09375f+indiceTextX, 0.25f+indiceTextY,
				0.0625f+indiceTextX, 0.28125f+indiceTextY

		};

	}

	private float[] genCementTexture(float indiceTextX, float indiceTextY){
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;

		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		return new float[]{
				//south
				0.03125f+indiceTextX, 0.28125f+indiceTextY,
				0.03125f+indiceTextX, 0.25f+indiceTextY,
				0.0f+indiceTextX, 0.28125f+indiceTextY,
				0.0f+indiceTextX, 0.28125f+indiceTextY,
				0.03125f+indiceTextX, 0.25f+indiceTextY,
				0.0f+indiceTextX, 0.25f+indiceTextY,

				0.03125f+indiceTextX, 0.25f+indiceTextY,
				0.0f+indiceTextX, 0.25f+indiceTextY,
				0.0f+indiceTextX, 0.28125f+indiceTextY,
				0.03125f+indiceTextX, 0.25f+indiceTextY,
				0.0f+indiceTextX, 0.28125f+indiceTextY,
				0.03125f+indiceTextX, 0.28125f+indiceTextY,

				//top
				0.03125f+indiceTextX, 0.28125f+indiceTextY,
				0.03125f+indiceTextX, 0.25f+indiceTextY,
				0.0f+indiceTextX, 0.28125f+indiceTextY,
				0.0f+indiceTextX, 0.28125f+indiceTextY,
				0.03125f+indiceTextX, 0.25f+indiceTextY,
				0.0f+indiceTextX, 0.25f+indiceTextY,

				//bottom
				0.0f+indiceTextX, 0.25f+indiceTextY,
				0.0f+indiceTextX, 0.28125f+indiceTextY,
				0.03125f+indiceTextX, 0.25f+indiceTextY,
				0.03125f+indiceTextX, 0.25f+indiceTextY,
				0.0f+indiceTextX, 0.28125f+indiceTextY,
				0.03125f+indiceTextX, 0.28125f+indiceTextY,

				0.03125f+indiceTextX, 0.28125f+indiceTextY,
				0.03125f+indiceTextX, 0.25f+indiceTextY,
				0.0f+indiceTextX, 0.28125f+indiceTextY,
				0.0f+indiceTextX, 0.28125f+indiceTextY,
				0.03125f+indiceTextX, 0.25f+indiceTextY,
				0.0f+indiceTextX, 0.25f+indiceTextY,

				0.0f+indiceTextX, 0.28125f+indiceTextY,
				0.03125f+indiceTextX, 0.25f+indiceTextY,
				0.0f+indiceTextX, 0.25f+indiceTextY,
				0.03125f+indiceTextX, 0.28125f+indiceTextY,
				0.03125f+indiceTextX, 0.25f+indiceTextY,
				0.0f+indiceTextX, 0.28125f+indiceTextY
		};

	}

	private float[] genConcreteTexture(float indiceTextX, float indiceTextY){
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;

		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		return new float[]{
				//south
				0.15625f+indiceTextX, 0.28125f+indiceTextY,
				0.15625f+indiceTextX, 0.25f+indiceTextY,
				0.125f+indiceTextX, 0.28125f+indiceTextY,
				0.125f+indiceTextX, 0.28125f+indiceTextY,
				0.15625f+indiceTextX, 0.25f+indiceTextY,
				0.125f+indiceTextX, 0.25f+indiceTextY,

				0.15625f+indiceTextX, 0.25f+indiceTextY,
				0.125f+indiceTextX, 0.25f+indiceTextY,
				0.125f+indiceTextX, 0.28125f+indiceTextY,
				0.15625f+indiceTextX, 0.25f+indiceTextY,
				0.125f+indiceTextX, 0.28125f+indiceTextY,
				0.15625f+indiceTextX, 0.28125f+indiceTextY,

				//top
				0.15625f+indiceTextX, 0.28125f+indiceTextY,
				0.15625f+indiceTextX, 0.25f+indiceTextY,
				0.125f+indiceTextX, 0.28125f+indiceTextY,
				0.125f+indiceTextX, 0.28125f+indiceTextY,
				0.15625f+indiceTextX, 0.25f+indiceTextY,
				0.125f+indiceTextX, 0.25f+indiceTextY,

				//bottom
				0.125f+indiceTextX, 0.25f+indiceTextY,
				0.125f+indiceTextX, 0.28125f+indiceTextY,
				0.15625f+indiceTextX, 0.25f+indiceTextY,
				0.15625f+indiceTextX, 0.25f+indiceTextY,
				0.125f+indiceTextX, 0.28125f+indiceTextY,
				0.15625f+indiceTextX, 0.28125f+indiceTextY,

				0.15625f+indiceTextX, 0.28125f+indiceTextY,
				0.15625f+indiceTextX, 0.25f+indiceTextY,
				0.125f+indiceTextX, 0.28125f+indiceTextY,
				0.125f+indiceTextX, 0.28125f+indiceTextY,
				0.15625f+indiceTextX, 0.25f+indiceTextY,
				0.125f+indiceTextX, 0.25f+indiceTextY,

				0.125f+indiceTextX, 0.28125f+indiceTextY,
				0.15625f+indiceTextX, 0.25f+indiceTextY,
				0.125f+indiceTextX, 0.25f+indiceTextY,
				0.15625f+indiceTextX, 0.28125f+indiceTextY,
				0.15625f+indiceTextX, 0.25f+indiceTextY,
				0.125f+indiceTextX, 0.28125f+indiceTextY
		};

	}

	private float[] genStoneBricksTexture(float indiceTextX, float indiceTextY){	

		indiceTextX = (int)(indiceTextX * ((1 - 0) + 1))*0.03125f;
		indiceTextY = (int)(indiceTextY * ((1 - 0) + 1))*0.03125f;

		return new float[]{
				//south
				0.28125f+indiceTextX, 0.21875f+indiceTextY,
				0.28125f+indiceTextX, 0.1875f+indiceTextY,
				0.25f+indiceTextX, 0.21875f+indiceTextY,
				0.25f+indiceTextX, 0.21875f+indiceTextY,
				0.28125f+indiceTextX, 0.1875f+indiceTextY,
				0.25f+indiceTextX, 0.1875f+indiceTextY,

				0.28125f+indiceTextX, 0.1875f+indiceTextY,
				0.25f+indiceTextX, 0.1875f+indiceTextY,
				0.25f+indiceTextX, 0.21875f+indiceTextY,
				0.28125f+indiceTextX, 0.1875f+indiceTextY,
				0.25f+indiceTextX, 0.21875f+indiceTextY,
				0.28125f+indiceTextX, 0.21875f+indiceTextY,

				//top
				0.28125f+indiceTextX, 0.21875f+indiceTextY,
				0.28125f+indiceTextX, 0.1875f+indiceTextY,
				0.25f+indiceTextX, 0.21875f+indiceTextY,
				0.25f+indiceTextX, 0.21875f+indiceTextY,
				0.28125f+indiceTextX, 0.1875f+indiceTextY,
				0.25f+indiceTextX, 0.1875f+indiceTextY,

				//bottom
				0.25f+indiceTextX, 0.1875f+indiceTextY,
				0.25f+indiceTextX, 0.21875f+indiceTextY,
				0.28125f+indiceTextX, 0.1875f+indiceTextY,
				0.28125f+indiceTextX, 0.1875f+indiceTextY,
				0.25f+indiceTextX, 0.21875f+indiceTextY,
				0.28125f+indiceTextX, 0.21875f+indiceTextY,

				0.28125f+indiceTextX, 0.21875f+indiceTextY,
				0.28125f+indiceTextX, 0.1875f+indiceTextY,
				0.25f+indiceTextX, 0.21875f+indiceTextY,
				0.25f+indiceTextX, 0.21875f+indiceTextY,
				0.28125f+indiceTextX, 0.1875f+indiceTextY,
				0.25f+indiceTextX, 0.1875f+indiceTextY,

				0.25f+indiceTextX, 0.21875f+indiceTextY,
				0.28125f+indiceTextX, 0.1875f+indiceTextY,
				0.25f+indiceTextX, 0.1875f+indiceTextY,
				0.28125f+indiceTextX, 0.21875f+indiceTextY,
				0.28125f+indiceTextX, 0.1875f+indiceTextY,
				0.25f+indiceTextX, 0.21875f+indiceTextY

		};

	}

	private float[] genSmallStoneBricksTexture(float indiceTextX, float indiceTextY){	

		indiceTextX = (int)(indiceTextX * ((1 - 0) + 1))*0.03125f;
		indiceTextY = (int)(indiceTextY * ((1 - 0) + 1))*0.03125f;

		return new float[]{
				//south
				0.21875f+indiceTextX, 0.28125f+indiceTextY,
				0.21875f+indiceTextX, 0.25f+indiceTextY,
				0.1875f+indiceTextX, 0.28125f+indiceTextY,
				0.1875f+indiceTextX, 0.28125f+indiceTextY,
				0.21875f+indiceTextX, 0.25f+indiceTextY,
				0.1875f+indiceTextX, 0.25f+indiceTextY,

				0.21875f+indiceTextX, 0.25f+indiceTextY,
				0.1875f+indiceTextX, 0.25f+indiceTextY,
				0.1875f+indiceTextX, 0.28125f+indiceTextY,
				0.21875f+indiceTextX, 0.25f+indiceTextY,
				0.1875f+indiceTextX, 0.28125f+indiceTextY,
				0.21875f+indiceTextX, 0.28125f+indiceTextY,

				//top
				0.21875f+indiceTextX, 0.28125f+indiceTextY,
				0.21875f+indiceTextX, 0.25f+indiceTextY,
				0.1875f+indiceTextX, 0.28125f+indiceTextY,
				0.1875f+indiceTextX, 0.28125f+indiceTextY,
				0.21875f+indiceTextX, 0.25f+indiceTextY,
				0.1875f+indiceTextX, 0.25f+indiceTextY,

				//bottom
				0.1875f+indiceTextX, 0.25f+indiceTextY,
				0.1875f+indiceTextX, 0.28125f+indiceTextY,
				0.21875f+indiceTextX, 0.25f+indiceTextY,
				0.21875f+indiceTextX, 0.25f+indiceTextY,
				0.1875f+indiceTextX, 0.28125f+indiceTextY,
				0.21875f+indiceTextX, 0.28125f+indiceTextY,

				0.21875f+indiceTextX, 0.28125f+indiceTextY,
				0.21875f+indiceTextX, 0.25f+indiceTextY,
				0.1875f+indiceTextX, 0.28125f+indiceTextY,
				0.1875f+indiceTextX, 0.28125f+indiceTextY,
				0.21875f+indiceTextX, 0.25f+indiceTextY,
				0.1875f+indiceTextX, 0.25f+indiceTextY,

				0.1875f+indiceTextX, 0.28125f+indiceTextY,
				0.21875f+indiceTextX, 0.25f+indiceTextY,
				0.1875f+indiceTextX, 0.25f+indiceTextY,
				0.21875f+indiceTextX, 0.28125f+indiceTextY,
				0.21875f+indiceTextX, 0.25f+indiceTextY,
				0.1875f+indiceTextX, 0.28125f+indiceTextY

		};

	}

	private float[] genWavySteelPanelTexture(){
		return new float[]{
				//south
				0.28125f, 0.1875f,
				0.28125f, 0.15625f,
				0.25f, 0.1875f,
				0.25f, 0.1875f,
				0.28125f, 0.15625f,
				0.25f, 0.15625f,

				0.28125f, 0.15625f,
				0.25f, 0.15625f,
				0.25f, 0.1875f,
				0.28125f, 0.15625f,
				0.25f, 0.1875f,
				0.28125f, 0.1875f,

				//top
				0.28125f, 0.1875f,
				0.28125f, 0.15625f,
				0.25f, 0.1875f,
				0.25f, 0.1875f,
				0.28125f, 0.15625f,
				0.25f, 0.15625f,

				//bottom
				0.25f, 0.15625f,
				0.25f, 0.1875f,
				0.28125f, 0.15625f,
				0.28125f, 0.15625f,
				0.25f, 0.1875f,
				0.28125f, 0.1875f,

				0.28125f, 0.1875f,
				0.28125f, 0.15625f,
				0.25f, 0.1875f,
				0.25f, 0.1875f,
				0.28125f, 0.15625f,
				0.25f, 0.15625f,

				0.25f, 0.1875f,
				0.28125f, 0.15625f,
				0.25f, 0.15625f,
				0.28125f, 0.1875f,
				0.28125f, 0.15625f,
				0.25f, 0.1875f
		};

	}

	private float[] genSteelPanelTexture(){
		return new float[]{
				//south
				0.3125f, 0.15625f,
				0.3125f, 0.125f,
				0.28125f, 0.15625f,
				0.28125f, 0.15625f,
				0.3125f, 0.125f,
				0.28125f, 0.125f,

				0.3125f, 0.125f,
				0.28125f, 0.125f,
				0.28125f, 0.15625f,
				0.3125f, 0.125f,
				0.28125f, 0.15625f,
				0.3125f, 0.15625f,

				//top
				0.3125f, 0.15625f,
				0.3125f, 0.125f,
				0.28125f, 0.15625f,
				0.28125f, 0.15625f,
				0.3125f, 0.125f,
				0.28125f, 0.125f,

				//bottom
				0.28125f, 0.125f,
				0.28125f, 0.15625f,
				0.3125f, 0.125f,
				0.3125f, 0.125f,
				0.28125f, 0.15625f,
				0.3125f, 0.15625f,

				0.3125f, 0.15625f,
				0.3125f, 0.125f,
				0.28125f, 0.15625f,
				0.28125f, 0.15625f,
				0.3125f, 0.125f,
				0.28125f, 0.125f,

				0.28125f, 0.15625f,
				0.3125f, 0.125f,
				0.28125f, 0.125f,
				0.3125f, 0.15625f,
				0.3125f, 0.125f,
				0.28125f, 0.15625f
		};

	}

	private float[] genElectricPanelTexture(){
		return new float[]{
				//south
				0.3125f, 0.15625f,
				0.3125f, 0.125f,
				0.28125f, 0.15625f,
				0.28125f, 0.15625f,
				0.3125f, 0.125f,
				0.28125f, 0.125f,

				0.3125f, 0.125f,
				0.28125f, 0.125f,
				0.28125f, 0.15625f,
				0.3125f, 0.125f,
				0.28125f, 0.15625f,
				0.3125f, 0.15625f,

				//top
				0.3125f, 0.15625f,
				0.3125f, 0.125f,
				0.28125f, 0.15625f,
				0.28125f, 0.15625f,
				0.3125f, 0.125f,
				0.28125f, 0.125f,

				//bottom
				0.28125f, 0.125f,
				0.28125f, 0.15625f,
				0.3125f, 0.125f,
				0.3125f, 0.125f,
				0.28125f, 0.15625f,
				0.3125f, 0.15625f,

				0.3125f, 0.1875f,
				0.3125f, 0.15625f,
				0.28125f, 0.1875f,
				0.28125f, 0.1875f,
				0.3125f, 0.15625f,
				0.28125f, 0.15625f,

				0.28125f, 0.15625f,
				0.3125f, 0.125f,
				0.28125f, 0.125f,
				0.3125f, 0.15625f,
				0.3125f, 0.125f,
				0.28125f, 0.15625f
		};

	}

	private float[] genChestTexture(){
		return new float[]{
				//south
				0.375f, 0.03125f,
				0.375f, 0.0f,
				0.34375f, 0.03125f,
				0.34375f, 0.03125f,
				0.375f, 0.0f,
				0.34375f, 0.0f,

				//face sud inverser
				0.34375f, 0.0f,
				0.375f, 0.0f,
				0.375f, 0.03125f,
				0.34375f, 0.0f,
				0.375f, 0.03125f,
				0.34375f, 0.03125f,

				//top
				0.34375f, 0.0625f,
				0.34375f, 0.03125f,
				0.3125f, 0.0625f,
				0.3125f, 0.0625f,
				0.34375f, 0.03125f,
				0.3125f, 0.03125f,

				//bottom
				0.3125f, 0.03125f,
				0.3125f, 0.0625f,
				0.34375f, 0.03125f,
				0.34375f, 0.03125f,
				0.3125f, 0.0625f,
				0.34375f, 0.0625f,

				0.34375f, 0.03125f,
				0.34375f, 0.0f,
				0.3125f, 0.03125f,
				0.3125f, 0.03125f,
				0.34375f, 0.0f,
				0.3125f, 0.0f,

				0.34375f, 0.0625f,
				0.375f, 0.03125f,
				0.34375f, 0.03125f,
				0.375f, 0.0625f,
				0.375f, 0.03125f,
				0.34375f, 0.0625f
		};

	}

	private float[] genSteelChestTexture(){
		return new float[]{
				//south
				0.375f, 0.09375f,
				0.375f, 0.0625f,
				0.34375f, 0.09375f,
				0.34375f, 0.09375f,
				0.375f, 0.0625f,
				0.34375f, 0.0625f,
				
				//face sud inverser
				0.34375f, 0.0625f,
				0.375f, 0.0625f,
				0.375f, 0.09375f,
				0.34375f, 0.0625f,
				0.375f, 0.09375f,
				0.34375f, 0.09375f,

				//top
				0.34375f, 0.125f,
				0.34375f, 0.09375f,
				0.3125f, 0.125f,
				0.3125f, 0.125f,
				0.34375f, 0.09375f,
				0.3125f, 0.09375f,

				//bottom
				0.3125f, 0.09375f,
				0.3125f, 0.125f,
				0.34375f, 0.09375f,
				0.34375f, 0.09375f,
				0.3125f, 0.125f,
				0.34375f, 0.125f,

				0.34375f, 0.09375f,
				0.34375f, 0.0625f,
				0.3125f, 0.09375f,
				0.3125f, 0.09375f,
				0.34375f, 0.0625f,
				0.3125f, 0.0625f,

				0.34375f, 0.125f,
				0.375f, 0.09375f,
				0.34375f, 0.09375f,
				0.375f, 0.125f,
				0.375f, 0.09375f,
				0.34375f, 0.125f
		};

	}
	
	private float[] genSandTexture(float indiceTextX, float indiceTextY){
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;

		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		return new float[]{
				//south
				0.34375f+indiceTextX, 0.15625f+indiceTextY,
				0.34375f+indiceTextX, 0.125f+indiceTextY,
				0.3125f+indiceTextX, 0.15625f+indiceTextY,
				0.3125f+indiceTextX, 0.15625f+indiceTextY,
				0.34375f+indiceTextX, 0.125f+indiceTextY,
				0.3125f+indiceTextX, 0.125f+indiceTextY,

				0.34375f+indiceTextX, 0.125f+indiceTextY,
				0.3125f+indiceTextX, 0.125f+indiceTextY,
				0.3125f+indiceTextX, 0.15625f+indiceTextY,
				0.34375f+indiceTextX, 0.125f+indiceTextY,
				0.3125f+indiceTextX, 0.15625f+indiceTextY,
				0.34375f+indiceTextX, 0.15625f+indiceTextY,

				//top
				0.34375f+indiceTextX, 0.15625f+indiceTextY,
				0.34375f+indiceTextX, 0.125f+indiceTextY,
				0.3125f+indiceTextX, 0.15625f+indiceTextY,
				0.3125f+indiceTextX, 0.15625f+indiceTextY,
				0.34375f+indiceTextX, 0.125f+indiceTextY,
				0.3125f+indiceTextX, 0.125f+indiceTextY,

				//bottom
				0.3125f+indiceTextX, 0.125f+indiceTextY,
				0.3125f+indiceTextX, 0.15625f+indiceTextY,
				0.34375f+indiceTextX, 0.125f+indiceTextY,
				0.34375f+indiceTextX, 0.125f+indiceTextY,
				0.3125f+indiceTextX, 0.15625f+indiceTextY,
				0.34375f+indiceTextX, 0.15625f+indiceTextY,

				0.34375f+indiceTextX, 0.15625f+indiceTextY,
				0.34375f+indiceTextX, 0.125f+indiceTextY,
				0.3125f+indiceTextX, 0.15625f+indiceTextY,
				0.3125f+indiceTextX, 0.15625f+indiceTextY,
				0.34375f+indiceTextX, 0.125f+indiceTextY,
				0.3125f+indiceTextX, 0.125f+indiceTextY,

				0.3125f+indiceTextX, 0.15625f+indiceTextY,
				0.34375f+indiceTextX, 0.125f+indiceTextY,
				0.3125f+indiceTextX, 0.125f+indiceTextY,
				0.34375f+indiceTextX, 0.15625f+indiceTextY,
				0.34375f+indiceTextX, 0.125f+indiceTextY,
				0.3125f+indiceTextX, 0.15625f+indiceTextY
		};

	}
	
	private float[] genSteerolitheTexture(float indiceTextX, float indiceTextY){
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;

		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		return new float[]{
				//south
				0.28125f+indiceTextX, 0.28125f+indiceTextY,
				0.28125f+indiceTextX, 0.25f+indiceTextY,
				0.25f+indiceTextX, 0.28125f+indiceTextY,
				0.25f+indiceTextX, 0.28125f+indiceTextY,
				0.28125f+indiceTextX, 0.25f+indiceTextY,
				0.25f+indiceTextX, 0.25f+indiceTextY,

				0.28125f+indiceTextX, 0.25f+indiceTextY,
				0.25f+indiceTextX, 0.25f+indiceTextY,
				0.25f+indiceTextX, 0.28125f+indiceTextY,
				0.28125f+indiceTextX, 0.25f+indiceTextY,
				0.25f+indiceTextX, 0.28125f+indiceTextY,
				0.28125f+indiceTextX, 0.28125f+indiceTextY,

				//top
				0.28125f+indiceTextX, 0.28125f+indiceTextY,
				0.28125f+indiceTextX, 0.25f+indiceTextY,
				0.25f+indiceTextX, 0.28125f+indiceTextY,
				0.25f+indiceTextX, 0.28125f+indiceTextY,
				0.28125f+indiceTextX, 0.25f+indiceTextY,
				0.25f+indiceTextX, 0.25f+indiceTextY,

				//bottom
				0.25f+indiceTextX, 0.25f+indiceTextY,
				0.25f+indiceTextX, 0.28125f+indiceTextY,
				0.28125f+indiceTextX, 0.25f+indiceTextY,
				0.28125f+indiceTextX, 0.25f+indiceTextY,
				0.25f+indiceTextX, 0.28125f+indiceTextY,
				0.28125f+indiceTextX, 0.28125f+indiceTextY,

				0.28125f+indiceTextX, 0.28125f+indiceTextY,
				0.28125f+indiceTextX, 0.25f+indiceTextY,
				0.25f+indiceTextX, 0.28125f+indiceTextY,
				0.25f+indiceTextX, 0.28125f+indiceTextY,
				0.28125f+indiceTextX, 0.25f+indiceTextY,
				0.25f+indiceTextX, 0.25f+indiceTextY,

				0.25f+indiceTextX, 0.28125f+indiceTextY,
				0.28125f+indiceTextX, 0.25f+indiceTextY,
				0.25f+indiceTextX, 0.25f+indiceTextY,
				0.28125f+indiceTextX, 0.28125f+indiceTextY,
				0.28125f+indiceTextX, 0.25f+indiceTextY,
				0.25f+indiceTextX, 0.28125f+indiceTextY
		};

	}
	
	private float[] genNaildeSteelTexture(float indiceTextX, float indiceTextY){	

		indiceTextX = (int)(indiceTextX * ((1 - 0) + 1))*0.03125f;
		indiceTextY = (int)(indiceTextY * ((1 - 0) + 1))*0.03125f;

		return new float[]{
				//south
				0.34375f+indiceTextX, 0.21875f+indiceTextY,
				0.34375f+indiceTextX, 0.1875f+indiceTextY,
				0.3125f+indiceTextX, 0.21875f+indiceTextY,
				0.3125f+indiceTextX, 0.21875f+indiceTextY,
				0.34375f+indiceTextX, 0.1875f+indiceTextY,
				0.3125f+indiceTextX, 0.1875f+indiceTextY,

				0.34375f+indiceTextX, 0.1875f+indiceTextY,
				0.3125f+indiceTextX, 0.1875f+indiceTextY,
				0.3125f+indiceTextX, 0.21875f+indiceTextY,
				0.34375f+indiceTextX, 0.1875f+indiceTextY,
				0.3125f+indiceTextX, 0.21875f+indiceTextY,
				0.34375f+indiceTextX, 0.21875f+indiceTextY,

				//top
				0.34375f+indiceTextX, 0.21875f+indiceTextY,
				0.34375f+indiceTextX, 0.1875f+indiceTextY,
				0.3125f+indiceTextX, 0.21875f+indiceTextY,
				0.3125f+indiceTextX, 0.21875f+indiceTextY,
				0.34375f+indiceTextX, 0.1875f+indiceTextY,
				0.3125f+indiceTextX, 0.1875f+indiceTextY,

				//bottom
				0.3125f+indiceTextX, 0.1875f+indiceTextY,
				0.3125f+indiceTextX, 0.21875f+indiceTextY,
				0.34375f+indiceTextX, 0.1875f+indiceTextY,
				0.34375f+indiceTextX, 0.1875f+indiceTextY,
				0.3125f+indiceTextX, 0.21875f+indiceTextY,
				0.34375f+indiceTextX, 0.21875f+indiceTextY,

				0.34375f+indiceTextX, 0.21875f+indiceTextY,
				0.34375f+indiceTextX, 0.1875f+indiceTextY,
				0.3125f+indiceTextX, 0.21875f+indiceTextY,
				0.3125f+indiceTextX, 0.21875f+indiceTextY,
				0.34375f+indiceTextX, 0.1875f+indiceTextY,
				0.3125f+indiceTextX, 0.1875f+indiceTextY,

				0.3125f+indiceTextX, 0.21875f+indiceTextY,
				0.34375f+indiceTextX, 0.1875f+indiceTextY,
				0.3125f+indiceTextX, 0.1875f+indiceTextY,
				0.34375f+indiceTextX, 0.21875f+indiceTextY,
				0.34375f+indiceTextX, 0.1875f+indiceTextY,
				0.3125f+indiceTextX, 0.21875f+indiceTextY

		};

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

	public float[] genText(int type, float x, float y){
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
		case 17:
			return genNailedPlankTexture(x,y);
		case 18:
			return genClayTexture(x,y);
		case 19:
			return genBricksTexture(x,y);
		case 20:
			return genCementTexture(x,y);
		case 21:
			return genConcreteTexture(x,y);
		case 22:
			return genStoneBricksTexture(x,y);
		case 23:
			return genSmallStoneBricksTexture(x,y);
		case 24:
			return genWavySteelPanelTexture();
		case 25:
			return genSteelPanelTexture();
		case 26:
			return genElectricPanelTexture();
		case 27:
			return genChestTexture();
		case 28:
			return genSteelChestTexture();
		case 29:
			return genSandTexture(x,y);
		case 30:
			return genSteerolitheTexture(x,y);
		case 31:
			return genNaildeSteelTexture(x, y);
		default:
			return genGrassTexture(x, y);
		}
	}

}
