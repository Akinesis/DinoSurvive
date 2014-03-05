package Modeles;

import java.util.Vector;

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
	
	public void drawChunks(){
		for(Chunk chunk : chunks){
			chunk.draw();
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
