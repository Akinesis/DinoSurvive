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

public class Hotbar extends AbstractEntity2D{
	
	//nombre de points 
	private static int verticiesNum = 12;
	//taille (x,y)
	private int vertexSize = 2;
	//ce qui est dessiné (le buffer)
	private int vboVertexHandle;
	//coordonnées des points
	private FloatBuffer vertexData;
	
	
	/**Constructeur
	 * 
	 */
	public Hotbar(){
		vertexData = BufferUtils.createFloatBuffer(verticiesNum * vertexSize);
		vboVertexHandle = glGenBuffers();
	}
	@Override
	public void draw() {
		glColor3f(0.1f, 0.1f, 1f);
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

	public void genHotbar() {
		//calcul de la taille de l'écran
		float lar = Display.getWidth();
		float hau = Display.getHeight();
		vertexData.put(new float[]{
				//on part du haut vers le bas pour la partie fond
				//premier triangle vers le haut
				0.065f*lar, 0.2f*hau,
				0f*lar, 0.1f*hau,
				0f*lar, 0.2f*hau,				
				//triangle supérieur du rectangle
				0.065f*lar, 0.8f*hau,
				0.065f*lar, 0.2f*hau,
				0f*lar, 0.2f*hau,
				// triangle inférieur du rectangle
				0.065f*lar, 0.8f*hau,
				0f*lar, 0.2f*hau,
				0f*lar, 0.8f*hau,
				//triangle vers le bas
				0.065f*lar, 0.8f*hau,
				0f*lar, 0.8f*hau,
				0f*lar, 0.9f*hau,
				
		});
		vertexData.flip();
		
	}

	public void bindBuffer() {
		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
		glBufferData(GL_ARRAY_BUFFER, vertexData, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);		
	}

	public void bindDrawHotbar() {
		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
        glVertexPointer(vertexSize, GL_FLOAT, 0, 0L);	
		
	}

	public void disableHotbar() {
		glDisableClientState(GL_VERTEX_ARRAY);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
	}

	public void enableHotbar() {
		glEnableClientState(GL_VERTEX_ARRAY);
		
	}

	//renvoie les coordonnées des points de la hotbar via un float[]
	public float[] getCoord() {
		float lar = Display.getWidth();
		float hau = Display.getHeight();
		return(new float[]{
				//on part du haut vers le bas pour la partie fond
				//premier triangle vers le haut
				0.065f*lar, 0.2f*hau,
				0f*lar, 0.1f*hau,
				0f*lar, 0.2f*hau,				
				//triangle supérieur du rectangle
				0.065f*lar, 0.8f*hau,
				0.065f*lar, 0.2f*hau,
				0f*lar, 0.2f*hau,
				// triangle inférieur du rectangle
				0.065f*lar, 0.8f*hau,
				0f*lar, 0.2f*hau,
				0f*lar, 0.8f*hau,
				//triangle vers le bas
				0.065f*lar, 0.8f*hau,
				0f*lar, 0.8f*hau,
				0f*lar, 0.9f*hau,
		});
	}

	
	public int getType() {
		return 0;
	}
}