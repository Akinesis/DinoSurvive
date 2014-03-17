package Modeles;

import Controleur.Controleur;

public class CollisionManager {

	private Controleur clone;
	
	public CollisionManager(Controleur contr){
		clone = contr;
	}
	
	public boolean colideXZ(Camera cam, float posX, float posZ){
		return (clone.getChunkManager().getCubeAt(cam.getPos().x+posX, cam.getPos().y, cam.getPos().z+posZ))!=null;
	}
	
	public boolean colideZ(Camera cam, float posZ){
		return true;
	}
	
	public boolean colideY(Camera cam, float posY){
		return true;
	}
}
