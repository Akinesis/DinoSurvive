package modeles.entities2D;


import org.lwjgl.opengl.Display;

public class BarreSac extends AbstractEntity2D {
	
	public BarreSac(){
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
		float lar = Display.getWidth();
		float hau = Display.getHeight();
		return(new float[]{
				//triangle qui dépasse à gauche
				0.65f*lar, 1f*hau,
				0.65f*lar, 1f*hau-0.07f*lar,
				0.58f*lar, 1f*hau,				
				//triangle sup  de la barre
				1f*lar, 1f*hau-0.07f*lar,
				0.65f*lar, 1f*hau-0.07f*lar,
				0.65f*lar, 1f*hau,
				//triangle inf de la barre
				1f*lar, 1f*hau-0.07f*lar,
				0.65f*lar, 1f*hau,
				1f*lar, 1f*hau,		
		});
	}
	
	@Override
	public int getType() {
		return 3;
	}

}
