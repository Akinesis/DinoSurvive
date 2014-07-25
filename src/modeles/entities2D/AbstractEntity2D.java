package modeles.entities2D;

import org.lwjgl.util.vector.Vector2f;

public abstract class AbstractEntity2D{
	protected Vector2f pos;
	
	/*
	 * Getters & Setters
	 */
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
	/*
	 * Abstract methods
	 */
	public abstract void draw();
	public abstract void setUp();
	public abstract void destroy() ;
	
	public abstract float[] getCoord();
	//arbitrairement hotbar = 1, barreEtat = 2, barreSac = 3, Inventaire2D = 4, MenuJeu = 5, au cas où cursor2D = 6, le reste 0
	public abstract int getType();
	public float getTextX() {
		// TODO Auto-generated method stub
		return 0;
	}
	public float getTextY() {
		// TODO Auto-generated method stub
		return 0;
	}
}
