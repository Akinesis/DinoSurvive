package modeles;

import controleur.Controleur;

public class TerrainGenerator {
	
	private Controleur clone;
	
	public TerrainGenerator(Controleur contr){
		clone = contr;
	}
	
	public void genereTerre(int x, int y, int z){
		Chunk terre = new Chunk(x, y, z, 2, clone);
		terre.genTerre();
		clone.getChunkManager().addChunk(terre);
	}
}
