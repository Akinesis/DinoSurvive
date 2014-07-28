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

public class BarreSac extends AbstractEntity2D {
	private static int verticiesNum = 9;
	//taille (x,y)
	private int vertexSize = 2;
	//ce qui est dessiné (le buffer)
	private int vboVertexHandle;
	//coordonnées des points
	private FloatBuffer vertexData;

	public BarreSac(){
		vertexData = BufferUtils.createFloatBuffer(verticiesNum * vertexSize);
		vboVertexHandle = glGenBuffers();
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	public void genBarreSac() {
		//calcul de la taille de l'écran
				float lar = Display.getWidth();
				float hau = Display.getHeight();
				vertexData.put(new float[]{
						//triangle qui dépasse
						0.75f*lar, 1f*hau,
						0.75f*lar, 0.9f*hau,
						0.68f*lar, 1f*hau,				
						//triangle sup  de la barre
						1f*lar, 0.9f*hau,
						0.75f*lar, 0.9f*hau,
						0.75f*lar, 1f*hau,
						//triangle inf de la barre
						1f*lar, 0.9f*hau,
						0.75f*lar, 1f*hau,
						1f*lar, 1f*hau,				
						
				});
				vertexData.flip();
		
	}

	@Override
	public float[] getCoord() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getType() {
		return 3;
	}

}