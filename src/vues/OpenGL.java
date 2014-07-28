package vues;

import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_FOG_COLOR;
import static org.lwjgl.opengl.GL11.GL_FOG_DENSITY;
import static org.lwjgl.opengl.GL11.GL_FOG_END;
import static org.lwjgl.opengl.GL11.GL_FOG_HINT;
import static org.lwjgl.opengl.GL11.GL_FOG_MODE;
import static org.lwjgl.opengl.GL11.GL_FOG_START;
import static org.lwjgl.opengl.GL11.GL_LINEAR;
import static org.lwjgl.opengl.GL11.GL_NICEST;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glFog;
import static org.lwjgl.opengl.GL11.glFogf;
import static org.lwjgl.opengl.GL11.glFogi;
import static org.lwjgl.opengl.GL11.glHint;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import parametres.Parametres;

public class OpenGL implements Parametres {

	private int haut, larg;
	
	public OpenGL(){
		haut = hauteur;
		larg = largeur;
	}
	
	public void init3D(){
		glViewport(0, 0, larg, haut);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(fov, (float)larg/(float)haut, near, far);
		glMatrixMode(GL_MODELVIEW);	
		enable();
		fogCreate();
		glLoadIdentity();
	}

	public void reset3D(){
		glViewport(0, 0, larg, haut);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(fov, (float)larg/(float)haut, near, far);
		glMatrixMode(GL_MODELVIEW);	
		enable();
	}

	private void enable(){
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_CULL_FACE);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}

	public void init2D(){
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, larg, haut, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glDisable(GL_DEPTH_TEST);
	}

	private void fogCreate(){
		//cr�ation du fog

		//floatBuffer avec la le fog
		FloatBuffer fogColours = BufferUtils.createFloatBuffer(4);
		fogColours.put(new float[]{0.0f, 0.8f, 0.95f, 1f});
		glClearColor(0.0f, 0.8f, 0.95f, 1f);
		fogColours.flip();

		//les option du fog
		glFog(GL_FOG_COLOR, fogColours);
		glFogi(GL_FOG_MODE, GL_LINEAR);
		glHint(GL_FOG_HINT, GL_NICEST);
		glFogf(GL_FOG_START, 20);
		glFogf(GL_FOG_END, 50);
		glFogf(GL_FOG_DENSITY, 0.05f);

	}
	
	public void setSize(int ht, int la){
		haut = ht;
		larg = la;
	}

}
