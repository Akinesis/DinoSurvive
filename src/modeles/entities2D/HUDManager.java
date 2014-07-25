package modeles.entities2D;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_COORD_ARRAY;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glDisableClientState;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glTexCoordPointer;
import static org.lwjgl.opengl.GL11.glVertexPointer;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glClientActiveTexture;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;

import java.nio.FloatBuffer;
import java.util.Vector;

import org.lwjgl.BufferUtils;
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
	private Vector<AbstractEntity2D> entitiesaAfficher;
	private int sizeBuffer;
	
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
		entitiesaAfficher = new Vector<AbstractEntity2D>();
		
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
			//en commentaire ancienne version, sans vbo, pas d'interleaving, pas bien
	/**		if (inventaire.getaffichInventaire()){
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
			**/
		}
		
		
			
	}

	public void draw(HUDTextureManager hudtexManager) {
		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandleHUD);
		glEnableClientState(GL_VERTEX_ARRAY);
		glVertexPointer(2, GL_FLOAT, 4*4, 0L);
		glClientActiveTexture(GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, hudText.getID());
		glEnableClientState(GL_TEXTURE_COORD_ARRAY);
		glTexCoordPointer(2, GL_FLOAT, 4*4, 2*4 );
		glDrawArrays(GL_TRIANGLES, 0, sizeBuffer);
		glDisableClientState(GL_VERTEX_ARRAY);
		glDisableClientState(GL_TEXTURE_COORD_ARRAY);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		
	}
	
	
	
	@Override
	public void setUp() {
		
	}

	@Override
	public void destroy() {
		
	}

	public DebugText getModeDebug() {
		return this.debug;
	}


	public Menu getMenu() {
		return this.menu;
	}

	@Override
	public float[] getCoord() {
		return null;
	}

	@Override
	public int getType() {
		return 0;
	}
	
	/**
	 * VBos
	 */
	public void genVBO(){
		vboVertexHandleHUD = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboVertexHandleHUD);		
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, interleavedBuffer, GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}

	public void unbindVbo(){
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL15.glDeleteBuffers(vboVertexHandleHUD);
		if(interleavedBuffer!=null){
			interleavedBuffer.clear();
		}
	}
	
	public void genHUD(HUDTextureManager texMan){
		float cubeCoord[],texCoord[];
		int j= 0;
		sizeBuffer = 0;
		for (AbstractEntity2D entity : entitiesaAfficher){
			sizeBuffer+=entity.getCoord().length;
		}
		interleavedBuffer = BufferUtils.createFloatBuffer(sizeBuffer);
		
		for(AbstractEntity2D entity : entitiesaAfficher){
			cubeCoord=entity.getCoord();
			texCoord=texMan.genText(entity.getType(), entity.getTextX(), entity.getTextY());
			for(int i = 0; i< cubeCoord.length; i+=2){
				interleavedBuffer.put(cubeCoord[i]);
				interleavedBuffer.put(cubeCoord[i+1]);

				interleavedBuffer.put(texCoord[j]);
				interleavedBuffer.put(texCoord[j+1]);

				j+=2;
			}
			j=0;
		}
		interleavedBuffer.flip();
	}
}
