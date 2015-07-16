package modeles.entities;


import modeles.entities.Entity3D;

import org.lwjgl.util.vector.Vector3f;


public abstract class AbstractEntity3D implements Entity3D {
	protected Vector3f pos;

	
	public int getX() {
		return (int)pos.getX();
	}

	
	public int getY() {
		return  (int)pos.getY();
	}

	
	public int getZ() {
		return (int)pos.getZ();
	}

	
	public void setX(float x) {
		pos.setX(x);

	}

	
	public void setY(float y) {
		pos.setY(y);

	}

	
	public void setZ(float z) {
		pos.setZ(z);

	}

	
	public void setPos(float x, float y, float z) {
		pos.setX(x);
		pos.setY(y);
		pos.setZ(z);

	}

	
	public abstract void draw();

	
	public abstract void setUp();

	
	public abstract void destroy() ;

}