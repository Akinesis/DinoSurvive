package controleur;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glLoadIdentity;

import modeles.Camera;
import modeles.ChunkManagerHash;
import modeles.CollisionManager;
import modeles.DropManager;
import modeles.DropTextureManager;
import modeles.InputManager;
import modeles.ItemManager;
import modeles.MapReader;
import modeles.RayPicker;
import modeles.TerrainGenerator;
import modeles.TextureManager;
import modeles.entities.FlatItemVBO;
import modeles.entities2D.HUDManager;
import modeles.entities2D.HUDTextureManager;

import org.lwjgl.input.Keyboard;

import parametres.Parametres;
import vues.GameDisplay;
import vues.OpenGL;

/**
 * Le controleur, au centre de tout le programme.
 * Il possède tout les objets de type "Manager" et est en charge de la boucle de jeu.
 * @author joachim
 *
 */
public class Controleur implements Parametres {

	//La fenêtre de jeu
	private GameDisplay display;
	//Le système OpenGL
	private OpenGL matrices;
	//Le gestionaire de chunks
	private ChunkManagerHash chunkManager;
	//La caméra/joueur
	private Camera camera;
	//Le gestionaire d'entrée clavier
	private InputManager input;
	//Le lecteur de sauvegarde
	private MapReader mapRead;
	//Le gestionaire de texture pour les blocks et les items
	private TextureManager texManager;
	//Le gestionaire de collisions verticale et horizontale
	private CollisionManager collision;
	//Le générateur de terrains
	private TerrainGenerator terrGen;
	//Le gestionaire de l'HUD et de son affichage
	private HUDManager hud;
	//Le gestionaire des textures pour l'HUD
	private HUDTextureManager hudtexManager;
	//La classe implémantant le Ray picking
	private RayPicker rayPick;
	//Le gestionaire des drops au sol et de leurs affichages
	private DropManager dropManager;
	//Le gestionaire des textures des drops
	private DropTextureManager dropTextManager;
	//NOT IN USE
	private ItemManager itemManager;

	/**
	 * Constructeur du controleur, il s'occupe de la déclaration des objets
	 */
	public Controleur() {
		display = new GameDisplay();
		matrices = new OpenGL();
		camera = new Camera(this);
		input = new InputManager(this);
		collision = new CollisionManager(this);
		input.setCam(camera);
		mapRead = new MapReader(this);
		//chunkManager = new ChunkManager(this);
		chunkManager = new ChunkManagerHash(this);
		chunkManager.setChunksList(mapRead.setChunks());
		terrGen = new TerrainGenerator(this);
		rayPick = new RayPicker(this);
		dropManager = new DropManager();
		itemManager = new ItemManager(this);
	}

	/**
	 * Le coeur du jeu, il initialise les gestionaire et l'openGL
	 * puis lance la boucle de jeu.
	 */
	public void init() {
		int position;

		//création de la fenêtre
		position = 5;
		display.create();
		hud = new HUDManager(this);
		texManager = new TextureManager();
		hudtexManager = new HUDTextureManager();
		dropTextManager = new DropTextureManager();

		//libération de la sourie
		this.changeGragMouse();
		
		//le menu
		while (this.hud.getMenu().getEstAfficher() && !Keyboard.isKeyDown(Keyboard.KEY_F10) && !this.display.isClose() && !this.hud.getMenu().getDisplayIsClose()) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glLoadIdentity();
			if(this.getHUDManager().getMenu().getMenuIsOnScreen()){		
				position = this.input.checkMenu(position);
			}else if(position == 1 && !this.getHUDManager().getMenu().getMenuIsOnScreen()){
				this.input.checkMenuNouvellePartie();
			}else if(position == 2 && !this.getHUDManager().getMenu().getMenuIsOnScreen()){
				this.input.checkMenuChargement();
			}else if(position == 3 && !this.getHUDManager().getMenu().getMenuIsOnScreen()){
				this.input.checkMenuOption();
			}//else if(position == 5 && !this.getHUDManager().getMenu().getMenuIsOnScreen()){
				//this.input.checkMenuNewGame();
			//}
			this.matrices.init2D();
			this.hud.drawMenu();
			this.display.update();
		}

		//Le jeu, suite au menu
		if (!this.display.isClose() && !this.hud.getMenu().getDisplayIsClose()) {
			//On vérouille la sourie
			this.changeGragMouse();

			hud.genHUD(hudtexManager);
			hud.draw(hudtexManager);

			//génération de l'openGL
			matrices.init3D();
			
			//création des différantes structures initals
			terrGen.buildStartRand();
			terrGen.genFond(1, -5, 0);
			terrGen.genWall(-1, -5, 1);
			terrGen.genereTerre(-2, -4, -2);
			
			dropManager.addDrop(new FlatItemVBO(-2, 80.2f, -2, "1"));
			dropManager.addDrop(new FlatItemVBO(-5, 80.2f, -3, "1"));
			dropManager.initDrop(dropTextManager);

			// spawn du joueur au point le plus haut en 8,Y,8
			camera.spawn(chunkManager.getHigherPointAt(8, 8));
			camera.setCurrentChunk();

			// initialise les chunks une première fois et met les cubes dans le
			// buffer, la position de la caméra doit êre connue.
			chunkManager.initChunks();

			//la boucle de jeu
			while (!Keyboard.isKeyDown(Keyboard.KEY_F10) && !this.display.isClose() && !this.hud.getMenu().getDisplayIsClose()) {
				matrices.setSize(display.getHeight(), display.getWidth());
				// texManager.bindText();

				glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

				// initialise la matrice 3D
				matrices.reset3D();
				glLoadIdentity();

				// bouge la caméra
				camera.useView();

				// tout ce qui à rapport aux input
				input.check();
				
				// dessine tous les chunks
				chunkManager.drawChunks(texManager);
				
				//dessine tout le drop
				//à remplacer par l'ItemManager
				dropManager.drawDrop(dropTextManager);
				
				//fait toutes les opérations pour le cube de picking.
				//rayPick.pick();

				// texManager.undindTexture();

				matrices.init2D();
				hud.draw(hudtexManager);

				display.update(); // met à jour la fenêtre, aucun rapport avec
									// les chunks
			}

			//chunkManager.taille();
			chunkManager.unbindAll();
		}
		texManager.deleteText();
		hudtexManager.deleteText();
		display.end();
	}

	/**
	 * Getters
	 */
	public Camera getCamera() {
		return camera;
	}

	public GameDisplay getDisplay() {
		return display;
	}

	public ChunkManagerHash getChunkManager() {
		return chunkManager;
	}

	public InputManager getInput() {
		return input;
	}

	public TextureManager getTexManager() {
		return texManager;
	}

	public String getMap() {
		return "res/map/carte.dmp";
	}

	public MapReader getMapRead() {
		return mapRead;
	}

	public CollisionManager getCollision() {
		return collision;
	}

	public HUDManager getHUDManager() {
		return hud;
	}

	public void changeGragMouse() {
		display.changeGrabeMouse();
	}

	public HUDTextureManager getHUDTextManager() {
		return this.hudtexManager;
	}

	public TerrainGenerator getTerrainGenerator() {
		return terrGen;
	}
	
	public OpenGL getMatrices(){
		return matrices;
	}
	
	public RayPicker getRayPicker(){
		return rayPick;
	}

}
