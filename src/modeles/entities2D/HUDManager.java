package modeles.entities2D;

/**
 * Classe gérant le HUD et interagissant avec les entities2D qui iront dessus
 *
 *
 */

public class HUDManager extends AbstractEntity2D{
	private Cursor2D curseur;
	private Inventaire2D inventaire;
	
	/*
	 * Constructeur
	 */
	public HUDManager(){
		curseur = new Cursor2D();
		inventaire = new Inventaire2D();
		curseur.genCurseur();
		inventaire.genInventaire();
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
	
	/*
	 * Méthodes
	 */	
	@Override
	public void draw() {
		if (inventaire.getaffichInventaire()){
			inventaire.bindBuffer();
			inventaire.bindDrawInventory();
			inventaire.enableInventory();
			inventaire.draw();
			inventaire.disableInventory();
		}
		curseur.bindBuffer();	
		curseur.bindDrawCursor();
		curseur.enableCursor();
		curseur.draw();
		curseur.disableCursor();
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
