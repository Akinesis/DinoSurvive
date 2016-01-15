package modeles.entities2D;

import org.lwjgl.opengl.Display;
/**
 * Classe qui gère l'affichage du menu en jeu (quand on fait échap)
 *
 * 
 *
 */

public class MenuJeu extends AbstractEntity2D {
		
	public MenuJeu(){
	}
	
	@Override
	public void setUp() {
	}

	@Override
	public void destroy() {		
	}

	@Override
	public float[] getCoord() {
		float lar = Display.getWidth();
		float hau = Display.getHeight();
		return new float[]{
				// deux triangles pour le premier bouton (partant du haut-après essais à la con)
				//triangle sup
				0.75f*lar, 0.18f*hau,
				0.25f*lar, 0.18f*hau,
				0.25f*lar, 0.28f*hau,
				//triangle inf
				0.75f*lar, 0.18f*hau,
				0.25f*lar, 0.28f*hau,
				0.75f*lar, 0.28f*hau,

				//deux triangles pour le deuxième bouton
				//triangle sup
				0.75f*lar, 0.36f*hau,
				0.25f*lar, 0.36f*hau,
				0.25f*lar, 0.46f*hau,
				//triangle inf
				0.75f*lar, 0.36f*hau,
				0.25f*lar, 0.46f*hau,
				0.75f*lar, 0.46f*hau,
				
				//deux triangles pour le troisième bouton
				//triangle sup
				0.75f*lar, 0.54f*hau,
				0.25f*lar, 0.54f*hau,
				0.25f*lar, 0.64f*hau,
				//triangle inf
				0.75f*lar, 0.54f*hau,
				0.25f*lar, 0.64f*hau,
				0.75f*lar, 0.64f*hau,
				
				//deux triangles pour le quatrième bouton
				//triangle sup
				0.75f*lar, 0.72f*hau,
				0.25f*lar, 0.72f*hau,
				0.25f*lar, 0.82f*hau,
				//triangle inf
				0.75f*lar, 0.72f*hau,
				0.25f*lar, 0.82f*hau,
				0.75f*lar, 0.82f*hau,
				
				
				// les deux premiers triangles forment le fond du menu
				//premier triangle (counterclockwise)
				0.8f*lar, 0.1f*hau,
				0.2f*lar, 0.1f*hau,
				0.2f*lar, 0.9f*hau,
				//deuxième triangle
				0.8f*lar, 0.1f*hau,
				0.2f*lar, 0.9f*hau,
				0.8f*lar, 0.9f*hau,				

				
		};
	}

	@Override
	public int getType() {
		return 5;
	}

}
