package modeles.entities2D;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.Display;

public class MenuJeu extends AbstractEntity2D {
	//au départ l'inventaire n'est pas affiché
	boolean onScreen = false;
	private static int verticiesNum = 6;
	//ce qui est dessiné (le buffer)
	//coordonnées des points
	private FloatBuffer vertexData;
	
	/*
	 * Constructeur
	 */
	
	public MenuJeu(){
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
