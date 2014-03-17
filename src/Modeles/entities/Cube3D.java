package Modeles.entities;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glCallList;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;
import static org.lwjgl.opengl.GL11.glColor3f;

import org.lwjgl.util.vector.Vector3f;

import Parametres.Parametres;


public class Cube3D extends AbstractEntity3D implements Parametres{
	
	protected Vector3f pos2;
	protected float size;
	protected BlankDisplayList cubeDisplayList;
	private boolean etat;
	
	public Cube3D(float x, float y, float z, float size){
		pos = new Vector3f(x, y, z);
		pos2 = new Vector3f(pos.x+size, pos.y+size, pos.z+size);
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
			return (temp.pos.x==pos.x && temp.pos.y == pos.y && temp.pos.z == pos.z);
		}
		return false;
	}
	
	public void genCube(){
		glBegin(GL_TRIANGLES);	
		
		{//south face
			glColor3f(0.2f, 0.5f, 0.5f);
			glVertex3f(pos.x, pos.y, pos.z);
			glVertex3f(pos.x, pos2.y, pos.z);
			glVertex3f(pos2.x, pos.y, pos.z);

			glVertex3f(pos2.x, pos.y, pos.z);
			glVertex3f(pos.x, pos2.y, pos.z);
			glVertex3f(pos2.x, pos2.y, pos.z);
		}
		
		{//north face
			glVertex3f(pos.x, pos.y, pos2.z);
			glVertex3f(pos2.x, pos.y, pos2.z);
			glVertex3f(pos.x, pos2.y, pos2.z);
			
			glVertex3f(pos.x, pos2.y, pos2.z);
			glVertex3f(pos2.x, pos.y, pos2.z);
			glVertex3f(pos2.x, pos2.y, pos2.z);
		}
		
		{//bottom face
			glVertex3f(pos.x, pos2.y, pos.z);
			glVertex3f(pos.x, pos2.y, pos2.z);
			glVertex3f(pos2.x, pos2.y, pos.z);

			glVertex3f(pos2.x, pos2.y, pos.z);
			glVertex3f(pos.x, pos2.y, pos2.z);
			glVertex3f(pos2.x, pos2.y, pos2.z);
		}
		
		{//top face;
			glVertex3f(pos.x, pos.y, pos.z);
			glVertex3f(pos2.x, pos.y, pos.z);
			glVertex3f(pos.x, pos.y, pos2.z);
			
			glVertex3f(pos.x, pos.y, pos2.z);
			glVertex3f(pos2.x, pos.y, pos.z);
			glVertex3f(pos2.x, pos.y, pos2.z);		
		}
		
		{//east face
			glVertex3f(pos2.x, pos.y, pos.z);
			glVertex3f(pos2.x, pos2.y, pos.z);
			glVertex3f(pos2.x, pos.y, pos2.z);

			glVertex3f(pos2.x, pos.y, pos2.z);
			glVertex3f(pos2.x, pos2.y, pos.z);
			glVertex3f(pos2.x, pos2.y, pos2.z);
		}
		
		{//west face
			glVertex3f(pos.x, pos.y, pos.z);
			glVertex3f(pos.x, pos.y, pos2.z);
			glVertex3f(pos.x, pos2.y, pos.z);

			glVertex3f(pos.x, pos2.y, pos.z);
			glVertex3f(pos.x, pos.y, pos2.z);
			glVertex3f(pos.x, pos2.y, pos2.z);
		}
		
		glEnd();
	}
	
	/**
	 *
	 * @return true si le cube est actif.
=======
	/**
	 * 
	 * @return true si le cube est actif
>>>>>>> 99a04e5f1bc8a53f0b6fea51b8ce1be7e0cf9f1f
=======
	/**
	 * 
	 * @return true si le cube est actif
>>>>>>> 99a04e5f1bc8a53f0b6fea51b8ce1be7e0cf9f1f
	 */
	public boolean getState(){
		return etat;
	}

}
