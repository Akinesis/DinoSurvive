package modeles;

import java.util.ArrayList;
import java.util.Vector;

import org.lwjgl.util.vector.Vector3f;

import parametres.Parametres;
import sun.util.calendar.ZoneInfo;
import controleur.Controleur;
import modeles.entities.Cube3dVbo;



public class ChunkManager implements Parametres {
	private Vector<Chunk> chunks, renderChunks,chunksToRender, chunksToLoad, chunksToCheck;
	private Controleur clone;
	private TransparentChunk transparancy;

	/*
	 * Constructeur
	 */
	public ChunkManager(Controleur contr){
		chunks = new Vector<Chunk>();
		renderChunks = new Vector<Chunk>();
		chunksToRender = new Vector<Chunk>();
		chunksToLoad = new Vector<Chunk>();
		chunksToCheck = new Vector<Chunk>();
		clone = contr;
		transparancy = new TransparentChunk(1, 1, 1, -1, clone);
	}


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
				ck.delCube((int)x, (int)y, (int)z);
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
	 * Ajoute un chunk dans la liste de chunks à charger
	 * @param chu
	 */
	public void addChunkToLoad(Chunk chu){
		if(!chunksToLoad.contains(chu)){
			chunksToLoad.add(chu);
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

	public void updateAt(float x, float y, float z){
		chunksToRender.addAll(getChunkToUpdate());
		Vector<Chunk> temp = new Vector<Chunk>();
		int i = 0;
		if(!(chunksToRender.isEmpty())){
			for(Chunk chunk : chunksToRender){
				if(i<6){
					chunk.updateAt(x, y, z);
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
		transparancy.unbindVbo();
		transparancy.genCubes(textMan);
		transparancy.genVBO();
		transparancy.draw(textMan);
	}

	/**
	 * Initialise les chunks
	 */
	public void initChunks(){
		clearRender();
		checkRender();
		for(Chunk chunk : renderChunks){
			chunk.addCubes();
			chunk.checkState();
			chunk.genCubes(clone.getTexManager());
			chunk.genVBO();
		}
	}

	public void reloadChunks(){
		int i =0;
		ArrayList<Chunk> temp = new ArrayList<Chunk>();
		for(Chunk chunk : chunksToLoad){
			if(i<2){
				chunk.unbindVbo();
				if(!chunk.getChecked() && !chunksToCheck.contains(chunk)){
					chunk.checkState();
				}
				chunk.genCubes(clone.getTexManager());
				chunk.genVBO();
				temp.add(chunk);
				i++;
			}
		}
		chunksToLoad.removeAll(temp);
	}

	public void checkAllStates(){
	}

	public void checkRender(){
		clearRender();
		for(Chunk ck : chunks){
			if(!chunksurround(ck) && ck.checkPos(clone.getCamera().getCurrentChunk())){
				renderChunks.add(ck);
			}
		}
	}

	private void clearRender(){
		renderChunks.clear();
	}


	public void unbindAll(){
		for(Chunk ck : chunks){
			ck.unbindVbo();
		}
	}


	/**
	 * Trouve le plus "haut" chunk, génère les cubes puis luis demande sont plus haut point.
	 * @param x Coordonée X de la caméra
	 * @param z Coordonée Z de la caméra
	 * @return Le plus haut Y du Chunk le plus haut.
	 */
	public float getHigherPointAt(int x, int z){
		int higherY = 0;
		Chunk temp=null;
		float xChunk = (float)Math.ceil(x / 16);
		float zChunk = (float)Math.ceil(z / 16);

		for(Chunk ck : chunks){
			if( (ck.getX() == xChunk) && (ck.getZ() == zChunk) && ck.getY()<higherY){
				higherY = ck.getY();
				temp = ck;
			}
		}

		temp.addCubes();
		temp.checkState();
		return temp.getHigher();

	}

	public Chunk getHigherChunk(){

		int higherY = 0;
		Chunk temp=null;

		for(Chunk ck : chunks){
			if(ck.getY()<higherY){
				higherY = ck.getY();
				temp = ck;
			}
		}

		return temp;
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

	/**
	 * Fonction initiale de récursive. Vérifie tout les chunk entre le joueur et le chunk à vérifier.
	 * @param xMove Facteur de déplacement X
	 * @param zMove Facteur de déplacement Y
	 */
	public void createChunks(int xMove, int zMove){
		Vector3f pos = clone.getCamera().getCurrentChunk();
		if(Math.abs(xMove)<Math.abs(zMove)){
			for(int i=1; i<=Math.abs(zMove) ; i++){
				if(chunkExist((int)pos.x+xMove, (int)pos.y+1, (int)pos.z+(int)(Math.signum(zMove)*i))){
					createChunksRec(xMove, (int)(Math.signum(zMove)*i), 0, 0);
				}else{
					clone.getTerrainGenerator().genereTerre((int)pos.x+xMove, (int)pos.y+1, (int)pos.z+(int)(Math.signum(zMove)*i));
					createChunksRec(xMove, (int)(Math.signum(zMove)*i), 0, 0);
				}
			}
		}else{
			for(int i=1; i<=Math.abs(xMove) ; i++){
				if(chunkExist((int)pos.x+(int)(Math.signum(xMove)*i), (int)pos.y+1, (int)pos.z+zMove)){
					createChunksRec((int)(Math.signum(xMove)*i), zMove, 0, 0);
				}else{
					clone.getTerrainGenerator().genereTerre((int)pos.x+(int)(Math.signum(xMove)*i), (int)pos.y+1, (int)pos.z+zMove);
					createChunksRec((int)(Math.signum(xMove)*i), zMove, 0, 0);
				}
			}
		}
	}

	/**
	 * Fonction recursive de création de chunks en marche
	 * @param xMove
	 * @param zMove
	 * @param nbInstance
	 * @param dir indice de direction : 1_x+ 2_x- 3_y+ 4_y-
	 */
	private void createChunksRec(int xMove, int zMove, int nbInstance, int dir){
		Vector3f pos = clone.getCamera().getCurrentChunk();

		if(nbInstance<chunkFar){
			if(chunkExist((int)pos.x+xMove, (int)pos.y+1, (int)pos.z+zMove)){
				if(dir!=0){
					switch (dir) {
					case 1:
						createChunksRec(xMove+1, zMove, nbInstance+1, dir);
						break;
					case 2:
						createChunksRec(xMove-1, zMove, nbInstance+1, dir);
						break;
					case 3:
						createChunksRec(xMove, zMove+1, nbInstance+1, dir);
						break;
					case 4:
						createChunksRec(xMove, zMove-1, nbInstance+1, dir);
						break;
					default:
						break;
					}
				}else{
					if(xMove!=0){
						System.out.println("4");
						createChunksRec(xMove, zMove+1, nbInstance, 3);
						createChunksRec(xMove, zMove-1, nbInstance, 4);
					}else{
						createChunksRec(xMove+1, zMove, nbInstance, 1);
						createChunksRec(xMove-1, zMove, nbInstance, 2);
					}
				}
			}else{
				clone.getTerrainGenerator().genereTerre((int)pos.x+xMove, (int)pos.y+1, (int)pos.z+zMove);
				createChunksRec(xMove, zMove, nbInstance+1, dir);
			}
		}
	}

	public void addTransparent(Cube3dVbo transp){
		transparancy.addTransparent(transp);
	}

	/**
	 * Renvoie vrais si toute une face est remplie.
	 * 
	 * @param xCh Les X du chunk
	 * @param yCh Les Y du chunk
	 * @param zCh Les Z du chunk
	 * @param face Indice de la face à analyser
	 * @return vrais si la face est pleine.
	 */
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
