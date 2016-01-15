package modeles.entities2D;

import org.lwjgl.opengl.Display;

public class Hotbar extends AbstractEntity2D{
	
	/**Constructeur
	 * 
	 */
	public Hotbar(){
		visible = true;
	}

	@Override
	public void setUp() {
	}

	@Override
	public void destroy() {
	}

	//renvoie les coordonnées des points de la hotbar via un float[]
	public float[] getCoord() {
		float lar = Display.getWidth();
		float hau = Display.getHeight();
		return(new float[]{
				//on part du haut vers le bas pour la partie fond
				//premier triangle vers le haut
				0f*lar, 0.15f*hau,	 		
				0.07f*hau, 0.15f*hau,	
				0f*lar, 0.08f*hau,
				//triangle supérieur du rectangle
				0.07f*hau, 0.85f*hau,
				0.07f*hau, 0.15f*hau,
				0f*lar, 0.15f*hau,
				// triangle inférieur du rectangle
				0.07f*hau, 0.85f*hau,
				0f*lar, 0.15f*hau,
				0f*lar, 0.85f*hau,
				//triangle vers le bas
				0.07f*hau, 0.85f*hau,
				0f*lar, 0.85f*hau,
				0f*lar, 0.92f*hau,
		});
	}
	
	public int getType() {
		return 0;
	}
}