package modeles.entities2D;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glDisableClientState;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glVertexPointer;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;

import java.nio.FloatBuffer;

import modeles.TextureManager;
import modeles.entities.AbstractEntity3D;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import controleur.Controleur;

public class Menu extends AbstractEntity3D{
	//private static int verticiesNum = 36;
	private int vertexSize = 2;
	private int vboVertexHandle;
	private FloatBuffer vertexDataFond;
	private FloatBuffer vertexDataBoutons;
	private boolean estAfficher;
	private TextureManager textureManagerFond;
	private TextureManager textureManagerBoutons;
	private float[] boutonsLimites;
	private float[] boutonsTextureCoordonnes;
	private float[] boutonsEtats;
	private Controleur controleurClone;
	private boolean displayIsClose;
	
	public Menu(Controleur controleur){
		this.controleurClone = controleur;
		this.estAfficher = true;
		this.vertexDataFond = BufferUtils.createFloatBuffer(6 * this.vertexSize);
		this.vertexDataBoutons = BufferUtils.createFloatBuffer(36 * this.vertexSize);
		this.vboVertexHandle = glGenBuffers();
		this.textureManagerFond = new TextureManager("res/Menu.png");
		this.textureManagerBoutons = new TextureManager("res/Boutons_v2.png");
		this.boutonsLimites = this.generationBoutonsLimites();
		this.boutonsTextureCoordonnes = this.generationBoutonsTextureCoordonnes();
		this.boutonsEtats = this.generationBoutonsEtats();
		this.displayIsClose = false;
	}
	
	private float[] generationBoutonsEtats() {
		return new float[]{2, 1, 1, 1, 1, 1};
	}

	public void boutonsEtatsReset(int i) {
		int j = 0;
		while(j < 6){
			if(j != i){
				this.boutonsEtats[j] = 1f;
			}
			j++;
		}
	}
	
	public void boutonsEtatsNormale(int i) {
		this.boutonsEtats[i] = 1f;
	}
	public void boutonsEtatsHighlight(int i) {
		this.boutonsEtats[i] = 2f;
	}
	public void boutonsEtatsDark(int i) {
		this.boutonsEtats[i] = 3f;
	}
	
	
	private float[] generationBoutonsTextureCoordonnes() {
		return new float[]{0f, 0.15625f, 0.3125f, 0.46875f, 0.625f, 0.78125f, 0.9375f};
	}

	public void MenuBoutonsFonctionaliter(int position) {
		if(position == 0){
			this.inverserEstAfficher();
		}
		if(position == 1){
			System.out.println("new game");
		}
		if(position == 2){
			System.out.println("charge");
		}
		if(position == 3){
			System.out.println("option");
		}
		if(position == 4){
			//this.controleurClone.getDisplay().end();
			this.displayIsClose = true;
		}
		if(position == 5){
			System.out.println("back");
		}
	}

	public void generationMenuFond() {
		this.vertexDataFond.put(new float[]{   
			Display.getWidth(), Display.getHeight(),	Display.getWidth(), 0,		0, 0,
			0, 0,										0, Display.getHeight(),		Display.getWidth(), Display.getHeight(),
		});
		this.vertexDataFond.flip();
	}
	
	private float[] generationBoutonsLimites() {
		return new float[]{ 0.9875f*Display.getWidth(), 0.6375f*Display.getWidth(), 
							0.10f*Display.getHeight(), 0.20f*Display.getHeight(),
							0.22f*Display.getHeight(), 0.32f*Display.getHeight(),
							0.34f*Display.getHeight(), 0.44f*Display.getHeight(),
							0.46f*Display.getHeight(), 0.56f*Display.getHeight(),
							0.58f*Display.getHeight(), 0.68f*Display.getHeight(),
		};
	}
	
