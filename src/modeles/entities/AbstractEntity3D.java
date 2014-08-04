package modeles.entities;


import modeles.entities.Entity3D;

import org.lwjgl.util.vector.Vector3f;


public abstract class AbstractEntity3D implements Entity3D {
	protected Vector3f pos;

	
	public int getX() {
		return (int)pos.x;
	}

	
	public int getY() {
		return  (int)pos.y;
	}

	
	public int getZ() {
		return (int)pos.z;
	}

	
	public void setX(float x) {
		this.pos.x=x;

	}

	
	public void setY(float y) {
		this.pos.y=y;

	}

	
	public void setz(float z) {
		this.pos.z=z;

	}

	
	public void setPos(float x, float y, float z) {
		this.pos.x=x;
		this.pos.y=y;
		this.pos.z=z;

	}

	
	public abstract void draw();

	
	public abstract void setUp();

	
	public abstract void destroy() ;

}