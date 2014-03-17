package Modeles;
/**
 * Classe représentant les Chunks comme divisions du "monde" 
 */
import java.util.Vector;

import Controleur.Controleur;
import Modeles.entities.*;



public class Chunk {
	private Cube3dVbo[][][] cubes;
	private Vector<Cube3dVbo> renderCubes;
	
	private Controleur clone;
	private int x,y,z,id;

	/**
	 * Constructeur du chunk
	 * @param x
	 * @param y
	 * @param z
	 * @param id
	 * @param contr
	 */
	public Chunk(int x,int y,int z, int id, Controleur contr){
		cubes = new Cube3dVbo[16][16][16];
		renderCubes = new Vector<Cube3dVbo>();
		clone = contr;

		this.x = x;
		this.y = y;
		this.z = z;
		//pour l'instant : id = ligne dans le programme, changer ça !!! (extrapoler l'iD des XYZ)
		this.id = id;
	}
	/*
	 * Getters
	 */
	/**
	 * Renvoie la position du chunk selon les x
	 * @return
	 */
	public int getX(){
		return x;
	}
	/**
	 * Renvoie la position du chunk selon les y
	 * @return
	 */
	public int getY(){
		return y;
	}
	/**
	 * Renvoie la position du chunk selon les z
	 * @return
	 */
	public int getZ(){
		return z;
	}
	
	/**
	 * Renvoie la liste des cubes 
	 * @return
	 */
	public Cube3D[][][] getArrayCubes(){
		return cubes;
	}
	
	/**
	 * Renvoie l'id du chunk
	 * @return
	 */
	public int getID(){
		return id;
	}

	/**
	 * Renvoie un cube aux coordonnées x,y,z
	 * @param xC
	 * @param y
	 * @param z
	 * @return un Cube3dVbo
	 */
	public Cube3dVbo getCube(float x, float y, float z) {
		/*int tempX = (x>0)?(int)Math.floor(x)%16:(int)Math.abs(Math.ceil(z))%16;
		int tempY = (y>0)?(int)Math.floor(y)%16:(int)Math.abs(Math.ceil(z))%16;
		int tempZ = (z>0)?(int)Math.floor(z)%16:(int)Math.abs(Math.ceil(z))%16;
		*/
		
		int tempX = (int)Math.abs(Math.ceil(x))%16;
		int tempY = (int)Math.abs(Math.ceil(y))%16;
		int tempZ = (int)Math.abs(Math.ceil(z))%16;
		
		return cubes[tempX][tempY][tempZ];
	}
	
	/*
	 * Methodes
	 */

	/**
	 * Ajoute tous les cube liés à l'ID du chunk (aka la ligne dans le programme)
	 */
	public void addCubes(){
		clone.getMapRead().setCubes(cubes, id);
	}
	
