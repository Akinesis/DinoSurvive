package modeles;

import java.util.Random;

import modeles.entities.Cube3dVbo;
import controleur.Controleur;

public class TerrainGenerator {

	private Controleur clone;

	public TerrainGenerator(Controleur contr){
		clone = contr;
	}

	public void buildStart(){
		for(int x = -4; x<5; x++){
			for(int y = 0; y>-5; y--){
				for(int z = -4; z<5; z++){
					if(y==-4 & z==1){
						genRandTop2(x, y, z);
					}else{
						genereTerre(x,y,z);
					}
				}
			}
		}
	}

	private void genRandTop2(int x, int y, int z){
		Chunk temp = clone.getChunkManager().getChunk(x,y,z);
		int originX = x*16;
		int originY = y*16;
		int originZ = z*16;

		for(int i = originX; i>originX-16; --i){
			for(int j = originY; j>originY-14; --j){
				for(int k = originZ; k>originZ-16; --k){
					if(i==originX-8 && k==originZ-8){
						temp.addCube3dVbo(new Cube3dVbo(i, -originY+15, k, 1, 1));
					}else{
						temp.addCube3dVbo(new Cube3dVbo(i, -j, k, 1, 2));
					}
				}
			}

			temp.addCube3dVbo(new Cube3dVbo(originX-1, -originY+15, originZ-1, 1, 14));
		}
		clone.getChunkManager().addChunkToLoad(temp);
	}

	public void genereTerre(int x, int y, int z){
		Chunk temp = clone.getChunkManager().getChunk(x,y,z);
		int originX = x*16;
		int originY = y*16;
		int originZ = z*16;

		for(int i = originX; i>originX-16; --i){
			for(int j = originY; j>originY-16; --j){
				for(int k = originZ; k>originZ-16; --k){
					temp.addCube3dVbo(new Cube3dVbo(i, -j, k, 1, 1));
				}
			}
		}
		clone.getChunkManager().addChunkToLoad(temp);
	}

	/**
	 * Fonction permettant la generation d'un sol d'herbe, tout beau tout propre.
	 */
	public void genFond(int x, int y, int z){
		Chunk temp = clone.getChunkManager().getChunk(x,y,z);
		Random r = new Random();
		int originX = x*16;
		int originY = y*16;
		int originZ = z*16;

		for(int i = originX; i>originX-16; --i){
			for(int j = originZ; j>originZ-16; --j){
				int valeur = 3 + r.nextInt(11 - 3);
				temp.addCube3dVbo(new Cube3dVbo(i, -originY, j, 1, valeur));
			}
		}
	}

	/**
	 * Fonction permettant la generation d'un sol d'herbe, tout beau tout propre.
	 */
	public void genWall(int x, int y, int z){
		Chunk temp = clone.getChunkManager().getChunk(x,y,z);
		int originX = x*16;
		int originY = y*16;
		int originZ = z*16;
		System.out.println(originX);

		for(int i = originY; i>originY-16; --i){
			for(int j = originZ; j>originZ-16; --j){
				System.out.println(i);
				temp.addCube3dVbo(new Cube3dVbo(originX-2, -i, j, 1, 34));
			}
		}
	}

	/**
	 * Fonction permettant la generation d'un plafond d'herbe, tout beau tout propre.
	 */
	public void genCeil(int x, int y, int z){
		Chunk temp = clone.getChunkManager().getChunk(x,y,z);
		int originX = x*16;
		int originY = y*16;
		int originZ = z*16;

		for(int i = originX; i>originX-16; --i){
			for(int j = originZ; j>originZ-16; --j){
				temp.addCube3dVbo(new Cube3dVbo(i, -originY+15, j, 1, 1));
			}
		}
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
	public void genTree(int chX, int chY, int chZ){
		//arbre d'une taille comprise entre 3 et 6 cubes de tronc
		//wouhou aléatoire !

		Chunk temp = clone.getChunkManager().getChunk(chX,chY,chZ);

		float x = chX*16+12;
		float y = chY*16;
		float z = chZ*16+12;

		int taille = (int)((6-3)*Math.random()) +3;
		int size = 1;

		//1 des cubes pour le moment 1

		for (int i = 0; i<taille; ++i){

			//à chaque itération il crée un cube de type tronc (11, cf TextureManager)

			Cube3dVbo tronc = new Cube3dVbo(x, y+i, z, 1, 11);

			//il l'ajoute dans le cube

			temp.addCube3dVbo(tronc);

			//création de feuilles (12 cf TextureManager)
			//schéma différent entre les différents niveaux de feuilles (basique)

			if (i==2){
				//feuilles en croix sur le premier niveau
				Cube3dVbo feuilles = new Cube3dVbo(x-1, y+i, z, size, 12);
				Cube3dVbo feuilles2 = new Cube3dVbo(x+1, y+i, z, size, 12);
				Cube3dVbo feuilles3 = new Cube3dVbo(x, y+i, z+1, size, 12);
				Cube3dVbo feuilles4 = new Cube3dVbo(x, y+i, z-1, size, 12);

				temp.addCube3dVbo(feuilles);
				temp.addCube3dVbo(feuilles2);
				temp.addCube3dVbo(feuilles3);
				temp.addCube3dVbo(feuilles4);
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

				temp.addCube3dVbo(feuilles);
				temp.addCube3dVbo(feuilles2);
				temp.addCube3dVbo(feuilles3);
				temp.addCube3dVbo(feuilles4);
				temp.addCube3dVbo(feuilles5);
				temp.addCube3dVbo(feuilles6);
				temp.addCube3dVbo(feuilles7);				
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

		temp.addCube3dVbo(feuilles);
		temp.addCube3dVbo(feuilles2);
		temp.addCube3dVbo(feuilles3);
		temp.addCube3dVbo(feuilles4);
		temp.addCube3dVbo(feuilles5);
		temp.addCube3dVbo(feuilles6);
		temp.addCube3dVbo(feuilles7);
		temp.addCube3dVbo(feuilles8);
		temp.addCube3dVbo(feuilles9);
	}
}
