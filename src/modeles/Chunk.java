package modeles;
/**
 * Classe représentant les Chunks comme divisions du "monde" 
 */
import java.nio.FloatBuffer;
import java.util.Vector;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.util.vector.Vector3f;

import parametres.Parametres;
import modeles.entities.*;
import controleur.Controleur;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_COORD_ARRAY;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glDisableClientState;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glTexCoordPointer;
import static org.lwjgl.opengl.GL11.glVertexPointer;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glClientActiveTexture;
import static org.lwjgl.opengl.GL15.*;

public class Chunk implements Parametres{
	private Cube3dVbo[][][] cubes;
	protected Vector<Cube3dVbo> renderCubes;
	private Vector<Cube3dVbo> nonRenderCubes;
	private int vboVertexHandleChunk;
	protected FloatBuffer interleavedBuffer;
	private int floatByteSize = 4;
	private int positionFloatCount = 3;
	private int floatsPerVertex = positionFloatCount*3;
	int vertexFloatSizeInBytes = floatByteSize * floatsPerVertex;

	protected Controleur clone;
	private int x,y,z,id;

	private boolean updated, checked;
	private boolean xPlus, xMinus, yPlus, yMinus, zPlus, zMinus;

	/**
	 * Constructeur du chunk
	 * @param x X Par raport aux autres Chunks.
	 * @param y Y Par raport aux autres Chunks. Toujours négatif.
	 * @param z Z Par raport aux autres Chunks.
	 * @param id L'indentifiant de ce chunk.
	 * @param contr Le controleur 
	 */
	public Chunk(int x,int y,int z, int id, Controleur contr){
		cubes = new Cube3dVbo[16][16][16];
		renderCubes = new Vector<Cube3dVbo>();
		nonRenderCubes = new Vector<Cube3dVbo>();
		clone = contr;
		updated = true;
		checked = false;

		xPlus = true; xMinus = true; yPlus = true; yMinus = true; zPlus = true; zMinus = true;

		this.x = x;
		this.y = y;//tous n�gatifs
		this.z = z;
		//pour l'instant : id = ligne dans le programme, changer ça !!! (extrapoler l'iD des XYZ)
		this.id = id;

		for(int i=0; i<16; i++){
			for(int j =0; j<16; j++){
				for(int k=0; k<16; k++){
					cubes[i][j][k]=null;
				}
			}
		}

	}

