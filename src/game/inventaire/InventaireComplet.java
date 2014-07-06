package game.inventaire;

import game.item.Armes;
import game.item.Armures;
import game.item.Consommables;
import game.item.Cubes;
import game.item.Item;
import game.item.Outils;


/**
 * Inventaire pour PJs
 */
public class InventaireComplet implements Inventaire {
	private Sac<Armes> armes;
	private Sac<Armures> armures;
	private Sac<Consommables> consommable;
	private Sac<Cubes> cubes;
	private Sac<Outils> outils;
	//les tableaux de collection de generique, Java il ne kiff vraiment pas ça
	
	public InventaireComplet(Sac<Armes> armes, Sac<Armures> armures, Sac<Consommables> consommable, Sac<Cubes> cubes, Sac<Outils> outils) {
		this.armes = armes;
		this.armures = armures;
		this.consommable = consommable;
		this.cubes = cubes;
		this.outils = outils;
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

	public Sac<Armes> getArmes() {
		return armes;
	}

	public Sac<Armures> getArmures() {
		return armures;
	}

	public Sac<Consommables> getConsommable() {
		return consommable;
	}

	public Sac<Cubes> getCubes() {
		return cubes;
	}

	public Sac<Outils> getOutils() {
		return outils;
	}
	
}
