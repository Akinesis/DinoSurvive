package modeles;

import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;
import modeles.textures.*;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class TextureManager {

	private Texture texture;
	private TexturesGenerator texGenerator;

	public TextureManager() {
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/text.png"));
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
			texGenerator = new GrassTexture();
			return texGenerator.genTexture(x, y);
		case 2:
			texGenerator = new DirtTexture();
			return texGenerator.genTexture(x, y);
		case 3:
			texGenerator = new StoneTexture();
			return texGenerator.genTexture(x, y);
		case 4:
			texGenerator = new CoalTexture();
			return texGenerator.genTexture(x, y);
		case 5:
			texGenerator = new GoldTexture();
			return texGenerator.genTexture(x, y);
		case 6:
			texGenerator = new SilverTexture();
			return texGenerator.genTexture(x, y);
		case 7:
			texGenerator = new IronTexture();
			return texGenerator.genTexture(x, y);
		case 8:
			texGenerator = new CopperTexture();
			return texGenerator.genTexture(x, y);
		case 9:
			texGenerator = new TitaniumTexture();
			return texGenerator.genTexture(x, y);
		case 10:
			texGenerator = new UraniumTexture();
			return texGenerator.genTexture(x, y);
		case 11:
			texGenerator = new WoodTexture();
			return texGenerator.genTexture(x, y);
		case 12:
			texGenerator = new LeafTexture();
			return texGenerator.genTexture(x, y);
		case 13:
			texGenerator = new WaterTexture();
			return texGenerator.genTexture(x, y);
		case 14:
			texGenerator = new LavaTexture();
			return texGenerator.genTexture(x, y);
		case 15:
			texGenerator = new PlankTexture();
			return texGenerator.genTexture(x, y);
		case 16:
			texGenerator = new WorkbenchTexture();
			return texGenerator.genTexture(x, y);
		case 17:
			texGenerator = new NailedPlankTexture();
			return texGenerator.genTexture(x, y);
		case 18:
			texGenerator = new ClayTexture();
			return texGenerator.genTexture(x, y);
		case 19:
			texGenerator = new BrickTexture();
			return texGenerator.genTexture(x, y);
		case 20:
			texGenerator = new CementTexture();
			return texGenerator.genTexture(x, y);
		case 21:
			texGenerator = new ConcreteTexture();
			return texGenerator.genTexture(x, y);
		case 22:
			texGenerator = new StoneBrickTexture();
			return texGenerator.genTexture(x, y);
		case 23:
			texGenerator = new SmallStoneBrickTexture();
			return texGenerator.genTexture(x, y);
		case 24:
			texGenerator = new WavySteelPanelTexture();
			return texGenerator.genTexture(x, y);
		case 25:
			texGenerator = new SteelPanelTexture();
			return texGenerator.genTexture(x, y);
		case 26:
			texGenerator = new ElectricPanelTexture();
			return texGenerator.genTexture(x, y);
		case 27:
			texGenerator = new ChestTexture();
			return texGenerator.genTexture(x, y);
		case 28:
			texGenerator = new SteelChestTexture();
			return texGenerator.genTexture(x, y);
		case 29:
			texGenerator = new SandTexture();
			return texGenerator.genTexture(x, y);
		case 30:
			texGenerator = new DaraniumTexture();
			return texGenerator.genTexture(x, y);
		case 31:
			texGenerator = new NailedSteelTexture();
			return texGenerator.genTexture(x, y);
		case 32:
			texGenerator = new RubyTexture();
			return texGenerator.genTexture(x, y);
		case 33:
			texGenerator = new DiamondTexture();
			return texGenerator.genTexture(x, y);
		case 34:
			texGenerator = new EmeraldTexture();
			return texGenerator.genTexture(x, y);
		default:
			texGenerator = new DirtTexture();
			return texGenerator.genTexture(x, y);
		}
	}

}
