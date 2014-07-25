package modeles.entities2D;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

import controleur.Controleur;

/**
 * Classe gérant le HUD et interagissant avec les entities2D qui iront dessus
 *
 *
 */

public class HUDManager extends AbstractEntity2D{
	private Cursor2D curseur;
	private Inventaire2D inventaire;
	private Hotbar hotbar;
	private DebugText debug;
	private Menu menu;
	private BarreEtat barreEtat;
	private BarreSac barreSac;
	private MenuJeu menuJeu;
	
	
	private HUDTextureManager hudText;
	private int vboVertexHandleHUD;
	private FloatBuffer interleavedBuffer;
	
	/*
	 * Constructeur
	 */
	public HUDManager(Controleur contr){
		curseur = new Cursor2D();
		inventaire = new Inventaire2D();
		hotbar = new Hotbar();
		barreEtat = new BarreEtat();
		barreSac = new BarreSac();
		menuJeu = new MenuJeu();
		
		curseur.genCurseur();
		inventaire.genInventaire();
		hotbar.genHotbar();
		barreEtat.genBarreEtat();
		barreSac.genBarreSac();
		menuJeu.genMenuJeu();
		
		debug = new DebugText(contr);
		
		this.menu = new Menu();
		this.menu.generationMenuFond();
		this.menu.generationMenuBoutons();
	}
	
	/*
	 * Getters
	 */
	public Cursor2D getCurseur(){
		return curseur;
	}
	public Inventaire2D getInventaire(){
		return inventaire;
	}
	public Hotbar getHotbar(){
		return hotbar;
	}
	public BarreEtat getBarreEtat(){
		return barreEtat;
	}
	public MenuJeu getMenuJeu(){
		return menuJeu;
	}
	/*
	 * Méthodes
	 */	
	
	/**
	 * VBos
	 */
	public void genVBO(){
		vboVertexHandleHUD = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboVertexHandleHUD);		
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, interleavedBuffer, GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	@Override
	public void draw() {
		//affichage de l'inventaire
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		if(debug.getModeDebug()){
			debug.setUp();
			debug.draw();
			debug.disable();
		}
		//hotfix du conflit debug/2d
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		

		if (this.menu.getEstAfficher()){
			this.menu.bindBufferMenu();;
			this.menu.bindDrawMenu();
			this.menu.enableMenu();
			this.menu.draw();
			this.menu.disableMenu();
		}else{
			//inhibe le curseur tantque e menu principale est present
			//affichage des données en plus (pour test et compagnie)
			if (inventaire.getaffichInventaire()){
				inventaire.bindBuffer();
				inventaire.bindDrawInventory();
				inventaire.enableInventory();
				inventaire.draw();
				inventaire.disableInventory();
			}
			if(menuJeu.getAffichMenu()){
				menuJeu.bindBuffer();
				menuJeu.bindDrawMenuJeu();
				menuJeu.enableMenuJeu();
				menuJeu.draw();
				menuJeu.disableMenuJeu();
			}
			curseur.bindBuffer();	
			curseur.bindDrawCursor();
			curseur.enableCursor();
			curseur.draw();
			curseur.disableCursor();
			
			hotbar.bindBuffer();
			hotbar.bindDrawHotbar();
			hotbar.enableHotbar();
			hotbar.draw();
			hotbar.disableHotbar();
			
			barreEtat.bindBuffer();
			barreEtat.bindDrawBarreEtat();
			barreEtat.enableBarreEtat();
			barreEtat.draw();
			barreEtat.disableBarreEtat();
			
			barreSac.bindBuffer();
			barreSac.bindDrawBarreSac();
			barreSac.enableBarreSac();
			barreSac.draw();
			barreSac.disableBarreSac();
		}
		
	}

	public void draw(HUDTextureManager hudtexManager) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public DebugText getModeDebug() {
		return this.debug;
	}


	public Menu getMenu() {
		return this.menu;
	}

	@Override
	public float[] getCoord() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getType() {
		return 0;
	}


}
