package modeles.entities;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class FlatItemVBO extends AbstractEntity3D {
	
	float size;
	protected Vector3f pos2;
	int id;
	Vector2f coord;
	
	public FlatItemVBO(float x, float y, float z, int identifiant){
		size = 0.5f;
		pos = new Vector3f(-x, y, -z);
		pos2 = new Vector3f(pos.x+size, pos.y+size, pos.z);
		id = identifiant;
		coord = new Vector2f();
		
		//coord à récupérer en fonction de l'id
		coord.x=357;
		coord.y=85;
		
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
				//north
				pos2.x, pos2.y, pos.z,
				pos.x, pos2.y, pos.z,
				pos2.x, pos.y, pos.z,
				
				pos2.x, pos.y, pos.z,
				pos.x, pos2.y, pos.z,
				pos.x, pos.y, pos.z,

		};
	}
	
	public void updateY(float moove){
		pos.y += moove;
		pos2.y += moove;
	}
	
	public Vector2f getCoord(){
		return coord;
	}

}
