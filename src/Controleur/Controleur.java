package Controleur;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glLoadIdentity;

import org.lwjgl.input.Keyboard;

import Modeles.Camera;
import Modeles.Chunk;
import Modeles.InputManager;
import Vues.GameDisplay;
import Vues.OpenGL;

public class Controleur {
	
	private GameDisplay display;
	private OpenGL matrices;
	private Chunk chunk;
	private Camera camera;
	private InputManager input;
	
	public Controleur(){
		display = new GameDisplay();
		matrices = new OpenGL();
		chunk = new Chunk();
		camera = new Camera(this);
		input = new InputManager(this);
		input.setCam(camera);
	}
	
	public void init(){
		display.create();
		matrices.init3D();
		chunk.addCube();
		
		while(!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			matrices.init3D();
			glLoadIdentity();
			
			camera.useView();
			
			input.check();
			
			chunk.draw();
			display.update();
			
			
		}
	}
	
	public Camera getCamera(){
		return camera;
	}
	
	public void setCamera(Camera cam){
		camera = cam;
	}

}
