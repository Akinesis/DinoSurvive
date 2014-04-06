package modeles;

import java.util.Vector;

import controleur.Controleur;
import modeles.entities.Cube3dVbo;



public class ChunkManager {
	private Vector<Chunk> chunks;
	private Controleur clone;
	
	/*
	 * Constructeur
	 */
	public ChunkManager(Controleur contr){
		chunks = new Vector<Chunk>();
		clone = contr;
	}
	
	/*
	 * Getters and setters
	 */
	
	/**
	 * Renvoie le cube à la position demandée
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public Cube3dVbo getCubeAt(float x, float y, float z){
		float xChunk = (float)Math.ceil(x / 16);
		float yChunk = (float)Math.ceil(y / 16);
		float zChunk = (float)Math.ceil(z / 16);
		
		Cube3dVbo cube = null;
   		for(Chunk ck : chunks){
			if( (ck.getX() == xChunk) && (ck.getY() == yChunk) && (ck.getZ() == zChunk) ){
				cube = ck.getCube(x, y, z);
			}
		}
		return cube;
	}
	
	//marche pas
	public void addCubeAt(Cube3dVbo cube){
		float xChunk = (float)Math.ceil(cube.getX() / 16);
		float yChunk = (float)Math.ceil(cube.getY() / 16);
		float zChunk = (float)Math.ceil(cube.getZ() / 16);
		
		for(Chunk ck : chunks){
			if((ck.getX() == xChunk) && (ck.getY() == yChunk) && (ck.getZ() == zChunk)){
				ck.addCube3dVbo(cube);
			}
		}
	}
	
	
	public void setChunksList(Vector<Chunk> chun){
		chunks = chun;
	}
	
	/**
	 * Ajoute un chunk dans la liste de chunks
	 * @param chu
	 */
	public void addChunk(Chunk chu){
		chunks.add(chu);
	}

	/**
	 * Vérifie l'état des chunks
	 */
	public void checkState(){
		for(Chunk chunk : chunks){
			chunk.checkState();
		}
	}
	
	public void update(){
		for(Chunk chunk : chunks){
			chunk.update();
		}
	}
	
	/**
	 * Dessine les chunks
	 * @param textMan
	 */
	public void drawChunks(TextureManager textMan){
		for(Chunk chunk : chunks){
			//chunk.checkState();
			chunk.genVBO();
			chunk.draw(textMan);
			chunk.delCubes();
		}
	}

	/**
	 * Initialise les chunks
	 */
	public void initChunks(){
		for(Chunk chunk : chunks){
			chunk.addCubes();
			chunk.update();
			chunk.checkState();

			chunk.genCubes();
		}
	}
	
	
	public void delCubes(){
		for (Chunk chunk : chunks){
			chunk.delCubes();
		}
	}
	
	public Chunk getChunk(int x, int y, int z){
		for(Chunk ck : chunks){
			if( (ck.getX() == x) && (ck.getY() == y) && (ck.getZ() == z) ){
				return ck;
			}
		}
		Chunk temp = new Chunk(x, y, z, chunks.size(), clone);
		chunks.add(temp);
		return temp;
	}


}
