package modeles.entities2D;

import org.lwjgl.util.vector.Vector2f;

public abstract class AbstractEntity2D{
	protected Vector2f pos;
	
	public int getX() {
		return (int)pos.x;
	}

	public int getY() {
		return  (int)pos.y;
	}
	
	public void setX(float x) {
		this.pos.x=x;

	}

	public void setY(float y) {
		this.pos.y=y;

	}
	
	public void setPos(float x, float y) {
		this.pos.x=x;
		this.pos.y=y;

	}

	public abstract void draw();

	public abstract void setUp();

	public abstract void destroy() ;
	
}
