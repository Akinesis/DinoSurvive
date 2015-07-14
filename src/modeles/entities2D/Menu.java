package modeles.entities2D;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glDisableClientState;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glVertexPointer;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;

import java.nio.FloatBuffer;

import modeles.TextureManager;
import modeles.entities.AbstractEntity3D;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import controleur.Controleur;

/**
 * Classe qui gère le menu avant le jeu 
 * 
 *
 */
public class Menu extends AbstractEntity3D{
	//private static int verticiesNum = 36;
	private int vertexSize = 2;
	private int vboVertexHandle;
	private FloatBuffer vertexDataFond;
	private FloatBuffer vertexDataBoutons;
	private boolean estAfficher;
	private TextureManager textureManagerFond;
	private TextureManager textureManagerBoutons;
	private float[] boutonsLimites;
	private float[] boutonsTextureCoordonnes;
	private float[] boutonsEtats;
	private Controleur controleurClone;
	private boolean displayIsClose;
	private float displayWidth;
	private float displayHeight;
	private float tempsHorlogeTransition;
	private boolean menuIsOnScreen;
	
	public Menu(Controleur controleur){
		this.tempsHorlogeTransition = 0.5f;
		this.controleurClone = controleur;
		this.displayHeight = Display.getHeight();
		this.displayWidth = Display.getWidth();
		this.estAfficher = true;
		this.vertexDataFond = BufferUtils.createFloatBuffer(6 * this.vertexSize);
		this.vertexDataBoutons = BufferUtils.createFloatBuffer(36 * this.vertexSize);
		this.vboVertexHandle = glGenBuffers();
		this.textureManagerFond = new TextureManager("res/Menu.png");
		this.textureManagerBoutons = new TextureManager("res/Boutons_v2.png");
		this.boutonsLimites = this.generationBoutonsLimites();
		this.boutonsTextureCoordonnes = this.generationBoutonsTextureCoordonnes();
		this.boutonsEtats = this.generationBoutonsEtats();
		this.displayIsClose = false;
		this.menuIsOnScreen = true;//indique si le menu affiche le bouton retour = false  ou si il affiche les 5 autres = true
	}
	
	private float[] generationBoutonsEtats() {
		return new float[]{1, 1, 1, 1, 1, 1};
	}

	public void boutonsEtatsReset(int i) {
		int j = 0;
		while(j < 6){
			if(j != i){
				this.boutonsEtats[j] = 1f;
			}
			j++;
		}
	}
	
	public void boutonsEtatsNormale(int i) {
		this.boutonsEtats[i] = 1f;
	}
	public void boutonsEtatsHighlight(int i) {
		this.boutonsEtats[i] = 2f;
	}
	public void boutonsEtatsDark(int i) {
		this.boutonsEtats[i] = 3f;
	}
	
	
	private float[] generationBoutonsTextureCoordonnes() {
		return new float[]{0f, 0.15625f, 0.3125f, 0.46875f, 0.625f, 0.78125f, 0.9375f};
	}

	public void MenuBoutonsFonctionaliter(int position) {
		if(position == 0){
			this.inverserEstAfficher();
		}
		if(position == 1){
			System.out.println("nouvelle partie");
			this.translation(1);//1 > 0 => droite
			this.menuIsOnScreen = false;
		}
		if(position == 2){
			System.out.println("charge");
			this.translation(1);//1 > 0 => droite
			this.menuIsOnScreen = false;
		}
		if(position == 3){
			System.out.println("option");
			this.translation(1);//1 > 0 => droite
			this.menuIsOnScreen = false;
		}
		if(position == 4){
			//this.controleurClone.getDisplay().end();
			this.displayIsClose = true;
		}
		if(position == 5){
			System.out.println("back");
			this.translation(-1);//-1 < 0 => gauche
			this.menuIsOnScreen = true;
		}
	}

