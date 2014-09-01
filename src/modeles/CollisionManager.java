package modeles;

import modeles.entities.Cube3dVbo;
import controleur.Controleur;

public class CollisionManager {

	private Controleur clone;

	public CollisionManager(Controleur contr){
		clone = contr;
	}

	public boolean colideX(Camera cam, float posX){

		Cube3dVbo[] temp = clone.getChunkManager().getBody(cam.getPos().x+posX, cam.getPos().y, cam.getPos().z);

		return (temp[0]!=null || temp[1]!=null || temp[2]!=null);
	}

	public boolean colideZ(Camera cam, float posZ){
		return ((clone.getChunkManager().getCubeAt(cam.getPos().x, cam.getPos().y, cam.getPos().z+posZ))!=null)||
				((clone.getChunkManager().getCubeAt(cam.getPos().x, cam.getPos().y+1.1f, cam.getPos().z+posZ))!=null)||
				((clone.getChunkManager().getCubeAt(cam.getPos().x, cam.getPos().y+2.1f, cam.getPos().z+posZ))!=null);
	}

	public boolean colideY(Camera cam, float posY){
		return (clone.getChunkManager().getCubeAt(cam.getPos().x, cam.getPos().y+posY-0.10f, cam.getPos().z))!=null||
				(clone.getChunkManager().getCubeAt(cam.getPos().x, cam.getPos().y+posY+2f, cam.getPos().z))!=null;
	}

	public boolean gravity(Camera cam){
		return (clone.getChunkManager().getCubeAt(cam.getPos().x, cam.getPos().y+2.75f, cam.getPos().z))!=null;
	}
}
