package Controleur;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glLoadIdentity;

import org.newdawn.slick.Color;

import java.util.Vector;

import org.lwjgl.input.Keyboard;

import Modeles.Camera;
import Modeles.Chunk;
import Modeles.ChunkManager;
import Modeles.InputManager;
import Modeles.MapReader;
import Modeles.TextureManager;
import Vues.GameDisplay;
import Vues.OpenGL;

public class Controleur {

	private GameDisplay display;
	private OpenGL matrices;
	private ChunkManager chunkManager;
	private Camera camera;
	private InputManager input;
	private MapReader mapRead;
	private TextureManager texManager;

	//contructeur du Controleur
	public Controleur(){
		display = new GameDisplay();
		matrices = new OpenGL();
		camera = new Camera(this);
		input = new InputManager(this);
		input.setCam(camera);
		mapRead = new MapReader(this);
		chunkManager = new ChunkManager();
		chunkManager.setChunksList(mapRead.setChunks());
	}

	//le coeur du jeux, ma mŽthode contenant la boucle de jeu.
	public void init(){
		display.create();
		matrices.init3D();
		
		texManager = new TextureManager();

		//initialise les chunks une première fois et met les cubes dans le buffer
		chunkManager.initChunks();
		texManager.genTexture();
		texManager.bindBuffer();


		while(!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

			//initialise la matrice 3D
			//matrices.init3D();
			glLoadIdentity();


			//bouge la caméra
			camera.useView();

			//toute se qui à rapport au input
			input.check();

			texManager.bindText();
			
			//déssine tout les chunks
			chunkManager.drawChunks();
			texManager.drawTexture();

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
