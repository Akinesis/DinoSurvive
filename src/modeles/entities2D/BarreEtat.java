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

/**
 * Classe gérant l'affichage des barres vie/etc du joueur
 * 
 *
 */
public class BarreEtat extends AbstractEntity2D {
	
	private static int verticiesNum = 18;
	//taille (x,y)
	private int vertexSize = 2;
	//ce qui est dessiné (le buffer)
	private int vboVertexHandle;
	//coordonnées des points
	private FloatBuffer vertexData;

	public BarreEtat(){
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

	public void bindBuffer() {
		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
		glBufferData(GL_ARRAY_BUFFER, vertexData, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);	
		
	}

	public void bindDrawBarreEtat() {
		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
        glVertexPointer(vertexSize, GL_FLOAT, 0, 0L);	
		
	}

	public void enableBarreEtat() {
		glEnableClientState(GL_VERTEX_ARRAY);
		
	}

	public void disableBarreEtat() {
		glDisableClientState(GL_VERTEX_ARRAY);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
	}
	public void genBarreEtat() {
		//calcul de la taille de l'écran
				float lar = Display.getWidth();
				float hau = Display.getHeight();
				vertexData.put(new float[]{
						//barre supérieure (santé)
						//triangle sup
						0.9f*lar, 0.1f*hau,
						0.9f*lar, 0.08f*hau,
						0.75f*lar, 0.08f*hau,				
						//triangle inf
						0.9f*lar, 0.1f*hau,
						0.75f*lar, 0.08f*hau,
						0.75f*lar, 0.1f*hau,
						//barre middle (anti matière)
						//triangle sup
						0.9f*lar, 0.13f*hau,
						0.9f*lar, 0.11f*hau,
						0.75f*lar, 0.11f*hau,				
						//triangle inf
						0.9f*lar, 0.13f*hau,
						0.75f*lar, 0.11f*hau,
						0.75f*lar, 0.13f*hau,
						//barre inf (endurance)
						//triangle sup
						0.9f*lar, 0.16f*hau,
						0.9f*lar, 0.14f*hau,
						0.75f*lar, 0.14f*hau,				
						//triangle inf
						0.9f*lar, 0.16f*hau,
						0.75f*lar, 0.14f*hau,
						0.75f*lar, 0.16f*hau,
						
				});
				vertexData.flip();
		
	}

}
