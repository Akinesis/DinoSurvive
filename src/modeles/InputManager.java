package modeles;


import java.util.Arrays;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
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
	private long lastFPS;
	private int fps;

	public InputManager(Controleur contr){
		clone = contr;
		clone.getMap();//à changer; inutile
		isJumping = false;
		lastFrame=getTime();
		lastFPS = getTime();
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
		boolean keyINSERT = Keyboard.isKeyDown(Keyboard.KEY_K);
		boolean keydol = Keyboard.isKeyDown(Keyboard.KEY_HOME); 
		boolean keyReturn = Keyboard.isKeyDown(Keyboard.KEY_RETURN);
		boolean keyJump = Keyboard.isKeyDown(Keyboard.KEY_SPACE); 
		boolean keyBas = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
		delta = getDelta();

		//updateFPS();

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
				clone.getHUDManager().getPortrait().changeVisible();
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
		
		if (getTime() - lastFPS > 150) {
			clone.getChunkManager().reloadChunks();
			clone.getChunkManager().createChunks();
			lastFPS += 150; //
		}
		
		if(!clone.getCollision().gravity(camera) && !isJumping){
			camera.getPos().y += 0.12;
		}

	}

	public int checkMenu(int position){
		int boutons[] = new int[]{0,1,2,3,4};
		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_DOWN){
				if(Keyboard.getEventKeyState()){
					this.clone.getHUDManager().getMenu().boutonsEtatsNormale(position);
					if(position != 4){
						position = boutons[position+1];
					}else{
						position = boutons[0];
					}
				}
			}
			if (Keyboard.getEventKey() == Keyboard.KEY_UP){
				if(Keyboard.getEventKeyState()){
					this.clone.getHUDManager().getMenu().boutonsEtatsNormale(position);
					if(position != 0){
						position = boutons[position-1];
					}else{
						position = boutons[4];
					}
				}
			}
			this.clone.getHUDManager().getMenu().boutonsEtatsHighlight(position);
			if(Keyboard.getEventKey() == Keyboard.KEY_RETURN){
				if(Keyboard.getEventKeyState()){
					this.clone.getHUDManager().getMenu().boutonsEtatsDark(position);
					this.clone.getHUDManager().getMenu().MenuBoutonsFonctionaliter(position);
				}
			}
		}
		while(Mouse.next()){
			if(Mouse.isButtonDown(0)){
				this.clone.getHUDManager().getMenu().boutonsEtatsDark(position);
				this.clone.getHUDManager().getMenu().MenuBoutonsFonctionaliter(position);
			}
		}

		if( (Mouse.getX() < 0.9875f*Display.getWidth()) && (Mouse.getX() > 0.6375f*Display.getWidth()) ){
			if( (Mouse.getY() > Display.getHeight() - 0.2f*Display.getHeight()) && (Mouse.getY() < Display.getHeight() - 0.1f*Display.getHeight()) ){
				position = 0;
			}
			if( (Mouse.getY() >  Display.getHeight() - 0.32f*Display.getHeight()) && (Mouse.getY() < Display.getHeight() - 0.22f*Display.getHeight()) ){
				position = 1;
			}
			if( (Mouse.getY() > Display.getHeight() - 0.44f*Display.getHeight()) && (Mouse.getY() < Display.getHeight() - 0.34f*Display.getHeight()) ){
				position = 2;
			}
			if( (Mouse.getY() > Display.getHeight() - 0.56f*Display.getHeight()) && (Mouse.getY() < Display.getHeight() - 0.46f*Display.getHeight()) ){
				position = 3;
			}
			if( (Mouse.getY() > Display.getHeight() - 0.68f*Display.getHeight()) && (Mouse.getY() < Display.getHeight() - 0.58f*Display.getHeight()) ){
				position = 4;
			}
			//System.out.println(position);
		}
		float[] test = new float[]{1, 1, 1, 1, 1} ;
		test[position] = this.clone.getHUDManager().getMenu().getBoutonsEtats()[position];
		if( (!Mouse.isButtonDown(0) && !(Keyboard.getEventKey() == Keyboard.KEY_RETURN)) || !Arrays.equals(this.clone.getHUDManager().getMenu().getBoutonsEtats(),test) ){
			this.clone.getHUDManager().getMenu().boutonsEtatsReset(position);
			this.clone.getHUDManager().getMenu().boutonsEtatsHighlight(position);
		}

		return position;
	}

	private void move(){
		/*
		 * verification du clavier
		 */
		boolean keyUp = Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_Z);
		boolean keyBack = Keyboard.isKeyDown(Keyboard.KEY_DOWN) || Keyboard.isKeyDown(Keyboard.KEY_S);
		boolean keyLeft = Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_Q);
		boolean keyRight = Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_D);

		Keyboard.isKeyDown(Keyboard.KEY_A);
		Keyboard.isKeyDown(Keyboard.KEY_Q); Keyboard.isKeyDown(Keyboard.KEY_W); Keyboard.isKeyDown(Keyboard.KEY_E);

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

		if(leftClik){
			clone.getChunkManager().delCubeAt(camera.getPos().x, camera.getPos().y, camera.getPos().z);
			clone.getChunkManager().updateAt(camera.getPos().x, camera.getPos().y, camera.getPos().z);
		}

		if(rightClik){
			clone.getChunkManager().addCubeAt(camera.getPos().x, camera.getPos().y, camera.getPos().z, 1);
			clone.getChunkManager().update();	
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

		float indiceX = ((camera.getPos().x)>posTempX )?-0.15f:+0.15f;
		float indiceZ = ((camera.getPos().z)>posTempZ )?-0.15f:+0.15f;

		int facteurX=0 , facteurZ =0;

		camera.getPos().z += (clone.getCollision().colideZ(camera, (float)tempZ+indiceZ))?0:(float)tempZ;
		camera.getPos().x += (clone.getCollision().colideX(camera, (float)tempX+indiceX))?0:(float)tempX;

		if(Math.abs(tempX)>=Math.abs(tempZ)){
			facteurX=(tempX>0)?4:-4;
		}else{
			facteurZ=(tempZ>0)?4:-4;
		}

		if(compareChunk()){
			clone.getChunkManager().createChunksInit(facteurX, facteurZ);
			clone.getChunkManager().checkRender();
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

		Vector3f temp = clone.getCamera().getCurrentChunk();
		if(temp.x!=curentChunk.x || temp.y!=curentChunk.y || temp.z!=curentChunk.z){
			setCurrentChunk(temp);
			return true;
		}
		return false;
	}

	public void setCurrentChunk(Vector3f chunk){
		curentChunk.x=chunk.x;
		curentChunk.y=chunk.y;
		curentChunk.z=chunk.z;
	}

	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			System.out.println(fps);
			fps = 0; //reset the FPS counter
			lastFPS += 1000; //add one second
		}
		fps++;
	}

}
