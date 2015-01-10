package modeles.entities;

import org.lwjgl.util.vector.Vector3f;

public class FlatItemVBO extends AbstractEntity3D {
	
	float size;
	protected Vector3f pos2;
	int id;
	
	public FlatItemVBO(float x, float y, float z, int identifiant){
		size = 1;
		pos = new Vector3f(-x, y, -z);
		pos2 = new Vector3f(pos.x+size, pos.y+size, pos.z);
		id = identifiant;
		System.out.println(pos);
		System.out.println(pos2);
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
	
	public float[] genCubes(){
		return new float[]{
				//south
				pos.x, pos.y, pos.z,
				pos.x, pos2.y, pos.z,
				pos2.x, pos.y, pos.z,
				//pos2.x, pos.y, pos.z,
				//pos.x, pos2.y, pos.z,
				//pos2.x, pos2.y, pos.z,

				//north
				//pos2.x, pos2.y, pos2.z,
				//pos.x, pos2.y, pos2.z,
				//pos.x, pos.y, pos2.z,
				//pos2.x, pos2.y, pos2.z,
				//pos.x, pos.y, pos2.z,
				//pos2.x, pos.y, pos2.z,

		};
	}

}
