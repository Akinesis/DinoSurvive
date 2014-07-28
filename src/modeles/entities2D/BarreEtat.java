package modeles.entities2D;

import org.lwjgl.opengl.Display;

/**
 * Classe gérant l'affichage des barres vie/etc du joueur
 * 
 *
 */
public class BarreEtat extends AbstractEntity2D {
	


	public BarreEtat(){
		visible = true;
	}
	
	@Override
	public void setUp() {
	}

	@Override
	public void destroy() {
	}

	public float[] getCoord() {
		float lar = Display.getWidth();
		float hau = Display.getHeight();
		return new float[]{
				//barre supérieure (santé)
				//triangle sup
				0.9f*lar, 0.03f*hau,
				0.9f*lar, 0.01f*hau,
				0.75f*lar, 0.01f*hau,				
				//triangle inf
				0.9f*lar, 0.03f*hau,
				0.75f*lar, 0.01f*hau,
				0.75f*lar, 0.03f*hau,
				//barre middle (anti matière)
				//triangle sup
				0.9f*lar, 0.06f*hau,
				0.9f*lar, 0.04f*hau,
				0.75f*lar, 0.04f*hau,				
				//triangle inf
				0.9f*lar, 0.06f*hau,
				0.75f*lar, 0.04f*hau,
				0.75f*lar, 0.06f*hau,
				//barre inf (endurance)
				//triangle sup
				0.9f*lar, 0.09f*hau,
				0.9f*lar, 0.07f*hau,
				0.75f*lar, 0.07f*hau,				
				//triangle inf
				0.9f*lar, 0.09f*hau,
				0.75f*lar, 0.07f*hau,
				0.75f*lar, 0.09f*hau
		};
	}
	@Override
	public int getType() {
		return 2;
	}

}
