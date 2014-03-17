package Modeles;

import java.util.Vector;

import Modeles.entities.Cube3dVbo;


public class ChunkManager {
	private Vector<Chunk> chunks;
	
	/*
	 * Constructeur
	 */
	public ChunkManager(){
		chunks = new Vector<Chunk>();
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
		float xChunk = (x>0)?(float)Math.floor(x / 16):(float)Math.ceil(x / 16);
		float yChunk = (y>0)?(float)Math.floor(y / 16):(float)Math.ceil(y / 16);
		float zChunk = (z>0)?(float)Math.floor(z / 16):(float)Math.ceil(z / 16);
		
		
		Cube3dVbo cube = null;
		for(Chunk ck : chunks){
			if( (ck.getX() == xChunk) && (ck.getY() == yChunk) && (ck.getZ() == zChunk) ){
				cube = ck.getCube(x, y, z);
			}
		}
		return cube;
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
	/**
	 * Dessine les chunks
	 * @param textMan
	 */
	public void drawChunks(TextureManager textMan){
		for(Chunk chunk : chunks){
			chunk.draw(textMan);
		}
	}

	/**
	 * Initialise les chunks
	 */
	public void initChunks(){
		for(Chunk chunk : chunks){
			chunk.addCubes();
			chunk.checkState();

			chunk.genCubes();
			chunk.bindBuffers();
		}
	}


}
