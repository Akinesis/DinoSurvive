package game.inventaire;

import java.util.ArrayList;

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
	//private Sac<Armes> armes;
	//private Sac<Armures> armures;
	//private Sac<Consommables> consommable;
	//private Sac<Cubes> cubes;
	//private Sac<Outils> outils;
	private ArrayList<Sac<Item>> inventaire;
	//les tableaux de collection de generique, Java il ne kiff vraiment pas ça
	
	/*public InventaireComplet(Sac<Armes> armes, Sac<Armures> armures, Sac<Consommables> consommable, Sac<Cubes> cubes, Sac<Outils> outils) {
 		this.armes = armes;
		this.armures = armures;
		this.consommable = consommable;
		this.cubes = cubes;
		this.outils = outils;
	 */
	
	//plus facile a utilisé mais quelque probleme d'un POV logique ( le sac d'armes est un sac d'item :{ bofbof)
	public InventaireComplet(Sac<Item> armes, Sac<Item> armures, Sac<Item> consommable, Sac<Item> cubes, Sac<Item> outils) {
		this.inventaire = new ArrayList<Sac<Item>>(5);
		this.inventaire.add(0, armes);
		this.inventaire.add(1, armures);
		this.inventaire.add(2, consommable);
		this.inventaire.add(3, cubes);
		this.inventaire.add(4, outils);
	}
	
	/* voir comment {Sac<Armes>, Sac<Armures>, Sac<Consommables>, Sac<Cubes>,  Sac<Outils>}
	 * faciliterait cette methode grace a l'identifiant des Items ( 0xxx armes, 1xxx armures, ... )
	 */

	public boolean addItem(Item item, Inventaire inventaire) {
		boolean done = false;
		if(inventaire.contains(item)){
			inventaire.removeItem(item);
			done = this.inventaire.get(item.getIdentifiant().charAt(0)).add(item);
		}
		return done;
	}
	
	public boolean addItem(Item item, int position, Inventaire inventaire) {
		Item temp;
		try {
			temp = this.inventaire.get(item.getIdentifiant().charAt(0)).getSac().get(position);
			
		}catch (IndexOutOfBoundsException IOOBExceptionAddItemItemPosition){
			
		}catch (NullPointerException e){
			
		}
		
		return false;
	}
	
	public boolean addInventaire(Inventaire inventaire) {
		
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
	public Sac<Item> getArmes() {
		return this.inventaire.get(0);
	}

	public Sac<Item> getArmures() {
		return this.inventaire.get(1);
	}

	public Sac<Item> getConsommable() {
		return this.inventaire.get(2);
	}

	public Sac<Item> getCubes() {
		return this.inventaire.get(3);
	}

	public Sac<Item> getOutils() {
		return this.inventaire.get(4);
	}
	
	public ArrayList<Sac<Item>> getInventaire() {
		return this.inventaire;
	}
}
