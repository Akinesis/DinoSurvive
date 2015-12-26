package modeles.textures;

public class GrassTexture implements TexturesGenerator {

	@Override
	public float[] genTexture(float indiceTextX, float indiceTextY) {
		float xGrass = indiceTextX*0.03125f; //dessus
		float yGrass = indiceTextY*0.03125f;

		xGrass = xGrass-(xGrass%0.001953125f);
		yGrass = yGrass-(yGrass%0.001953125f);

		float xDirt = indiceTextX*0.03125f; //dessous
		float yDirt = indiceTextY*0.03125f;

		xDirt = xDirt-(xDirt%0.001953125f);
		yDirt = yDirt-(yDirt%0.001953125f);

		//indiceTextX = (int)(indiceTextX * ((1 - 0) + 1))*0.03125f;
		indiceTextX *= 0.03125f; //cotés
		indiceTextX = indiceTextX-(indiceTextX%0.001953125f);
		indiceTextY = (int)(indiceTextY * ((1 - 0) + 1))*0.03125f;

		return new float[]{
				//south
				0.03125f+indiceTextX, 0.03125f+indiceTextY,
				0.03125f+indiceTextX, 0+indiceTextY,
				0+indiceTextX, 0.03125f+indiceTextY,
				0+indiceTextX, 0.03125f+indiceTextY,
				0.03125f+indiceTextX, 0+indiceTextY,
				0+indiceTextX, 0+indiceTextY,

				0.03125f+indiceTextX, 0+indiceTextY,
				0+indiceTextX, 0+indiceTextY,
				0+indiceTextX, 0.03125f+indiceTextY,
				0.03125f+indiceTextX, 0+indiceTextY,
				0+indiceTextX, 0.03125f+indiceTextY,
				0.03125f+indiceTextX, 0.03125f+indiceTextY,

				//top
				0.03125f+xGrass, 0.09375f+yDirt,
				0.03125f+xGrass, 0.0625f+yDirt,
				0.0f+xGrass, 0.09375f+yDirt,
				0.0f+xGrass, 0.09375f+yDirt,
				0.03125f+xGrass, 0.0625f+yDirt,
				0.0f+xGrass, 0.0625f+yDirt,

				//bottom
				0.0625f+xDirt, 0.0f+indiceTextY,
				0.0625f+xDirt, 0.03125f+indiceTextY,
				0.09375f+xDirt, 0.0f+indiceTextY,
				0.09375f+xDirt, 0.0f+indiceTextY,
				0.0625f+xDirt, 0.03125f+indiceTextY,
				0.09375f+xDirt, 0.03125f+indiceTextY,

				0.03125f+indiceTextX, 0.03125f+indiceTextY,
				0.03125f+indiceTextX, 0+indiceTextY,
				0+indiceTextX, 0.03125f+indiceTextY,
				0+indiceTextX, 0.03125f+indiceTextY,
				0.03125f+indiceTextX, 0+indiceTextY,
				0+indiceTextX, 0+indiceTextY,

				0+indiceTextX, 0.03125f+indiceTextY,
				0.03125f+indiceTextX, 0+indiceTextY,
				0+indiceTextX, 0+indiceTextY,
				0.03125f+indiceTextX,0.03125f+indiceTextY,
				0.03125f+indiceTextX, 0+indiceTextY,
				0+indiceTextX, 0.03125f+indiceTextY

		};
	}

}
