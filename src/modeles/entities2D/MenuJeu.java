package modeles.entities2D;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glDisableClientState;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glVertexPointer;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;

public class MenuJeu extends AbstractEntity2D {
	//au départ l'inventaire n'est pas affiché
	boolean onScreen = false;
	private static int verticiesNum = 6;
	//taille (x,y)
	private int vertexSize = 2;
	//ce qui est dessiné (le buffer)
	private int vboVertexHandle;
	//coordonnées des points
	private FloatBuffer vertexData;
	
	/*
	 * Constructeur
	 */
	
	public MenuJeu(){
		vertexData = BufferUtils.createFloatBuffer(verticiesNum * vertexSize);
		vboVertexHandle = glGenBuffers();
	}
	
	/*
	 * Getter
	 */
	public boolean getAffichMenu(){
		return onScreen;
	}
	
	/*
	 * Setter
	 */
	public void changeAffichMenu(){
		onScreen = !(onScreen);
	}
	@Override
	public void draw() {
		glColor3f(0.4f, 0.45f, 1f);
		glDrawArrays(GL_TRIANGLES, 0, verticiesNum);
		glColor3f(1f,1f,1f);
		
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void genMenuJeu() {
		//calcul de la taille de l'écran
		float lar = Display.getWidth();
		float hau = Display.getHeight();
		vertexData.put(new float[]{
				//premier triangle (counterclockwise)
				0.8f*lar, 0.1f*hau,
				0.2f*lar, 0.1f*hau,
				0.2f*lar, 0.9f*hau,
				
				//deuxième triangle
				0.8f*lar, 0.1f*hau,
				0.2f*lar, 0.9f*hau,
				0.8f*lar, 0.9f*hau						
		});
		vertexData.flip();
		
	}

	public void bindBuffer() {
		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
		glBufferData(GL_ARRAY_BUFFER, vertexData, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);	
		
	}

	public void bindDrawMenuJeu() {
		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
        glVertexPointer(vertexSize, GL_FLOAT, 0, 0L);	
		
	}

	public void enableMenuJeu() {
		glEnableClientState(GL_VERTEX_ARRAY);
		
	}

	public void disableMenuJeu() {
		glDisableClientState(GL_VERTEX_ARRAY);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
	}

	@Override
	public float[] getCoord() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getType() {
		return 5;
	}

}
