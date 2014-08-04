package modeles;


import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

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
	private long lastFrame;
	private int delta;
	private Vector3f curentChunk;

	public InputManager(Controleur contr){
		clone = contr;
		clone.getMap();//à changer; inutile
		isJumping = false;
		lastFrame=getTime();
		curentChunk= new Vector3f();
	}

	public void setCam(Camera cam){
		camera = cam;
	}

	/**
	 * Méthode de vérification de tous les inputs et actions en conséquence
	 * TODO: alléger et améliorer
	 */
	public void check(){

		boolean keyChap = Keyboard.isKeyDown(Keyboard.KEY_ESCAPE);
		boolean keyI = Keyboard.isKeyDown(Keyboard.KEY_I);
		boolean keyINSERT = Keyboard.isKeyDown(Keyboard.KEY_INSERT);
		boolean keydol = Keyboard.isKeyDown(Keyboard.KEY_HOME); 
		boolean keyReturn = Keyboard.isKeyDown(Keyboard.KEY_RETURN);
		boolean keyJump = Keyboard.isKeyDown(Keyboard.KEY_SPACE); 
		boolean keyBas = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
		delta = getDelta();

		//neutralise tout les mouvements
		if(this.clone.getHUDManager().getMenu().getEstAfficher()){
			//this.clone.changeGragMouse();
			if(Keyboard.isKeyDown(Keyboard.KEY_M)){
				this.clone.getHUDManager().getMenu().inverserEstAfficher();
			}
		}else{
			//inversion de la souris
			while(Keyboard.next()){
				if(keydol){
					coef*=-1;
				}
				if(keyReturn){
					clone.changeGragMouse();
				}

				if(keyBas)
					moveY(1);
				if(keyI){
					clone.getHUDManager().getInventaire().changeVisible();
					clone.getHUDManager().update(clone.getHUDTextManager());
				}
				if(keyINSERT){
					clone.getHUDManager().getModeDebug().changeVisible();
					clone.getHUDManager().update(clone.getHUDTextManager());
				}
				if(keyChap){
					clone.changeGragMouse();
					clone.getHUDManager().getCurseur().changeVisible();
					clone.getHUDManager().getMenuJeu().changeVisible();
					clone.getHUDManager().update(clone.getHUDTextManager());					
				}

			}

			if(clone.getDisplay().isResized()){
				clone.getHUDManager().update(clone.getHUDTextManager());
			}

			mouse();
			move();

			jump(keyJump);

			if(!clone.getCollision().gravity(camera) && !isJumping){
				//camera.getPos().y += 0.12;
			}
		}
	}

	private void move(){
		/*
		 * verification du clavier
		 */
		boolean keyUp = Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_Z);
		boolean keyBack = Keyboard.isKeyDown(Keyboard.KEY_DOWN) || Keyboard.isKeyDown(Keyboard.KEY_S);
		boolean keyLeft = Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_Q);
		boolean keyRight = Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_D);

		/*
		 * vitesse de déplacement
		 */
		float speed = 0.0050f*delta;

		/*
		 * Déplacement
		 */
		if(keyUp)
			move(speed,1);
		if(keyBack)
			move(-speed,1);
		if(keyLeft)
			move(speed,0);
		if(keyRight)
			move(-speed,0);

	}

	private void mouse(){
		boolean leftClik = Mouse.isButtonDown(0);
		boolean rightClik = Mouse.isButtonDown(1);

		float mouseDX = Mouse.getDX() * 1 * 0.16f;
		float mouseDY = Mouse.getDY() * 1 * 0.16f;

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

		while(Mouse.next()){
			if(leftClik){
				clone.getChunkManager().delCubeAt(camera.getPos().x, camera.getPos().y, camera.getPos().z);
				clone.getChunkManager().update();
			}

			if(rightClik){
				clone.getChunkManager().addCubeAt(camera.getPos().x, camera.getPos().y, camera.getPos().z, 1);
				clone.getChunkManager().update();	
			}

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

		camera.getPos().z += tempZ;//(clone.getCollision().colideZ(camera, (float)tempZ+indiceZ))?0:(float)tempZ;
		camera.getPos().x += tempX;//(clone.getCollision().colideX(camera, (float)tempX+indiceX))?0:(float)tempX;

		if(compareChunk() || Keyboard.isKeyDown(Keyboard.KEY_P)){
			clone.getChunkManager().checkRender();
			clone.getChunkManager().update();
			System.out.println("yo");
		}
	}

	private void moveY(float amt){
		if(clone.getCollision().colideY(camera, amt)){
			isJumping = false;
		}else{
			camera.getPos().y += amt;
		}
	}

	private void jump(boolean space){
		if(!isJumping && space && getTime()-lastJump > 800){
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

	private int getDelta() {
		long currentTime = getTime();
		int delta = (int) (currentTime - lastFrame);
		lastFrame = getTime();
		return delta;
	}

	private boolean compareChunk(){
		if(curentChunk==null){
			//curentChunk.x=1;
			//curentChunk.y=1;
			//curentChunk.z=1;
		}
		Vector3f temp = clone.getCamera().getCurrentChunk();
		if(temp.x!=curentChunk.x || temp.y!=curentChunk.y || temp.z!=curentChunk.z){
			curentChunk = temp;
			return true;
		}
		return false;
	}

	public void setCurrentChunk(float[] chunk){
		curentChunk.x=chunk[0];
		curentChunk.y=chunk[1];
		curentChunk.z=chunk[2];
	}

}
