package modeles;

import java.util.Vector;

import controleur.Controleur;
import modeles.entities.Cube3dVbo;



public class ChunkManager {
	private Vector<Chunk> chunks, renderChunks,chunksToRender;
	private Controleur clone;

	/*
	 * Constructeur
	 */
	public ChunkManager(Controleur contr){
		chunks = new Vector<Chunk>();
		renderChunks = new Vector<Chunk>();
		chunksToRender = new Vector<Chunk>();
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
	public void addCubeAt(float x, float y, float z, int typ){
		float xChunk = (float)Math.ceil(x / 16);
		float yChunk = (float)Math.ceil(y / 16);
		float zChunk = (float)Math.ceil(z / 16);
		
		x = (int)Math.abs(Math.ceil(x))%16;
		y = (int)Math.abs(Math.ceil(y))%16;
		z = (int)Math.abs(Math.ceil(z))%16;
		
		Cube3dVbo temp = new Cube3dVbo(x, y, z, 1, typ);

		for(Chunk ck : chunks){
			if((ck.getX() == xChunk) && (ck.getY() == yChunk) && (ck.getZ() == zChunk)){
				ck.addCube3dVbo(temp);
			}
		}
	}

	public void delCubeAt(float x, float y, float z){
		float xChunk = (float)Math.ceil(x / 16);
		float yChunk = (float)Math.ceil(y / 16);
		float zChunk = (float)Math.ceil(z / 16);

		x = (int)Math.abs(Math.ceil(x))%16;
		y = (int)Math.abs(Math.ceil(y))%16;
		z = (int)Math.abs(Math.ceil(z))%16;

		for(Chunk ck : chunks){
			if( (ck.getX() == xChunk) && (ck.getY() == yChunk) && (ck.getZ() == zChunk) ){
				ck.delCube(x, y, z);
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
		if(!chunks.contains(chu)){
			chunks.add(chu);
		}
	}

	/**
	 * Vérifie l'état des cubes du chunk
	 */
	public void checkState(){
		for(Chunk chunk : chunks){
			chunk.checkState();
		}
	}

	public void updateStates(){
		for(Chunk chunk : chunks){
			//chunk.updateStates();
		}
	}

	public void update(){
		chunksToRender.addAll(getChunkToUpdate());
		Vector<Chunk> temp = new Vector<Chunk>();
		int i = 0;
		if(!(chunksToRender.isEmpty())){
			for(Chunk chunk : chunksToRender){
				if(i<6){
					chunk.update();
					temp.add(chunk);
					i++;
					chunk.haveBeenUpdated(true);
				}
			}
			chunksToRender.removeAll(temp);
		}
	}

	private Vector<Chunk> getChunkToUpdate(){
		Vector<Chunk> temp = new Vector<Chunk>();
		for(Chunk chunk : renderChunks){
			if(!chunk.isUpdated()){
				temp.add(chunk);
			}
		}
		return temp;
	}

	/**
	 * Dessine les chunks
	 * @param textMan
	 */
	public void drawChunks(TextureManager textMan){
		for(Chunk chunk : renderChunks){
			chunk.draw(textMan);

		}
	}

	/**
	 * Initialise les chunks
	 */
	public void initChunks(){
		checkRender();
		for(Chunk chunk : renderChunks){
			chunk.addCubes();
			//chunk.updateStates();
			chunk.checkState();
			chunk.genCubes(clone.getTexManager());
			chunk.genVBO();
		}
	}

	public void checkRender(){
		for(Chunk ck : chunks){
			if(!chunksurround(ck)&&ck.checkPos(clone.getCamera().getCurrentChunk())){
				renderChunks.add(ck);
			}
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

	public float[] getChunkAt(float x, float y, float z){

		float[] temp = {-1,-1,-1};

		float xChunk = (float)Math.ceil(x / 16);
		float yChunk = (float)Math.ceil(y / 16);
		float zChunk = (float)Math.ceil(z / 16);

		for(Chunk ck : chunks){
			if( (ck.getX() == xChunk) && (ck.getY() == yChunk) && (ck.getZ() == zChunk) ){
				temp[0]=ck.getX();
				temp[1]=ck.getY();
				temp[2]=ck.getZ();
			}
		}

		return temp;

	}

	public boolean cubeExist(int x, int y, int z){
		float xChunk = (float)Math.ceil(x / 16);
		float yChunk = (float)Math.ceil(y / 16);
		float zChunk = (float)Math.ceil(z / 16);

		boolean temp = false;

		for(Chunk ck : chunks){
			if( (ck.getX() == xChunk) && (ck.getY() == yChunk) && (ck.getZ() == zChunk) ){
				temp = ck.getCube(x, y, z)!=null;
			}
		}
		return temp;
	}

	private boolean chunksurround(Chunk ck){

		int xCh = ck.getX();
		int yCh = ck.getY();
		int zCh = ck.getZ();

		boolean temp=true;

		temp = (chunkExist(xCh+1,yCh,zCh))?temp && completeFace(xCh+1,yCh,zCh, 5):false;
		temp = (chunkExist(xCh-1,yCh,zCh))?temp && completeFace(xCh-1,yCh,zCh, 6):false;

		temp = (chunkExist(xCh,yCh+1,zCh))?temp && completeFace(xCh,yCh+1,zCh, 4)|| yCh+1>0:false;
		temp = (chunkExist(xCh,yCh-1,zCh))?temp && completeFace(xCh,yCh-1,zCh, 3):false;

		temp = (chunkExist(xCh,yCh,zCh+1))?temp && completeFace(xCh,yCh,zCh+1, 1):false;
		temp = (chunkExist(xCh,yCh,zCh-1))?temp && completeFace(xCh,yCh,zCh-1, 2):false;


		return  temp;
	}

	public boolean chunkExist(int xCh, int yCh, int zCh){

		for(Chunk chun : chunks){
			if(chun.getX()==xCh && chun.getY()==yCh && chun.getZ()==zCh){
				return true;
			}
		}
		return false;
	}

	private boolean completeFace(int xCh, int yCh, int zCh, int face){
		int i = 1;
		int j = 1;

		switch(face){
		case 1:
			for(Chunk chun : chunks){
				if(chun.getX()==xCh && chun.getY()==yCh && chun.getZ()==zCh){
					for(i =0; i<15;i++){
						for(j=0; j<15; j++){
							if(chun.getCube(i, j, 0)==null){
								return false;
							}
						}
					}
				}
			}
			break;
		case 2:
			for(Chunk chun : chunks){
				if(chun.getX()==xCh && chun.getY()==yCh && chun.getZ()==zCh){
					for(i =0; i<15;i++){
						for(j=0; j<15; j++){
							if(chun.getCube(i, j, 15)==null){
								return false;
							}
						}
					}
				}
			}
			break;
		case 3:
			for(Chunk chun : chunks){
				if(chun.getX()==xCh && chun.getY()==yCh && chun.getZ()==zCh){
					for(i =0; i<15;i++){
						for(j=0; j<15; j++){
							if(chun.getCube(i, 0, j)==null){
								return false;
							}
						}
					}
				}
			}
			break;
		case 4:
			for(Chunk chun : chunks){
				if(chun.getX()==xCh && chun.getY()==yCh && chun.getZ()==zCh){
					for(i =0; i<15;i++){
						for(j=0; j<15; j++){
							if(chun.getCube(i, 15, j)==null){
								return false;
							}
						}
					}
				}
			}
			break;
		case 5:
			for(Chunk chun : chunks){
				if(chun.getX()==xCh && chun.getY()==yCh && chun.getZ()==zCh){
					for(i =0; i<15;i++){
						for(j=0; j<15; j++){
							if(chun.getCube(0, i, j)==null){
								return false;
							}
						}
					}
				}
			}
			break;
		case 6:
			for(Chunk chun : chunks){
				if(chun.getX()==xCh && chun.getY()==yCh && chun.getZ()==zCh){
					for(i =0; i<15;i++){
						for(j=0; j<15; j++){
							if(chun.getCube(15, i, j)==null){
								return false;
							}
						}
					}
				}
			}
			break;	
		}

		return true;
	}


}
