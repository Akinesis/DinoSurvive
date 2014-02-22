package Modeles;

import java.util.Vector;
import Modeles.entities.*;

public class Chunk {
	private Vector<Cube3D> cubes;
	private Vector<Cube3D> renderCubes;
	
	public Chunk(){
		cubes = new Vector<Cube3D>();
		renderCubes = new Vector<Cube3D>();
	}
	
	public void addCube(){
		cubes.add(new Cube3D(0, 0, -5, 1));
	}
	
	private void checkState(){
		for(Cube3D cube : cubes){
			if(cube.getState()){
				renderCubes.add(cube);
			}
		}
	}
	
	private void genCubes(){
		for(Cube3D cube : renderCubes){
			cube.genCube();
		}
	}
	
	public void draw(){
		checkState();
		genCubes();
		for(Cube3D cube : renderCubes){
			cube.draw();
		}
	}
	
	public void clearChunk(){
		renderCubes.clear();
	}

}
