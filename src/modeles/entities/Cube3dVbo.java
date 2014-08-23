package modeles.entities;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL11.*;

import java.nio.FloatBuffer;


public class Cube3dVbo extends Cube3D {

	private static int verticiesNum = 36;
	private int vertexSize = 3;
	private int vboVertexHandle;
	private FloatBuffer vertexData;
	private int type;
	private float indiceTextX, indiceTextY;

	public Cube3dVbo(float x,float  y,float z,float size, int typ){
		super(-x,y,-z,size);
		type=typ;

		//vertexData = BufferUtils.createFloatBuffer(verticiesNum * vertexSize);
		indiceTextX = (float)(Math.random());
		indiceTextY = (float)(Math.random());
		
		//vboVertexHandle = glGenBuffers();
	}

	public void draw(){
        glDrawArrays(GL_TRIANGLES, 0, verticiesNum);
	}
	
	public void delCube(int vboChunk){
		glDeleteBuffers(vboChunk);
	}
	
	public float[] genCubes(){
		return new float[]{
				//south
				pos.x, pos.y, pos.z,
				pos.x, pos2.y, pos.z,
				pos2.x, pos.y, pos.z,
				pos2.x, pos.y, pos.z,
				pos.x, pos2.y, pos.z,
				pos2.x, pos2.y, pos.z,

				//north
				pos2.x, pos2.y, pos2.z,
				pos.x, pos2.y, pos2.z,
				pos.x, pos.y, pos2.z,
				pos2.x, pos2.y, pos2.z,
				pos.x, pos.y, pos2.z,
				pos2.x, pos.y, pos2.z,

				//bottom
				pos.x, pos2.y, pos.z,
				pos.x, pos2.y, pos2.z,
				pos2.x, pos2.y, pos.z,
				pos2.x, pos2.y, pos.z,
				pos.x, pos2.y, pos2.z,
				pos2.x, pos2.y, pos2.z,

				//top
				pos.x, pos.y, pos.z,
				pos2.x, pos.y, pos.z,
				pos.x, pos.y, pos2.z,
				pos.x, pos.y, pos2.z,
				pos2.x, pos.y, pos.z,
				pos2.x, pos.y, pos2.z,	

				//east
				pos2.x, pos.y, pos.z,
				pos2.x, pos2.y, pos.z,
				pos2.x, pos.y, pos2.z,
				pos2.x, pos.y, pos2.z,
				pos2.x, pos2.y, pos.z,
				pos2.x, pos2.y, pos2.z,

				//west
				pos.x, pos.y, pos.z,
				pos.x, pos2.y, pos2.z,
				pos.x, pos2.y, pos.z,
				pos.x, pos.y, pos2.z,
				pos.x, pos2.y, pos2.z,
				pos.x, pos.y, pos.z
		};
	}
	
	public void bindBuffers(int vboChunk){
		glBindBuffer(GL_ARRAY_BUFFER, vboChunk);
		glBufferData(GL_ARRAY_BUFFER, vertexData, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	public int getType(){
		return type;
	}
	
	public boolean isSolide(){
		return type==13 || type==14;
	}
	
	public float getTextX(){
		return indiceTextX;
	}
	
	public float getTextY(){
		return indiceTextY;
	}
	
}

