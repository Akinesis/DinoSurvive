package Modeles.entities;

import Modeles.entities.Entity3D;
import org.lwjgl.util.vector.Vector3f;

public abstract class AbstractEntity3D implements Entity3D {
	protected Vector3f pos;

	@Override
	public float getX() {
		return pos.x;
	}

	@Override
	public float getY() {
		return pos.y;
	}

	@Override
	public float getZ() {
		return pos.z;
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
