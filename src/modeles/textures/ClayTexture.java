package modeles.textures;

public class ClayTexture implements TexturesGenerator {

	@Override
	public float[] genTexture(float indiceTextX, float indiceTextY) {
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;

		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		return new float[]{
				//north
				0.21875f+indiceTextX, 0.21875f+indiceTextY,
				0.21875f+indiceTextX, 0.1875f+indiceTextY,
				0.1875f+indiceTextX, 0.21875f+indiceTextY,
				0.1875f+indiceTextX, 0.21875f+indiceTextY,
				0.21875f+indiceTextX, 0.1875f+indiceTextY,
				0.1875f+indiceTextX, 0.1875f+indiceTextY,

				0.21875f+indiceTextX, 0.1875f+indiceTextY,
				0.1875f+indiceTextX, 0.1875f+indiceTextY,
				0.1875f+indiceTextX, 0.21875f+indiceTextY,
				0.21875f+indiceTextX, 0.1875f+indiceTextY,
				0.1875f+indiceTextX, 0.21875f+indiceTextY,
				0.21875f+indiceTextX, 0.21875f+indiceTextY,

				//top
				0.21875f+indiceTextX, 0.21875f+indiceTextY,
				0.21875f+indiceTextX, 0.1875f+indiceTextY,
				0.1875f+indiceTextX, 0.21875f+indiceTextY,
				0.1875f+indiceTextX, 0.21875f+indiceTextY,
				0.21875f+indiceTextX, 0.1875f+indiceTextY,
				0.1875f+indiceTextX, 0.1875f+indiceTextY,

				//bottom
				0.1875f+indiceTextX, 0.1875f+indiceTextY,
				0.1875f+indiceTextX, 0.21875f+indiceTextY,
				0.21875f+indiceTextX, 0.1875f+indiceTextY,
				0.21875f+indiceTextX, 0.1875f+indiceTextY,
				0.1875f+indiceTextX, 0.21875f+indiceTextY,
				0.21875f+indiceTextX, 0.21875f+indiceTextY,

				0.21875f+indiceTextX, 0.21875f+indiceTextY,
				0.21875f+indiceTextX, 0.1875f+indiceTextY,
				0.1875f+indiceTextX, 0.21875f+indiceTextY,
				0.1875f+indiceTextX, 0.21875f+indiceTextY,
				0.21875f+indiceTextX, 0.1875f+indiceTextY,
				0.1875f+indiceTextX, 0.1875f+indiceTextY,

				0.1875f+indiceTextX, 0.21875f+indiceTextY,
				0.21875f+indiceTextX, 0.1875f+indiceTextY,
				0.1875f+indiceTextX, 0.1875f+indiceTextY,
				0.21875f+indiceTextX, 0.21875f+indiceTextY,
				0.21875f+indiceTextX, 0.1875f+indiceTextY,
				0.1875f+indiceTextX, 0.21875f+indiceTextY
		};
	}

}
