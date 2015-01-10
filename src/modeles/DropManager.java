package modeles;

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
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;

import java.nio.FloatBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

import com.sun.org.apache.bcel.internal.generic.NEW;

import modeles.entities.Cube3dVbo;
import modeles.entities.FlatItemVBO;

public class DropManager {

	private ArrayList<FlatItemVBO> drops;
	protected FloatBuffer interleavedBuffer;
	private int vboVertexHandleDrop;

	public DropManager(){
		drops = new ArrayList<FlatItemVBO>();
	}

	public void addDrop(FlatItemVBO item){
		drops.add(item);
	}
	
	public void drawn(){
		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandleDrop);
		glEnableClientState(GL_VERTEX_ARRAY);
		glVertexPointer(3, GL_FLOAT, 5*4, 0L);
		glTexCoordPointer(2, GL_FLOAT, 5*4, 3*4 );
		glDrawArrays(GL_TRIANGLES, 0, drops.size()*6*6);
		glDisableClientState(GL_VERTEX_ARRAY);
	}
	
	public void gen(){
		genDrop();
		genVBO();
	}

	private void genDrop(){
		if(!drops.isEmpty()){
			float cubeCoord[];
			interleavedBuffer = BufferUtils.createFloatBuffer(drops.size()*(3*3*4*4));
			for(FlatItemVBO item : drops){
				cubeCoord=item.genCubes();
				for(int i = 0; i< cubeCoord.length; i+=3){
					interleavedBuffer.put(cubeCoord[i]);
					interleavedBuffer.put(cubeCoord[i+1]);
					interleavedBuffer.put(cubeCoord[i+2]);
				}
			}
		}
		interleavedBuffer.flip();
	}
	
	private void genVBO(){
		if(!drops.isEmpty()){
			vboVertexHandleDrop = GL15.glGenBuffers();
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboVertexHandleDrop);
			GL15.glBufferData(GL15.GL_ARRAY_BUFFER, interleavedBuffer, GL15.GL_STATIC_DRAW);

			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		}
	}
}
