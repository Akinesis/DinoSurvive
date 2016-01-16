package modeles;

import java.util.ArrayList;
import java.util.Vector;

import org.lwjgl.util.vector.Vector3f;

import parametres.Parametres;
import controleur.Controleur;
import modeles.entities.Cube3dVbo;

/**
 * Le gestionaire de chunk, il possède tout les chunk.
 * Tout tentative de communication avec un chunk passe par cette classe.
 * @author joachim
 *
 */

public class ChunkManagerHash implements Parametres {
	private Vector<Chunk>  renderChunks, chunksToLoad,
	chunksToCreate;
	private Maillon [] chunksHash;
	private Controleur clone;
	private TransparentChunk transparancy;

	/**
	 * Initialise les chunks et la table des chunks
	 * @param contr le controleur
	 */
	public ChunkManagerHash(Controleur contr) {
		//chunks = new Vector<Chunk>();
		chunksHash = new Maillon[100];
		renderChunks = new Vector<Chunk>();
		chunksToLoad = new Vector<Chunk>();
		chunksToCreate = new Vector<Chunk>();
		clone = contr;
		transparancy = new TransparentChunk(1, 1, 1, -1, clone);
		initHash();
	}

	/**
	 * Initialise les chunks pour le rendus
	 */
	public void initChunks(){
		//taille();
		clearRender();
		checkRender();
		for(Chunk chunk : renderChunks){
			chunk.addCubes();
			chunk.checkState();
			chunk.genCubes(clone.getTexManager());
			chunk.genVBO();
		}

		createLoadInit();
	}

	/**
	 * Renvoie le cube à la position demandée
	 * 
	 * @param x le x global du cube
	 * @param y le y global du cube
	 * @param z le z global du cube
	 * @return Le cube demander
	 */
	public Cube3dVbo getCubeAt(float x, float y, float z) {
		float xChunk = (float) Math.ceil(x / 16);
		float yChunk = (float) Math.ceil(y / 16);
		float zChunk = (float) Math.ceil(z / 16);

		Cube3dVbo cube = null;

		Chunk temp = chunksHash[hash((int)xChunk, (int)yChunk, (int)zChunk)].containts((int)xChunk, (int)yChunk, (int)zChunk);
		if(temp != null){
			return temp.getCubeCam(x, y, z);
		}

		return cube;
	}

	/**
	 * Renvoi les coordonées des 3 cubes du corps du joueur
	 * @param x Le X de la caméra
	 * @param y Le Y de la caméra
	 * @param z Le Z de la caméra
	 * @return
	 */
	public Cube3dVbo[] getBody(float x, float y, float z) {
		Cube3dVbo[] temp = new Cube3dVbo[3];

		temp[0] = getCubeAt(x,y,z);
		temp[1] = getCubeAt(x,y+1.1f,z);
		temp[2] = getCubeAt(x,y+2.1f,z);
		return temp;
	}

	/**
	 * Ajoute le cube au coordonées envoyées. 
	 * @param pos.x Les x du cube
	 * @param pos.y Les y du cube
	 * @param pos.z Les z du cube
	 */
	public void addCubeAt(Vector3f pos) {
		int xChunk = (int) Math.ceil(pos.x / 16);
		int yChunk = (int) Math.ceil(pos.y / 16);
		int zChunk = (int) Math.ceil(pos.z / 16);

		Chunk temp = chunksHash[hash(xChunk, yChunk, zChunk)].containts(xChunk, yChunk, zChunk);
		if(temp != null){
			temp.createCubeAt(pos.getX(), pos.getY(), pos.getZ());
		}else{
			temp = new Chunk(xChunk, yChunk, zChunk, makeID(xChunk, yChunk, zChunk), clone);
			chunksHash[hash(xChunk, yChunk, zChunk)].addChunkMaill(temp);
			renderChunks.add(temp);
			temp.createCubeAt(pos.getX(), pos.getY(), pos.getZ());
		}

	}

