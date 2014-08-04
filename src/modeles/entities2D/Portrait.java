package modeles.entities2D;

import org.lwjgl.opengl.Display;

public class Portrait extends AbstractEntity2D {

	public Portrait(){
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
		
		return new float[]{
				//triangle du haut
				1f*lar, 0.11f*hau,
				1f*lar-0.15f*hau, 0.11f*hau,
				1f*lar-0.15f*hau, 0.26f*hau,
				
				//triangle du bas
				1f*lar-0.15f*hau, 0.26f*hau,
				1f*lar, 0.26f*hau,
				1f*lar, 0.11f*hau
				
		};
	}

	@Override
	public int getType() {
		return 7;
	}

}
