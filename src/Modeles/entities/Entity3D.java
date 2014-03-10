package Modeles.entities;

public interface Entity3D {
	public int getX();
	public int getY();
	public int getZ();
	public void setX(float x);
	public void setY(float y);
	public void setz(float z);
	public void setPos(float x, float y, float z);
	
	public void draw();
	public void setUp();
	public void destroy();
}