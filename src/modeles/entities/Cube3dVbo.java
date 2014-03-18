package modeles.entities;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL11.*;

import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;


public class Cube3dVbo extends Cube3D {

	private static int verticiesNum = 36;
	private int vertexSize = 3;
	private int vboVertexHandle;
	private FloatBuffer vertexData;
	private int type;

	public Cube3dVbo(float x,float  y,float z,float size, int typ){
		super(-x,y,-z,size);
		type=typ;

		vertexData = BufferUtils.createFloatBuffer(verticiesNum * vertexSize);
	}

	public void draw(){
        glDrawArrays(GL_TRIANGLES, 0, verticiesNum);
	}
	
	public void bindDrawCube(){
		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
        glVertexPointer(vertexSize, GL_FLOAT, 0, 0L);
	}
	
	public void enableCube(){
		glEnableClientState(GL_VERTEX_ARRAY);
	}
	
	public void disableCube(){
		glDisableClientState(GL_VERTEX_ARRAY);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	public void genCube(){
		vertexData.put(new float[]{
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
		});
		vertexData.flip();
	}
	
	public void bindBuffers(){
		vboVertexHandle = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
		glBufferData(GL_ARRAY_BUFFER, vertexData, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	public int getType(){
		return type;
	}
	
}

