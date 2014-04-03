package modeles;

import modeles.entities.Cube3dVbo;
import controleur.Controleur;

public class TerrainGenerator {

	private Controleur clone;

	public TerrainGenerator(Controleur contr){
		clone = contr;
	}

	public void genereTerre(int x, int y, int z){
		Chunk temp = clone.getChunkManager().getChunk(x,y,z);
		int originX = x*16+((x>0)?-1:0);
		int originY = y*16;
		int originZ = z*16+((x>0)?-1:0);

		for(int i = originX; i>originX-16; --i){
			for(int j = originY; j>originY-16; --j){
				for(int k = originZ; k>originZ-16; --k){
					temp.addCube3dVbo(new Cube3dVbo(i, -j, k, 1, 2));
				}
			}
		}
		clone.getChunkManager().addChunk(temp);
	}

	/**
	 * Fonction permettant la generation d'un sol d'herbe, tout beau tout propre.
	 */
	public void genFond(int x, int y, int z){
		Chunk temp = clone.getChunkManager().getChunk(x,y,z);
		int originX = x*16;
		int originY = y*16;
		int originZ = z*16;

		for(int i = originX; i>originX-16; --i){
			for(int j = originZ; j>originZ-16; --j){
				temp.addCube3dVbo(new Cube3dVbo(i, originY, j, 1, 1));
			}
		}
	}
}
