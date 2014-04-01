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
		//terre.genTerre();
		int originX = (temp.getX()*16);
		int originY = (temp.getY()*16);
		int originZ = (temp.getZ()*16);

		for(int i = originX; i>originX-16; i--){
			for(int j = originY; j>originY-16; j--){
				for(int k = originZ; k>originZ-16; k--){
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
		int originX = (temp.getX()*16);
		int originY = (temp.getY()*16);
		int originZ = (temp.getZ()*16);

		for(int i = originX; i>originX-16; i--){
			for(int j = originZ; j>originZ-16; j--){
				temp.addCube3dVbo(new Cube3dVbo(i, originY, j, 1, 1));
			}
		}
	}
}
