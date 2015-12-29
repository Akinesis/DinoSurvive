package modeles;



import modeles.entities.Cube3dVbo;
import modeles.entities.FlatItemVBO;
import game.item.Item;
import controleur.Controleur;

public class ItemManager {
	
	Controleur clone;
	DropManager dropManager;
	
	public ItemManager(Controleur contr){
		clone = contr;
		dropManager = new DropManager();
	}
	
	public void addDropOnFoot(Item item){
		FlatItemVBO temp = new FlatItemVBO(10, 10, 10, item.getIdentifiant()); //donnée de l'item et du joueur.
		dropManager.addDrop(temp);
	}
	
	public void addDropOnCube(Item item, Cube3dVbo cube){
		FlatItemVBO temp = new FlatItemVBO(cube.getX()+0.5f, cube.getY(), cube.getZ()-0.5f, item.getIdentifiant()); //à modifier un peut.
		dropManager.addDrop(temp);
	}

	public void createItem(int id){
		
	}
	
	
	

}
