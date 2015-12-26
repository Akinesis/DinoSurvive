package modeles.textures;

public class RubyTexture implements TexturesGenerator {

	@Override
	public float[] genTexture(float indiceTextX, float indiceTextY) {
		indiceTextX *= 0.03125f;
		indiceTextY *= 0.03125f;

		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = indiceTextY-(indiceTextY%0.001953125f);
		return new float[]{
				//south
				0.03125f+indiceTextX, 0.34375f+indiceTextY,
				0.03125f+indiceTextX, 0.3125f+indiceTextY,
				0.0f+indiceTextX, 0.34375f+indiceTextY,
				0.0f+indiceTextX, 0.34375f+indiceTextY,
				0.03125f+indiceTextX, 0.3125f+indiceTextY,
				0.0f+indiceTextX, 0.3125f+indiceTextY,

				0.03125f+indiceTextX, 0.3125f+indiceTextY,
				0.0f+indiceTextX, 0.3125f+indiceTextY,
				0.0f+indiceTextX, 0.34375f+indiceTextY,
				0.03125f+indiceTextX, 0.3125f+indiceTextY,
				0.0f+indiceTextX, 0.34375f+indiceTextY,
				0.03125f+indiceTextX, 0.34375f+indiceTextY,

				//top
				0.03125f+indiceTextX, 0.34375f+indiceTextY,
				0.03125f+indiceTextX, 0.3125f+indiceTextY,
				0.0f+indiceTextX, 0.34375f+indiceTextY,
				0.0f+indiceTextX, 0.34375f+indiceTextY,
				0.03125f+indiceTextX, 0.3125f+indiceTextY,
				0.0f+indiceTextX, 0.3125f+indiceTextY,

				//bottom
				0.0f+indiceTextX, 0.3125f+indiceTextY,
				0.0f+indiceTextX, 0.34375f+indiceTextY,
				0.03125f+indiceTextX, 0.3125f+indiceTextY,
				0.03125f+indiceTextX, 0.3125f+indiceTextY,
				0.0f+indiceTextX, 0.34375f+indiceTextY,
				0.03125f+indiceTextX, 0.34375f+indiceTextY,

				0.03125f+indiceTextX, 0.34375f+indiceTextY,
				0.03125f+indiceTextX, 0.3125f+indiceTextY,
				0.0f+indiceTextX, 0.34375f+indiceTextY,
				0.0f+indiceTextX, 0.34375f+indiceTextY,
				0.03125f+indiceTextX, 0.3125f+indiceTextY,
				0.0f+indiceTextX, 0.3125f+indiceTextY,

				0.0f+indiceTextX, 0.34375f+indiceTextY,
				0.03125f+indiceTextX, 0.3125f+indiceTextY,
				0.0f+indiceTextX, 0.3125f+indiceTextY,
				0.03125f+indiceTextX, 0.34375f+indiceTextY,
				0.03125f+indiceTextX, 0.3125f+indiceTextY,
				0.0f+indiceTextX, 0.34375f+indiceTextY,
		};
	}

}
