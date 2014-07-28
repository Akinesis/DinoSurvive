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
				0.98f*lar, 0.09f*hau,
				0.98f*lar, 0.01f*hau,
				0.91f*lar, 0.01f*hau,
				
				//triangle du bas
				0.98f*lar, 0.09f*hau,
				0.91f*lar, 0.01f*hau,
				0.91f*lar, 0.09f*hau
				
		};
	}

	@Override
	public int getType() {
		return 7;
	}

}
