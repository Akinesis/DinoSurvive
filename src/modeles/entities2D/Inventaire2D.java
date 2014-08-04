package modeles.entities2D;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.Display;

/**
 * Classe gérant l'entité 2D  qui représente l'inventaire affiché sur le HUD
 */
public class Inventaire2D extends AbstractEntity2D{
	
	/*
	 * Constructeur
	 */
	
	public Inventaire2D(){
	}
	

	/*
	 * Méthodes
	 */

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

		float lar = Display.getWidth();
		float hau = Display.getHeight();
		return new float[]{
				//premier triangle (counterclockwise)
				lar, 0.93f*hau,
				lar, 0.11f*hau,
				0.65f*lar, 0.11f*hau,
				
				//deuxième triangle
				0.65f*lar, 0.11f*hau,
				0.65f*lar, 0.93f*hau,
				lar, 0.93f*hau						
		};
	}

	@Override
	public int getType() {
		return 0;
	}



}
