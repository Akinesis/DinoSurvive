package modeles.entities2D;

import java.nio.FloatBuffer;

/**
 * Classe gerant le curseur affiche sur le HUD
 * 
 */
public class Cursor2D extends AbstractEntity2D{
	private static int verticiesNum = 2;
	private int vertexSize = 2;
	private int vboVertexHandle;
	private FloatBuffer vertexData;

	/**
	 * Constructeur du curseur
	 */	
	public Cursor2D(){
		
	}
	
	/*
	 * dessin du curseur
	 */
	public void draw(){
		
	}
	
	/*
	 * génération du curseur
	 */
	public void genCurseur(){
		
	}
	/**
	 * calculs à utiliser pour l'affichage du curseur (osef de glVertex2f)
	 *glVertex2f(Display.getWidth()/2 +10, Display.getHeight()/2);
	 *glVertex2f(Display.getWidth()/2 -10, Display.getHeight()/2);
	 *
	 *glVertex2f(Display.getWidth()/2, Display.getHeight()/2 +10);
	 *glVertex2f(Display.getWidth()/2, Display.getHeight()/2 -10);
	 */

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
