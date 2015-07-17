package modeles;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glDisableClientState;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glVertexPointer;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;

import java.nio.FloatBuffer;

import modeles.entities.WiredCube3D;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.util.vector.Vector3f;

import javax.vecmath.Matrix3f;

import controleur.Controleur;

public class RayPicker {

	private Controleur clone;
	private Vector3f ray,posCam, rayMatrix;
	private WiredCube3D picker;
	private int vboVertexHandle;
	private FloatBuffer interleavedBuffer;
	private Matrix3f direction;

	public RayPicker(Controleur clone){
		this.clone = clone;
		ray = new Vector3f();
		rayMatrix = new Vector3f();
		picker = new WiredCube3D(12, 83, -8, 1);
		direction = new Matrix3f();

		direction.m00 = 1;
		direction.m01 = 0;
		direction.m02 = 0;

		direction.m10 = 0;
		direction.m11 = 1;
		direction.m12 = 0;

		direction.m20 = 0;
		direction.m21 = 0;
		direction.m22 = -1;
	}

	public void pick(){
		ray =  getRay();
		posCam = clone.getCamera().getPos();
		drawPicker();
	}

	public void rotateMatrix(Vector3f rot){
		System.out.println(rot);
		direction.rotX(rot.x);
		direction.rotY(rot.y);
		direction.rotZ(rot.z);
		rayMatrix.x = direction.m20;
		rayMatrix.y = direction.m21;
		rayMatrix.z = direction.m22;
	}

	private Vector3f getRay(){
		return clone.getMatrices().getPickingRayBis(clone.getDisplay().getHeight()/2, clone.getDisplay().getWidth()/2);
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
		rotateMatrix(clone.getCamera().getRot());
		setRayCoord();
		unbinde();
		genCube();
		genVBO();
		draw();
	}
	
	//c'est cette fonction qui fait chier.
	private void setRayCoord(){
		
		boolean xNeg = ray.x<0;
		boolean zNeg = ray.z<0;

		int xStart = (xNeg)?(int)Math.ceil(posCam.getX()):(int)Math.floor(posCam.getX());
		int yStart = (int)Math.floor(posCam.getY());
		int zStart = (zNeg)?(int)Math.ceil(posCam.getZ()):(int)Math.floor(posCam.getZ());
		
		xStart *= -1;
		yStart *= -1;
		zStart *= -1;
		

		Vector3f origine = new Vector3f((float)xStart-(float)0.5, yStart+(float)0.5, (float)zStart-(float)0.5);
		System.out.println("Caméra : "+ posCam.getX() +", "+ posCam.getY() +", "+ posCam.getZ());
		System.out.println("Start : "+ origine.x +", "+ origine.y +", "+ origine.z);
		
		/*
		if(xNeg){
			origine.x = origine.x - 1;
		}else{
			origine.x = origine.x + 1;
		}
		
		if(zNeg){
			origine.z = origine.z + 1;
		}else{
			origine.z = origine.z - 1;
		}
	
		System.out.println("avant : "+rayMatrix);
		System.out.println("Caméra : "+ posCam.getX() +", "+ posCam.getY() +", "+ posCam.getZ());

		ray.x *= 5;
		ray.y *= 5;
		ray.z *= 5;

		ray.x += origine.x;
		ray.y += origine.y+2;
		ray.z += origine.z;

		//System.out.println(ray);
		System.out.println(picker.getSpacePos());
		System.out.println("---------------------------------");

		if(xNeg && zNeg){ //  +  +
			ray.x = (float)Math.ceil(ray.x)+1;
			ray.z = (float)Math.floor(ray.z)-1;
		}else if(xNeg && !zNeg){//  -  + 
			ray.x = (float)Math.floor(ray.x)-1;
			ray.z = (float)Math.floor(ray.z)-1;
		}else if(!xNeg && zNeg){ // +  - work ?
			ray.x = (float)Math.ceil(ray.x);
			ray.z = (float)Math.ceil(ray.z);
		}else{// -  - work?
			ray.x = (float)Math.floor(ray.x);
			ray.z = (float)Math.ceil(ray.z);
		}
		*/
		
		ray.x *= 5;
		ray.y *= 5;
		ray.z *= 5;

		ray.x += origine.x;
		ray.y += origine.y;
		ray.z += origine.z;
		
		/*ray.x += 8;
		ray.y += 82;
		ray.z += 8;*/
		
		System.out.println(ray);
		System.out.println(picker.getSpacePos());
		
		picker.setPos((int)Math.ceil(ray.x), /*(int)Math.floor(ray.y)*/ 82, (int)Math.ceil(ray.z));

		System.out.println("---------------------------------");
	}
	
	private void setRayCoordBis(){
		float xStart = -(float)posCam.getX()+0.5f;
		float yStart = -(float)posCam.getY()+0.5f;
		float zStart = -(float)posCam.getZ()+0.5f;
		
		Vector3f origine = new Vector3f((float)Math.floor(xStart), yStart, (float)Math.floor(zStart));
		
		boolean xNeg = ray.x<0;
		boolean zNeg = ray.z<0;
		
		ray.y *= 5;
		ray.y += origine.y+2;
		
		float x = ray.x;
		float z = ray.z;

		if(xNeg && zNeg){ //  -  -
			x = /*distance du centre de la caméra jusqu'au pointeur :*/5 * /*le moins c'est par rapport au fait que l'angle est négatif je crois. On cherche le sinus de l'angle :*/-(float)Math.sin(/*angle de la souris par rapport à l'axe fixe : tan(teta) = ray.x/ray.z donc teta =*/Math.atan(ray.x/ray.z)) + /*pour que tout soit calculer selon la position de la caméra :*/origine.x;
			z = 6 * -(float)Math.cos(Math.atan(ray.x/ray.z)) + origine.z;
		}else if(xNeg && !zNeg){//  -  +
			x = 6 * (float)Math.sin(Math.atan(ray.x/ray.z)) + origine.x;
			z = 6 * (float)Math.cos(Math.atan(ray.x/ray.z)) + origine.z;
		}else if(!xNeg && zNeg){ // +  -
			x = 6 * -(float)Math.sin(Math.atan(ray.x/ray.z)) + origine.x;
			z = 6 * -(float)Math.cos(Math.atan(ray.x/ray.z)) + origine.z;
		}else{// +  + 
			x = 6 * (float)Math.sin(Math.atan(ray.x/ray.z)) + origine.x;
			z = 6 * (float)Math.cos(Math.atan(ray.x/ray.z)) + origine.z;
		}
		
		picker.setPos(x, (int)Math.floor(ray.y), z);
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
	
	public Vector3f getPosCube(){
		return picker.getSpacePos();
	}
}
