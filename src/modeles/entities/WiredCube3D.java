package modeles.entities;

import org.lwjgl.util.vector.Vector3f;

public class WiredCube3D extends Cube3D {
	
	public WiredCube3D(float x, float y, float z, float size){
		super(x, y, z, size);

	}

	public void draw() {
		genCube();
	}

	public void destroy(){

	}

	public float[] genCubes(){

		return new float[]{
				pos.getX(), pos.getY(), pos.getZ(),
				pos.getX(), pos.getY()-size, pos.getZ(),
				
				pos.getX(), pos.getY(), pos.getZ(),
				pos.getX()+size, pos.getY(), pos.getZ(),
				
				pos.getX(), pos.getY()-size, pos.getZ(),
				pos.getX()+size, pos.getY()-size, pos.getZ(),

				pos.getX()+size, pos.getY()-size, pos.getZ(),
				pos.getX()+size, pos.getY(), pos.getZ(),

				//north face
				pos.getX(), pos.getY(), pos.getZ()+size,
				pos.getX(), pos.getY()-size, pos.getZ()+size,

				pos.getX(), pos.getY(), pos.getZ()+size,
				pos.getX()+size, pos.getY(), pos.getZ()+size,

				pos.getX(), pos.getY()-size, pos.getZ()+size,
				pos.getX()+size, pos.getY()-size, pos.getZ()+size,

				pos.getX()+size, pos.getY()-size, pos.getZ()+size,
				pos.getX()+size, pos.getY(), pos.getZ()+size,

				//bottom face
				pos.getX(), pos.getY()-size, pos.getZ(),
				pos.getX(), pos.getY()-size, pos.getZ()+size,

				pos.getX()+size, pos.getY()-size, pos.getZ(),
				pos.getX()+size, pos.getY()-size, pos.getZ()+size,


				//top face
				pos.getX(), pos.getY(), pos.getZ(),
				pos.getX(), pos.getY(), pos.getZ()+size,

				pos.getX()+size, pos.getY(), pos.getZ(),
				pos.getX()+size, pos.getY(), pos.getZ()+size,};
		
	}

	public Vector3f getSpacePos() {
		Vector3f ret = new Vector3f(-pos.getX(), -pos.getY()+1, -pos.getZ());
		return ret;
	}

}