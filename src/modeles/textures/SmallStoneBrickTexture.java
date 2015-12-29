package modeles.textures;

public class SmallStoneBrickTexture implements TexturesGenerator {

	@Override
	public float[] genTexture(float indiceTextX, float indiceTextY) {
		indiceTextX = (int)(indiceTextX * ((1 - 0) + 1))*0.03125f;
		indiceTextY = (int)(indiceTextY * ((1 - 0) + 1))*0.03125f;

		return new float[]{
				//north
				0.21875f+indiceTextX, 0.28125f+indiceTextY,
				0.21875f+indiceTextX, 0.25f+indiceTextY,
				0.1875f+indiceTextX, 0.28125f+indiceTextY,
				0.1875f+indiceTextX, 0.28125f+indiceTextY,
				0.21875f+indiceTextX, 0.25f+indiceTextY,
				0.1875f+indiceTextX, 0.25f+indiceTextY,

				0.21875f+indiceTextX, 0.25f+indiceTextY,
				0.1875f+indiceTextX, 0.25f+indiceTextY,
				0.1875f+indiceTextX, 0.28125f+indiceTextY,
				0.21875f+indiceTextX, 0.25f+indiceTextY,
				0.1875f+indiceTextX, 0.28125f+indiceTextY,
				0.21875f+indiceTextX, 0.28125f+indiceTextY,

				//top
				0.21875f+indiceTextX, 0.28125f+indiceTextY,
				0.21875f+indiceTextX, 0.25f+indiceTextY,
				0.1875f+indiceTextX, 0.28125f+indiceTextY,
				0.1875f+indiceTextX, 0.28125f+indiceTextY,
				0.21875f+indiceTextX, 0.25f+indiceTextY,
				0.1875f+indiceTextX, 0.25f+indiceTextY,

				//bottom
				0.1875f+indiceTextX, 0.25f+indiceTextY,
				0.1875f+indiceTextX, 0.28125f+indiceTextY,
				0.21875f+indiceTextX, 0.25f+indiceTextY,
				0.21875f+indiceTextX, 0.25f+indiceTextY,
				0.1875f+indiceTextX, 0.28125f+indiceTextY,
				0.21875f+indiceTextX, 0.28125f+indiceTextY,

				0.21875f+indiceTextX, 0.28125f+indiceTextY,
				0.21875f+indiceTextX, 0.25f+indiceTextY,
				0.1875f+indiceTextX, 0.28125f+indiceTextY,
				0.1875f+indiceTextX, 0.28125f+indiceTextY,
				0.21875f+indiceTextX, 0.25f+indiceTextY,
				0.1875f+indiceTextX, 0.25f+indiceTextY,

				0.1875f+indiceTextX, 0.28125f+indiceTextY,
				0.21875f+indiceTextX, 0.25f+indiceTextY,
				0.1875f+indiceTextX, 0.25f+indiceTextY,
				0.21875f+indiceTextX, 0.28125f+indiceTextY,
				0.21875f+indiceTextX, 0.25f+indiceTextY,
				0.1875f+indiceTextX, 0.28125f+indiceTextY

		};
	}

}
