package modeles;
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

		textureData = BufferUtils.createFloatBuffer(36 * 2);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	}

	private void genGrassTexture(){
		textureData.put(new float[]{
				//south
				0.03125f, 0.03125f,
				0.03125f, 0,
				0, 0.03125f,
				0, 0.03125f,
				0.03125f, 0,
				0, 0,

				0.03125f, 0,
				0, 0,
				0, 0.03125f,
				0.03125f, 0,
				0, 0.03125f,
				0.03125f, 0.03125f,

				//top
				0.03125f, 0.0625f,
				0.03125f, 0.03125f,
				0, 0.0625f,
				0, 0.0625f,
				0.03125f, 0.03125f,
				0, 0.03125f,

				//bottom
				0.03125f, 0,
				0.03125f, 0.03125f,
				0.0625f, 0,
				0.0625f, 0,
				0.03125f, 0.03125f,
				0.0625f, 0.03125f,

				0.03125f, 0.03125f,
				0.03125f, 0,
				0, 0.03125f,
				0, 0.03125f,
				0.03125f, 0,
				0, 0,

				0, 0.03125f,
				0.03125f, 0,
				0, 0,
				0.03125f,0.03125f,
				0.03125f, 0,
				0, 0.03125f

		});
		textureData.flip();
	}

	private void genDirtTexture(){
		textureData.put(new float[]{
				//south
				0.0625f, 0.03125f,
				0.0625f, 0,
				0.03125f, 0.03125f,
				0.03125f, 0.03125f,
				0.0625f, 0,
				0.03125f, 0,

				0.0625f, 0,
				0.03125f, 0,
				0.03125f, 0.03125f,
				0.0625f, 0,
				0.03125f, 0.03125f,
				0.0625f, 0.03125f,

				//top
				0.0625f, 0.03125f,
				0.0625f, 0,
				0.03125f, 0.03125f,
				0.03125f, 0.03125f,
				0.0625f, 0,
				0.03125f, 0,

				//bottom
				0.03125f, 0,
				0.03125f, 0.03125f,
				0.0625f, 0,
				0.0625f, 0,
				0.03125f, 0.03125f,
				0.0625f, 0.03125f,

				0.0625f, 0.03125f,
				0.0625f, 0,
				0.03125f, 0.03125f,
				0.03125f, 0.03125f,
				0.0625f, 0,
				0.03125f, 0,

				0.03125f, 0.03125f,
				0.0625f, 0,
				0.03125f, 0,
				0.0625f,0.03125f,
				0.0625f, 0,
				0.03125f, 0.03125f

		});
		textureData.flip();
	}

	private void genStoneTexture(){
		textureData.put(new float[]{
				//south
				0.0625f, 0.0625f,
				0.0625f, 0.03125f,
				0.03125f, 0.0625f,
				0.03125f, 0.0625f,
				0.0625f, 0.03125f,
				0.03125f, 0.03125f,

				0.0625f, 0.03125f,
				0.03125f, 0.03125f,
				0.03125f, 0.0625f,
				0.0625f, 0.03125f,
				0.03125f, 0.0625f,
				0.0625f, 0.0625f,

				//top
				0.0625f, 0.0625f,
				0.0625f, 0.03125f,
				0.03125f, 0.0625f,
				0.03125f, 0.0625f,
				0.0625f, 0.03125f,
				0.03125f, 0.03125f,

				//bottom
				0.03125f, 0.03125f,
				0.03125f, 0.0625f,
				0.0625f, 0.03125f,
				0.0625f, 0.03125f,
				0.03125f, 0.0625f,
				0.0625f, 0.0625f,

				0.0625f, 0.0625f,
				0.0625f, 0.03125f,
				0.03125f, 0.0625f,
				0.03125f, 0.0625f,
				0.0625f, 0.03125f,
				0.03125f, 0.03125f,

				0.03125f, 0.0625f,
				0.0625f, 0.03125f,
				0.03125f, 0.03125f,
				0.0625f, 0.0625f,
				0.0625f, 0.03125f,
				0.03125f, 0.0625f,
		});
		textureData.flip();
	}
	
	private void genCoalTexture(){
		textureData.put(new float[]{
				//south
				0.09375f, 0.03125f,
				0.09375f, 0.0f,
				0.0625f, 0.03125f,
				0.0625f, 0.03125f,
				0.09375f, 0.0f,
				0.0625f, 0.0f,

				0.09375f, 0.0f,
				0.0625f, 0.0f,
				0.0625f, 0.03125f,
				0.09375f, 0.0f,
				0.0625f, 0.03125f,
				0.09375f, 0.03125f,

				//top
				0.09375f, 0.03125f,
				0.09375f, 0.0f,
				0.0625f, 0.03125f,
				0.0625f, 0.03125f,
				0.09375f, 0.0f,
				0.0625f, 0.0f,

				//bottom
				0.0625f, 0.0f,
				0.0625f, 0.03125f,
				0.09375f, 0.0f,
				0.09375f, 0.0f,
				0.0625f, 0.03125f,
				0.09375f, 0.03125f,

				0.09375f, 0.03125f,
				0.09375f, 0.0f,
				0.0625f, 0.03125f,
				0.0625f, 0.03125f,
				0.09375f, 0.0f,
				0.0625f, 0.0f,

				0.0625f, 0.03125f,
				0.09375f, 0.0f,
				0.0625f, 0.0f,
				0.09375f, 0.03125f,
				0.09375f, 0.0f,
				0.0625f, 0.03125f

		});
		textureData.flip();
	}

	private void genGoldTexture(){
		textureData.put(new float[]{
				//south
				0.125f, 0.03125f,
				0.125f, 0.0f,
				0.09375f, 0.03125f,
				0.09375f, 0.03125f,
				0.125f, 0.0f,
				0.09375f, 0.0f,

				0.125f, 0.0f,
				0.09375f, 0.0f,
				0.09375f, 0.03125f,
				0.125f, 0.0f,
				0.09375f, 0.03125f,
				0.125f, 0.03125f,

				//top
				0.125f, 0.03125f,
				0.125f, 0.0f,
				0.09375f, 0.03125f,
				0.09375f, 0.03125f,
				0.125f, 0.0f,
				0.09375f, 0.0f,

				//bottom
				0.09375f, 0.0f,
				0.09375f, 0.03125f,
				0.125f, 0.0f,
				0.125f, 0.0f,
				0.09375f, 0.03125f,
				0.125f, 0.03125f,

				0.125f, 0.03125f,
				0.125f, 0.0f,
				0.09375f, 0.03125f,
				0.09375f, 0.03125f,
				0.125f, 0.0f,
				0.09375f, 0.0f,

				0.09375f, 0.03125f,
				0.125f, 0.0f,
				0.09375f, 0.0f,
				0.125f, 0.03125f,
				0.125f, 0.0f,
				0.09375f, 0.03125f
		});
		textureData.flip();
	}

	private void genSilverTexture(){
		textureData.put(new float[]{
				//south
				0.03125f, 0.09375f,
				0.03125f, 0.0625f,
				0.0f, 0.09375f,
				0.0f, 0.09375f,
				0.03125f, 0.0625f,
				0.0f, 0.0625f,

				0.03125f, 0.0625f,
				0.0f, 0.0625f,
				0.0f, 0.09375f,
				0.03125f, 0.0625f,
				0.0f, 0.09375f,
				0.03125f, 0.09375f,

				//top
				0.03125f, 0.09375f,
				0.03125f, 0.0625f,
				0.0f, 0.09375f,
				0.0f, 0.09375f,
				0.03125f, 0.0625f,
				0.0f, 0.0625f,

				//bottom
				0.0f, 0.0625f,
				0.0f, 0.09375f,
				0.03125f, 0.0625f,
				0.03125f, 0.0625f,
				0.0f, 0.09375f,
				0.03125f, 0.09375f,

				0.03125f, 0.09375f,
				0.03125f, 0.0625f,
				0.0f, 0.09375f,
				0.0f, 0.09375f,
				0.03125f, 0.0625f,
				0.0f, 0.0625f,

				0.0f, 0.09375f,
				0.03125f, 0.0625f,
				0.0f, 0.0625f,
				0.03125f, 0.09375f,
				0.03125f, 0.0625f,
				0.0f, 0.09375f
		});
		textureData.flip();
	}

	private void genIronTexture(){
		textureData.put(new float[]{
				//south
				0.09375f, 0.0625f,
				0.09375f, 0.03125f,
				0.0625f, 0.0625f,
				0.0625f, 0.0625f,
				0.09375f, 0.03125f,
				0.0625f, 0.03125f,

				0.09375f, 0.03125f,
				0.0625f, 0.03125f,
				0.0625f, 0.0625f,
				0.09375f, 0.03125f,
				0.0625f, 0.0625f,
				0.09375f, 0.0625f,

				//top
				0.09375f, 0.0625f,
				0.09375f, 0.03125f,
				0.0625f, 0.0625f,
				0.0625f, 0.0625f,
				0.09375f, 0.03125f,
				0.0625f, 0.03125f,

				//bottom
				0.0625f, 0.03125f,
				0.0625f, 0.0625f,
				0.09375f, 0.03125f,
				0.09375f, 0.03125f,
				0.0625f, 0.0625f,
				0.09375f, 0.0625f,

				0.09375f, 0.0625f,
				0.09375f, 0.03125f,
				0.0625f, 0.0625f,
				0.0625f, 0.0625f,
				0.09375f, 0.03125f,
				0.0625f, 0.03125f,

				0.0625f, 0.0625f,
				0.09375f, 0.03125f,
				0.0625f, 0.03125f,
				0.09375f, 0.0625f,
				0.09375f, 0.03125f,
				0.0625f, 0.0625f
		});
		textureData.flip();
	}

	private void genCopperTexture(){
		textureData.put(new float[]{
				//south
				0.0625f, 0.09375f,
				0.0625f, 0.0625f,
				0.03125f, 0.09375f,
				0.03125f, 0.09375f,
				0.0625f, 0.0625f,
				0.03125f, 0.0625f,

				0.0625f, 0.0625f,
				0.03125f, 0.0625f,
				0.03125f, 0.09375f,
				0.0625f, 0.0625f,
				0.03125f, 0.09375f,
				0.0625f, 0.09375f,

				//top
				0.0625f, 0.09375f,
				0.0625f, 0.0625f,
				0.03125f, 0.09375f,
				0.03125f, 0.09375f,
				0.0625f, 0.0625f,
				0.03125f, 0.0625f,

				//bottom
				0.03125f, 0.0625f,
				0.03125f, 0.09375f,
				0.0625f, 0.0625f,
				0.0625f, 0.0625f,
				0.03125f, 0.09375f,
				0.0625f, 0.09375f,

				0.0625f, 0.09375f,
				0.0625f, 0.0625f,
				0.03125f, 0.09375f,
				0.03125f, 0.09375f,
				0.0625f, 0.0625f,
				0.03125f, 0.0625f,

				0.03125f, 0.09375f,
				0.0625f, 0.0625f,
				0.03125f, 0.0625f,
				0.0625f, 0.09375f,
				0.0625f, 0.0625f,
				0.03125f, 0.09375f
		});
		textureData.flip();
	}

	private void genTitaneTexture(){
		textureData.put(new float[]{
				//south
				0.09375f, 0.09375f,
				0.09375f, 0.0625f,
				0.0625f, 0.09375f,
				0.0625f, 0.09375f,
				0.09375f, 0.0625f,
				0.0625f, 0.0625f,

				0.09375f, 0.0625f,
				0.0625f, 0.0625f,
				0.0625f, 0.09375f,
				0.09375f, 0.0625f,
				0.0625f, 0.09375f,
				0.09375f, 0.09375f,

				//top
				0.09375f, 0.09375f,
				0.09375f, 0.0625f,
				0.0625f, 0.09375f,
				0.0625f, 0.09375f,
				0.09375f, 0.0625f,
				0.0625f, 0.0625f,

				//bottom
				0.0625f, 0.0625f,
				0.0625f, 0.09375f,
				0.09375f, 0.0625f,
				0.09375f, 0.0625f,
				0.0625f, 0.09375f,
				0.09375f, 0.09375f,

				0.09375f, 0.09375f,
				0.09375f, 0.0625f,
				0.0625f, 0.09375f,
				0.0625f, 0.09375f,
				0.09375f, 0.0625f,
				0.0625f, 0.0625f,

				0.0625f, 0.09375f,
				0.09375f, 0.0625f,
				0.0625f, 0.0625f,
				0.09375f, 0.09375f,
				0.09375f, 0.0625f,
				0.0625f, 0.09375f
		});
		textureData.flip();
	}

	private void genUraniumTexture(){
		textureData.put(new float[]{
				//south
				0.125f, 0.0625f,
				0.125f, 0.03125f,
				0.09375f, 0.0625f,
				0.09375f, 0.0625f,
				0.125f, 0.03125f,
				0.09375f, 0.03125f,

				0.125f, 0.03125f,
				0.09375f, 0.03125f,
				0.09375f, 0.0625f,
				0.125f, 0.03125f,
				0.09375f, 0.0625f,
				0.125f, 0.0625f,

				//top
				0.125f, 0.0625f,
				0.125f, 0.03125f,
				0.09375f, 0.0625f,
				0.09375f, 0.0625f,
				0.125f, 0.03125f,
				0.09375f, 0.03125f,

				//bottom
				0.09375f, 0.03125f,
				0.09375f, 0.0625f,
				0.125f, 0.03125f,
				0.125f, 0.03125f,
				0.09375f, 0.0625f,
				0.125f, 0.0625f,

				0.125f, 0.0625f,
				0.125f, 0.03125f,
				0.09375f, 0.0625f,
				0.09375f, 0.0625f,
				0.125f, 0.03125f,
				0.09375f, 0.03125f,

				0.09375f, 0.0625f,
				0.125f, 0.03125f,
				0.09375f, 0.03125f,
				0.125f, 0.0625f,
				0.125f, 0.03125f,
				0.09375f, 0.0625f,
		});
		textureData.flip();
	}

	private void genWoodTexture(){
		textureData.put(new float[]{
				//south
				0.03125f, 0.125f,
				0.03125f, 0.09375f,
				0.0f, 0.125f,
				0.0f, 0.125f,
				0.03125f, 0.09375f,
				0.0f, 0.09375f,

				0.03125f, 0.09375f,
				0.0f, 0.09375f,
				0.0f, 0.125f,
				0.03125f, 0.09375f,
				0.0f, 0.125f,
				0.03125f, 0.125f,

				//top
				0.0625f, 0.125f,
				0.0625f, 0.09375f,
				0.03125f, 0.125f,
				0.03125f, 0.125f,
				0.0625f, 0.09375f,
				0.03125f, 0.09375f,

				//bottom
				0.03125f, 0.09375f,
				0.03125f, 0.125f,
				0.0625f, 0.09375f,
				0.0625f, 0.09375f,
				0.03125f, 0.125f,
				0.0625f, 0.125f,

				0.03125f, 0.125f,
				0.03125f, 0.09375f,
				0.0f, 0.125f,
				0.0f, 0.125f,
				0.03125f, 0.09375f,
				0.0f, 0.09375f,

				0.0f, 0.125f,
				0.03125f, 0.09375f,
				0.0f, 0.09375f,
				0.03125f, 0.125f,
				0.03125f, 0.09375f,
				0.0f, 0.125f
		});
		textureData.flip();
	}

	private void genLeafTexture(){
		textureData.put(new float[]{
				//south
				0.09375f, 0.125f,
				0.09375f, 0.09375f,
				0.0625f, 0.125f,
				0.0625f, 0.125f,
				0.09375f, 0.09375f,
				0.0625f, 0.09375f,

				0.09375f, 0.09375f,
				0.0625f, 0.09375f,
				0.0625f, 0.125f,
				0.09375f, 0.09375f,
				0.0625f, 0.125f,
				0.09375f, 0.125f,

				//top
				0.09375f, 0.125f,
				0.09375f, 0.09375f,
				0.0625f, 0.125f,
				0.0625f, 0.125f,
				0.09375f, 0.09375f,
				0.0625f, 0.09375f,

				//bottom
				0.0625f, 0.09375f,
				0.0625f, 0.125f,
				0.09375f, 0.09375f,
				0.09375f, 0.09375f,
				0.0625f, 0.125f,
				0.09375f, 0.125f,

				0.09375f, 0.125f,
				0.09375f, 0.09375f,
				0.0625f, 0.125f,
				0.0625f, 0.125f,
				0.09375f, 0.09375f,
				0.0625f, 0.09375f,

				0.0625f, 0.125f,
				0.09375f, 0.09375f,
				0.0625f, 0.09375f,
				0.09375f, 0.125f,
				0.09375f, 0.09375f,
				0.0625f, 0.125f
		});
		textureData.flip();
	}

	private void genWaterTexture(){
		textureData.put(new float[]{
				//south
				0.15625f, 0.03125f,
				0.15625f, 0.0f,
				0.125f, 0.03125f,
				0.125f, 0.03125f,
				0.15625f, 0.0f,
				0.125f, 0.0f,

				0.15625f, 0.0f,
				0.125f, 0.0f,
				0.125f, 0.03125f,
				0.15625f, 0.0f,
				0.125f, 0.03125f,
				0.15625f, 0.03125f,

				//top
				0.15625f, 0.03125f,
				0.15625f, 0.0f,
				0.125f, 0.03125f,
				0.125f, 0.03125f,
				0.15625f, 0.0f,
				0.125f, 0.0f,

				//bottom
				0.125f, 0.0f,
				0.125f, 0.03125f,
				0.15625f, 0.0f,
				0.15625f, 0.0f,
				0.125f, 0.03125f,
				0.15625f, 0.03125f,

				0.15625f, 0.03125f,
				0.15625f, 0.0f,
				0.125f, 0.03125f,
				0.125f, 0.03125f,
				0.15625f, 0.0f,
				0.125f, 0.0f,

				0.125f, 0.03125f,
				0.15625f, 0.0f,
				0.125f, 0.0f,
				0.15625f, 0.03125f,
				0.15625f, 0.0f,
				0.125f, 0.03125f
		});
		textureData.flip();
	}

	private void genLavaTexture(){
		textureData.put(new float[]{
				//south
				0.15625f, 0.0625f,
				0.15625f, 0.03125f,
				0.125f, 0.0625f,
				0.125f, 0.0625f,
				0.15625f, 0.03125f,
				0.125f, 0.03125f,

				0.15625f, 0.03125f,
				0.125f, 0.03125f,
				0.125f, 0.0625f,
				0.15625f, 0.03125f,
				0.125f, 0.0625f,
				0.15625f, 0.0625f,

				//top
				0.15625f, 0.0625f,
				0.15625f, 0.03125f,
				0.125f, 0.0625f,
				0.125f, 0.0625f,
				0.15625f, 0.03125f,
				0.125f, 0.03125f,

				//bottom
				0.125f, 0.03125f,
				0.125f, 0.0625f,
				0.15625f, 0.03125f,
				0.15625f, 0.03125f,
				0.125f, 0.0625f,
				0.15625f, 0.0625f,

				0.15625f, 0.0625f,
				0.15625f, 0.03125f,
				0.125f, 0.0625f,
				0.125f, 0.0625f,
				0.15625f, 0.03125f,
				0.125f, 0.03125f,

				0.125f, 0.0625f,
				0.15625f, 0.03125f,
				0.125f, 0.03125f,
				0.15625f, 0.0625f,
				0.15625f, 0.03125f,
				0.125f, 0.0625f
		});
		textureData.flip();
	}
	
	private void genPlankTexture(){
		textureData.put(new float[]{
				//south
				0.125f, 0.09375f,
				0.125f, 0.0625f,
				0.09375f, 0.09375f,
				0.09375f, 0.09375f,
				0.125f, 0.0625f,
				0.09375f, 0.0625f,

				0.125f, 0.0625f,
				0.09375f, 0.0625f,
				0.09375f, 0.09375f,
				0.125f, 0.0625f,
				0.09375f, 0.09375f,
				0.125f, 0.09375f,

				//top
				0.125f, 0.09375f,
				0.125f, 0.0625f,
				0.09375f, 0.09375f,
				0.09375f, 0.09375f,
				0.125f, 0.0625f,
				0.09375f, 0.0625f,

				//bottom
				0.09375f, 0.0625f,
				0.09375f, 0.09375f,
				0.125f, 0.0625f,
				0.125f, 0.0625f,
				0.09375f, 0.09375f,
				0.125f, 0.09375f,

				0.125f, 0.09375f,
				0.125f, 0.0625f,
				0.09375f, 0.09375f,
				0.09375f, 0.09375f,
				0.125f, 0.0625f,
				0.09375f, 0.0625f,

				0.09375f, 0.09375f,
				0.125f, 0.0625f,
				0.09375f, 0.0625f,
				0.125f, 0.09375f,
				0.125f, 0.0625f,
				0.09375f, 0.09375f
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

	public void genText(int type){
		textureData.clear();
		switch (type) {
		case 1:
			genGrassTexture();
			break;
		case 2:
			genDirtTexture();
			break;
		case 3:
			genStoneTexture();
			break;
		case 4:
			genCoalTexture();
			break;
		case 5:
			genGoldTexture();
			break;
		case 6:
			genSilverTexture();
			break;
		case 7:
			genIronTexture();
			break;
		case 8:
			genCopperTexture();
			break;
		case 9:
			genTitaneTexture();
			break;
		case 10:
			genUraniumTexture();
			break;
		case 11:
			genWoodTexture();
			break;
		case 12:
			genLeafTexture();
			break;
		case 13:
			genWaterTexture();
			break;
		case 14:
			genLavaTexture();
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
