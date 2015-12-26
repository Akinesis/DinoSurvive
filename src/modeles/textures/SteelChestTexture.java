package modeles.textures;

public class SteelChestTexture implements TexturesGenerator {

	@Override
	public float[] genTexture(float indiceTextX, float indiceTextY) {
		return new float[]{
				//south
				0.375f, 0.09375f,
				0.375f, 0.0625f,
				0.34375f, 0.09375f,
				0.34375f, 0.09375f,
				0.375f, 0.0625f,
				0.34375f, 0.0625f,

				//face sud inverser
				0.34375f, 0.0625f,
				0.375f, 0.0625f,
				0.375f, 0.09375f,
				0.34375f, 0.0625f,
				0.375f, 0.09375f,
				0.34375f, 0.09375f,

				//top
				0.34375f, 0.125f,
				0.34375f, 0.09375f,
				0.3125f, 0.125f,
				0.3125f, 0.125f,
				0.34375f, 0.09375f,
				0.3125f, 0.09375f,

				//bottom
				0.3125f, 0.09375f,
				0.3125f, 0.125f,
				0.34375f, 0.09375f,
				0.34375f, 0.09375f,
				0.3125f, 0.125f,
				0.34375f, 0.125f,

				0.34375f, 0.09375f,
				0.34375f, 0.0625f,
				0.3125f, 0.09375f,
				0.3125f, 0.09375f,
				0.34375f, 0.0625f,
				0.3125f, 0.0625f,

				0.34375f, 0.125f,
				0.375f, 0.09375f,
				0.34375f, 0.09375f,
				0.375f, 0.125f,
				0.375f, 0.09375f,
				0.34375f, 0.125f
		};
	}

}
