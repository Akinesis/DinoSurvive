package Modeles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;



import Controleur.Controleur;
import Modeles.entities.Cube3D;

public class MapReader {
	Controleur clone;
	private BufferedReader reader;

	public MapReader(Controleur contr){
		clone = contr;
	}

	//ouvre le fichier map
	private Boolean openFile(String file){
		try {
			FileReader fr = new FileReader(file);
			reader = new BufferedReader(fr);
			return true;
		}
		catch(IOException ex) {
			return false;
		}
	}

	//cré les chunks trouver dnas le fichier map
	public Vector<Chunk> setChunks(){
		openFile(clone.getMap());

		Vector<Chunk> chunks = new Vector<Chunk>();
		String ligne = readLine();
		String addr[];
		int i = 0;

		while(ligne!=null){
			addr = ligne.split(":"); // [{0,0,0}, ...]
			ligne = addr[0];
			addr = ligne.split(",");//[{0;0;0}]
			int x = Integer.parseInt(addr[0].subSequence(1, addr[0].length()).toString()); //transforme "{xxx" en un int XXX
			int y = Integer.parseInt(addr[1].toString());
			int z = Integer.parseInt(addr[2].subSequence(0, addr[2].length()-1).toString());

			chunks.add(new Chunk(x, y, z, i, clone));
			i++;
			ligne = readLine();
		}	

		close();

		return chunks;
	}

	public void setCubes(HashMap<String, Cube3D> liste, int id){
		openFile(clone.getMap());
		String ligne = readLine();
		String temp[];
		int compteur =0;
		
		while(ligne!=null && compteur!=id){
			ligne = readLine();
			compteur++;
		}
		
		if(ligne!=null){
			String cubes[] = ligne.split(":");
			for(int i = 1; i < cubes.length; i++ ){
				temp = cubes[i].split(",");
				int x = Integer.parseInt(temp[0].subSequence(1, temp[0].length()).toString()); //transforme "{xxx" en un int XXX
				int y = Integer.parseInt(temp[1].toString());
				int z = Integer.parseInt(temp[2].subSequence(0, temp[2].length()-1).toString());

				liste.put(cubes[i], new Cube3D(x, y, z, 1));
			}
		}
		
		close();
	}

	private String readLine() {
		try {
			return reader.readLine();
		}
		catch(IOException ex) {
			return null;
		}
	}

	private void close() {
		if (reader != null) {
			try {
				reader.close();
			}
			catch(IOException ex) {
			}
		}
	}

}
