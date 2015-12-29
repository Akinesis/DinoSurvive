package modeles.textures;

public class NailedSteelTexture implements TexturesGenerator {

	@Override
	public float[] genTexture(float indiceTextX, float indiceTextY) {
		indiceTextX = (int)(indiceTextX * ((1 - 0) + 1))*0.03125f;
		indiceTextY = (int)(indiceTextY * ((1 - 0) + 1))*0.03125f;

		return new float[]{
				//north
				0.34375f+indiceTextX, 0.21875f+indiceTextY,
				0.34375f+indiceTextX, 0.1875f+indiceTextY,
				0.3125f+indiceTextX, 0.21875f+indiceTextY,
				0.3125f+indiceTextX, 0.21875f+indiceTextY,
				0.34375f+indiceTextX, 0.1875f+indiceTextY,
				0.3125f+indiceTextX, 0.1875f+indiceTextY,

				0.34375f+indiceTextX, 0.1875f+indiceTextY,
				0.3125f+indiceTextX, 0.1875f+indiceTextY,
				0.3125f+indiceTextX, 0.21875f+indiceTextY,
				0.34375f+indiceTextX, 0.1875f+indiceTextY,
				0.3125f+indiceTextX, 0.21875f+indiceTextY,
				0.34375f+indiceTextX, 0.21875f+indiceTextY,

				//top
				0.34375f+indiceTextX, 0.21875f+indiceTextY,
				0.34375f+indiceTextX, 0.1875f+indiceTextY,
				0.3125f+indiceTextX, 0.21875f+indiceTextY,
				0.3125f+indiceTextX, 0.21875f+indiceTextY,
				0.34375f+indiceTextX, 0.1875f+indiceTextY,
				0.3125f+indiceTextX, 0.1875f+indiceTextY,

				//bottom
				0.3125f+indiceTextX, 0.1875f+indiceTextY,
				0.3125f+indiceTextX, 0.21875f+indiceTextY,
				0.34375f+indiceTextX, 0.1875f+indiceTextY,
				0.34375f+indiceTextX, 0.1875f+indiceTextY,
				0.3125f+indiceTextX, 0.21875f+indiceTextY,
				0.34375f+indiceTextX, 0.21875f+indiceTextY,

				0.34375f+indiceTextX, 0.21875f+indiceTextY,
				0.34375f+indiceTextX, 0.1875f+indiceTextY,
				0.3125f+indiceTextX, 0.21875f+indiceTextY,
				0.3125f+indiceTextX, 0.21875f+indiceTextY,
				0.34375f+indiceTextX, 0.1875f+indiceTextY,
				0.3125f+indiceTextX, 0.1875f+indiceTextY,

				0.3125f+indiceTextX, 0.21875f+indiceTextY,
				0.34375f+indiceTextX, 0.1875f+indiceTextY,
				0.3125f+indiceTextX, 0.1875f+indiceTextY,
				0.34375f+indiceTextX, 0.21875f+indiceTextY,
				0.34375f+indiceTextX, 0.1875f+indiceTextY,
				0.3125f+indiceTextX, 0.21875f+indiceTextY

		};
	}

}
