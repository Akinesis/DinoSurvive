package Modeles;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import Controleur.Controleur;

public class InputManager {

	private Camera camera;
	private Controleur controleur;
	private int coef = 1;

	public InputManager(Controleur contr){
		controleur = contr;
		controleur.getMap();//à changer; inutile
	}

	public void setCam(Camera cam){
		camera = cam;
	}

	//méthode de vérification de tout les input et agis en consquence
	//doit être allégé et amélioré
	public void check(){

		boolean keyC = Keyboard.isKeyDown(Keyboard.KEY_C);
		boolean keyReturn = Keyboard.isKeyDown(Keyboard.KEY_RETURN);

		float mouseDX = Mouse.getDX() * 1 * 0.16f;
		float mouseDY = Mouse.getDY() * 1 * 0.16f;

		//inversion de la souris
		while(Keyboard.next()){
			if(keyC){
				coef*=-1;
			}
			if(keyReturn){
				controleur.changeGragMouse();
			}
		}

		//mouvement de la souris
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

		//vérification du clavier
		boolean keyUp = Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_Z);
		boolean keyDown = Keyboard.isKeyDown(Keyboard.KEY_DOWN) || Keyboard.isKeyDown(Keyboard.KEY_S);
		boolean keyLeft = Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_Q);
		boolean keyRight = Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_D);
		boolean keyJump = Keyboard.isKeyDown(Keyboard.KEY_SPACE); 
		boolean keyBas = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);

		//vitesse de déplacement
		float speed = 0.09f;

		//déplacement
		if(keyUp)
			move(speed,1);
		if(keyDown)
			move(-speed,1);
		if(keyLeft)
			move(speed,0);
		if(keyRight)
			move(-speed,0);
		if(keyJump)
			moveY(-speed);
		if(keyBas)
			moveY(speed);

	}
	
	private void moveY(float amt){
		double tempY = amt;	
		camera.getPos().y += tempY;
	}
	
	//méthode de déplacement
	private void move(float amt, float dir){
		double tempZ = amt * Math.sin(Math.toRadians(camera.getRot().y + 90 * dir));
		double tempX = amt * Math.cos(Math.toRadians(camera.getRot().y + 90 * dir));
		
		tempZ += camera.getPos().z;
		tempX += camera.getPos().x;
		double tempY = camera.getPos().y;
		
		if( controleur.getChunkManager().getCubeAt( (int)Math.floor(tempX), (int)Math.floor(tempY), (int)Math.floor(tempZ) ) == null ){
			camera.getPos().z = (float) tempZ;
			camera.getPos().x = (float) tempX;
		}
	}

}
