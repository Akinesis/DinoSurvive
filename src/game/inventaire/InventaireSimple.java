package game.inventaire;

import game.item.Item;


/**
 * Inventaire des PNJs
 */
public class InventaireSimple implements Inventaire {
	private Sac<Item> inventaire;

	public InventaireSimple(Sac<Item> inventaire) {
		this.inventaire = inventaire;
	}

	
	public boolean addItem(Item item, Inventaire inventaire) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean addItem(Item item, int position, Inventaire inventaire) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean addInventaire(Inventaire inventaire) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void removeItem(Item item) {
		// TODO Auto-generated method stub
		
	}
	
	public void transferItem(Inventaire inventaire, Item item) {
		// TODO Auto-generated method stub
		
	}
	
	public void moveItem(Item item, int position) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean contains(Item item) {
		// TODO Auto-generated method stub
		return false;
	}
	public Sac<Item> getInventaire() {
		return this.inventaire;
	}

	

}
