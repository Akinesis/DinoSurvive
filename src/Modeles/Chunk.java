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

	//constructeur d'un chunk, poss�de des cubes et des vecteurs de cubes � d�ssin�
	public Chunk(int x,int y,int z, int id, Controleur contr){
		cubes = new HashMap<String, Cube3D>();
		renderCubes = new Vector<Cube3D>();
		clone = contr;
		
		this.x = x;
		this.y = y;
		this.z = z;
		//pour l'instant : id = ligne dnas le programe, changer �a !!! (extrapoler l'iD des XYZ)
		this.id = id;
	}

	//boucle qui ajoute tout les cube li� � l'ID du chunk (aka la ligne dans le programme)
	public void addCubes(){
		clone.getMapRead().setCubes(cubes, id);
	}

	//v�rife quels cubes sont actife ou non (visible ou non) et les met dans la liste de rendue
	public void checkState(){
		Vector<Cube3D> liste = new Vector<Cube3D>(cubes.values());
		for(Cube3D cube : liste){
			if(cube.getState()){
				renderCubes.add(cube);
			}
		}
	}

	//g�n�re les cubes dans le buffer;
	//DOIT ETRE ASSOCIER A UNE METHODE DE RESET DES BUFFER !!
	public void genCubes(){
		for(Cube3D cube : renderCubes){
			cube.genCube();
		}
	}

	//d�ssine tout les cubes actif
	public void draw(){
		checkState();
		for(Cube3D cube : renderCubes){
			cube.draw();
		}
	}

	//vide la lste de rendus
	public void clearChunk(){
		renderCubes.clear();
	}
	
	//getter de la liste des cubes
	public HashMap<String, Cube3D> getHashCube(){
		return cubes;
	}

}