	/**
	 * Vérifie quels cubes sont actifs (visible ou non) et les met dans la liste de rendu
	 */
	public void checkState(){
		clearChunk();
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



	/**
	 * Méthode générant les cubes dans le buffer
	 * 	DOIT ETRE ASSOCIEE A UNE METHODE DE RESET DES BUFFER !!
	 */
	public void genCubes(){
		for(Cube3dVbo cube : renderCubes){
			cube.genCube();
		}
	}

	/**
	 * Dessine tous les cubes actifs
	 * @param texMan
	 */
	public void draw(TextureManager texMan){
		checkState();
		for(Cube3dVbo cube : renderCubes){
			cube.bindBuffers();
			
			texMan.genText(cube.getType());
			texMan.bindBuffer();
			
			cube.bindDrawCube();
			texMan.bindDrawTexture();
			
			cube.enableCube();
			texMan.enableTexture();
			
			cube.draw();
			
			texMan.disableTexture();
			cube.disableCube();
		}
	}

	/**
	 * Vide la liste de rendu
	 */
	public void clearChunk(){
		renderCubes.clear();
	}


	public void bindBuffers(){
		for(int i=0; i<16; i++){
			for(int j =0; j<16; j++){
				for(int k=0; k<16; k++){
					if(cubes[i][j][k]!=null){
						//cubes[i][j][k].bindBuffers();
					}
				}
			}
		}
	}
	
	/**
	 * Méthode ajoutant un cube dans le chunk
	 * TODO : vérification que le cube a le droit d'être dans ce cube
	 * @param cube
	 */
	public void addCube3dVbo(Cube3dVbo cube){
		cubes[cube.getX()][cube.getY()][cube.getZ()] = cube;		
	}
	
	/**
	 * Fonction permettant la génération d'un arbre dont le premier bloc de tronc est en position x,y,z
	 * @param x : position du cube selon les x
	 * @param y :  position du cube selon les y
	 * @param z :  position du cube selon les z 
	 */
	//(pas besoin du paramètre 1 car pour un arbre les cubes font forcément 1)
	//(potentiellement pour "jolifier" l'arbre, mettre un facteur de 0,5 pour les feuilles du dessus
	//(aka : demi cube de feuilles)
	public void genTree(float x, float y, float z){
		//arbre d'une taille comprise entre 3 et 6 cubes de tronc
		//wouhou aléatoire !
		
		int taille = (int)((6-3)*Math.random()) +3;
		int size = 1;
		
		//1 des cubes pour le moment 1
		
		for (int i = 0; i<taille; ++i){
			
			//à chaque itération il crée un cube de type tronc (11, cf TextureManager)
			
			Cube3dVbo tronc = new Cube3dVbo(x, y+i, z, 1, 11);
			
			//il l'ajoute dans le cube
			
			addCube3dVbo(tronc);
			
			//cr��ation de feuilles (12 cf TextureManager)
			//sch�ma différent entre les différents niveaux de feuilles
			
			if (i==2){
				//feuilles en croix sur le premier niveau
				Cube3dVbo feuilles = new Cube3dVbo(x-1, y+i, z, size, 12);
				Cube3dVbo feuilles2 = new Cube3dVbo(x+1, y+i, z, size, 12);
				Cube3dVbo feuilles3 = new Cube3dVbo(x, y+i, z+1, size, 12);
				Cube3dVbo feuilles4 = new Cube3dVbo(x, y+i, z-1, size, 12);

				addCube3dVbo(feuilles);
				addCube3dVbo(feuilles2);
				addCube3dVbo(feuilles3);
				addCube3dVbo(feuilles4);
			}
			else if (i>2){
				//feuilles partout sur les niveaux suivants (moche !)
				Cube3dVbo feuilles = new Cube3dVbo(x-1, y+i, z, 1, 12);
				Cube3dVbo feuilles2 = new Cube3dVbo(x+1, y+i, z, 1, 12);
				Cube3dVbo feuilles3 = new Cube3dVbo(x, y+i, z+1, 1, 12);
				Cube3dVbo feuilles4 = new Cube3dVbo(x, y+i, z-1, 1, 12);
				Cube3dVbo feuilles5 = new Cube3dVbo(x-1, y+i, z-1, 1, 12);
				Cube3dVbo feuilles6 = new Cube3dVbo(x+1, y+i, z+1, 1, 12);
				Cube3dVbo feuilles7 = new Cube3dVbo(x-1, y+i, z+1, 1, 12);

				addCube3dVbo(feuilles);
				addCube3dVbo(feuilles2);
				addCube3dVbo(feuilles3);
				addCube3dVbo(feuilles4);
				addCube3dVbo(feuilles5);
				addCube3dVbo(feuilles6);
				addCube3dVbo(feuilles7);				
			}
		}
		// à la fin de la génération de l'arbre, on génère une couche de feuilles au dessus

		Cube3dVbo feuilles = new Cube3dVbo(x-1, y+taille, z, size, 12);
		Cube3dVbo feuilles2 = new Cube3dVbo(x, y+taille, z, size, 12);
		Cube3dVbo feuilles3 = new Cube3dVbo(x-1, y+taille, z-1, size, 12);
		Cube3dVbo feuilles4 = new Cube3dVbo(x-1, y+taille, z+1, size, 12);
		Cube3dVbo feuilles5 = new Cube3dVbo(x+1, y+taille, z, size, 12);
		Cube3dVbo feuilles6 = new Cube3dVbo(x+1, y+taille, z-1, size, 12);
		Cube3dVbo feuilles7 = new Cube3dVbo(x+1, y+taille, z+1, size, 12);
		Cube3dVbo feuilles8 = new Cube3dVbo(x, y+taille, z+1, size, 12);
		Cube3dVbo feuilles9 = new Cube3dVbo(x, y+taille, z-1, size, 12);

		addCube3dVbo(feuilles);
		addCube3dVbo(feuilles2);
		addCube3dVbo(feuilles3);
		addCube3dVbo(feuilles4);
		addCube3dVbo(feuilles5);
		addCube3dVbo(feuilles6);
		addCube3dVbo(feuilles7);
		addCube3dVbo(feuilles8);
		addCube3dVbo(feuilles9);
	}

}