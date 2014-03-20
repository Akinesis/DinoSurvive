package modeles;

import controleur.Controleur;

public class CollisionManager {

	private Controleur clone;
	
	public CollisionManager(Controleur contr){
		clone = contr;
	}
	
	public boolean colideX(Camera cam, float posX){
		return (((clone.getChunkManager().getCubeAt(cam.getPos().x+posX, cam.getPos().y, cam.getPos().z))!=null)||
				((clone.getChunkManager().getCubeAt(cam.getPos().x+posX, cam.getPos().y+1, cam.getPos().z))!=null)||
				((clone.getChunkManager().getCubeAt(cam.getPos().x+posX, cam.getPos().y+2, cam.getPos().z))!=null));
	}
	
	public boolean colideZ(Camera cam, float posZ){
		return ((clone.getChunkManager().getCubeAt(cam.getPos().x, cam.getPos().y, cam.getPos().z+posZ))!=null)||
				((clone.getChunkManager().getCubeAt(cam.getPos().x, cam.getPos().y+1, cam.getPos().z+posZ))!=null)||
				((clone.getChunkManager().getCubeAt(cam.getPos().x, cam.getPos().y+2, cam.getPos().z+posZ))!=null);
	}
	
	public boolean colideY(Camera cam, float posY){
		return (clone.getChunkManager().getCubeAt(cam.getPos().x, cam.getPos().y+posY, cam.getPos().z))!=null||
				(clone.getChunkManager().getCubeAt(cam.getPos().x, cam.getPos().y+posY+2, cam.getPos().z))!=null;
	}
}
