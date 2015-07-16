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
				pos.getX(), pos.getY(), pos.getZ(),
				pos.getX(), pos2.getY(), pos.getZ(),
				pos2.getX(), pos.getY(), pos.getZ(),
				pos2.getX(), pos.getY(), pos.getZ(),
				pos.getX(), pos2.getY(), pos.getZ(),
				pos2.getX(), pos2.getY(), pos.getZ(),

				//north
				pos2.getX(), pos2.getY(), pos2.getZ(),
				pos.getX(), pos2.getY(), pos2.getZ(),
				pos.getX(), pos.getY(), pos2.getZ(),
				pos2.getX(), pos2.getY(), pos2.getZ(),
				pos.getX(), pos.getY(), pos2.getZ(),
				pos2.getX(), pos.getY(), pos2.getZ(),

				//top
				pos.getX(), pos2.getY(), pos.getZ(),
				pos.getX(), pos2.getY(), pos2.getZ(),
				pos2.getX(), pos2.getY(), pos.getZ(),
				pos2.getX(), pos2.getY(), pos.getZ(),
				pos.getX(), pos2.getY(), pos2.getZ(),
				pos2.getX(), pos2.getY(), pos2.getZ(),

				//tbottom
				pos.getX(), pos.getY(), pos.getZ(),
				pos2.getX(), pos.getY(), pos.getZ(),
				pos.getX(), pos.getY(), pos2.getZ(),
				pos.getX(), pos.getY(), pos2.getZ(),
				pos2.getX(), pos.getY(), pos.getZ(),
				pos2.getX(), pos.getY(), pos2.getZ(),	

				//east
				pos2.getX(), pos.getY(), pos.getZ(),
				pos2.getX(), pos2.getY(), pos.getZ(),
				pos2.getX(), pos.getY(), pos2.getZ(),
				pos2.getX(), pos.getY(), pos2.getZ(),
				pos2.getX(), pos2.getY(), pos.getZ(),
				pos2.getX(), pos2.getY(), pos2.getZ(),

				//west
				pos.getX(), pos.getY(), pos.getZ(),
				pos.getX(), pos2.getY(), pos2.getZ(),
				pos.getX(), pos2.getY(), pos.getZ(),
				pos.getX(), pos.getY(), pos2.getZ(),
				pos.getX(), pos2.getY(), pos2.getZ(),
				pos.getX(), pos.getY(), pos.getZ()
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
		return (int)pos.getX();
	}

	@Override
	public int getY() {
		return  (int)pos.getY();
	}

	@Override
	public int getZ() {
		return (int)pos.getZ();
	}
	
}

