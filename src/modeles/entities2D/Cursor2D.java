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
	private static int verticiesNum = 4;
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
		vboVertexHandle = glGenBuffers();
		
	}
	
	/*
	 * dessin du curseur
	 * 4
	 */
	public void draw(){
		glDrawArrays(GL_LINES, 0, verticiesNum);
	}
	
	/*
	 * génération du curseur
	 */
	public void genCurseur(){
		//calcul du centre
		float c_hor = Display.getWidth()/2;
		float c_vert = Display.getHeight()/2;
		vertexData.put(new float[]{
				c_hor+25, c_vert,
				c_hor-25, c_vert,
				c_hor, c_vert +25,
				c_hor, c_vert -25,
		});
		vertexData.flip();
	}

	//1
	public void bindBuffer() {
		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
		glBufferData(GL_ARRAY_BUFFER, vertexData, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	//2
	public void bindDrawCursor() {
		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
        glVertexPointer(vertexSize, GL_FLOAT, 0, 0L);		
	}

	//3
	public void enableCursor() {
		glEnableClientState(GL_VERTEX_ARRAY);
		
	}

	//5
	public void disableCursor() {
		glDisableClientState(GL_VERTEX_ARRAY);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
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
	public float[] getCoord() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getType() {
		return 6;
	}
}
