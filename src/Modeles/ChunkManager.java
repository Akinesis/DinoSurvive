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
