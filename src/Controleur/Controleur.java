package Controleur;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glLoadIdentity;

import java.util.Vector;

import org.lwjgl.input.Keyboard;

import Modeles.Camera;
import Modeles.Chunk;
import Modeles.InputManager;
import Modeles.MapReader;
import Vues.GameDisplay;
import Vues.OpenGL;

public class Controleur {

	private GameDisplay display;
	private OpenGL matrices;
	private Vector<Chunk> chunks;
	private Camera camera;
	private InputManager input;
	private MapReader mapRead;

	//contructeur du Controleur
	public Controleur(){
		display = new GameDisplay();
		matrices = new OpenGL();
		camera = new Camera(this);
		input = new InputManager(this);
		input.setCam(camera);
		mapRead = new MapReader(this);
		chunks = mapRead.setChunks();
	}

	//la coeure du jeux, ma méthode contenant la boucle de jeu.
	public void init(){
		display.create();
		matrices.init3D();

		//initialise les chunks une première fois et met les cubes dans le buffer
		for(Chunk chunk : chunks){
			chunk.addCubes();
			chunk.checkState();
			//pas opti de faire ça içi, voir avec le ChunkManager
			chunk.genCubes();
		}

		while(!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

			//initialise la matrice 3D
			matrices.init3D();
			glLoadIdentity();

			//bouge la caméra
			camera.useView();

			//toute se qui à rapport au input
			input.check();
			
			//déssine tout les chunks
			for(Chunk chunk : chunks){
				chunk.draw();
			}

			display.update();

		}
	}

	//série de getter
	
	public Camera getCamera(){
		return camera;
	}

	public void setCamera(Camera cam){
		camera = cam;
	}

	public String getMap(){
		return "res/map/carte.dmp";
	}
	
	public MapReader getMapRead(){
		return mapRead;
	}

}
