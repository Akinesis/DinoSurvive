package modeles.entities2D;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * Classe gérant le curseur affiché sur le HUD
 * 
 *
 */
public class Cursor2D extends AbstractEntity2D{
	//nombre de vertex (traits)
	private static int verticiesNum = 2;
	//taille (x,y)
	private int vertexSize = 2;
	//ce qui est dessiné (le buffer)
	private int vboVertexHandle;
	//coordonnées des points
	private FloatBuffer vertexData;

	

	/**
	 * Constructeur du curseur
	 */	
	public Cursor2D(){
		vertexData = BufferUtils.createFloatBuffer(verticiesNum * vertexSize);
		
	}
	
	/*
	 * dessin du curseur
	 */
	public void draw(){
		glDrawArrays(GL_LINES, 0, verticiesNum);
	}
	
	/*
	 * génération du curseur
	 */
	public void genCurseur(){
		//calcul du centre
		float c_hor = Display.getWidth();
		float c_vert = Display.getHeight();
		vertexData.put(new float[]{
				c_hor+10, c_vert,
				c_hor-10, c_vert,
				c_hor, c_vert +10,
				c_hor, c_vert -10,
		});
		vertexData.flip();
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
        glVertexPointer(vertexSize, GL_FLOAT, 0, 0L);
	}

	public void bindDrawCursor() {
		// TODO Auto-generated method stub
		
		
	}

	public void enableCursor() {
		// TODO Auto-generated method stub
		
	}

	public void disableCursor() {
		glDisableClientState(GL_VERTEX_ARRAY);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
	}
}
