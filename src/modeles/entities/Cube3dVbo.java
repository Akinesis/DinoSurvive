package modeles.entities;



public class Cube3dVbo extends Cube3D {

	private int type;
	private float indiceTextX, indiceTextY;

	public Cube3dVbo(float x,float  y,float z,float size, int typ){
		super(x,y,z,size);
		type=typ;

		//vertexData = BufferUtils.createFloatBuffer(verticiesNum * vertexSize);
		indiceTextX = (float)(Math.random());
		indiceTextY = (float)(Math.random());
		
		//vboVertexHandle = glGenBuffers();
	}

	public void draw(){
        
	}
	
	public void delCube(int vboChunk){
		
	}
	
	public float[] genCubes(){
		return new float[]{
				//south
				pos.x, pos.y, pos.z,
				pos.x, pos2.y, pos.z,
				pos2.x, pos.y, pos.z,
				pos2.x, pos.y, pos.z,
				pos.x, pos2.y, pos.z,
				pos2.x, pos2.y, pos.z,

				//north
				pos2.x, pos2.y, pos2.z,
				pos.x, pos2.y, pos2.z,
				pos.x, pos.y, pos2.z,
				pos2.x, pos2.y, pos2.z,
				pos.x, pos.y, pos2.z,
				pos2.x, pos.y, pos2.z,

				//bottom
				pos.x, pos2.y, pos.z,
				pos.x, pos2.y, pos2.z,
				pos2.x, pos2.y, pos.z,
				pos2.x, pos2.y, pos.z,
				pos.x, pos2.y, pos2.z,
				pos2.x, pos2.y, pos2.z,

				//top
				pos.x, pos.y, pos.z,
				pos2.x, pos.y, pos.z,
				pos.x, pos.y, pos2.z,
				pos.x, pos.y, pos2.z,
				pos2.x, pos.y, pos.z,
				pos2.x, pos.y, pos2.z,	

				//east
				pos2.x, pos.y, pos.z,
				pos2.x, pos2.y, pos.z,
				pos2.x, pos.y, pos2.z,
				pos2.x, pos.y, pos2.z,
				pos2.x, pos2.y, pos.z,
				pos2.x, pos2.y, pos2.z,

				//west
				pos.x, pos.y, pos.z,
				pos.x, pos2.y, pos2.z,
				pos.x, pos2.y, pos.z,
				pos.x, pos.y, pos2.z,
				pos.x, pos2.y, pos2.z,
				pos.x, pos.y, pos.z
		};
	}
	
	public int getType(){
		return type;
	}
	
	public boolean isSolide(){
		return type==13 || type==14;
	}
	
	public float getTextX(){
		return indiceTextX;
	}
	
	public float getTextY(){
		return indiceTextY;
	}
	
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
	
}

