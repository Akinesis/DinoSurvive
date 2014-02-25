package Controleur;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL15.glGenBuffers;

import java.util.Vector;

import org.lwjgl.input.Keyboard;

import Modeles.Camera;
import Modeles.Chunk;
import Modeles.InputManager;
import Modeles.MapReader;
import Modeles.entities.BlankDisplayList;
import Modeles.entities.Cube3dVbo;
import Vues.GameDisplay;
import Vues.OpenGL;

public class Controleur {

	private GameDisplay display;
	private OpenGL matrices;
	private Vector<Chunk> chunks;
	private Camera camera;
	private InputManager input;
	private MapReader mapRead;
	private BlankDisplayList blank;
	private Cube3dVbo test;

	//contructeur du Controleur
	public Controleur(){
		display = new GameDisplay();
		matrices = new OpenGL();
		blank = new BlankDisplayList();
		camera = new Camera(this);
		input = new InputManager(this);
		input.setCam(camera);
		mapRead = new MapReader(this);
		chunks = mapRead.setChunks();
		test = new Cube3dVbo(0, 2, -1, 1, this);
	}

	//le coeur du jeux, ma mŽthode contenant la boucle de jeu.
	public void init(){
		display.create();
		matrices.init3D();
		//blank.genDisplayList();
		//blank.startDisplayList();
		//initialise les chunks une premi�re fois et met les cubes dans le buffer
		
		//for(Chunk chunk : chunks){
			//chunk.addCubes();
			//chunk.checkState();
			//pas opti de faire �a i�i, voir avec le ChunkManager
			//chunk.genCubes();
		//}
		//blank.endDisplayList();
		
		test.testBuffers();

		while(!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

			//initialise la matrice 3D
			//matrices.init3D();
			glLoadIdentity();


			//bouge la camŽra
			camera.useView();

			//toute se qui ˆ rapport au input
			input.check();
			
			test.draw();
			
			//dŽssine tout les chunks
			//for(Chunk chunk : chunks){
			//	chunk.draw();
			//}
			
			display.update();
		}
	}

	//sŽrie de getter
	
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
	
	public BlankDisplayList getDisplayList(){
		return blank;
	}
}
