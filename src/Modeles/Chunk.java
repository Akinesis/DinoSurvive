package Modeles;

import java.util.HashMap;
import java.util.Vector;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

import Modeles.entities.*;

public class Chunk {
	private HashMap<String, Cube3D> cubes;
	private Vector<Cube3D> renderCubes;

	public Chunk(){
		cubes = new HashMap<String, Cube3D>();
		renderCubes = new Vector<Cube3D>();
	}

	public void addCube(){
		cubes.put("1", new Cube3D(0, 0, -5, 1));
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

}
