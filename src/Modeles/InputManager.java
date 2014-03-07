package Modeles;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import Controleur.Controleur;

public class InputManager {

	private Camera camera;
	private Controleur clone;
	private int coef = 1;

	public InputManager(Controleur contr){
		clone = contr;
		clone.getMap();//à changer; inutile
	}

	public void setCam(Camera cam){
		camera = cam;
	}

	//méthode de vérification de tout les input et agis en consquence
	//doit être allégé et amŽliorŽ
	public void check(){

		boolean keyC = Keyboard.isKeyDown(Keyboard.KEY_C);
		boolean keyReturn = Keyboard.isKeyDown(Keyboard.KEY_RETURN);

		float mouseDX = Mouse.getDX() * 1 * 0.16f;
		float mouseDY = Mouse.getDY() * 1 * 0.16f;

		//invertion de la sourie
		while(Keyboard.next()){
			if(keyC){
				coef*=-1;
			}
			if(keyReturn){
				clone.changeGragMouse();
			}
		}

		//mouvement de la sourie
		if(Mouse.isGrabbed()){
			if (camera.getRot().y + mouseDX >= 360) {
				camera.getRot().y = (camera.getRot().y + mouseDX - 360)*coef;
			} else if (camera.getRot().y + mouseDX < 0) {
				camera.getRot().y = (360 - camera.getRot().y + mouseDX)*coef;
			} else {
				camera.getRot().y += mouseDX*coef;
			}if (camera.getRot().x - mouseDY >= -85 && camera.getRot().x - mouseDY <= 85) {
				camera.getRot().x += -mouseDY*coef;
			} else if (camera.getRot().x - mouseDY < -85) {
				camera.getRot().x = -85;
			} else if (camera.getRot().x - mouseDY > 85) {
				camera.getRot().x = 85;
			}
		}

		//vŽrification du clavier
		boolean keyUp = Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_Z);
		boolean keyDown = Keyboard.isKeyDown(Keyboard.KEY_DOWN) || Keyboard.isKeyDown(Keyboard.KEY_S);
		boolean keyLeft = Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_Q);
		boolean keyRight = Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_D);

		//vitesse de dŽplacement
		float speed = 0.09f;

		//dŽplacement
		if(keyUp)
			move(speed,1);
		if(keyDown)
			move(-speed,1);
		if(keyLeft)
			move(speed,0);
		if(keyRight)
			move(-speed,0);

	}

	//mŽthode de dŽplacement
	private void move(float amt, float dir){
		double tempZ = amt * Math.sin(Math.toRadians(camera.getRot().y + 90 * dir));
		double tempX = amt * Math.cos(Math.toRadians(camera.getRot().y + 90 * dir));

		camera.getPos().z += tempZ;
		camera.getPos().x += tempX;
	}

}
