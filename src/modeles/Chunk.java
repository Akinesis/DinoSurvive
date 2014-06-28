package modeles;
/**
 * Classe représentant les Chunks comme divisions du "monde" 
 */
import java.nio.FloatBuffer;
import java.util.Vector;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

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

public class Chunk {
	private Cube3dVbo[][][] cubes;
	private Vector<Cube3dVbo> renderCubes;
	private Vector<Cube3dVbo> nonRenderCubes;
	private int vboVertexHandleChunk;
	private FloatBuffer interleavedBuffer;
	//private FloatBuffer vertexData, vertexTexture;
	private int floatByteSize = 4;
	private int positionFloatCount = 3;
	private int floatsPerVertex = positionFloatCount*3;
	int vertexFloatSizeInBytes = floatByteSize * floatsPerVertex;

	private Controleur clone;
	private int x,y,z,id;

	/**
	 * Constructeur du chunk
	 * @param x
	 * @param y
	 * @param z
	 * @param id
	 * @param contr
	 */
	public Chunk(int x,int y,int z, int id, Controleur contr){
		cubes = new Cube3dVbo[16][16][16];
		renderCubes = new Vector<Cube3dVbo>();
		nonRenderCubes = new Vector<Cube3dVbo>();
		clone = contr;

		this.x = x;
		this.y = y;
		this.z = z;
		//pour l'instant : id = ligne dans le programme, changer ça !!! (extrapoler l'iD des XYZ)
		this.id = id;
	}

	public void genVBO(){
		vboVertexHandleChunk = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboVertexHandleChunk);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, interleavedBuffer, GL15.GL_STATIC_DRAW);

		// -- Now we can split our interleaved data over 2 attribute lists
		// First up is our positional information in list 0
		/*GL20.glVertexAttribPointer(0, positionFloatCount, GL11.GL_FLOAT, false,
				vertexFloatSizeInBytes, 0);*/

		// Second is our texture information in list 1, for this we also need the offset
		//int byteOffset = floatByteSize * positionFloatCount;
		/*GL20.glVertexAttribPointer(1, positionFloatCount, GL11.GL_FLOAT, false,
				vertexFloatSizeInBytes, byteOffset);*/

		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);

	}

	/**
	 * Renvoie un cube aux coordonnées x,y,z
	 * @param x
	 * @param y
	 * @param z
	 * @return un Cube3dVbo
	 */
	public Cube3dVbo getCube(float x, float y, float z) {
		int tempX = (int)Math.abs(Math.ceil(x))%16;
		int tempY = (int)Math.abs(Math.ceil(y))%16;
		int tempZ = (int)Math.abs(Math.ceil(z))%16;

		return cubes[tempX][tempY][tempZ];
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
		for(int i=0; i<16; i++){
			for(int j =0; j<16; j++){
				for(int k=0; k<16; k++){
					if(cubes[i][j][k]!=null){
						if(cubes[i][j][k].getState()){
							renderCubes.add(cubes[i][j][k]);
						}else{
							nonRenderCubes.add(cubes[i][j][k]);
						}
					}
				}
			}
		}
	}

	/**
	 * Met a jours les cubes a rendre ou non
	 */
	public void update(){
		for(int i=0; i<16; i++){
			for(int j =0; j<16; j++){
				for(int k=0; k<16; k++){
					if(cubes[i][j][k]!=null){
						if(surround(i,j,k)){
							cubes[i][j][k].setEtat(false);
						}else{
							cubes[i][j][k].setEtat(true);
						}
					}
				}
			}
		}
	}

	/**
	 * Pour un cube donné, renvoi si il est visible ou non
	 */
	private boolean surround(int x, int y, int z){
		boolean temp;
		temp = true;

		temp = (x>0) ? cubes[x-1][y][z]!=null && temp : clone.getChunkManager().getCubeAt(cubes[x][y][z].getX()-1, cubes[x][y][z].getY(), cubes[x][y][z].getZ())!=null && temp || !(clone.getChunkManager().chunkExist(this.x-1, this.y, this.z));
		//temp = (y>0) ? cubes[x][y-1][z]!=null && temp : false;//clone.getChunkManager().getCubeAt(x+this.x*16, 0-this.y*16, z+this.x*16)!=null && temp || !(clone.getChunkManager().chunkExist(this.x, this.y-1, this.z));
		temp = (z>0) ? cubes[x][y][z-1]!=null && temp : false;//clone.getChunkManager().getCubeAt(x+this.x*16, y-this.x*16, 0+this.z*16)!=null && temp || !(clone.getChunkManager().chunkExist(this.x, this.y, this.z-1));

		temp = (x<15) ? cubes[x+1][y][z]!=null && temp : false;//clone.getChunkManager().getCubeAt(15+this.x*16+1, y-this.x*16, z+this.x*16)!=null && temp || !(clone.getChunkManager().chunkExist(this.x+1, this.y, this.z));
		//temp = (y<15) ? cubes[x][y+1][z]!=null && temp : false;//clone.getChunkManager().getCubeAt(x+this.x*16, 15-this.y*16, z+this.x*16)!=null && temp || !(clone.getChunkManager().chunkExist(this.x, this.y+1, this.z));
		temp = (z<15) ? cubes[x][y][z+1]!=null && temp : false;//clone.getChunkManager().getCubeAt(x+this.x*16, y-this.x*16, 15+this.z*16+1)!=null && temp || !(clone.getChunkManager().chunkExist(this.x, this.y, this.z+1));

		return  temp;
	}

	/**
	 * Methode générant les cubes dans le buffer
	 * 	DOIT ETRE ASSOCIEE A UNE METHODE DE RESET DES BUFFER !!
	 */
	public void genCubes(TextureManager texMan){
		float cubeCoord[],texCoord[];
		int j= 0;
		interleavedBuffer = BufferUtils.createFloatBuffer(renderCubes.size()*(6*3+6*2)*6);
		for(Cube3dVbo cube : renderCubes){
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
		interleavedBuffer.flip();
	}
	
	public void updateVbo(){
		genCubes(clone.getTexManager());
	}


	/**
	 * Dessine tous les cubes actifs
	 * @param texMan
	 */
	public void draw(TextureManager texMan){
		
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
	}

	/**
	 * Vide la liste de rendu
	 */
	public void clearChunk(){
		renderCubes.clear();
		nonRenderCubes.clear();
	}

	/**
	 * Méthode ajoutant un cube dans le chunk
	 * TODO : vérification que le cube a le droit d'être dans ce cube
	 * @param cube
	 */
	public void addCube3dVbo(Cube3dVbo cube){
		int posX = (Math.abs(cube.getX())+((cube.getX()<0)?-1:0))%16;
		int posY = Math.abs(cube.getY())%16;
		int posZ = (Math.abs(cube.getZ())+((cube.getZ()<0)?-1:0))%16;

		cubes[posX][posY][posZ] = cube;		
	}
	
	public void unbindVbo(){
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL15.glDeleteBuffers(vboVertexHandleChunk);
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

	/**
	 * Renvoie l'id du chunk
	 * @return
	 */
	public int getID(){
		return id;
	}

}