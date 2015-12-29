package modeles.textures;

public class IronTexture implements TexturesGenerator {

	@Override
	public float[] genTexture(float indiceTextX, float indiceTextY) {
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;

		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		return new float[]{
				//north
				0.15625f+indiceTextX, 0.09375f+indiceTextY,
				0.15625f+indiceTextX, 0.0625f+indiceTextY,
				0.125f+indiceTextX, 0.09375f+indiceTextY,
				0.125f+indiceTextX, 0.09375f+indiceTextY,
				0.15625f+indiceTextX, 0.0625f+indiceTextY,
				0.125f+indiceTextX, 0.0625f+indiceTextY,

				0.15625f+indiceTextX, 0.0625f+indiceTextY,
				0.125f+indiceTextX, 0.0625f+indiceTextY,
				0.125f+indiceTextX, 0.09375f+indiceTextY,
				0.15625f+indiceTextX, 0.0625f+indiceTextY,
				0.125f+indiceTextX, 0.09375f+indiceTextY,
				0.15625f+indiceTextX, 0.09375f+indiceTextY,

				//top
				0.15625f+indiceTextX, 0.09375f+indiceTextY,
				0.15625f+indiceTextX, 0.0625f+indiceTextY,
				0.125f+indiceTextX, 0.09375f+indiceTextY,
				0.125f+indiceTextX, 0.09375f+indiceTextY,
				0.15625f+indiceTextX, 0.0625f+indiceTextY,
				0.125f+indiceTextX, 0.0625f+indiceTextY,

				//bottom
				0.125f+indiceTextX, 0.0625f+indiceTextY,
				0.125f+indiceTextX, 0.09375f+indiceTextY,
				0.15625f+indiceTextX, 0.0625f+indiceTextY,
				0.15625f+indiceTextX, 0.0625f+indiceTextY,
				0.125f+indiceTextX, 0.09375f+indiceTextY,
				0.15625f+indiceTextX, 0.09375f+indiceTextY,

				0.15625f+indiceTextX, 0.09375f+indiceTextY,
				0.15625f+indiceTextX, 0.0625f+indiceTextY,
				0.125f+indiceTextX, 0.09375f+indiceTextY,
				0.125f+indiceTextX, 0.09375f+indiceTextY,
				0.15625f+indiceTextX, 0.0625f+indiceTextY,
				0.125f+indiceTextX, 0.0625f+indiceTextY,

				0.125f+indiceTextX, 0.09375f+indiceTextY,
				0.15625f+indiceTextX, 0.0625f+indiceTextY,
				0.125f+indiceTextX, 0.0625f+indiceTextY,
				0.15625f+indiceTextX, 0.09375f+indiceTextY,
				0.15625f+indiceTextX, 0.0625f+indiceTextY,
				0.125f+indiceTextX, 0.09375f+indiceTextY,
		};
	}

}
