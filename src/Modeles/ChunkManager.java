package Modeles;

import java.util.Vector;


import Modeles.entities.Cube3dVbo;

public class ChunkManager {
	private Vector<Chunk> chunks;

	public ChunkManager(){
		chunks = new Vector<Chunk>();
	}

	public void addChunk(Chunk chu){
		chunks.add(chu);
	}

	public void checkState(){
		for(Chunk chunk : chunks){
			chunk.checkState();
		}
	}
	
	public Vector<Chunk> getChunks() {
		return chunks;
	}
	
	public Cube3dVbo getCubeAt(int x, int y, int z){
		int xChunk = x % 16;
		int yChunk = y % 16;
		int zChunk = z % 16;
		Cube3dVbo cube = null;
		for(Chunk ck : chunks){
			if( (ck.getX() == xChunk) && (ck.getY() == yChunk) && (ck.getZ() == zChunk) ){
				cube = ck.getCubes(xChunk, yChunk, zChunk);
			}
		}
		return cube;
	}
	/*
	public boolean isEmptyAt(int x, int y, int z){
		return 
	}
	*/
	public void drawChunks(TextureManager textMan){
		for(Chunk chunk : chunks){
			chunk.draw(textMan);
		}
	}

	public void initChunks(){
		for(Chunk chunk : chunks){
			chunk.addCubes();
			chunk.checkState();

			chunk.genCubes();
			chunk.bindBuffers();
		}
	}
	
	public void setChunksList(Vector<Chunk> chun){
		chunks = chun;
	}

}
