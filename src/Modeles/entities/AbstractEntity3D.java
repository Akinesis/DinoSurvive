package Modeles.entities;

import Modeles.entities.Entity3D;
import org.lwjgl.util.vector.Vector3f;

public abstract class AbstractEntity3D implements Entity3D {
	protected Vector3f pos;

	@Override
	public int getX() {
		return (int)pos.x;
	}

	@Override
	public int getY() {
		return  (int)pos.y;
	}

	@Override
	public int getZ() {
		return (int)pos.z;
	}

	@Override
	public void setX(float x) {
		this.pos.x=x;

	}

	@Override
	public void setY(float y) {
		this.pos.y=y;

	}

	@Override
	public void setz(float z) {
		this.pos.z=z;

	}

	@Override
	public void setPos(float x, float y, float z) {
		this.pos.x=x;
		this.pos.y=y;
		this.pos.z=z;

	}

	@Override
	public abstract void draw();

	@Override
	public abstract void setUp();

	@Override
	public abstract void destroy() ;

}