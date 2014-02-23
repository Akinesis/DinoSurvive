package Modeles;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import Controleur.Controleur;

public class InputManager {
	
	private Camera camera;
	private Controleur clone;
	
	public InputManager(Controleur contr){
		clone = contr;
	}
	
	public void setCam(Camera cam){
		camera = cam;
	}
	
	public void check(){
		
		setCam(clone.getCamera());
		
		float mouseDX = Mouse.getDX() * 1 * 0.16f;
		float mouseDY = Mouse.getDY() * 1 * 0.16f;
		
		boolean inverser = true;
		boolean keyC = Keyboard.isKeyDown(Keyboard.KEY_C);
		if(keyC)
			inverser=false;
		
		if(Mouse.isGrabbed() && inverser==false){
			if (camera.getRot().y + mouseDX >= 360) {										//si la souris est en haut par rapport à la caméra
				camera.getRot().y = camera.getRot().y + mouseDX - 360;							//alors on monte la caméra.
			} else if (camera.getRot().y + mouseDX < 0) {									//si la souris est plus bas que la caméra
				camera.getRot().y = 360 - camera.getRot().y + mouseDX;							//alors on baisse la caméra.
			} else {																		//sinon
				camera.getRot().y += mouseDX;													//on se place à l'endroit ou se situe la souris.
			}if (camera.getRot().x - mouseDY >= -85 && camera.getRot().x - mouseDY <= 85) {	//si la souris est proche du centre de la caméra
				camera.getRot().x += -mouseDY;													//la caméra se déplace vers la souris
			} else if (camera.getRot().x - mouseDY < -85) {									//si la souris est à gauche
				camera.getRot().x = -85;														//alors on va à droite
			} else if (camera.getRot().x - mouseDY > 85) {									//si la sours est à droite
				camera.getRot().x = 85;															//alors on va à gauche
			}
		}
		else{																			//INVERSER LES COMMENTAIRES ! J'AI LA FLEMME
			if (camera.getRot().y + mouseDX >= 360) {										//si la souris est en haut par rapport à la caméra
				camera.getRot().y = -(camera.getRot().y + mouseDX - 360);							//alors on monte la caméra.
			} else if (camera.getRot().y + mouseDX < 0) {									//si la souris est plus bas que la caméra
				camera.getRot().y = -(360 - camera.getRot().y + mouseDX);							//alors on baisse la caméra.
			} else {																		//sinon
				camera.getRot().y += -mouseDX;													//on se place à l'endroit ou se situe la souris.
			}if (camera.getRot().x - mouseDY >= -85 && camera.getRot().x - mouseDY <= 85) {	//si la souris est proche du centre de la caméra
				camera.getRot().x += mouseDY;													//la caméra se déplace vers la souris
			} else if (camera.getRot().x - mouseDY < -85) {									//si la souris est à gauche
				camera.getRot().x = 85;														//alors on va à droite
			} else if (camera.getRot().x - mouseDY > 85) {									//si la sours est à droite
				camera.getRot().x = -85;															//alors on va à gauche
			}
		}


		boolean keyUp = Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_Z);
		boolean keyDown = Keyboard.isKeyDown(Keyboard.KEY_DOWN) || Keyboard.isKeyDown(Keyboard.KEY_S);
		boolean keyLeft = Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_Q);
		boolean keyRight = Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_D);
		
		float speed = 0.04f;
		
		if(keyUp)
			move(speed,1);
		if(keyDown)
			move(-speed,1);
		if(keyLeft)
			move(speed,0);
		if(keyRight)
			move(-speed,0);
		
		clone.setCamera(camera);
	}
	
	private void move(float amt, float dir){
		double tempZ = amt * Math.sin(Math.toRadians(camera.getRot().y + 90 * dir));
		double tempX = amt * Math.cos(Math.toRadians(camera.getRot().y + 90 * dir));
		
		camera.getPos().z += tempZ;
		camera.getPos().x += tempX;
	}

}