	public void genVBO(){

		if(!renderCubes.isEmpty()){
			vboVertexHandleChunk = GL15.glGenBuffers();
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboVertexHandleChunk);
			GL15.glBufferData(GL15.GL_ARRAY_BUFFER, interleavedBuffer, GL15.GL_STATIC_DRAW);

			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		}
	}

	private void updateVBO(){

		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboVertexHandleChunk);
		GL15.glBufferSubData(GL_ARRAY_BUFFER, 5*4*renderCubes.size()*6*2, interleavedBuffer);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

	}
	
	/**
	 * Méthode ajoutant un cube dans le chunk
	 * TODO : vérification que le cube a le droit d'être dans ce cube
	 * @param cube
	 */
	public void addCube3dVbo(Cube3dVbo cube){
		int posX = convertCoordAdd(cube.getX());
		int posY = Math.abs(cube.getY())%16;
		int posZ = convertCoordAdd(cube.getZ());

		cubes[posX][posY][posZ] = cube;
	}
	
	/**
	 * Renvoie un cube aux coordonnées x,y,z de la caméra
	 * @param x
	 * @param y
	 * @param z
	 * @return un Cube3dVbo
	 */
	public Cube3dVbo getCubeCam(float x, float y, float z) {
		int tempX = convertCoordGet(x);
		int tempY = (int)Math.abs(Math.ceil(y))%16;
		int tempZ = convertCoordGet(z);

		return cubes[tempX][tempY][tempZ];
	}
	
	/**
	 * Renvoie un cube aux coordonnées x,y,z par raport à la matrice de déssin
	 * @param x
	 * @param y
	 * @param z
	 * @return un Cube3dVbo
	 */
	public Cube3dVbo getCubeDraw(float x, float y, float z) {
		int tempX = convertCoordAdd(x);
		int tempY = (int)Math.abs(y)%16;
		int tempZ = convertCoordAdd(z);

		return cubes[tempX][tempY][tempZ];
	}

	public void delCube(float x, float y, float z){
		int tempX = convertCoordGet(x);
		int tempY = (int)Math.abs(Math.ceil(y))%16;
		int tempZ = convertCoordGet(z);

		cubes[tempX][tempY][tempZ]=null;
		updated=false;
	}
	
	public void createCubeAt(float x, float y, float z){
		int tempX = convertCoordGet(x-1);
		int tempY = convertCoordGet(y);
		int tempZ = convertCoordGet(z);
		
		int xCube = this.x*16-tempX;
		int yCube = this.y*16-tempY;
		int zCube = this.z*16-tempZ;

		cubes[tempX][tempY][tempZ] = new Cube3dVbo(xCube, -yCube, zCube, 1, clone.getInput().getBlockType());
		updated=false;
	}

	/*
	 * Methodes
	 */

	/**
	 * Ajoute tous les cube liés à l'ID du chunk (aka la ligne dans le programme)
	 */
	public void addCubes(){
		clone.getMapRead().setCubes(cubes, id);
	}

	/**
	 * Vérifie quels cubes sont actifs (visible ou non) et les met dans la liste de rendu
	 */
	public void checkState(){
		clearChunk();
		checked = true;
		for(int i=0; i<16; i++){
			for(int j =0; j<16; j++){
				for(int k=0; k<16; k++){
					if(cubes[i][j][k]!=null){
						if(surround(i,j,k)){
							cubes[i][j][k].setEtat(false);
							nonRenderCubes.add(cubes[i][j][k]);
						}else{
							cubes[i][j][k].setEtat(true);
							renderCubes.add(cubes[i][j][k]);
						}
					}else{
						switch(whichFace(i,j,k)){
						case 1:
							xPlus=true;
							break;
						case 2:
							xMinus=true;
							break;
						case 3:
							yMinus=true;
							break;
						case 4:
							yPlus=true;
							break;
						case 5:
							zPlus=true;
							break;
						case 6:
							zMinus=true;
							break;
						default:
							break;
						}


					}
				}
			}
		}
	}

	public void checkStateAt(int x, int y, int z){
		checked = true;

		for(int i=-1; i<2; i++){
			for(int j=-1; j<2; j++){
				for(int k=-1; k<2; k++){
					if(getCubeCam(x+i, y+j, z+k)!=null){
						nonRenderCubes.remove(getCubeCam(x+i, y+j, z+k));
						renderCubes.remove(getCubeCam(x+i, y+j, z+k));
						if(surround(convertCoordGet(x+i),convertCoordGet(y+j),convertCoordGet(z+k))){
							getCubeCam(x+i, y+j, z+k).setEtat(false);
							nonRenderCubes.add(getCubeCam(x+i, y+j, z+k));
						}else{
							getCubeCam(x+i, y+j, z+k).setEtat(true);
							renderCubes.add(getCubeCam(x+i, y+j, z+k));
						}
					}
				}
			}
		}
	}

	public boolean checkPos(Vector3f current){
		if(Math.abs(current.x-x)>=chunkFar || Math.abs(current.y-y)>=chunkFar || Math.abs(current.z-z)>=chunkFar){
			return false;
		}
		return true;
	}

	/**
	 * Met a jours tout les étas des cubes,
	 * Puis suprimer les ancien buffer pour les recrée avec les nouvelles valeurs.
	 */
	public void update(){
		checkState();
		unbindVbo();
		genCubes(clone.getTexManager());
		genVBO();
	}

	public void updateAt(float x, float y, float z){

		int i = convertCoordGet(x);
		int j = (int)Math.abs(Math.ceil(y))%16;
		int k = convertCoordGet(z);

		//checkStateAt(i,j,k);
		checkState();
		unbindVbo();
		genCubes(clone.getTexManager());
		genVBO();
	}

	/**
	 * Pour un cube donné, renvoi si il est visible ou non
	 */
	private boolean surround(int x, int y, int z){
		boolean temp;
		int xCube = cubes[x][y][z].getX();
		int yCube = cubes[x][y][z].getY();
		int zCube = cubes[x][y][z].getZ();

		temp = (x>0) ? cubes[x-1][y][z]!=null : clone.getChunkManager().cubeExist(xCube-1, yCube, zCube);
		temp = (y>0) ? cubes[x][y-1][z]!=null && temp : clone.getChunkManager().cubeExist(xCube, yCube-1, zCube) && temp;
		temp = (z>0) ? cubes[x][y][z-1]!=null && temp : clone.getChunkManager().cubeExist(xCube, yCube, zCube-1) && temp;

		temp = (x<15) ? cubes[x+1][y][z]!=null && temp : clone.getChunkManager().cubeExist(xCube+1, yCube, zCube) && temp;
		temp = (y<15) ? cubes[x][y+1][z]!=null && temp : clone.getChunkManager().cubeExist(xCube, yCube+1, zCube) && temp;
		temp = (z<15) ? cubes[x][y][z+1]!=null && temp : clone.getChunkManager().cubeExist(xCube, yCube, zCube+1) && temp;

		return  temp;
	}

	/**
	 * Methode générant les cubes dans le buffer
	 * 	DOIT ETRE ASSOCIEE A UNE METHODE DE RESET DES BUFFER !!
	 */
	public void genCubes(TextureManager texMan){
		if(!renderCubes.isEmpty()){
			float cubeCoord[],texCoord[];
			int j= 0;
			interleavedBuffer = BufferUtils.createFloatBuffer(renderCubes.size()*(6*3+6*2)*6);
			for(Cube3dVbo cube : renderCubes){
				if(cube.getType()==12){
					clone.getChunkManager().addTransparent(cube);
				}else{
					cubeCoord=cube.genCubes();
					texCoord=texMan.genText(cube.getType(), cube.getTextX(), cube.getTextY());
					for(int i = 0; i< cubeCoord.length; i+=3){
						interleavedBuffer.put(cubeCoord[i]);
						interleavedBuffer.put(cubeCoord[i+1]);
						interleavedBuffer.put(cubeCoord[i+2]);

						interleavedBuffer.put(texCoord[j]);
						interleavedBuffer.put(texCoord[j+1]);

						j+=2;
					}
					j=0;
				}
			}
			interleavedBuffer.flip();
		}
	}

	/**
	 * Dessine tous les cubes actifs
	 * @param texMan
	 */
	public void draw(TextureManager texMan){
		if(!renderCubes.isEmpty()){
			glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandleChunk);
			glEnableClientState(GL_VERTEX_ARRAY);
			glVertexPointer(3, GL_FLOAT, 5*4, 0L);
			glClientActiveTexture(GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, texMan.getID());
			glEnableClientState(GL_TEXTURE_COORD_ARRAY);
			glTexCoordPointer(2, GL_FLOAT, 5*4, 3*4 );
			glDrawArrays(GL_TRIANGLES, 0, renderCubes.size()*6*6);
			glDisableClientState(GL_VERTEX_ARRAY);
			glDisableClientState(GL_TEXTURE_COORD_ARRAY);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		}
	}

	/**
	 * Vide la liste de rendu
	 */
	public void clearChunk(){
		renderCubes.clear();
		nonRenderCubes.clear();
	}

	public void unbindVbo(){
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboVertexHandleChunk);
		GL15.glDeleteBuffers(vboVertexHandleChunk);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		if(interleavedBuffer!=null){
			interleavedBuffer.clear();
		}
	}

	/*
	 * Getters
	 */
	/**
	 * Renvoie la position du chunk selon les x
	 * @return
	 */
	public int getX(){
		return x;
	}
	/**
	 * Renvoie la position du chunk selon les y
	 * @return
	 */
	public int getY(){
		return y;
	}
	/**
	 * Renvoie la position du chunk selon les z
	 * @return
	 */
	public int getZ(){
		return z;
	}

	public boolean isUpdated(){
		return updated;
	}

	public void haveBeenUpdated(boolean status){
		updated = status;
	}

	public boolean getChecked(){
		return checked;
	}

	/**
	 * Trouve le plus haut point du Chunk. A defaut de block, renvoi la hauteur de son "sol".
	 * @return Le point le plus haut du chunk.
	 */
	public float getHigher(){

		float tempY = -1;

		for(int i=0; i<16; i++){
			if(cubes[8][i][8]!=null){
				if(cubes[8][i][8].getY()>tempY){
					tempY=cubes[8][i][8].getY();
				}
			}
		}

		if(tempY==-1){
			tempY=-(this.y*16)-1;
		}

		return -(tempY+1);
	}

	private int whichFace(int i, int j, int k){

		if(i==0){
			return 5;
		}else if(i==15){
			return 6;
		}

		if(j==0){
			return 3;
		}else if(j==15){
			return 4;
		}

		if(k==0){
			return 1;
		}else if(k==15){
			return 2;
		}

		return 0;
	}

	public boolean isxPlus() {
		return xPlus;
	}

	public boolean isxMinus() {
		return xMinus;
	}

	public boolean isyPlus() {
		return yPlus;
	}

	public boolean isyMinus() {
		return yMinus;
	}

	public boolean iszPlus() {
		return zPlus;
	}

	public boolean iszMinus() {
		return zMinus;
	}
	
	private int convertCoordGet(float nb){
		float temp;
		
		temp = nb/Math.abs(nb)*((float)Math.floor(Math.abs(nb)));
		temp = (temp%16);
		if(temp<0){
			temp += 16;
		}
		temp = -temp-((nb>0)?1:0);
		temp = temp%16;
		if(temp<0){
			temp += 16;
		}
		
		return (int)temp;
	}
	
	private int convertCoordAdd(float nb){
		float temp;
		
		temp = nb/Math.abs(nb)*((float)Math.floor(Math.abs(nb)));
		temp = (temp%16);
		if(temp<0){
			temp += 16;
		}
	
		return (int)temp;
	}


}