	/**
	 * Supprime le cube au coordonées envoyées. 
	 * @param pos.x Les x du cube
	 * @param pos.y Les y du cube
	 * @param pos.z Les z du cube
	 */
	public void delCubeAt(Vector3f pos) {
		float xChunk = (float) Math.ceil(pos.x / 16);
		float yChunk = (float) Math.ceil(pos.y / 16);
		float zChunk = (float) Math.ceil(pos.z / 16);

		Chunk temp = chunksHash[hash((int)xChunk, (int)yChunk, (int)zChunk)].containts((int)xChunk, (int)yChunk, (int)zChunk);
		if(temp != null){
			temp.delCube(pos.x, pos.y, pos.z);
		}
	}

	/**
	 * Ajoute tout les chunk de la map
	 * @param chun La liste de chunk de la map
	 */
	public void setChunksList(Vector<Chunk> chun) {
		//chunks = chun;
		for(Chunk ck : chun){
			chunksHash[hash(ck.getX(), ck.getY(), ck.getZ())].addChunkMaill(ck);
		}
	}

	/**
	 * Ajoute un chunk dans la liste de chunks
	 * 
	 * @param ck le chunk à ajouter
	 */
	public void addChunk(Chunk ck) {
		chunksHash[hash(ck.getX(), ck.getY(), ck.getZ())].addChunkMaill(ck);
	}

	/**
	 * Ajoute un chunk dans la liste de chunks à charger
	 * 
	 * @param chu le chunk à charger
	 */
	public void addChunkToLoad(Chunk chu) {
		if (!chunksToLoad.contains(chu)) {
			chunksToLoad.add(chu);
		}
	}

	/**
	 * Met à jour les cube autours d'un cube donnée
	 * @param pos la position du cube qui à été mis à jour.
	 */
	public void updateAt(Vector3f pos) {
		Vector<Chunk> chunksToRender = new Vector<Chunk>();
		chunksToRender.addAll(getChunkToUpdate());
		Vector<Chunk> temp = new Vector<Chunk>();
		int i = 0;
		if (!(chunksToRender.isEmpty())) {
			for (Chunk chunk : chunksToRender) {
				if (i < 6) {
					chunk.updateAt(pos.x, pos.y, pos.z);
					temp.add(chunk);
					i++;
					chunk.haveBeenUpdated(true);
				}
			}
			chunksToRender.removeAll(temp);
		}
		checkRender(chunksToRender);
	}

	/**
	 * Retrouve la liste des chunks à metre à jour.
	 * @return Les chunks non mis à jour.
	 */
	private Vector<Chunk> getChunkToUpdate() {
		Vector<Chunk> temp = new Vector<Chunk>();
		for (Chunk chunk : renderChunks) {
			if (!chunk.isUpdated()) {
				temp.add(chunk);
			}
		}
		return temp;
	}

	/**
	 * Dessine les chunks visible
	 * 
	 * @param textMan le TextureManager
	 */
	public void drawChunks(TextureManager textMan) {
		for (Chunk chunk : renderChunks) {
			chunk.draw(textMan);
		}
		transparancy.unbindVbo();
		transparancy.genCubes(textMan);
		transparancy.genVBO();
		transparancy.draw(textMan);
	}


