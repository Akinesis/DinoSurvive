package modeles.entities2D;

import org.lwjgl.opengl.GL11;

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
	public void drawMenu(){
		this.menu.bindBufferMenu();;
		this.menu.bindDrawMenu();
		this.menu.enableMenu();
		this.menu.draw();
		this.menu.disableMenu();
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
}
