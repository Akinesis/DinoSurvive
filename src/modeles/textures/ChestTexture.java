package modeles.textures;

public class ChestTexture implements TexturesGenerator {

	@Override
	public float[] genTexture(float indiceTextX, float indiceTextY) {
		return new float[]{
				//south
				0.375f, 0.03125f,
				0.375f, 0.0f,
				0.34375f, 0.03125f,
				0.34375f, 0.03125f,
				0.375f, 0.0f,
				0.34375f, 0.0f,

				//face sud inverser
				0.34375f, 0.0f,
				0.375f, 0.0f,
				0.375f, 0.03125f,
				0.34375f, 0.0f,
				0.375f, 0.03125f,
				0.34375f, 0.03125f,

				//top
				0.34375f, 0.0625f,
				0.34375f, 0.03125f,
				0.3125f, 0.0625f,
				0.3125f, 0.0625f,
				0.34375f, 0.03125f,
				0.3125f, 0.03125f,

				//bottom
				0.3125f, 0.03125f,
				0.3125f, 0.0625f,
				0.34375f, 0.03125f,
				0.34375f, 0.03125f,
				0.3125f, 0.0625f,
				0.34375f, 0.0625f,

				0.34375f, 0.03125f,
				0.34375f, 0.0f,
				0.3125f, 0.03125f,
				0.3125f, 0.03125f,
				0.34375f, 0.0f,
				0.3125f, 0.0f,

				0.34375f, 0.0625f,
				0.375f, 0.03125f,
				0.34375f, 0.03125f,
				0.375f, 0.0625f,
				0.375f, 0.03125f,
				0.34375f, 0.0625f
		};
	}

}
