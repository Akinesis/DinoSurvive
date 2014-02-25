package Modeles.entities;

import static org.lwjgl.opengl.GL11.glGenLists;

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

}
