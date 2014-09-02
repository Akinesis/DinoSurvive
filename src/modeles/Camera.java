package modeles;

import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;

import org.lwjgl.util.vector.Vector3f;

import controleur.Controleur;



public class Camera {

	private Controleur clone;
	private Vector3f position, rotation, curentChunk;

	//constructeur de la caméra, initialise la position
	public Camera(Controleur contr){
		clone = contr;
		clone.getCamera();
		position = new Vector3f(-0.5f, -3.75f, -1.5f);
		rotation = new Vector3f(0, 0, 0);
		curentChunk = new Vector3f();
	}

	/**
	 * Initialise la première position de la caméra.
	 * @param y le plus haut point du plus haut chunk (en x=8, z=8)
	 */
	public void spawn(float y){
		//position = new Vector3f(-8f, y-2.75f, -8f);
		position = new Vector3f(-8f, y-2.75f, -8f);
		System.out.println(y);
	}

	//met à jour la position et la rotation
	public void useView(){
		glRotatef(rotation.x, 1, 0, 0);
		glRotatef(rotation.y, 0, 1, 0);
		glRotatef(rotation.z, 0, 0, 1);
		glTranslatef(position.x, position.y, position.z);
	}

	//getters et setters
	public Vector3f getPos(){
		return position;
	}

	public Vector3f getRot(){
		return rotation;
	}

	public void setPos(Vector3f pos){
		position=pos;
	}

	public void setRot(Vector3f rot){
		rotation=rot;
	}

	public Vector3f getCurrentChunk(){
		setCurrentChunk();
		return curentChunk;
	}

	public void setCurrentChunk(){

		float xChunk = (float)Math.ceil(position.x / 16);
		float yChunk = (float)Math.ceil(position.y / 16);
		float zChunk = (float)Math.ceil(position.z / 16);

		curentChunk.x=xChunk;
		curentChunk.y=yChunk;
		curentChunk.z=zChunk;
	}

}
