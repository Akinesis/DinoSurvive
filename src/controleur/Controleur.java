package controleur;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glLoadIdentity;


import modeles.Camera;
import modeles.ChunkManager;
import modeles.CollisionManager;
import modeles.InputManager;
import modeles.MapReader;
import modeles.TerrainGenerator;
import modeles.TextureManager;

import org.lwjgl.input.Keyboard;

import vues.GameDisplay;
import vues.OpenGL;



public class Controleur {

	private GameDisplay display;
	private OpenGL matrices;
	private ChunkManager chunkManager;
	private Camera camera;
	private InputManager input;
	private MapReader mapRead;
	private TextureManager texManager;
	private CollisionManager collision;
	private TerrainGenerator chunkTerre;

	/**
	 * Constructeur du controleur
	 */
	public Controleur(){
		display = new GameDisplay();
		matrices = new OpenGL();
		camera = new Camera(this);
		input = new InputManager(this);
		collision = new CollisionManager(this);
		input.setCam(camera);
		mapRead = new MapReader(this);
		chunkManager = new ChunkManager(this);
		chunkManager.setChunksList(mapRead.setChunks());
		chunkTerre = new TerrainGenerator(this);
		
	}

	//le coeur du jeu, ma méthode contenant la boucle de jeu.
	public void init(){
		display.create();
		matrices.init3D();
		texManager = new TextureManager();

		//ajoute un chunk de terre dans le chunk manager
		chunkTerre.genereTerre(1, 0, 1);
		chunkTerre.genFond(0, 0, 0);
		chunkTerre.genFond(1, 0, 0);
		
		//initialise les chunks une première fois et met les cubes dans le buffer
		chunkManager.initChunks();
		
		texManager.bindBuffer();
		texManager.bindText();

		chunkManager.delCubes();

		while(!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && !display.isClose()){
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

			//initialise la matrice 3D
			//matrices.init3D();
			glLoadIdentity();


			//bouge la came�ra
			camera.useView();

			//tout ce qui à rapport aux input
			input.check();
			
			//dessine tout les chunks
			chunkManager.drawChunks(texManager);

			display.update();
		}
	}
	
	/*
	 * Getters
	 */

	public Camera getCamera(){
		return camera;
	}

	public GameDisplay getDisplay() {
		return display;
	}

	public ChunkManager getChunkManager() {
		return chunkManager;
	}

	public InputManager getInput() {
		return input;
	}

	public TextureManager getTexManager() {
		return texManager;
	}

	public String getMap(){
		return "res/map/carte.dmp";
	}

	public MapReader getMapRead(){
		return mapRead;
	}
	
	public CollisionManager getCollision(){
		return collision;
	}

 	public void changeGragMouse(){
		display.changeGrabeMouse();
	}

	
}