	/**
	 * recharge les chunk qui on été mis à jours.
	 */
	public void reloadChunks(){
		int i =0;
		ArrayList<Chunk> temp = new ArrayList<Chunk>();
		for(Chunk chunk : chunksToLoad){
			if(i<2){
				chunk.unbindVbo();
				if (!chunk.getChecked()) {
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

	/**
	 * révérife tout les cube à déssiner
	 * si un cube est visible et a portée de vue, il faut le déssiner
	 */
	public void checkRender() {
		clearRender();
		for(int i = 0; i<100; i++){
			Vector<Chunk> temp = chunksHash[i].getListe();
			for (Chunk ck : temp) {
				if (ck.checkPos(clone.getCamera().getCurrentChunk())) {
					renderChunks.add(ck);
					if(!chunksToLoad.contains(ck)){
						chunksToLoad.add(ck);
					}
				}else{
					chunksToLoad.remove(ck);

				}
			}
		}
	}

	/**
	 * Vérifie les visibilitées des chunks proche du cube ayant été modifiés.
	 * @param liste les chunks ayant été modifiers
	 */
	private void checkRender(Vector<Chunk> liste){
		renderChunks.removeAll(liste);
		for(Chunk ck : liste){
			if (ck.checkPos(clone.getCamera().getCurrentChunk())){
				renderChunks.add(ck);
				if(!chunksToLoad.contains(ck)){
					chunksToLoad.add(ck);
				}
			}else{
				chunksToLoad.remove(ck);

			}
		}
	}

	/**
	 * suprime tout les chunks de la liste de rendu
	 */
	private void clearRender() {
		renderChunks.clear();
	}

	/**
	 * unbind tout les VBO de tout les chunks
	 */
	public void unbindAll(){
		for(int i = 0; i<100; i++){
			Vector<Chunk> temp = chunksHash[i].getListe();
			for (Chunk ck : temp) {
				ck.unbindVbo();
			}
		}
	}

	/**
	 * Trouve le plus haut cube à la position x,z
	 * Trouve en premier lieu le plsu haut chunk
	 * puis génére les cube via la map avant de récupérer le plus haut cube.
	 * 
	 * Cette méthode est appeler avant la génération des autres chunks.
	 * @param x coordonée X de la caméra
	 * @param z coordonée Z de la caméra
	 * @return Le plus haut Y du Chunk le plus haut.
	 */
	public float getHigherPointAt(int x, int z) {
		int higherY = 0;
		float xChunk = (float) Math.ceil(x / 16);
		float zChunk = (float) Math.ceil(z / 16);

		Chunk temp = null;
		Chunk test = null;

		for(int yChunk = 0; yChunk< 20; yChunk++){
			test = chunksHash[hash((int)xChunk, (int)yChunk, (int)zChunk)].containts((int)xChunk, -(int)yChunk, (int)zChunk);
			if(test != null){
				if(test.getY() < higherY){
					higherY = test.getY();
					temp = test;
				}
			}
		}
		return temp.getHigher();
	}

	/**
	 * Renvoi le chunck rechercher
	 * Si le chunk n'éxiste pas , le crée
	 * @param x les X du Chunk cherché
	 * @param y les Y du chunk cherché
	 * @param z Les Z du chunk cherché
	 * @return Le chunck rechercher
	 */
	public Chunk getChunk(int x, int y, int z) {

		Chunk temp = chunksHash[hash(x, y, z)].containts(x,y,z);
		if(temp != null){
			return temp;
		}

		temp = new Chunk(x, y, z, makeID(x, y, z), clone);
		chunksHash[hash(x, y, z)].addChunkMaill(temp);

		return temp;
	}

	/**
	 * Renvoi les coordonées du chunk à la position des coordonées en entrés.
	 * @param x Les X de la caméra
	 * @param y Les Y de la caméra
	 * @param z Les Z de la caméra
	 * @return Les coordonée du chunk
	 */
	public int[] getChunkAt(float x, float y, float z) {
		int[] temp = { -1, -1, -1 };
		int xChunk = (int) Math.ceil(x / 16);
		int yChunk = (int) Math.ceil(y / 16);
		int zChunk = (int) Math.ceil(z / 16);

		Chunk ck = chunksHash[hash(xChunk, yChunk, zChunk)].containts(xChunk, yChunk, zChunk);

		temp[0] = ck.getX();
		temp[1] = ck.getY();
		temp[2] = ck.getZ();

		return temp;
	}

	/**
	 * Trouve si le cube voulu existe
	 * @param x Les X du cubes à vérifier
	 * @param y Les Y du cubes à vérifier
	 * @param z Les Z du cubes à vérifier
	 * @return vrais si le cube existe, faux sinon
	 */
	public boolean cubeExist(int x, int y, int z) {
		float xChunk = (float) Math.ceil((float)(x*-1) / 16);
		float yChunk = (float) Math.ceil((float)(y*-1) / 16);
		float zChunk = (float) Math.ceil((float)(z*-1) / 16);

		Chunk ck = chunksHash[hash((int)xChunk, (int)yChunk, (int)zChunk)].containts((int)xChunk, (int)yChunk, (int)zChunk);

		if(ck != null){
			return ck.getCubeDraw(x, y, z) != null;
		}
		return false;
	}

	/**
	 * Determine si un Chunk est présent
	 * @param xCh Les X du chunk chercher
	 * @param yCh Les Y du chunk chercher
	 * @param zCh Les Z du chunk chercher
	 * @return Vrais si le chunk est trouver
	 */
	public boolean chunkExist(int xCh, int yCh, int zCh) {
		return chunksHash[hash(xCh, yCh, zCh)].containts(xCh, yCh, zCh) != null;
	}

	/**
	 * Charge une première fois tout les chuks et crée ceux qu'il faut créé
	 */
	private void createLoadInit(){

		for(Chunk chunk : chunksToLoad){
			chunk.unbindVbo();
			if(!chunk.getChecked()){
				chunk.checkState();
			}
			chunk.genCubes(clone.getTexManager());
			chunk.genVBO();
		}
		chunksToLoad.clear();

		for(Chunk ck : chunksToCreate){
			clone.getTerrainGenerator().genereTerre(ck.getX(), ck.getY(), ck.getZ());
		}
		chunksToCreate.clear();
	}
	
	/**
	 * Fonction initiale de récursive. Vérifie tout les chunks
	 * entre le joueur et le chunk à vérifier.
	 * 
	 * @param xMove Facteur de déplacement X
	 * @param zMove Facteur de déplacement Y
	 */
	public void createChunksInit(int xMove, int zMove){
		Vector3f pos = clone.getCamera().getCurrentChunk();

		if(Math.abs(xMove)<Math.abs(zMove)){
			for(int i=1; i<=Math.abs(zMove) ; i++){
				if(chunkExist((int)pos.x+xMove, (int)pos.y+1, (int)pos.z+(int)(Math.signum(zMove)*i))){
					createChunksRec(xMove, (int)(Math.signum(zMove)*i), 0, 0);
				}else{
					addChunnksToCreate((int)pos.x+xMove, (int)pos.y+1, (int)pos.z+(int)(Math.signum(zMove)*i));
					createChunksRec(xMove, (int)(Math.signum(zMove)*i), 0, 0);
				}
			}
		} else {
			for (int i = 1; i <= Math.abs(xMove); i++) {
				if (chunkExist((int) pos.x + (int) (Math.signum(xMove) * i), (int) pos.y + 1, (int) pos.z + zMove)) {
					createChunksRec((int) (Math.signum(xMove) * i), zMove, 0, 0);
				} else {
					addChunnksToCreate((int) pos.x + xMove, (int) pos.y + 1, (int) pos.z + (int) (Math.signum(zMove) * i));
					createChunksRec((int) (Math.signum(xMove) * i), zMove, 0, 0);
				}
			}
		}
	}

	/**
	 * Fonction recursive de création de chunks en marche
	 * 
	 * @param xMove Facteur de déplacement X
	 * @param zMove Facteur de déplacement Y
	 * @param nbInstance le nombre de chunk générer dans cette direction
	 * @param dir indice de direction : 1->x+ 2->x- 3->y+ 4->y-
	 */
	private void createChunksRec(int xMove, int zMove, int nbInstance, int dir) {
		Vector3f pos = clone.getCamera().getCurrentChunk();
		if (nbInstance < chunkFar) {
			if (chunkExist((int) pos.x + xMove, (int) pos.y + 1, (int) pos.z+ zMove)) {
				if (dir != 0) {
					switch (dir) {
					case 1:
						createChunksRec(xMove + 1, zMove, nbInstance + 1, dir);
						break;
					case 2:
						createChunksRec(xMove - 1, zMove, nbInstance + 1, dir);
						break;
					case 3:
						createChunksRec(xMove, zMove + 1, nbInstance + 1, dir);
						break;
					case 4:
						createChunksRec(xMove, zMove - 1, nbInstance + 1, dir);
						break;
					default:
						break;
					}
				} else {
					if (xMove != 0) {
						createChunksRec(xMove, zMove + 1, nbInstance, 3);
						createChunksRec(xMove, zMove - 1, nbInstance, 4);
					} else {
						createChunksRec(xMove + 1, zMove, nbInstance, 1);
						createChunksRec(xMove - 1, zMove, nbInstance, 2);
					}
				}
			} else {
				addChunnksToCreate((int) pos.x + xMove ,(int) pos.y + 1, (int) pos.z + zMove);
				createChunksRec(xMove, zMove, nbInstance + 1, dir);
			}
		}
	}


	/**
	 * Génere 2 des chunks à crée de la liste chunksToCreate
	 */
	public void createChunks(){
		Vector<Chunk> temp = new Vector<Chunk>();
		int i=0;
		for(Chunk ck : chunksToCreate){
			if(i<2){
				clone.getTerrainGenerator().genTopNoise(ck.getX(), ck.getY()-1, ck.getZ());
				temp.add(ck);
				i++;
			}
		}
		chunksToCreate.removeAll(temp);
	}
	
	/**
	 * ajoute un chunk à crée
	 * @param posX Les X du chunk à ajouter
	 * @param posY Les Y du chunk à ajouter
	 * @param posZ Les Z du chunk à ajouter
	 */
	private void addChunnksToCreate(int posX, int posY, int posZ){
		chunksToCreate.add(getChunk(posX,posY, posZ));
	}

	/**
	 * Ajoute un cube transparant
	 * @param transp Le cube transparant
	 */
	public void addTransparent(Cube3dVbo transp){
		transparancy.addTransparent(transp);
	}

	/**
	 * Suppriem un cube transparant
	 * @param transp
	 */
	public void dellTransparent(Cube3dVbo transp){
		transparancy.delTransparent(transp);
	}

	/**
	 * renvoi l'ID du chunk voulu
	 * @param x Les X du chunk
	 * @param y Les Y du chunk
	 * @param z Les Z du chunk
	 * @return l'id calculer du chunk
	 */
	private int makeID(int x, int y, int z){
		/*int temp;
		x = Math.abs(x);
		y = Math.abs(y);
		z = Math.abs(z);

		temp = Integer.parseInt(Integer.toString(x)+Integer.toString(y)+Integer.toString(z));
		System.out.println(temp);*/
		
		return x;
	}

	/**
	 * Retourne le hash code pour le chunk
	 * @param x Les X du chunk
	 * @param y Les Y du chunk
	 * @param z Les Z du chunk
	 * @return le hash code calculer
	 */
	private int hash(int x, int y, int z){
		return (Math.abs(x)%45+Math.abs(y)%10+Math.abs(z)%45)%100;
	}

	/**
	 * Crée un maillon dans chaque case de la hash table
	 */
	private void initHash(){
		for(int i=0; i< 100; i++){
			chunksHash[i] = new Maillon();
		}
	}
	
	/**
	 * Affiche la taille de chaque case de la hash table
	 */
	public void taille(){
		for(int i = 0; i<100; i++){
			System.out.println(chunksHash[i].getListe().size());
		}
	}

	/**
	 * la classe maillon qui gère la liste des chunk corespondant à son hash code
	 * @author joachimvanoni
	 *
	 */
	private class Maillon{
		//la liste des chunk 
		Vector<Chunk> liste;

		public Maillon(){
			liste = new Vector<Chunk>();
		}

		public Vector<Chunk> getListe() {
			return liste;

		}

		/**
		 * renvoi le chunk si il existe
		 * @param x Les X du chunk rechercher
		 * @param y Les Y du chunk rechercher
		 * @param z Les Z du chunk rechercher
		 * @return le chunk si il est là, null sinon.
		 */
		public Chunk containts(int x, int y, int z) {
			for (Chunk ck : liste) {
				if ((ck.getX() == x) && (ck.getY() == y) && (ck.getZ() == z)) {
					return ck;
				}
			}

			return null;
		}

		/**
		 * rajoute un chunk à la liste.
		 * @param chunk Le chunk à ajouter
		 */
		public void addChunkMaill(Chunk chunk){
			if(!liste.contains(chunk)){
				liste.add(chunk);
			}
		}
		
	}
}
