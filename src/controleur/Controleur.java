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
import modeles.entities2D.HUDManager;
import modeles.entities2D.Menu;

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
	private TerrainGenerator terrGen;
	private HUDManager hud;

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
		terrGen = new TerrainGenerator(this);
	}

	//le coeur du jeu, ma méthode contenant la boucle de jeu.
	public void init(){
		display.create();
		hud = new HUDManager(this);
		texManager = new TextureManager();	
		while(this.hud.getMenu().getEstAfficher() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && !display.isClose()){
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glLoadIdentity();
			input.check();
			matrices.init2D();
			this.hud.draw();//peut-etre cree et utilise un drawMenu()
			display.update();
		}
		
		matrices.init3D();
		//ajoute un chunk de terre dans le chunk manager
		/*terrGen.genereTerre(1, 0, 1);
		terrGen.genereTerre(-1, -1, 0);
		terrGen.genereTerre(-1, 0, 0);
		terrGen.genFond(0, 0, 0);
		terrGen.genFond(1, 0, 0);
		terrGen.genTree(1, 0, 0);*/
		terrGen.buildStart();
		terrGen.genFond(1, -5, 0);
		//terrGen.genWall(1, -5, 0);
		camera.spawn(chunkManager.getHigherPointAt(8, 8));
		
		//initialise les chunks une première fois et met les cubes dans le buffer
		chunkManager.initChunks();
		

		while(!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && !display.isClose()){
		
			texManager.bindText();
			
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

			//initialise la matrice 3D
			matrices.init3D();
			glLoadIdentity();


			//bouge la came�ra
			camera.useView();

			//tout ce qui à rapport aux input
			input.check();
			
			
			//dessine tout les chunks
			chunkManager.drawChunks(texManager);
			
			//texManager.undindTexture();
			
			matrices.init2D();
			hud.draw();

			display.update(); //met à jour la fenêtre, aucun rapport avec les chunks
		}
		
		chunkManager.unbindAll();
		texManager.deleteText();
		display.end();
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
	
	public HUDManager getHUDManager(){
		return hud;
	}

 	public void changeGragMouse(){
		display.changeGrabeMouse();
	}

	
}
