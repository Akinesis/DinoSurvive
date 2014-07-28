package modeles.entities2D;

import org.lwjgl.opengl.Display;

public class MenuJeu extends AbstractEntity2D {
		
	public MenuJeu(){
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
		float lar = Display.getWidth();
		float hau = Display.getHeight();
		return new float[]{
				//premier triangle (counterclockwise)
				0.8f*lar, 0.1f*hau,
				0.2f*lar, 0.1f*hau,
				0.2f*lar, 0.9f*hau,
				
				//deuxième triangle
				0.8f*lar, 0.1f*hau,
				0.2f*lar, 0.9f*hau,
				0.8f*lar, 0.9f*hau						
		};
	}

	@Override
	public int getType() {
		return 0;
	}

}
