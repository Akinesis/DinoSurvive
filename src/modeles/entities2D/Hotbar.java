package modeles.entities2D;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.Display;

public class Hotbar extends AbstractEntity2D{
	
	//nombre de points 
	private static int verticiesNum = 12;
	//taille (x,y)
	private int vertexSize = 2;
	//ce qui est dessiné (le buffer)
	private int vboVertexHandle;
	//coordonnées des points
	private FloatBuffer vertexData;
	
	
	/**Constructeur
	 * 
	 */
	public Hotbar(){
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/*public void genHotbar() {
		//calcul de la taille de l'écran
		float lar = Display.getWidth();
		float hau = Display.getHeight();
		vertexData.put(new float[]{
				//on part du haut vers le bas pour la partie fond
				//premier triangle vers le haut
				0.065f*lar, 0.2f*hau,
				0f*lar, 0.1f*hau,
				0f*lar, 0.2f*hau,				
				//triangle supérieur du rectangle
				0.065f*lar, 0.8f*hau,
				0.065f*lar, 0.2f*hau,
				0f*lar, 0.2f*hau,
				// triangle inférieur du rectangle
				0.065f*lar, 0.8f*hau,
				0f*lar, 0.2f*hau,
				0f*lar, 0.8f*hau,
				//triangle vers le bas
				0.065f*lar, 0.8f*hau,
				0f*lar, 0.8f*hau,
				0f*lar, 0.9f*hau,
				
		});
		vertexData.flip();
		
	}*/

	//renvoie les coordonnées des points de la hotbar via un float[]
	public float[] getCoord() {
		float lar = Display.getWidth();
		float hau = Display.getHeight();
		return(new float[]{
				//on part du haut vers le bas pour la partie fond
				//premier triangle vers le haut
				0.065f*lar, 0.2f*hau,
				0f*lar, 0.1f*hau,
				0f*lar, 0.2f*hau,				
				//triangle supérieur du rectangle
				0.065f*lar, 0.8f*hau,
				0.065f*lar, 0.2f*hau,
				0f*lar, 0.2f*hau,
				// triangle inférieur du rectangle
				0.065f*lar, 0.8f*hau,
				0f*lar, 0.2f*hau,
				0f*lar, 0.8f*hau,
				//triangle vers le bas
				0.065f*lar, 0.8f*hau,
				0f*lar, 0.8f*hau,
				0f*lar, 0.9f*hau,
		});
	}

	
	public int getType() {
		return 0;
	}
}