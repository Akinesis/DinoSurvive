package modeles;

import org.lwjgl.BufferUtils;

import modeles.entities.Cube3dVbo;
import controleur.Controleur;

public class TransparentChunk extends Chunk {

	public TransparentChunk(int x, int y, int z, int id, Controleur contr) {
		super(x, y, z, id, contr);
	}

	public void addTransparent(Cube3dVbo transp){
		renderCubes.add(transp);
	}

	/**
	 * Methode générant les cubes dans le buffer
	 * 	DOIT ETRE ASSOCIEE A UNE METHODE DE RESET DES BUFFER !!
	 */
	public void genCubes(TextureManager texMan){
		float cubeCoord[],texCoord[];
		int j= 0;
		interleavedBuffer = BufferUtils.createFloatBuffer(renderCubes.size()*(6*3+6*2)*6);
		for(Cube3dVbo cube : renderCubes){
			cubeCoord=cube.genCubes();
			texCoord=texMan.genText(cube.getType(), cube.getTextX(), cube.getTextY());
			for(int i = 0; i< cubeCoord.length; i+=3){
				interleavedBuffer.put(cubeCoord[i]);
				interleavedBuffer.put(cubeCoord[i+1]);
				interleavedBuffer.put(cubeCoord[i+2]);

				interleavedBuffer.put(texCoord[j]);
				interleavedBuffer.put(texCoord[j+1]);

				j+=2;
			}
			j=0;
		}

		interleavedBuffer.flip();
	}

}
