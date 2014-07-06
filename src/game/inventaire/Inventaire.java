package game.inventaire;

import game.item.Item;

public interface Inventaire {
	public void addItem(Item item);
	public void addItem(Item item, int position);
	public void addInventaire(Inventaire inventaire);
	public void removeItem(Item item);
	public void transferItem(Inventaire inventaire, Item item);
	public void moveItem(Item item, int position);
	
	
}
