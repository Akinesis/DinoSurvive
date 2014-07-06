package game.inventaire;

import game.item.Item;

public interface Inventaire {
	public boolean addItem(Item item, Inventaire inventaire);
	public boolean addItem(Item item, int position, Inventaire inventaire);
	public boolean addInventaire(Inventaire inventaire);
	public void removeItem(Item item);
	public void transferItem(Inventaire inventaire, Item item);
	public void moveItem(Item item, int position);
	public boolean contains(Item item);
	
}
