package modeles.entities;

import static org.lwjgl.opengl.GL11.GL_COMPILE;
import static org.lwjgl.opengl.GL11.glEndList;
import static org.lwjgl.opengl.GL11.glGenLists;
import static org.lwjgl.opengl.GL11.glNewList;

public class BlankDisplayList {
	private int cubeDisplayList;
	
	public BlankDisplayList(){
		cubeDisplayList=0;
	}
	
	public void genDisplayList(){
		cubeDisplayList = glGenLists(1);
	}
	
	public int getList(){
		return cubeDisplayList;
	}
	
	public void startDisplayList(){
		glNewList(cubeDisplayList, GL_COMPILE);
	}
	
	public void endDisplayList(){
		glEndList();
	}

}
