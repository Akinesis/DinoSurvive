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
	private Portrait portrait;
	
	
	private int vboVertexHandleHUD;
	private FloatBuffer interleavedBuffer;
	private Vector<AbstractEntity2D> entitiesaAfficher;
	private Vector<AbstractEntity2D> entitiesTotales;
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
		portrait = new Portrait();

		debug = new DebugText(contr);
		
		entitiesaAfficher = new Vector<AbstractEntity2D>();
		entitiesTotales = new Vector<AbstractEntity2D>();
		entitiesTotales.add(curseur);
		entitiesTotales.add(inventaire);
		entitiesTotales.add(hotbar);
		entitiesTotales.add(barreEtat);
		entitiesTotales.add(barreSac);
		entitiesTotales.add(menuJeu);
		entitiesTotales.add(portrait);
		entitiesTotales.add(curseur);
		
		entitiesaAfficher.add(hotbar);
		entitiesaAfficher.add(barreSac);
		entitiesaAfficher.add(barreEtat);
		entitiesaAfficher.add(portrait);
		entitiesaAfficher.add(curseur);
		
		
		this.menu = new Menu(contr);
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
	
	public void draw(HUDTextureManager hudtexManager) {
		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandleHUD);
		glEnableClientState(GL_VERTEX_ARRAY);
		glVertexPointer(2, GL_FLOAT,4*4, 0L);
		glClientActiveTexture(GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, hudtexManager.getID());
		glEnableClientState(GL_TEXTURE_COORD_ARRAY);
		glTexCoordPointer(2, GL_FLOAT, 4*4, 2*4 );
		glDrawArrays(GL_TRIANGLES, 0, sizeBuffer);
		glDisableClientState(GL_VERTEX_ARRAY);
		glDisableClientState(GL_TEXTURE_COORD_ARRAY);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);		
	}
	
	public void genHUD(HUDTextureManager hudtexManager){
		this.genHUDBuffer(hudtexManager);
		this.genVBO();
	}
	
	public void drawMenu(){
		this.menu.bindBufferMenu();;
		this.menu.bindDrawMenu();
		this.menu.enableMenu();
		this.menu.draw();
		this.menu.disableMenu();
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
	
	public void genHUDBuffer(HUDTextureManager texMan){
		float cubeCoord[],texCoord[];
		int j= 0;
		sizeBuffer = 0;
		for (AbstractEntity2D entity : entitiesaAfficher){
			sizeBuffer+=entity.getCoord().length ;
			sizeBuffer+=texMan.genText(entity.getType()).length;
		}
		interleavedBuffer = BufferUtils.createFloatBuffer(sizeBuffer);
		
		for(AbstractEntity2D entity : entitiesaAfficher){
			cubeCoord=entity.getCoord();
			texCoord=texMan.genText(entity.getType());
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
	public void checkVisible(){
		entitiesaAfficher.clear();
		for (AbstractEntity2D entity : entitiesTotales){
			if (entity.isVisible()){
				entitiesaAfficher.add(entity);
			}
		}
	}
	public void update(HUDTextureManager tex){
		checkVisible();
		unbindVbo();
		genHUD(tex);
		genVBO();
	}

	public Portrait getPortrait() {
		return portrait;
	}
}
