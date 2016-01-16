package modeles;

import modeles.entities.Cube3dVbo;
import controleur.Controleur;

/**
 * La classe qui s'occupe des collisions entre le joueur et l'environement
 * @author joachimvanoni
 *
 */

public class CollisionManager {

	private Controleur clone;
	
	/**
	 * Le controleur du gestionaire de collision
	 * @param contr Le controleur
	 */
	public CollisionManager(Controleur contr){
		clone = contr;
	}
	
	/**
	 * Détermine les collisions en X
	 * @param cam La caméra
	 * @param posX Le modificateur de la position en X
	 * @return Vrais s'il y as collision, faux sinon
	 */
	public boolean colideX(Camera cam, float posX){
		
		Cube3dVbo[] temp = clone.getChunkManager().getBody(cam.getPos().x+posX, cam.getPos().y, cam.getPos().z);
		
		return (temp[0]!=null || temp[1]!=null || temp[2]!=null);
	}
	
	/**
	 * Détermine les collisions en Z
	 * @param cam La caméra
	 * @param posZ Le modificateur de collision en Z
	 * @return Vrais s'il y as collision, faux sinon
	 */
	public boolean colideZ(Camera cam, float posZ){
		return ((clone.getChunkManager().getCubeAt(cam.getPos().x, cam.getPos().y, cam.getPos().z+posZ))!=null)||
				((clone.getChunkManager().getCubeAt(cam.getPos().x, cam.getPos().y+1.1f, cam.getPos().z+posZ))!=null)||
				((clone.getChunkManager().getCubeAt(cam.getPos().x, cam.getPos().y+2.1f, cam.getPos().z+posZ))!=null);
	}
	
	/**
	 * Détermine les collisions en Y
	 * @param cam La caméra
	 * @param posY Le modificateur de collision en Y
	 * @return Vrais s'il y as collision, faux sinon
	 */
	public boolean colideY(Camera cam, float posY){
		return (clone.getChunkManager().getCubeAt(cam.getPos().x, cam.getPos().y+posY-0.10f, cam.getPos().z))!=null||
				(clone.getChunkManager().getCubeAt(cam.getPos().x, cam.getPos().y+posY+2f, cam.getPos().z))!=null;
	}
	
	/**
	 * Vérifie si le joueur touche le sol
	 * @param cam La caméra
	 * @return Vrais si le joueur est en train de tomber
	 */
	public boolean gravity(Camera cam){
		return (clone.getChunkManager().getCubeAt(cam.getPos().x, cam.getPos().y+2.75f, cam.getPos().z))!=null;
	}
}