	public void generationMenuBoutons() {
		this.vertexDataBoutons.put(new float[]{   
			0.9875f*Display.getWidth(), 0.2f*Display.getHeight(),		0.6375f*Display.getWidth(), 0.2f*Display.getHeight(),		0.6375f*Display.getWidth(), 0.1f*Display.getHeight(),
			0.6375f*Display.getWidth(), 0.1f*Display.getHeight(),		0.9875f*Display.getWidth(), 0.1f*Display.getHeight(),		0.9875f*Display.getWidth(), 0.2f*Display.getHeight(),
			
			0.9875f*Display.getWidth(), 0.32f*Display.getHeight(),		0.6375f*Display.getWidth(), 0.32f*Display.getHeight(),		0.6375f*Display.getWidth(), 0.22f*Display.getHeight(),
			0.6375f*Display.getWidth(), 0.22f*Display.getHeight(),		0.9875f*Display.getWidth(), 0.22f*Display.getHeight(),		0.9875f*Display.getWidth(), 0.32f*Display.getHeight(),
			
			0.9875f*Display.getWidth(), 0.44f*Display.getHeight(),		0.6375f*Display.getWidth(), 0.44f*Display.getHeight(),		0.6375f*Display.getWidth(), 0.34f*Display.getHeight(),
			0.6375f*Display.getWidth(), 0.34f*Display.getHeight(),		0.9875f*Display.getWidth(), 0.34f*Display.getHeight(),		0.9875f*Display.getWidth(), 0.44f*Display.getHeight(),
			
			0.9875f*Display.getWidth(), 0.56f*Display.getHeight(),		0.6375f*Display.getWidth(), 0.56f*Display.getHeight(),		0.6375f*Display.getWidth(), 0.46f*Display.getHeight(),
			0.6375f*Display.getWidth(), 0.46f*Display.getHeight(),		0.9875f*Display.getWidth(), 0.46f*Display.getHeight(),		0.9875f*Display.getWidth(), 0.56f*Display.getHeight(),
			
			0.9875f*Display.getWidth(), 0.68f*Display.getHeight(),		0.6375f*Display.getWidth(), 0.68f*Display.getHeight(),		0.6375f*Display.getWidth(), 0.58f*Display.getHeight(),
			0.6375f*Display.getWidth(), 0.58f*Display.getHeight(),		0.9875f*Display.getWidth(), 0.58f*Display.getHeight(),		0.9875f*Display.getWidth(), 0.68f*Display.getHeight(),
		});
		this.vertexDataBoutons.flip();
	}


	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw() {
		float i;
		float j;
		float modulo;
		
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.textureManagerFond.getID());
		GL11.glBegin(GL_TRIANGLES);

		GL11.glTexCoord2d(1,1); GL11.glVertex2d(Display.getWidth(), Display.getHeight());
		GL11.glTexCoord2d(1,0); GL11.glVertex2d(Display.getWidth(), 0);
		GL11.glTexCoord2d(0,0); GL11.glVertex2d(0, 0);
		GL11.glTexCoord2d(0,0); GL11.glVertex2d(0, 0);
		GL11.glTexCoord2d(0,1); GL11.glVertex2d(0, Display.getHeight());
		GL11.glTexCoord2d(1,1); GL11.glVertex2d(Display.getWidth(), Display.getHeight());
		
