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

	/**
	 * Constructeur du curseur
	 */	
	public Cursor2D(){
		visible = true;
		
	}
	
	@Override
	public void setUp() {
	}

	@Override
	public void destroy() {
	}

	@Override
	public float[] getCoord() {
		float c_hor = Display.getWidth()/2;
		float c_vert = Display.getHeight()/2;
		return new float[]{
				//triangle sup
				c_hor+10, c_vert +10,
				c_hor +10, c_vert-10,
				c_hor -10, c_vert -10,
				
				//triangle inf
				c_hor+10, c_vert +10,
				c_hor -10, c_vert -10,
				c_hor -10, c_vert +10
		};
	}

	@Override
	public int getType() {
		return 6;
	}
}