	private void translation(int direction){
		for(int i=12;i<this.boutonsLimites.length;i++){
			this.boutonsLimites[i] = this.boutonsLimites[i] + direction*Math.abs((0.3f + 0.9875f)*this.displayWidth*this.tempsHorlogeTransition);
		}
		if(this.tempsHorlogeTransition > 0 && 0.25f< Math.abs(this.boutonsLimites[12] -( this.boutonsLimites[12] + direction*Math.abs((0.3f + 0.9875f)*this.displayWidth*this.tempsHorlogeTransition))) ){
			
			this.tempsHorlogeTransition = this.tempsHorlogeTransition *1/2;
			try {
			    Thread.sleep(125);                 //1000 milliseconds is one second.
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glLoadIdentity();
			this.controleurClone.getMatrices().init2D();
			this.controleurClone.getHUDManager().drawMenu();
			this.controleurClone.getDisplay().update();
			this.translation(direction);
		}else{
			this.tempsHorlogeTransition = 0.5f;//reset
		}
	}
	
	public void generationMenuFond() {
		this.vertexDataFond.put(new float[]{   
			this.displayWidth, this.displayHeight,	this.displayWidth, 0,		0, 0,
			0, 0,										0, this.displayHeight,		this.displayWidth, this.displayHeight,
		});
		this.vertexDataFond.flip();
	}
	
	private float[] generationBoutonsLimites() {
		return new float[]{ 0.10f*this.displayHeight, 0.20f*this.displayHeight,//0 1
							0.22f*this.displayHeight, 0.32f*this.displayHeight,//2 3
							0.34f*this.displayHeight, 0.44f*this.displayHeight,//4 5
							0.46f*this.displayHeight, 0.56f*this.displayHeight,//6 7
							0.58f*this.displayHeight, 0.68f*this.displayHeight,//8 9
							0.85f*this.displayHeight, 0.95f*this.displayHeight,//10 11
							0.9875f*this.displayWidth, 1.216875f*this.displayHeight,//12 13
							-0.3f*this.displayWidth, (1.216875f*this.displayHeight - (0.3f + 0.9875f)*this.displayWidth) //14 15
							//need to add value for return to main menu -x.xxxxf*this.displayWidth
							//and 1.216875f*this.displayHeight - x.xxxxf for translation.
		};
	}
	
	public void generationMenuBoutons() {
		this.vertexDataBoutons.put(new float[]{
			//1	
			this.boutonsLimites[12], this.boutonsLimites[1], 	this.boutonsLimites[13], this.boutonsLimites[1], 	this.boutonsLimites[13], this.boutonsLimites[0],
			this.boutonsLimites[13], this.boutonsLimites[0], 	this.boutonsLimites[12], this.boutonsLimites[0], 	this.boutonsLimites[12], this.boutonsLimites[1],
			//2
			this.boutonsLimites[12], this.boutonsLimites[3], 	this.boutonsLimites[13], this.boutonsLimites[3], 	this.boutonsLimites[13], this.boutonsLimites[2],
			this.boutonsLimites[13], this.boutonsLimites[2], 	this.boutonsLimites[12], this.boutonsLimites[2], 	this.boutonsLimites[12], this.boutonsLimites[3],
			//3
			this.boutonsLimites[12], this.boutonsLimites[5], 	this.boutonsLimites[13], this.boutonsLimites[5], 	this.boutonsLimites[13], this.boutonsLimites[4],
			this.boutonsLimites[13], this.boutonsLimites[4], 	this.boutonsLimites[12], this.boutonsLimites[4], 	this.boutonsLimites[12], this.boutonsLimites[5],
			//4
			this.boutonsLimites[12], this.boutonsLimites[7], 	this.boutonsLimites[13], this.boutonsLimites[7], 	this.boutonsLimites[13], this.boutonsLimites[6],
			this.boutonsLimites[13], this.boutonsLimites[6], 	this.boutonsLimites[12], this.boutonsLimites[6], 	this.boutonsLimites[12], this.boutonsLimites[7],
			//5
			this.boutonsLimites[12], this.boutonsLimites[9], 	this.boutonsLimites[13], this.boutonsLimites[9], 	this.boutonsLimites[13], this.boutonsLimites[8],
			this.boutonsLimites[13], this.boutonsLimites[8], 	this.boutonsLimites[12], this.boutonsLimites[8], 	this.boutonsLimites[12], this.boutonsLimites[9],
			//6
			this.boutonsLimites[14], this.boutonsLimites[11], 	this.boutonsLimites[15], this.boutonsLimites[11], 	this.boutonsLimites[15], this.boutonsLimites[10],
			this.boutonsLimites[15], this.boutonsLimites[10], 	this.boutonsLimites[14], this.boutonsLimites[10], 	this.boutonsLimites[14], this.boutonsLimites[11],
			
			/*
			0.9875f*this.displayWidth, 0.2f*this.displayHeight,		1.216875f*this.displayHeight, 0.2f*this.displayHeight,	1.216875f*this.displayHeight, 0.1f*this.displayHeight,
			1.216875f*this.displayHeight, 0.1f*this.displayHeight,	0.9875f*this.displayWidth, 0.1f*this.displayHeight,		0.9875f*this.displayWidth, 0.2f*this.displayHeight,
			
			0.9875f*this.displayWidth, 0.32f*this.displayHeight,	1.216875f*this.displayHeight, 0.32f*this.displayHeight,	1.216875f*this.displayHeight, 0.22f*this.displayHeight,
			1.216875f*this.displayHeight, 0.22f*this.displayHeight,	0.9875f*this.displayWidth, 0.22f*this.displayHeight,	0.9875f*this.displayWidth, 0.32f*this.displayHeight,
			
			0.9875f*this.displayWidth, 0.44f*this.displayHeight,	1.216875f*this.displayHeight, 0.44f*this.displayHeight,	1.216875f*this.displayHeight, 0.34f*this.displayHeight,
			1.216875f*this.displayHeight, 0.34f*this.displayHeight,	0.9875f*this.displayWidth, 0.34f*this.displayHeight,	0.9875f*this.displayWidth, 0.44f*this.displayHeight,
			
			0.9875f*this.displayWidth, 0.56f*this.displayHeight,	1.216875f*this.displayHeight, 0.56f*this.displayHeight,	1.216875f*this.displayHeight, 0.46f*this.displayHeight,
			1.216875f*this.displayHeight, 0.46f*this.displayHeight,	0.9875f*this.displayWidth, 0.46f*this.displayHeight,	0.9875f*this.displayWidth, 0.56f*this.displayHeight,
			
			0.9875f*this.displayWidth, 0.68f*this.displayHeight,	1.216875f*this.displayHeight, 0.68f*this.displayHeight,	1.216875f*this.displayHeight, 0.58f*this.displayHeight,
			1.216875f*this.displayHeight, 0.58f*this.displayHeight,	0.9875f*this.displayWidth, 0.58f*this.displayHeight,	0.9875f*this.displayWidth, 0.68f*this.displayHeight,
			
			0.9875f*this.displayWidth, 0.95f*this.displayHeight,	1.216875f*this.displayHeight, 0.95f*this.displayHeight,	1.216875f*this.displayHeight, 0.85f*this.displayHeight,
			1.216875f*this.displayHeight, 0.85f*this.displayHeight,	0.9875f*this.displayWidth, 0.85f*this.displayHeight,	0.9875f*this.displayWidth, 0.95f*this.displayHeight,
		*/
		
		});
		this.vertexDataBoutons.flip();
	}


	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw() {
		float i;
		float j;
		float modulo;
		
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.textureManagerFond.getID());
		GL11.glBegin(GL_TRIANGLES);

		GL11.glTexCoord2d(1,1); GL11.glVertex2d(this.displayWidth, this.displayHeight);
		GL11.glTexCoord2d(1,0); GL11.glVertex2d(this.displayWidth, 0);
		GL11.glTexCoord2d(0,0); GL11.glVertex2d(0, 0);
		GL11.glTexCoord2d(0,0); GL11.glVertex2d(0, 0);
		GL11.glTexCoord2d(0,1); GL11.glVertex2d(0, this.displayHeight);
		GL11.glTexCoord2d(1,1); GL11.glVertex2d(this.displayWidth, this.displayHeight);
		
		GL11.glEnd();
		
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.textureManagerBoutons.getID());
		GL11.glBegin(GL_TRIANGLES);
		
		
		//1	
		modulo = 0.33203125f; // je suis pas sur que c'etait le nom le plus approrier !
		i = this.boutonsEtats[0] * modulo; //de base 1 * modulo
		j = (this.boutonsEtats[0] - 1) * modulo; //de base 0 * modulo
		//0.9875f*this.displayWidth, 0.2f*this.displayHeight
		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[1]); GL11.glVertex2d(this.boutonsLimites[12], this.boutonsLimites[1]);
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[1]); GL11.glVertex2d(this.boutonsLimites[13], this.boutonsLimites[1]);
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[0]); GL11.glVertex2d(this.boutonsLimites[13], this.boutonsLimites[0]);		
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[0]); GL11.glVertex2d(this.boutonsLimites[13], this.boutonsLimites[0]);
		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[0]); GL11.glVertex2d(this.boutonsLimites[12], this.boutonsLimites[0]);
		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[1]); GL11.glVertex2d(this.boutonsLimites[12], this.boutonsLimites[1]);
		//2
		i = this.boutonsEtats[1] * modulo;
		j = (this.boutonsEtats[1] - 1) * modulo;
		
		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[2]); GL11.glVertex2d(this.boutonsLimites[12], this.boutonsLimites[3]);
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[2]); GL11.glVertex2d(this.boutonsLimites[13], this.boutonsLimites[3]);
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[1]); GL11.glVertex2d(this.boutonsLimites[13], this.boutonsLimites[2]);
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[1]); GL11.glVertex2d(this.boutonsLimites[13], this.boutonsLimites[2]);
		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[1]); GL11.glVertex2d(this.boutonsLimites[12], this.boutonsLimites[2]);
		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[2]); GL11.glVertex2d(this.boutonsLimites[12], this.boutonsLimites[3]);
		//3
		i = this.boutonsEtats[2] * modulo;
		j = (this.boutonsEtats[2] - 1) * modulo;
		
		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[3]); GL11.glVertex2d(this.boutonsLimites[12], this.boutonsLimites[5]);
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[3]); GL11.glVertex2d(this.boutonsLimites[13], this.boutonsLimites[5]);
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[2]); GL11.glVertex2d(this.boutonsLimites[13], this.boutonsLimites[4]);
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[2]); GL11.glVertex2d(this.boutonsLimites[13], this.boutonsLimites[4]);
		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[2]); GL11.glVertex2d(this.boutonsLimites[12], this.boutonsLimites[4]);
		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[3]); GL11.glVertex2d(this.boutonsLimites[12], this.boutonsLimites[5]);
		//4
		i = this.boutonsEtats[3] * modulo;
		j = (this.boutonsEtats[3] - 1) * modulo;

		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[4]); GL11.glVertex2d(this.boutonsLimites[12], this.boutonsLimites[7]);
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[4]); GL11.glVertex2d(this.boutonsLimites[13], this.boutonsLimites[7]);
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[3]); GL11.glVertex2d(this.boutonsLimites[13], this.boutonsLimites[6]);
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[3]); GL11.glVertex2d(this.boutonsLimites[13], this.boutonsLimites[6]);
		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[3]); GL11.glVertex2d(this.boutonsLimites[12], this.boutonsLimites[6]);
		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[4]); GL11.glVertex2d(this.boutonsLimites[12], this.boutonsLimites[7]);
		//5
		i = this.boutonsEtats[4] * modulo;
		j = (this.boutonsEtats[4] - 1) * modulo;

		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[5]); GL11.glVertex2d(this.boutonsLimites[12], this.boutonsLimites[9]);
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[5]); GL11.glVertex2d(this.boutonsLimites[13], this.boutonsLimites[9]);
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[4]); GL11.glVertex2d(this.boutonsLimites[13], this.boutonsLimites[8]);
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[4]); GL11.glVertex2d(this.boutonsLimites[13], this.boutonsLimites[8]);
		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[4]); GL11.glVertex2d(this.boutonsLimites[12], this.boutonsLimites[8]);
		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[5]); GL11.glVertex2d(this.boutonsLimites[12], this.boutonsLimites[9]);
		//6
		i = this.boutonsEtats[5] * modulo;
		j = (this.boutonsEtats[5] - 1) * modulo;

		//TODO create this.boutonLimites[14] and this.boutonLimites[15], see this.generationBoutonsLimites.
		
		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[6]); GL11.glVertex2d(this.boutonsLimites[14], this.boutonsLimites[11]);
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[6]); GL11.glVertex2d(this.boutonsLimites[15], this.boutonsLimites[11]);
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[5]); GL11.glVertex2d(this.boutonsLimites[15], this.boutonsLimites[10]);
		GL11.glTexCoord2d(j,this.boutonsTextureCoordonnes[5]); GL11.glVertex2d(this.boutonsLimites[15], this.boutonsLimites[10]);
		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[5]); GL11.glVertex2d(this.boutonsLimites[14], this.boutonsLimites[10]);
		GL11.glTexCoord2d(i,this.boutonsTextureCoordonnes[6]); GL11.glVertex2d(this.boutonsLimites[14], this.boutonsLimites[11]);
		
		GL11.glEnd();
		
		//
	}

	public void bindBufferMenu() {
		glBindBuffer(GL_ARRAY_BUFFER, this.vboVertexHandle);
		glBufferData(GL_ARRAY_BUFFER, this.vertexDataFond, GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	public void bindDrawMenu() {
		glBindBuffer(GL_ARRAY_BUFFER, this.vboVertexHandle);
        glVertexPointer(this.vertexSize, GL_FLOAT, 0, 0L);	
		
	}

	public void enableMenu() {
		glEnableClientState(GL_VERTEX_ARRAY);
		
	}

	public void disableMenu() {
		glDisableClientState(GL_VERTEX_ARRAY);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	
	public int getVertexSize() {
		return this.vertexSize;
	}

	public int getVboVertexHandle() {
		return this.vboVertexHandle;
	}

	public FloatBuffer getVertexData() {
		return this.vertexDataFond;
	}
	
	public float[] getBoutonsLimites() {
		return this.boutonsLimites;
	}

	public float[] getBoutonsTextureCoordonnes() {
		return this.boutonsTextureCoordonnes;
	}
	
	public boolean getEstAfficher() {
		return this.estAfficher;
	}

	public FloatBuffer getVertexDataFond() {
		return this.vertexDataFond;
	}

	public FloatBuffer getVertexDataBoutons() {
		return this.vertexDataBoutons;
	}

	public TextureManager getTextureManagerFond() {
		return this.textureManagerFond;
	}

	public TextureManager getTextureManagerBoutons() {
		return this.textureManagerBoutons;
	}

	public float[] getBoutonsEtats() {
		return this.boutonsEtats;
	}

	public Controleur getControleurClone() {
		return this.controleurClone;
	}

	public float getDisplayWidth() {
		return this.displayWidth;
	}

	public float getDisplayHeight() {
		return this.displayHeight;
	}

	public boolean getDisplayIsClose() {
		return this.displayIsClose;
	}
	
	public float getTempsHorlogeTransition() {
		return tempsHorlogeTransition;
	}

	public boolean getMenuIsOnScreen() {
		return menuIsOnScreen;
	}

	public void inverserEstAfficher() {
		this.estAfficher = !(this.estAfficher);
	}

}
