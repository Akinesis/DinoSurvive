package modeles.entities2D;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_COORD_ARRAY;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glDisableClientState;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glTexCoordPointer;
import static org.lwjgl.opengl.GL11.glVertexPointer;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glClientActiveTexture;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import org.lwjgl.util.glu.GLU;

import java.nio.FloatBuffer;

import modeles.TextureManager;
import modeles.entities.AbstractEntity3D;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class Menu extends AbstractEntity3D{
	//private static int verticiesNum = 36;
	private int vertexSize = 2;
	private int vboVertexHandle;
	private FloatBuffer vertexDataFond;
	private FloatBuffer vertexDataBoutons;
	private boolean estAfficher;
	private TextureManager textureManagerFond;
	private TextureManager textureManagerBoutons;
	
	public Menu(){
		this.estAfficher = true;
		this.vertexDataFond = BufferUtils.createFloatBuffer(6 * this.vertexSize);
		this.vertexDataBoutons = BufferUtils.createFloatBuffer(30 * this.vertexSize);
		this.vboVertexHandle = glGenBuffers();
		this.textureManagerFond = new TextureManager("res/Menue.png");
		this.textureManagerBoutons = new TextureManager("res/Menu.png");
	}
	
	public void generationMenuFond() {
		this.vertexDataFond.put(new float[]{   
			Display.getWidth(), Display.getHeight(),	Display.getWidth(), 0,		0, 0,
			0, 0,										0, Display.getHeight(),		Display.getWidth(), Display.getHeight(),
		});
		this.vertexDataFond.flip();
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
		//glColor3f(0f, 0.28f, 0.84f);
		//glDrawArrays(GL_TRIANGLES, 0, verticiesNum);
		
		//GL11.glLoadIdentity();
		//GL11.glMatrixMode(GL11.GL_PROJECTION);
		//GLU.gluOrtho2D(0.0f, Display.getWidth(), Display.getHeight(), 0.0f);
		
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.textureManagerFond.getID());
		GL11.glBegin(GL_TRIANGLES);
		//this.generationMenu();
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
		GL11.glTexCoord2d(1,1); GL11.glVertex2d(0.9875f*Display.getWidth(), 0.2f*Display.getHeight());
		GL11.glTexCoord2d(0,1); GL11.glVertex2d(0.6375f*Display.getWidth(), 0.2f*Display.getHeight());
		GL11.glTexCoord2d(0,0); GL11.glVertex2d(0.6375f*Display.getWidth(), 0.1f*Display.getHeight());
		GL11.glTexCoord2d(0,0); GL11.glVertex2d(0.6375f*Display.getWidth(), 0.1f*Display.getHeight());
		GL11.glTexCoord2d(1,0); GL11.glVertex2d(0.9875f*Display.getWidth(), 0.1f*Display.getHeight());
		GL11.glTexCoord2d(1,1); GL11.glVertex2d(0.9875f*Display.getWidth(), 0.2f*Display.getHeight());
		//2
		GL11.glTexCoord2d(1,1); GL11.glVertex2d(0.9875f*Display.getWidth(), 0.32f*Display.getHeight());
		GL11.glTexCoord2d(0,1); GL11.glVertex2d(0.6375f*Display.getWidth(), 0.32f*Display.getHeight());
		GL11.glTexCoord2d(0,0); GL11.glVertex2d(0.6375f*Display.getWidth(), 0.22f*Display.getHeight());
		GL11.glTexCoord2d(0,0); GL11.glVertex2d(0.6375f*Display.getWidth(), 0.22f*Display.getHeight());
		GL11.glTexCoord2d(1,0); GL11.glVertex2d(0.9875f*Display.getWidth(), 0.22f*Display.getHeight());
		GL11.glTexCoord2d(1,1); GL11.glVertex2d(0.9875f*Display.getWidth(), 0.32f*Display.getHeight());
		//3
		GL11.glTexCoord2d(1,1); GL11.glVertex2d(0.9875f*Display.getWidth(), 0.44f*Display.getHeight());
		GL11.glTexCoord2d(0,1); GL11.glVertex2d(0.6375f*Display.getWidth(), 0.44f*Display.getHeight());
		GL11.glTexCoord2d(0,0); GL11.glVertex2d(0.6375f*Display.getWidth(), 0.34f*Display.getHeight());
		GL11.glTexCoord2d(0,0); GL11.glVertex2d(0.6375f*Display.getWidth(), 0.34f*Display.getHeight());
		GL11.glTexCoord2d(1,0); GL11.glVertex2d(0.9875f*Display.getWidth(), 0.34f*Display.getHeight());
		GL11.glTexCoord2d(1,1); GL11.glVertex2d(0.9875f*Display.getWidth(), 0.44f*Display.getHeight());
		//4
		GL11.glTexCoord2d(1,1); GL11.glVertex2d(0.9875f*Display.getWidth(), 0.56f*Display.getHeight());
		GL11.glTexCoord2d(0,1); GL11.glVertex2d(0.6375f*Display.getWidth(), 0.56f*Display.getHeight());
		GL11.glTexCoord2d(0,0); GL11.glVertex2d(0.6375f*Display.getWidth(), 0.46f*Display.getHeight());
		GL11.glTexCoord2d(0,0); GL11.glVertex2d(0.6375f*Display.getWidth(), 0.46f*Display.getHeight());
		GL11.glTexCoord2d(1,0); GL11.glVertex2d(0.9875f*Display.getWidth(), 0.46f*Display.getHeight());
		GL11.glTexCoord2d(1,1); GL11.glVertex2d(0.9875f*Display.getWidth(), 0.56f*Display.getHeight());
		//5
		GL11.glTexCoord2d(1,1); GL11.glVertex2d(0.9875f*Display.getWidth(), 0.68f*Display.getHeight());
		GL11.glTexCoord2d(0,1); GL11.glVertex2d(0.6375f*Display.getWidth(), 0.68f*Display.getHeight());
		GL11.glTexCoord2d(0,0); GL11.glVertex2d(0.6375f*Display.getWidth(), 0.58f*Display.getHeight());
		GL11.glTexCoord2d(0,0); GL11.glVertex2d(0.6375f*Display.getWidth(), 0.58f*Display.getHeight());
		GL11.glTexCoord2d(1,0); GL11.glVertex2d(0.9875f*Display.getWidth(), 0.58f*Display.getHeight());
		GL11.glTexCoord2d(1,1); GL11.glVertex2d(0.9875f*Display.getWidth(), 0.68f*Display.getHeight());
		
		GL11.glEnd();
		
		//glColor3f(1f, 1f, 1f);
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

	public boolean getEstAfficher() {
		return this.estAfficher;
	}

	public void inverserEstAfficher() {
		this.estAfficher = !(this.estAfficher);
	}
	
}
