package modeles;

import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;

import org.lwjgl.util.vector.Vector3f;

import controleur.Controleur;

/**
 * La caméra qui représente le joueur.
 * Posséde tout les fonctions relative à ca possition.
 * @author joachim
 *
 */

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

	/**
	 * Met à jour la position et la rotation de la caméra.
	 */
	public void useView(){
		glRotatef(rotation.getX(), 1, 0, 0);
		glRotatef(rotation.getY(), 0, 1, 0);
		glRotatef(rotation.getZ(), 0, 0, 1);
		glTranslatef(position.getX(), position.getY(), position.getZ());
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
	
	public void setRotX(float x){
		rotation.setX(x);
	}
	
	public void setRotY(float y){
		rotation.setY(y);
	}
	
	public void setRotZ(float z){
		rotation.setZ(z);
	}

	public Vector3f getCurrentChunk(){
		setCurrentChunk();
		return curentChunk;
	}

	public void setCurrentChunk(){

		float xChunk = (float)Math.ceil(position.getX() / 16);
		float yChunk = (float)Math.ceil(position.getY() / 16);
		float zChunk = (float)Math.ceil(position.getZ() / 16);

		curentChunk.setX(xChunk);
		curentChunk.setY(yChunk);
		curentChunk.setZ(zChunk);
	}

}
