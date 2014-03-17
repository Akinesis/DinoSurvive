package Modeles.entities2D;

import java.nio.FloatBuffer;

/**
 * Classe gérant le curseur affiché sur le HUD
 * 
 *
 */
public class Cursor2D {
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
}
