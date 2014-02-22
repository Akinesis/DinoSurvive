package Controleur;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
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
		camera = new Camera();
		input = new InputManager();
	}
	
	public void init(){
		display.create();
		matrices.init3D();
		chunk.addCube();
		
		while(!display.isClose()){
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			input.setCam(camera);
			
			input.check();
			camera.useView();
			
			chunk.draw();
			display.update();
			
			
		}
	}

}
