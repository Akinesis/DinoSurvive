package Modeles;

import controleur.Controleur;

public class CollisionManager {

	private Controleur clone;
	
	public CollisionManager(Controleur contr){
		clone = contr;
	}
	
	public boolean colideX(Camera cam, float posX){
		return true;
	}
	
	public boolean colideZ(Camera cam, float posZ){
		return true;
	}
	
	public boolean colideY(Camera cam, float posY){
		return true;
	}
}
