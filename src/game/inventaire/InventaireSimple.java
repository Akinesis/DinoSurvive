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
	public void addItem(Item item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addItem(Item item, int position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addInventaire(Inventaire inventaire) {
		// TODO Auto-generated method stub
		
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
	
	public Sac<Item> getInventaire() {
		return inventaire;
	}
}
