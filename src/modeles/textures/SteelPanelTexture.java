package modeles.textures;

public class SteelPanelTexture implements TexturesGenerator {

	@Override
	public float[] genTexture(float indiceTextX, float indiceTextY) {
		return new float[]{
				//south
				0.3125f, 0.15625f,
				0.3125f, 0.125f,
				0.28125f, 0.15625f,
				0.28125f, 0.15625f,
				0.3125f, 0.125f,
				0.28125f, 0.125f,

				0.3125f, 0.125f,
				0.28125f, 0.125f,
				0.28125f, 0.15625f,
				0.3125f, 0.125f,
				0.28125f, 0.15625f,
				0.3125f, 0.15625f,

				//top
				0.3125f, 0.15625f,
				0.3125f, 0.125f,
				0.28125f, 0.15625f,
				0.28125f, 0.15625f,
				0.3125f, 0.125f,
				0.28125f, 0.125f,

				//bottom
				0.28125f, 0.125f,
				0.28125f, 0.15625f,
				0.3125f, 0.125f,
				0.3125f, 0.125f,
				0.28125f, 0.15625f,
				0.3125f, 0.15625f,

				0.3125f, 0.15625f,
				0.3125f, 0.125f,
				0.28125f, 0.15625f,
				0.28125f, 0.15625f,
				0.3125f, 0.125f,
				0.28125f, 0.125f,

				0.28125f, 0.15625f,
				0.3125f, 0.125f,
				0.28125f, 0.125f,
				0.3125f, 0.15625f,
				0.3125f, 0.125f,
				0.28125f, 0.15625f
		};

	}

}
