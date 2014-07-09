package modeles.entities2D;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
<<<<<<< HEAD
import static org.lwjgl.opengl.GL11.GL_TEXTURE_COORD_ARRAY;
=======
>>>>>>> origin/master
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glDisableClientState;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnableClientState;
<<<<<<< HEAD
import static org.lwjgl.opengl.GL11.glTexCoordPointer;
import static org.lwjgl.opengl.GL11.glVertexPointer;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glClientActiveTexture;
=======
import static org.lwjgl.opengl.GL11.glVertexPointer;
>>>>>>> origin/master
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
<<<<<<< HEAD
import org.lwjgl.util.glu.GLU;
=======
>>>>>>> origin/master

import java.nio.FloatBuffer;

import modeles.TextureManager;
import modeles.entities.AbstractEntity3D;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
<<<<<<< HEAD
import org.lwjgl.opengl.GL11;
=======
>>>>>>> origin/master

public class Menu extends AbstractEntity3D{
	private static int verticiesNum = 6;
	private int vertexSize = 2;
	private int vboVertexHandle;
	private FloatBuffer vertexData;
	private boolean estAfficher;
	private TextureManager textureManager;
	
	public Menu(){
		this.estAfficher = true;
<<<<<<< HEAD
		this.vertexData = BufferUtils.createFloatBuffer(verticiesNum * this.vertexSize);
		this.vboVertexHandle = glGenBuffers();
=======
		vertexData = BufferUtils.createFloatBuffer(verticiesNum * vertexSize);
		vboVertexHandle = glGenBuffers();
>>>>>>> origin/master
		this.textureManager = new TextureManager("res/Menu.png");
	}
	
	public void generationMenu() {
<<<<<<< HEAD
		this.vertexData.put(new float[]{   
			Display.getWidth(), Display.getHeight(),	Display.getWidth(), 0,		0, 0,
			0, 0,										0, Display.getHeight(),		Display.getWidth(), Display.getHeight(),
		});
		this.vertexData.flip();
=======
		vertexData.put(new float[]{   
			Display.getWidth(), Display.getHeight(),	Display.getWidth(), 0,		0, 0,
			0, 0,										0, Display.getHeight(),		Display.getWidth(), Display.getHeight(),
		});
		vertexData.flip();
>>>>>>> origin/master
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
<<<<<<< HEAD
		//glColor3f(0f, 0.28f, 0.84f);
		//glDrawArrays(GL_TRIANGLES, 0, verticiesNum);
		
		//GL11.glLoadIdentity();
		//GL11.glMatrixMode(GL11.GL_PROJECTION);
		//GLU.gluOrtho2D(0.0f, Display.getWidth(), Display.getHeight(), 0.0f);
		
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.textureManager.getID());
		GL11.glBegin(GL_TRIANGLES);
		//this.generationMenu();
		GL11.glTexCoord2d(1,1); GL11.glVertex2d(Display.getWidth(), Display.getHeight());
		GL11.glTexCoord2d(1,0); GL11.glVertex2d(Display.getWidth(), 0);
		GL11.glTexCoord2d(0,0); GL11.glVertex2d(0, 0);
		GL11.glTexCoord2d(0,0); GL11.glVertex2d(0, 0);
		GL11.glTexCoord2d(0,1); GL11.glVertex2d(0, Display.getHeight());
		GL11.glTexCoord2d(1,1); GL11.glVertex2d(Display.getWidth(), Display.getHeight());
		
		GL11.glEnd();
		
		//glColor3f(1f, 1f, 1f);
	}

	public void bindBufferMenu() {
		glBindBuffer(GL_ARRAY_BUFFER, this.vboVertexHandle);
		glBufferData(GL_ARRAY_BUFFER, this.vertexData, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	public void bindDrawMenu() {
		glBindBuffer(GL_ARRAY_BUFFER, this.vboVertexHandle);
        glVertexPointer(this.vertexSize, GL_FLOAT, 0, 0L);	
=======
		glColor3f(0f, 0.1f, 0.2f);
		glDrawArrays(GL_TRIANGLES, 0, verticiesNum);
		System.out.println("menu.draw");
		glColor3f(1f, 1f, 1f);
	}

	public void bindBufferMenu() {
		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
		glBufferData(GL_ARRAY_BUFFER, vertexData, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        System.out.println("menu.bindbuffer");
	}

	public void bindDrawMenu() {
		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
        glVertexPointer(vertexSize, GL_FLOAT, 0, 0L);	
>>>>>>> origin/master
		
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
		return this.vertexData;
	}

	public boolean getEstAfficher() {
		return this.estAfficher;
	}

	public void inverserEstAfficher() {
		this.estAfficher = !(this.estAfficher);
<<<<<<< HEAD
=======
		System.out.println("menu.estAfficher = " + this.estAfficher);
>>>>>>> origin/master
	}
	
}