		GL11.glEnd();
		
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.textureManagerBoutons.getID());
		GL11.glBegin(GL_TRIANGLES);
		
		
		//1	
		modulo = 0.33203125f;
		i = this.boutonsEtats[0] * modulo; //de base 1
		j = (this.boutonsEtats[0] - 1) * modulo; //de base 0
		
		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[1]); GL11.glVertex2d(0.9875f*Display.getWidth(), 0.2f*Display.getHeight());
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[1]); GL11.glVertex2d(0.6375f*Display.getWidth(), 0.2f*Display.getHeight());
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[0]); GL11.glVertex2d(0.6375f*Display.getWidth(), 0.1f*Display.getHeight());
		
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[0]); GL11.glVertex2d(0.6375f*Display.getWidth(), 0.1f*Display.getHeight());
		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[0]); GL11.glVertex2d(0.9875f*Display.getWidth(), 0.1f*Display.getHeight());
		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[1]); GL11.glVertex2d(0.9875f*Display.getWidth(), 0.2f*Display.getHeight());
		//2
		i = this.boutonsEtats[1] * modulo;
		j = (this.boutonsEtats[1] - 1) * modulo;
		
		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[2]); GL11.glVertex2d(0.9875f*Display.getWidth(), 0.32f*Display.getHeight());
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[2]); GL11.glVertex2d(0.6375f*Display.getWidth(), 0.32f*Display.getHeight());
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[1]); GL11.glVertex2d(0.6375f*Display.getWidth(), 0.22f*Display.getHeight());
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[1]); GL11.glVertex2d(0.6375f*Display.getWidth(), 0.22f*Display.getHeight());
		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[1]); GL11.glVertex2d(0.9875f*Display.getWidth(), 0.22f*Display.getHeight());
		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[2]); GL11.glVertex2d(0.9875f*Display.getWidth(), 0.32f*Display.getHeight());
		//3
		i = this.boutonsEtats[2] * modulo;
		j = (this.boutonsEtats[2] - 1) * modulo;
		
		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[3]); GL11.glVertex2d(0.9875f*Display.getWidth(), 0.44f*Display.getHeight());
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[3]); GL11.glVertex2d(0.6375f*Display.getWidth(), 0.44f*Display.getHeight());
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[2]); GL11.glVertex2d(0.6375f*Display.getWidth(), 0.34f*Display.getHeight());
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[2]); GL11.glVertex2d(0.6375f*Display.getWidth(), 0.34f*Display.getHeight());
		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[2]); GL11.glVertex2d(0.9875f*Display.getWidth(), 0.34f*Display.getHeight());
		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[3]); GL11.glVertex2d(0.9875f*Display.getWidth(), 0.44f*Display.getHeight());
		//4
		i = this.boutonsEtats[3] * modulo;
		j = (this.boutonsEtats[3] - 1) * modulo;

		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[4]); GL11.glVertex2d(0.9875f*Display.getWidth(), 0.56f*Display.getHeight());
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[4]); GL11.glVertex2d(0.6375f*Display.getWidth(), 0.56f*Display.getHeight());
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[3]); GL11.glVertex2d(0.6375f*Display.getWidth(), 0.46f*Display.getHeight());
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[3]); GL11.glVertex2d(0.6375f*Display.getWidth(), 0.46f*Display.getHeight());
		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[3]); GL11.glVertex2d(0.9875f*Display.getWidth(), 0.46f*Display.getHeight());
		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[4]); GL11.glVertex2d(0.9875f*Display.getWidth(), 0.56f*Display.getHeight());
		//5
		i = this.boutonsEtats[4] * modulo;
		j = (this.boutonsEtats[4] - 1) * modulo;

		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[5]); GL11.glVertex2d(0.9875f*Display.getWidth(), 0.68f*Display.getHeight());
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[5]); GL11.glVertex2d(0.6375f*Display.getWidth(), 0.68f*Display.getHeight());
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[4]); GL11.glVertex2d(0.6375f*Display.getWidth(), 0.58f*Display.getHeight());
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[4]); GL11.glVertex2d(0.6375f*Display.getWidth(), 0.58f*Display.getHeight());
		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[4]); GL11.glVertex2d(0.9875f*Display.getWidth(), 0.58f*Display.getHeight());
		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[5]); GL11.glVertex2d(0.9875f*Display.getWidth(), 0.68f*Display.getHeight());
		
		GL11.glEnd();
		
		//
	}

	public void bindBufferMenu() {
		glBindBuffer(GL_ARRAY_BUFFER, this.vboVertexHandle);
		glBufferData(GL_ARRAY_BUFFER, this.vertexDataFond, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	public void bindDrawMenu() {
		glBindBuffer(GL_ARRAY_BUFFER, this.vboVertexHandle);
        glVertexPointer(this.vertexSize, GL_FLOAT, 0, 0L);	
		
	}

	public void enableMenu() {
		glEnableClientState(GL_VERTEX_ARRAY);
		
	}

	public void disableMenu() {
		glDisableClientState(GL_VERTEX_ARRAY);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	
	public int getVertexSize() {
		return this.vertexSize;
	}

	public int getVboVertexHandle() {
		return this.vboVertexHandle;
	}

	public FloatBuffer getVertexData() {
		return this.vertexDataFond;
	}
	
	public float[] getBoutonsLimites() {
		return boutonsLimites;
	}

	public float[] getBoutonsTextureCoordonnes() {
		return boutonsTextureCoordonnes;
	}
	
	public boolean getEstAfficher() {
		return this.estAfficher;
	}

	public FloatBuffer getVertexDataFond() {
		return vertexDataFond;
	}

	public FloatBuffer getVertexDataBoutons() {
		return vertexDataBoutons;
	}

	public TextureManager getTextureManagerFond() {
		return textureManagerFond;
	}

	public TextureManager getTextureManagerBoutons() {
		return textureManagerBoutons;
	}

	public float[] getBoutonsEtats() {
		return boutonsEtats;
	}

	public Controleur getControleurClone() {
		return controleurClone;
	}

	public boolean getDisplayIsClose() {
		return displayIsClose;
	}
	
	public void inverserEstAfficher() {
		this.estAfficher = !(this.estAfficher);
	}

}
