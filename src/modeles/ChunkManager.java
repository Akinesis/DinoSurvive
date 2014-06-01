package modeles;

import java.util.Vector;

import controleur.Controleur;
import modeles.entities.Cube3dVbo;



public class ChunkManager {
	private Vector<Chunk> chunks, renderChunks;
	private Controleur clone;
	
	/*
	 * Constructeur
	 */
	public ChunkManager(Controleur contr){
		chunks = new Vector<Chunk>();
		renderChunks = new Vector<Chunk>();
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
	 * Vérifie l'état des cubes du chunk
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
	
	public void updateVbo(){
		for(Chunk chunk : renderChunks){
			chunk.updateVbo();
		}
	}
	
	/**
	 * Dessine les chunks
	 * @param textMan
	 */
	public void drawChunks(TextureManager textMan){
		for(Chunk chunk : renderChunks){
			chunk.draw(textMan);
			chunk.delCubes();
		}
	}

	/**
	 * Initialise les chunks
	 */
	public void initChunks(){
		checkRender();
		for(Chunk chunk : renderChunks){
			chunk.addCubes();
			chunk.update();
			chunk.checkState();
			chunk.genCubes(clone.getTexManager());
			chunk.genVBO();
		}
	}
	
	private void checkRender(){
		for(Chunk ck : chunks){
			if(!chunksurround(ck)){
				renderChunks.add(ck);
			}
		}
	}
	
	
	public void delCubes(){
		for (Chunk chunk : chunks){
			chunk.delCubes();
		}
	}
	
	public void unbindAll(){
		for(Chunk ck : chunks){
			ck.unbindVbo();
		}
	}
	
	public int getHigherPointAt(int x, int z){
		int higherY = 0;
		float xChunk = (float)Math.ceil(x / 16);
		float zChunk = (float)Math.ceil(z / 16);
		
		for(Chunk ck : chunks){
			if( (ck.getX() == xChunk) && (ck.getZ() == zChunk) && ck.getY()<higherY){
				higherY = ck.getY();
			}
		}
		
		return higherY;
		
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
	
	private boolean chunksurround(Chunk ck){
		
		int xCh = ck.getX();
		int yCh = ck.getY();
		int zCh = ck.getZ();
		
		boolean temp=true;

		temp = temp && chunkExist(xCh+1,yCh,zCh);
		temp = temp && chunkExist(xCh-1,yCh,zCh);
		
		temp = temp && chunkExist(xCh,yCh-1,zCh) || yCh+1>0;
		temp = temp && chunkExist(xCh,yCh+1,zCh);
		
		temp = temp && chunkExist(xCh,yCh,zCh+1);
		temp = temp && chunkExist(xCh,yCh,zCh-1);
		

		return  temp;
	}
	
	private boolean chunkExist(int xCh, int yCh, int zCh){

		boolean temp = false;
		
		for(Chunk chun : chunks){
			if(chun.getX()==xCh && chun.getY()==yCh && chun.getZ()==zCh){
				temp=true;
			}
		}
		return temp;
	}


}
