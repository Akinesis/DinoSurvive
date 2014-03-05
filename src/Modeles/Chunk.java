package Modeles;

import java.util.HashMap;
import java.util.Vector;

import Controleur.Controleur;
import Modeles.entities.*;

public class Chunk {
	private Cube3dVbo[][][] cubes;
	private Vector<Cube3dVbo> renderCubes;
	private Controleur clone;
	private int x,y,z,id;

	//constructeur d'un chunk, poss�de des cubes et des vecteurs de cubes ˆ dŽssinŽ
	public Chunk(int x,int y,int z, int id, Controleur contr){
		cubes = new Cube3dVbo[16][16][16];
		renderCubes = new Vector<Cube3dVbo>();
		clone = contr;

		this.x = x;
		this.y = y;
		this.z = z;
		//pour l'instant : id = ligne dnas le programe, changer �a !!! (extrapoler l'iD des XYZ)
		this.id = id;
	}

	//boucle qui ajoute tout les cube liŽ ˆ l'ID du chunk (aka la ligne dans le programme)
	public void addCubes(){
		clone.getMapRead().setCubes(cubes, id);
	}

	//vŽrife quels cubes sont actife ou non (visible ou non) et les met dans la liste de rendue
	public void checkState(){

		for(int i=0; i<16; i++){
			for(int j =0; j<16; j++){
				for(int k=0; k<16; k++){
					if(cubes[i][j][k]!=null){
						if(cubes[i][j][k].getState()){
							renderCubes.add(cubes[i][j][k]);
						}
					}
				}
			}
		}
	}


	//gŽn�re les cubes dans le buffer;
	//DOIT ETRE ASSOCIER A UNE METHODE DE RESET DES BUFFER !!
	public void genCubes(){
		for(Cube3dVbo cube : renderCubes){
			cube.genCube();
		}
	}

	//dŽssine tout les cubes actif
	public void draw(){
		checkState();
		for(Cube3dVbo cube : renderCubes){
			cube.draw();
		}
		clearChunk();
	}

	//vide la lste de rendus
	public void clearChunk(){
		renderCubes.clear();
	}

	//getter de la liste des cubes
	public Cube3D[][][] getArrayCubes(){
		return cubes;
	}
	
	public void bindBuffers(){
		for(int i=0; i<16; i++){
			for(int j =0; j<16; j++){
				for(int k=0; k<16; k++){
					if(cubes[i][j][k]!=null){
						cubes[i][j][k].bindBuffers();
					}
				}
			}
		}
	}

}
