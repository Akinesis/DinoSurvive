package Modeles;

import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;

import org.lwjgl.util.vector.Vector3f;

import Controleur.Controleur;

public class Camera {
	
	private Controleur clone;
	private Vector3f position, rotation;
	
	//constructeur de la caméra, initialise la possition
	public Camera(Controleur contr){
		clone = contr;
		position = new Vector3f(0, -3, 0);
		rotation = new Vector3f(0, 0, 0);
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

}
