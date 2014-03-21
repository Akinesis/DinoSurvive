package modeles;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import controleur.Controleur;


/**
 * Classe gérant les inputs clavier et souris
 * 
 *
 */
public class InputManager {

	private Camera camera;
	private Controleur clone;
	private int coef = 1;
	private boolean isJumping;
	private float hauteurSaut, lastJump;

	public InputManager(Controleur contr){
		clone = contr;
		clone.getMap();//à changer; inutile
		isJumping = false;
	}

	public void setCam(Camera cam){
		camera = cam;
	}

	/**
	 * Méthode de vérification de tous les inputs et actions en conséquence
	 * TODO: alléger et améliorer
	 */
	public void check(){

		boolean keyC = Keyboard.isKeyDown(Keyboard.KEY_C);
		boolean keyReturn = Keyboard.isKeyDown(Keyboard.KEY_RETURN);
		boolean keyJump = Keyboard.isKeyDown(Keyboard.KEY_SPACE); 
		boolean keyBas = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
		

		float mouseDX = Mouse.getDX() * 1 * 0.16f;
		float mouseDY = Mouse.getDY() * 1 * 0.16f;

		//inversion de la souris
		while(Keyboard.next()){
			if(keyC){
				coef*=-1;
			}
			if(keyReturn){
				clone.changeGragMouse();
			}
			
			if(keyBas)
				moveY(1);
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

		/*
		 * vérification du clavier
		 */
		boolean keyUp = Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_Z);
		boolean keyDown = Keyboard.isKeyDown(Keyboard.KEY_DOWN) || Keyboard.isKeyDown(Keyboard.KEY_S);
		boolean keyLeft = Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_Q);
		boolean keyRight = Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_D);

		/*
		 * vitesse de déplacement
		 */
		float speed = 0.09f;

		/*
		 * Déplacement
		 */
		if(keyUp)
			move(speed,1);
		if(keyDown)
			move(-speed,1);
		if(keyLeft)
			move(speed,0);
		if(keyRight)
			move(-speed,0);
		
		jump(keyJump);
		
		if(!clone.getCollision().gravity(camera) && !isJumping){
			camera.getPos().y += 0.12;
		}

	}

	private void moveY(float amt){
		if(clone.getCollision().colideY(camera, amt)){
			isJumping = false;
		}else{
			camera.getPos().y += amt;
		}
	}
	
	/**
	 * Méthode de déplacement
	 * @param amt
	 * @param dir
	 */
	private void move(float amt, float dir){
		double tempZ = amt * Math.sin(Math.toRadians(camera.getRot().y + 90 * dir));
		double tempX = amt * Math.cos(Math.toRadians(camera.getRot().y + 90 * dir));
		
		float posTempZ = camera.getPos().z+(float)tempZ;
		float posTempX = camera.getPos().x+(float)tempX;
		
		float indiceX = ((camera.getPos().x)>posTempX )?-0.2f:+0.2f;
		float indiceZ = ((camera.getPos().z)>posTempZ )?-0.2f:+0.2f;

		camera.getPos().z += (clone.getCollision().colideZ(camera, (float)tempZ+indiceZ))?0:(float)tempZ;
		camera.getPos().x += (clone.getCollision().colideX(camera, (float)tempX+indiceX))?0:(float)tempX;

	}
	
	private void jump(boolean space){
		if(!isJumping && space && getTime()-lastJump > 700){
			isJumping = true;
			hauteurSaut = camera.getPos().y-1.2f;
			lastJump = getTime();
		}
		
		if(isJumping){
			if(camera.getPos().y > hauteurSaut){
				moveY(-0.05f);
			}else{
				isJumping = false;
			}
		}
		
		
	}
	
	private static long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

}
