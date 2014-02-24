package Modeles;

import java.util.HashMap;
import java.util.Vector;

import Controleur.Controleur;
import Modeles.entities.*;

public class Chunk {
	private HashMap<String, Cube3D> cubes;
	private Vector<Cube3D> renderCubes;
	private Controleur clone;
	private int x,y,z,id;

	public Chunk(int x,int y,int z, int id, Controleur contr){
		cubes = new HashMap<String, Cube3D>();
		renderCubes = new Vector<Cube3D>();
		clone = contr;
		
		this.x = x;
		this.y = y;
		this.z = z;
		this.id = id;
	}

	public void addCubes(){
		clone.getMapRead().setCubes(cubes, id);
	}

	private void checkState(){
		Vector<Cube3D> liste = new Vector<Cube3D>(cubes.values());
		for(Cube3D cube : liste){
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
	
	public HashMap<String, Cube3D> getHashCube(){
		return cubes;
	}

}
