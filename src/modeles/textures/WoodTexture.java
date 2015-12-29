package modeles.textures;

public class WoodTexture implements TexturesGenerator {

	@Override
	public float[] genTexture(float indiceTextX, float indiceTextY) {
		float indiceTextTopX = (int)(indiceTextX * ((1 - 0) + 1))*0.03125f;
		
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;
		

		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		
		return new float[]{
				//north
				0.03125f, 0.21875f+indiceTextY,
				0.03125f+indiceTextX, 0.1875f+indiceTextY,
				0.0f+indiceTextX, 0.21875f+indiceTextY,
				0.0f+indiceTextX, 0.21875f+indiceTextY,
				0.03125f+indiceTextX, 0.1875f+indiceTextY,
				0.0f+indiceTextX, 0.1875f+indiceTextY,

				0.03125f+indiceTextX, 0.1875f+indiceTextY,
				0.0f+indiceTextX, 0.1875f+indiceTextY,
				0.0f+indiceTextX, 0.21875f+indiceTextY,
				0.03125f+indiceTextX, 0.1875f+indiceTextY,
				0.0f+indiceTextX, 0.21875f+indiceTextY,
				0.03125f+indiceTextX, 0.21875f+indiceTextY,

				//top
				0.21875f+indiceTextTopX, 0.15625f,
				0.21875f+indiceTextTopX, 0.125f,
				0.1875f+indiceTextTopX, 0.15625f,
				0.1875f+indiceTextTopX, 0.15625f,
				0.21875f+indiceTextTopX, 0.125f,
				0.1875f+indiceTextTopX, 0.125f,

				//bottom
				0.1875f+indiceTextTopX, 0.125f,
				0.1875f+indiceTextTopX, 0.15625f,
				0.21875f+indiceTextTopX, 0.125f,
				0.21875f+indiceTextTopX, 0.125f,
				0.1875f+indiceTextTopX, 0.15625f,
				0.21875f+indiceTextTopX, 0.15625f,

				0.03125f+indiceTextX, 0.21875f+indiceTextY,
				0.03125f+indiceTextX, 0.1875f+indiceTextY,
				0.0f+indiceTextX, 0.21875f+indiceTextY,
				0.0f+indiceTextX, 0.21875f+indiceTextY,
				0.03125f+indiceTextX, 0.1875f+indiceTextY,
				0.0f+indiceTextX, 0.1875f+indiceTextY,

				0.0f+indiceTextX, 0.21875f+indiceTextY,
				0.03125f+indiceTextX, 0.1875f+indiceTextY,
				0.0f+indiceTextX, 0.1875f+indiceTextY,
				0.03125f+indiceTextX, 0.21875f+indiceTextY,
				0.03125f+indiceTextX, 0.1875f+indiceTextY,
				0.0f+indiceTextX, 0.21875f+indiceTextY,
		};
	}

}
