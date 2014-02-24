package Modeles;

import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;

import org.lwjgl.util.vector.Vector3f;

import Controleur.Controleur;

public class Camera {
	
	private Controleur clone;
	private Vector3f position, rotation;
	
	
	public Camera(Controleur contr){
		clone = contr;
		position = new Vector3f(0, -3, 0);
		rotation = new Vector3f(0, 0, 0);
	}
	
	public void useView(){
		glRotatef(rotation.x, 1, 0, 0);
		glRotatef(rotation.y, 0, 1, 0);
		glRotatef(rotation.z, 0, 0, 1);
		glTranslatef(position.x, position.y, position.z);
	}
	
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
