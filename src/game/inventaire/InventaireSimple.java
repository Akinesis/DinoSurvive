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

	@Override
	public boolean addItem(Item item, Inventaire inventaire) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean addItem(Item item, int position, Inventaire inventaire) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean addInventaire(Inventaire inventaire) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void removeItem(Item item) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void transferItem(Inventaire inventaire, Item item) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void moveItem(Item item, int position) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean contains(Item item) {
		// TODO Auto-generated method stub
		return false;
	}
	public Sac<Item> getInventaire() {
		return this.inventaire;
	}

	

}
