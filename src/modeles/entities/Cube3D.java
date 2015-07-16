package modeles.entities;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glCallList;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;
import static org.lwjgl.opengl.GL11.glColor3f;

import org.lwjgl.util.vector.Vector3f;

import parametres.Parametres;



public class Cube3D extends AbstractEntity3D implements Parametres{
	
	protected Vector3f pos2;
	protected float size;
	protected BlankDisplayList cubeDisplayList;
	private boolean etat;
	
	public Cube3D(float x, float y, float z, float size){
		pos = new Vector3f(-x, y, -z); //y positifs.
		pos2 = new Vector3f(pos.getX()+size, pos.getY()+size, pos.getZ()+size);
		this.size=size;
		
		//cubeDisplayList = clone.getDisplayList();
		etat = true;
	}

	@Override
	public void draw() {
		glCallList(cubeDisplayList.getList());
	}

	@Override
	public void setUp() {
	}

	@Override
	public void destroy() {
	}
	
	public void changeState(){
		etat = !etat;
	}
	
	public boolean equals(Object o){
		if(o instanceof Cube3D){
			Cube3D temp = (Cube3D)o;
			return (temp.pos.getX()==pos.getX() && temp.pos.getY() == pos.getY() && temp.pos.getZ() == pos.getZ());
		}
		return false;
	}
	
	public void genCube(){
		glBegin(GL_TRIANGLES);	
		
		{//south face
			glColor3f(0.9f, 0.5f, 0.9f);
			glVertex3f(pos.getX(), pos.getY(), pos.getZ());
			glVertex3f(pos.getX(), pos2.getY(), pos.getZ());
			glVertex3f(pos2.getX(), pos.getY(), pos.getZ());

			glVertex3f(pos2.getX(), pos.getY(), pos.getZ());
			glVertex3f(pos.getX(), pos2.getY(), pos.getZ());
			glVertex3f(pos2.getX(), pos2.getY(), pos.getZ());
		}
		
		{//north face
			glVertex3f(pos.getX(), pos.getY(), pos2.getZ());
			glVertex3f(pos2.getX(), pos.getY(), pos2.getZ());
			glVertex3f(pos.getX(), pos2.getY(), pos2.getZ());
			
			glVertex3f(pos.getX(), pos2.getY(), pos2.getZ());
			glVertex3f(pos2.getX(), pos.getY(), pos2.getZ());
			glVertex3f(pos2.getX(), pos2.getY(), pos2.getZ());
		}
		
		{//bottom face
			glVertex3f(pos.getX(), pos2.getY(), pos.getZ());
			glVertex3f(pos.getX(), pos2.getY(), pos2.getZ());
			glVertex3f(pos2.getX(), pos2.getY(), pos.getZ());

			glVertex3f(pos2.getX(), pos2.getY(), pos.getZ());
			glVertex3f(pos.getX(), pos2.getY(), pos2.getZ());
			glVertex3f(pos2.getX(), pos2.getY(), pos2.getZ());
		}
		
		{//top face;
			glVertex3f(pos.getX(), pos.getY(), pos.getZ());
			glVertex3f(pos2.getX(), pos.getY(), pos.getZ());
			glVertex3f(pos.getX(), pos.getY(), pos2.getZ());
			
			glVertex3f(pos.getX(), pos.getY(), pos2.getZ());
			glVertex3f(pos2.getX(), pos.getY(), pos.getZ());
			glVertex3f(pos2.getX(), pos.getY(), pos2.getZ());		
		}
		
		{//east face
			glVertex3f(pos2.getX(), pos.getY(), pos.getZ());
			glVertex3f(pos2.getX(), pos2.getY(), pos.getZ());
			glVertex3f(pos2.getX(), pos.getY(), pos2.getZ());

			glVertex3f(pos2.getX(), pos.getY(), pos2.getZ());
			glVertex3f(pos2.getX(), pos2.getY(), pos.getZ());
			glVertex3f(pos2.getX(), pos2.getY(), pos2.getZ());
		}
		
		{//west face
			glVertex3f(pos.getX(), pos.getY(), pos.z);
			glVertex3f(pos.getX(), pos.getY(), pos2.z);
			glVertex3f(pos.getX(), pos2.getY(), pos.z);

			glVertex3f(pos.getX(), pos2.getY(), pos.z);
			glVertex3f(pos.getX(), pos.getY(), pos2.z);
			glVertex3f(pos.getX(), pos2.getY(), pos2.z);
		}
		
		glEnd();
	}
	
	/**
	 *
	 * @return true si le cube est actif.
	 */
	public boolean getState(){
		return etat;
	}
	
	public void setEtat(boolean state){
		etat = state;
	}

}
