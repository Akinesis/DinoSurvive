package modeles;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_COORD_ARRAY;
import static org.lwjgl.opengl.GL11.GL_LINES;
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

import modeles.entities.WiredCube3D;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.util.vector.Vector3f;

import controleur.Controleur;

public class RayPicker {

	private Controleur clone;
	private Vector3f ray,posCam;
	private WiredCube3D picker;
	private int vboVertexHandle;
	protected FloatBuffer interleavedBuffer;

	public RayPicker(Controleur clone){
		this.clone = clone;
		ray = new Vector3f();
		picker = new WiredCube3D(12, 83, -8, 1);
	}

	public void pick(){
		ray =  getRay();
		posCam = clone.getCamera().getPos();
		drawPicker();
	}
	
	private Vector3f getRay(){
		return clone.getMatrices().getPickingRay(clone.getDisplay().getHeight()/2, clone.getDisplay().getWidth()/2);
	}

	private void genVBO(){

		vboVertexHandle = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboVertexHandle);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, interleavedBuffer, GL15.GL_STATIC_DRAW);

		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	private void genCube(){
		interleavedBuffer = BufferUtils.createFloatBuffer(24*3*4);
		interleavedBuffer.put(picker.genCubes());
		interleavedBuffer.flip();
	}
	
	public void draw(){
		GL11.glLineWidth(5f);
		GL11.glColor3f(0.011f, 0.31f, 0.58f);
		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
		glVertexPointer(3, GL_FLOAT, 0, 0l);

		glEnableClientState(GL_VERTEX_ARRAY);

		glDrawArrays(GL_LINES, 0, 24);

		glDisableClientState(GL_VERTEX_ARRAY);
		GL11.glLineWidth(1f);
		GL11.glColor3f(1, 1, 1);
	}
	
	private void drawPicker(){
		setRayCoord();
		unbinde();
		genCube();
		genVBO();
		draw();
	}
	
	private void setRayCoord(){
		
		picker.setPos(-(float)Math.ceil(posCam.getX()), -(float)Math.floor(posCam.getY()), -(float)Math.ceil(posCam.getZ()));

	}
	
	private void unbinde(){
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboVertexHandle);
		GL15.glDeleteBuffers(vboVertexHandle);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		if(interleavedBuffer!=null){
			interleavedBuffer.clear();
		}
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
}
